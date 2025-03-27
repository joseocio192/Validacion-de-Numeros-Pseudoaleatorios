package com.mycompany.app.Model;

import java.util.ArrayList;

public class Modelo {
    public Modelo(ArrayList<Float> numeros) {
        System.out.println("Modelo creado");
    }

    public static void ji_Cuadrado(ArrayList<Float> numeros) {
        System.out.println("Metodo 1"); 
        while (numeros.size() > 0) {
            System.out.println("Número: " + numeros.remove(0));
        }
    }

    public static void kolmogorov_Smirnov(ArrayList<Float> numeros) {
        System.out.println("Metodo 2"); 
        while (numeros.size() > 0) {
            System.out.println("Número: " + numeros.remove(0));
        }
    }

    public static void series(ArrayList<Float> numeros) {
        System.out.println("Metodo 3"); 
        while (numeros.size() > 0) {
            System.out.println("Número: " + numeros.remove(0));
        }
    }

    public static void distancias(ArrayList<Float> numeros) {
        System.out.println("Metodo 4"); 
        while (numeros.size() > 0) {
            System.out.println("Número: " + numeros.remove(0));
        }
    }
}
