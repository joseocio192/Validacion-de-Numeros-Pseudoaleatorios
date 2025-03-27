package com.mycompany.app.Model;

import java.util.ArrayList;

public class Modelo {

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

    public static void distancias(ArrayList<Float> numeros, Float alpha, Float theta) {
        System.out.println("Metodo distancias"); 
        float beta = alpha + theta;
        Float[] distancias = new Float[numeros.size() - 1];
        for (int i = 0; i < numeros.size() - 1; i++) {
            distancias[i] = numeros.get(i + 1) - numeros.get(i);
            System.out.println(distancias[i]);
        }
    }
}
