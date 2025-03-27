package com.mycompany.app.Controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class Controller {
    public static ArrayList<Float> procesarArchivo(String path){
        System.out.println("Procesando archivo: " + path);
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

            for (Float number : numeros) {
                System.out.println("Número: " + number);
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

}
