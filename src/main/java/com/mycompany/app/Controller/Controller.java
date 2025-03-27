package com.mycompany.app.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class Controller {
    public static ArrayList<Float> procesarArchivo(String path) {
        System.out.println("Procesando archivo: " + path);
        if (path.toLowerCase().endsWith(".csv")) {
            return procesarArchivoCSV(path);
        }

        if (path.toLowerCase().endsWith(".xlsx")) {
            return procesarArchivoExcel(path);
        }
        return null;
    }

    public static ArrayList<Float> procesarArchivoCSV(String path) {
        try {
            CSVReader csvReader = new CSVReader(new FileReader(path));
            String[] fila;
            ArrayList<Float> numeros = new ArrayList<>();
            while ((fila = csvReader.readNext()) != null) {
                if (fila[0] != null && !fila[0].isEmpty()) {
                    try {
                        Float number = Float.parseFloat(fila[0]);
                        System.out.println("Número procesado: " + number);
                        numeros.add((float) number);
                    } catch (NumberFormatException e) {
                        System.out.println("No es un número válido: " + fila[0]);
                    }
                }
            }
            numeros.sort(Float::compareTo);
            csvReader.close();
            return numeros;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Float> procesarArchivoExcel(String path) {
        FileInputStream file;
        try {
            file = new FileInputStream(new File(path));
            Workbook workbook = new XSSFWorkbook(file);
            ArrayList<Float> numeros = new ArrayList<>();
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 0; i <= sheet.getLastRowNum(); i++) {
                // Check if the row is null
                if (sheet.getRow(i) == null) {
                    continue; // Skip empty rows
                }
                try {
                    Float number = (float) sheet.getRow(i).getCell(0).getNumericCellValue();
                    System.out.println("Número procesado: " + number);
                    numeros.add(number);
                } catch (IllegalStateException e) {
                    System.out.println("No es un número válido: " + sheet.getRow(i).getCell(0).getStringCellValue());
                } catch (NullPointerException e) {
                    System.out.println("La celda está vacía en la fila: " + i);
                }
            }
            numeros.sort(Float::compareTo);
            workbook.close();
            return numeros;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
