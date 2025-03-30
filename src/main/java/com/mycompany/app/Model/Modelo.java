package com.mycompany.app.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.mycompany.app.View.GraficaJiCuadrado;
import com.mycompany.app.View.TablaDistancias;
import com.mycompany.app.View.TablaJiCuadrado;

public class Modelo {

    public static String ji_Cuadrado(ArrayList<Float> numeros) {

        ArrayList<RenglonJiCuadrado> tabla = new ArrayList<>();
        int N = numeros.size();
        int k = (int) Math.round(Math.sqrt(N));
        double intervalo = 1.0 / k;

        int[] frecuenciasObservadas = new int[k];
        for (double dato : numeros) {
            int indice = (int) (dato / intervalo);
            if (indice == k)
                indice--;
            frecuenciasObservadas[indice]++;
        }

        double E = (double) N / k;

        double chiCuadrada = 0;
        for (int i = 0; i < k; i++) {
            RenglonJiCuadrado renglon = new RenglonJiCuadrado();
            double diferencia = frecuenciasObservadas[i] - E;
            double contribucion = (diferencia * diferencia) / E;
            chiCuadrada += contribucion;
            renglon.setI((i + 1) * (intervalo));
            renglon.setO(frecuenciasObservadas[i]);
            renglon.setE(E);
            renglon.setOme(diferencia);
            renglon.setEso(contribucion);
            tabla.add(renglon);
        }
        new TablaJiCuadrado(tabla);

        double[] categorias = new double[tabla.size()];
        for (int i = 0; i < tabla.size(); i++) {
            categorias[i] = tabla.get(i).getI();
        }
        double[] o = new double[tabla.size()];
        for (int i = 0; i < tabla.size(); i++) {
            o[i] = tabla.get(i).getO();
        }

        SwingUtilities.invokeLater(() -> {
            GraficaJiCuadrado chart = new GraficaJiCuadrado(categorias, o, E);
            chart.setVisible(true);
        });

        double valorCritico = obtenerValorCritico(k - 1);

        if (chiCuadrada < valorCritico) {
            return "No existe evidencia suficiente para sustentar que los datos no estan distribuidos uniformemente.";
        } else {
            return "Existe evidencia suficiente para sustentar que los datos no estan distribuidos uniformemente.";
        }
    }

    public static double obtenerValorCritico(int gl) {
        Map<Integer, Double> tablaChiCuadrada = Map.ofEntries(
                Map.entry(1, 3.8415), Map.entry(2, 5.9915), Map.entry(3, 7.8147), Map.entry(4, 9.4877),
                Map.entry(5, 11.0705),
                Map.entry(6, 12.5916), Map.entry(7, 14.0671), Map.entry(8, 15.5073), Map.entry(9, 16.9190),
                Map.entry(10, 18.3070),
                Map.entry(11, 19.6752), Map.entry(12, 21.0261), Map.entry(13, 22.3620), Map.entry(14, 23.6848),
                Map.entry(15, 24.9958));
        return tablaChiCuadrada.getOrDefault(gl, 16.92);
    }

    public static String kolmogorov_Smirnov(ArrayList<Float> numeros, Float error) {
        System.out.println("Metodo 2");
        int serie = numeros.size();
        double Fn = 1.0 / serie;
        float D = 0;

        for (int i = 0; i < serie; i++) {
            float Fni = (i + 1f) / serie;
            float Di = Math.abs(Fni - numeros.get(i));
            if (Di > D) {
                D = Di;
            }
        }
        System.out.println("Error: " + error);

        float Dx;
        if (error == 0.10f) {
            Dx = (float) (1.22 / Math.sqrt(serie));
        } else if (error == 0.05f) {
            Dx = (float) (1.36 / Math.sqrt(serie));
        } else if (error == 0.01f) {
            Dx = (float) (1.63 / Math.sqrt(serie));
        } else {
            return "El error no es correcto, debe ser 0.10, 0.05 o 0.01";
        }

        if (D > Dx) {
            return "Existe evidencia suficiente para decir que la muestra NO está distribuida uniformemente";
        } else {
            return "No existe evidencia suficiente para decir que la muestra NO está distribuida uniformemente";
        }
    }

    public static void series(ArrayList<Float> nums) {
        System.out.println("Metodo 3 - Prueba de Series");
        if (nums.size() < 2) {
            System.out.println("No hay suficientes datos.");
            return;
        }

        String input = JOptionPane.showInputDialog("Ingrese el valor de n (divisiones del intervalo [0,1]):");
        int n;
        try {
            n = Integer.parseInt(input);
            if (n <= 0) {
                System.out.println("El valor de n debe ser mayor que 0.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido para n.");
            return;
        }

        int N = nums.size() - 1;
        double intervalo = 1.0 / n;

        double[][] Oij = new double[n][n];
        for (int i = 0; i < N; i++) {
            int fila = (int) (nums.get(i) / intervalo);
            int col = (int) (nums.get(i + 1) / intervalo);
            if (fila >= n)
                fila = n - 1;
            if (col >= n)
                col = n - 1;
            Oij[fila][col]++;
        }

        double[][] Eij = new double[n][n];
        double esperado = (double) N / (n * n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Eij[i][j] = esperado;
            }
        }

        double[][] diff = new double[n][n];

        double[][] chi = new double[n][n];
        double chiCuadrado = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                diff[i][j] = Oij[i][j] - Eij[i][j];
                chi[i][j] = Math.pow(diff[i][j], 2) / Eij[i][j];
                chiCuadrado += chi[i][j];
            }
        }

        System.out.println("\nTabla Oij:");
        System.out.printf("%20s", "");
        for (int j = 0; j < n; j++) {
            double intIni = j * intervalo;
            double intFin = (j + 1) * intervalo;
            System.out.printf("%15s", String.format("[%.2f-%.2f)", intIni, intFin));
        }
        System.out.println();
        for (int i = 0; i < n; i++) {
            double intIni = i * intervalo;
            double intFin = (i + 1) * intervalo;
            System.out.printf("%20s", String.format("[%.2f-%.2f)", intIni, intFin));
            for (int j = 0; j < n; j++) {
                System.out.printf("%15.2f", Oij[i][j]);
            }
            System.out.println();
        }

        System.out.println("\nTabla Eij:");
        System.out.printf("%20s", "");
        for (int j = 0; j < n; j++) {
            double intIni = j * intervalo;
            double intFin = (j + 1) * intervalo;
            System.out.printf("%15s", String.format("[%.2f-%.2f)", intIni, intFin));
        }
        System.out.println();
        for (int i = 0; i < n; i++) {
            double intIni = i * intervalo;
            double intFin = (i + 1) * intervalo;
            System.out.printf("%20s", String.format("[%.2f-%.2f)", intIni, intFin));
            for (int j = 0; j < n; j++) {
                System.out.printf("%15.2f", Eij[i][j]);
            }
            System.out.println();
        }

        System.out.println("\nTabla (Oij - Eij):");
        System.out.printf("%20s", "");
        for (int j = 0; j < n; j++) {
            double intIni = j * intervalo;
            double intFin = (j + 1) * intervalo;
            System.out.printf("%15s", String.format("[%.2f-%.2f)", intIni, intFin));
        }
        System.out.println();
        for (int i = 0; i < n; i++) {
            double intIni = i * intervalo;
            double intFin = (i + 1) * intervalo;
            System.out.printf("%20s", String.format("[%.2f-%.2f)", intIni, intFin));
            for (int j = 0; j < n; j++) {
                System.out.printf("%15.2f", diff[i][j]);
            }
            System.out.println();
        }

        System.out.println("\nTabla (Oij - Eij)^2 / Eij:");
        System.out.printf("%20s", "");
        for (int j = 0; j < n; j++) {
            double intIni = j * intervalo;
            double intFin = (j + 1) * intervalo;
            System.out.printf("%15s", String.format("[%.2f-%.2f)", intIni, intFin));
        }
        System.out.println();
        for (int i = 0; i < n; i++) {
            double intIni = i * intervalo;
            double intFin = (i + 1) * intervalo;
            System.out.printf("%20s", String.format("[%.2f-%.2f)", intIni, intFin));
            for (int j = 0; j < n; j++) {
                System.out.printf("%15.4f", chi[i][j]);
            }
            System.out.println();
        }

        System.out.println("\n---------------------------------------------");
        System.out.printf("Chi-cuadrado total: %.4f\n", chiCuadrado);
        System.out.printf("Grados de libertad: %d\n", (n * n - 1));
        System.out.println("---------------------------------------------");
    }

    public static void distancias(ArrayList<Float> numeros, Float alpha, Float theta) {
        System.out.println("Metodo distancias");
        float beta = alpha + theta;
        float PE = theta;
        float PF = 1f - theta;
        Integer ceros = 0;
        ArrayList<RenglonDistancia> tabla = new ArrayList<>();
        for (int i = 0; i < numeros.size() - 1; i++) {
            RenglonDistancia r = new RenglonDistancia();
            float numero = numeros.get(i);
            r.setN(i);
            r.setUi(numero);
            if (numero < alpha || numero > beta) {
                r.setC(0);
                ceros++;
            } else {
                r.setC(1);
            }
            tabla.add(r);
        }

        int count = 0;
        for (int i = 0; i < tabla.size(); i++) {
            if (tabla.get(i).getC() == 0) {
                count++;
            } else {
                if (count > 0) {
                    tabla.get(i - count).setI(count);
                    for (int j = i - count + 1; j < i; j++) {
                        tabla.get(j).setI(0);
                    }
                }
                count = 0;
                tabla.get(i).setI(0);
            }
        }

        if (count > 0) {
            tabla.get(tabla.size() - count).setI(count);
            for (int j = tabla.size() - count + 1; j < tabla.size(); j++) {
                tabla.get(j).setI(0);
            }
        }

        new TablaDistancias(tabla);

        ArrayList<Integer> listaArreglada = new ArrayList<>();
        for (RenglonDistancia r : tabla) {
            if (r.getI() != 0) {
                listaArreglada.add(r.getI());
            } else {
                if (listaArreglada.isEmpty() || listaArreglada.get(listaArreglada.size() - 1) != 0) {
                    listaArreglada.add(r.getI());
                }
            }
        }

        System.out.println("Oi sum: " + listaArreglada.size());

        HashMap<Integer, Integer> listaUnicos = new HashMap<>();
        for (Integer r : listaArreglada) {
            if (!listaUnicos.containsKey(r)) {
                listaUnicos.put(r, 0);
            } else {
                listaUnicos.put(r, listaUnicos.get(r) + 1);
            }
        }
        AtomicInteger counter = new AtomicInteger(0);
        listaUnicos.forEach((n, i) -> {
            System.out.println(n + " " + i);
            counter.addAndGet(i);
        });

        System.out.println(counter);

        ArrayList<TablaDistancia2> tabla2 = new ArrayList<>();
    }
}
