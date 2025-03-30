package com.mycompany.app.Model;

import java.util.ArrayList;
import com.mycompany.app.Model.TablaDistancia;

public class Modelo {

    public static void ji_Cuadrado(ArrayList<Float> numeros) {
        System.out.println("Metodo 1");
        while (numeros.size() > 0) {
            System.out.println("Número: " + numeros.remove(0));
        }
    }

    public static String kolmogorov_Smirnov(ArrayList<Float> numeros, Float error ) {
        System.out.println("Metodo 2"); 
        int serie = numeros.size();
        error = 5f;
        double Fn = 1/serie;
        float D = 0;
        for (int i = 0; i < serie; i++) {
            float Fni =  (float) ((i + 1f) * Fn);
            float Di = (float) Math.abs(Fni - Fn);
            if (Di > D) {
                D = Di;
            }
        }
       float Dx =  1.63f/ (float) Math.sqrt(serie);
        if (D < Dx) {
            return "No existe evidencia suficiente para decir que la muestra no esta distribuida uniformemente";
        } else {
            return "Existen evidencias suficientes para decir que la muestra no esta distribuida uniformemente"; 
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
        float PE = beta;
        float PF = 1f - beta;
        ArrayList<Integer> huecos = new ArrayList<>();
        ArrayList<TablaDistancia> tabla = new ArrayList<>();
        int pos = 0;
        for (int i = 0; i < numeros.size() - 1; i++) {
            if (!(numeros.get(i) < alpha && numeros.get(i) > beta)) { // no esta en el intervalo
                if (huecos.size() == 0) {
                    huecos.add(1);
                } else {
                    if (huecos.get(pos) == 0) {
                        huecos.add(1);
                        pos++;
                    } else {
                        huecos.set(pos, huecos.get(pos) + 1);
                    }
                }
            } else {                                                   // esta en el intervalo
                if (huecos.size() == 0) {
                    huecos.add(0);
                } else if (huecos.get(pos) != 0) {
                    huecos.add(0);
                    pos++;
                }
            }
            tabla.add(new TablaDistancia(i, numeros.get(i), huecos.get(pos) == 0 ? 1 : 0, huecos.get(pos)));
        }

        for (TablaDistancia tablaDistancia : tabla) {
            System.out.println(tablaDistancia.getN() + " " + tablaDistancia.getUi() + " " + tablaDistancia.getC() + " " + tablaDistancia.getI());
        }
    }
}
