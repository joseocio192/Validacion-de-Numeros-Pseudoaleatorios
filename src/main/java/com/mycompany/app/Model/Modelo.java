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
