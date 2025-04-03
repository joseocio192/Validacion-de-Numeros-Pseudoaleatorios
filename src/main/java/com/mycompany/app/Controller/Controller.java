package com.mycompany.app.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mycompany.app.Model.Modelo;
import com.mycompany.app.View.Vista;
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
                        numeros.add((float) number);
                    } catch (NumberFormatException e) {
                        System.out.println("No es un número válido: " + fila[0]);
                    }
                }
            }
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
                if (sheet.getRow(i) == null) {
                    continue;
                }
                try {
                    Float number = (float) sheet.getRow(i).getCell(0).getNumericCellValue();
                    numeros.add(number);
                } catch (IllegalStateException e) {
                    System.out.println("No es un número válido: " + sheet.getRow(i).getCell(0).getStringCellValue());
                } catch (NullPointerException e) {
                    System.out.println("La celda está vacía en la fila: " + i);
                }
            }
            workbook.close();
            return numeros;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void procesarJi_Cuadrado(Vista vista) {
        ArrayList<Float> numeros = vista.numeros;
        numeros.sort(Float::compareTo);
        String mensaje =  Modelo.ji_Cuadrado(numeros);
        JOptionPane.showMessageDialog(vista, mensaje, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void procesaKolmogorov_Smirnov(Vista vista) {
        ArrayList<Float> numeros = vista.numeros;
        numeros.sort(Float::compareTo);
        String input;
        float error = 0;
        boolean validInput = false;
        do {
            input = JOptionPane.showInputDialog(vista, "Ingrese el error:", "Entrada requerida",
                    JOptionPane.PLAIN_MESSAGE);
            if (input == null) {
                return;
            }
            try {
                error = Float.parseFloat(input);
                validInput = true;
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vista, "Por favor, ingrese un número válido.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } while (!validInput);
        String mensaje = Modelo.kolmogorov_Smirnov(numeros, error);
        JOptionPane.showMessageDialog(vista, mensaje, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void procesarSeries(Vista vista) {
        ArrayList<Float> numeros = vista.numeros;
     
        Modelo.series(numeros);
    }

    public static void procesarDistancias(Vista vista) {
        String input;
        float alpha = 0;
        float theta = 0;
        boolean validInput = false;
        do {
            input = JOptionPane.showInputDialog(vista, "Ingrese Alpha:", "Entrada requerida",
                    JOptionPane.PLAIN_MESSAGE);
            if (input == null) {
                return;
            }
            try {
                alpha = Float.parseFloat(input);
                validInput = true;
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vista, "Por favor, ingrese un número válido.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } while (!validInput);
        do {
            input = JOptionPane.showInputDialog(vista, "Ingrese theta:", "Entrada requerida",
                    JOptionPane.PLAIN_MESSAGE);
            if (input == null) {
                return;
            }
            try {
                theta = Float.parseFloat(input);
                validInput = true;
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vista, "Por favor, ingrese un número válido.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } while (!validInput);
        String mensaje =  Modelo.distancias(vista.numeros, alpha, theta);
        JOptionPane.showMessageDialog(vista, mensaje, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
    }
}
