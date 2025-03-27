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

    public static void distancias(ArrayList<Float> numeros, Double x) {
        System.out.println("Metodo 4"); 
        Float[] distancias = new Float[numeros.size() - 1];
        for (int i = 0; i < numeros.size() - 1; i++) {
            distancias[i] = numeros.get(i + 1) - numeros.get(i);
        }

        // Calcular media y varianza
        Float media = calcularMedia(distancias);
        Float varianza = calcularVarianza(distancias, media);
    }
    
    public static Float calcularMedia(Float[] valores) {
        Float suma = 0f;
        for (Float v : valores) {
            suma += v;
        }
        return suma / valores.length;
    }

    public static Float calcularVarianza(Float[] valores, Float media) {
        Float suma = 0f;
        for (Float v : valores) {
            suma += (float) Math.pow(v - media, 2);
        }
        return suma / valores.length;
    }
}
