package com.mycompany.app.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.jfree.data.xy.XYSeries;

import com.mycompany.app.View.GraficaJiCuadrado;
import com.mycompany.app.View.LineChartkolmogorov_Smirnov;
import com.mycompany.app.View.TablaDistancias;
import com.mycompany.app.View.TablaDistancias2;
import com.mycompany.app.View.TablaGenerica;
import com.mycompany.app.View.TablaJiCuadrado;
import com.mycompany.app.View.TablaKolmogorv;

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
        int serie = numeros.size();
        float D = 0;

        ArrayList<RenglonKolmogorov> tabla = new ArrayList<>();

        for (int i = 0; i < serie; i++) {
            RenglonKolmogorov r = new RenglonKolmogorov();
            float Fni = (i + 1f) / serie;
            float Di = Math.abs(Fni - numeros.get(i));
            if (Di > D) {
                D = Di;
            }
            r.setDi(Di);
            r.setFi(Fni);
            r.setI(i+1);
            r.setUi(numeros.get(i));
            tabla.add(r);
        }
        new TablaKolmogorv(tabla);
        System.out.println("Error: " + error);
        XYSeries series1 = new XYSeries("Datos reales");
        XYSeries series2 = new XYSeries("Línea de referencia");
        for (RenglonKolmogorov renglonKolmogorov : tabla) {
            series1.add(renglonKolmogorov.getFi(), renglonKolmogorov.getUi());
            series2.add(renglonKolmogorov.getUi(), renglonKolmogorov.getUi());
        }
        SwingUtilities.invokeLater(() -> {
            LineChartkolmogorov_Smirnov example = new LineChartkolmogorov_Smirnov(series1,series2);
            example.setVisible(true);
        });
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
        Collections.shuffle(nums);
    
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

    // Inicializar la matriz Oij
    double[][] Oij = new double[n][n];

    // Recorrer pares consecutivos (Xi, Xi+1)
    for (int i = 0; i < N; i++) {
        float num1 = nums.get(i);
        float num2 = nums.get(i + 1);

        int fila = (int) Math.floor(num1 / intervalo);
        int col = (int) Math.floor(num2 / intervalo);

        // Asegurar que los índices no excedan n-1
        fila = Math.min(fila, n - 1);
        col = Math.min(col, n - 1);

        // Depuración: imprimir valores antes de agregar a la matriz
        System.out.println("Par (" + num1 + ", " + num2 + ") -> Indices [" + fila + "][" + col + "]");

        Oij[fila][col]++;
    }
    // Agregar el par circular: (último, primer elemento)
int filaCircular = (int) Math.floor(nums.get(nums.size() - 1) / intervalo);
int colCircular = (int) Math.floor(nums.get(0) / intervalo);
filaCircular = Math.min(filaCircular, n - 1);
colCircular = Math.min(colCircular, n - 1);
System.out.println("Par circular (" + nums.get(nums.size()-1) + ", " + nums.get(0) + ") -> Indices [" + filaCircular + "][" + colCircular + "]");
Oij[filaCircular][colCircular]++;

    // Imprimir la matriz Oij para depuración
    System.out.println("Matriz Oij:");
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            System.out.print(Oij[i][j] + "\t");
        }
        System.out.println();
    }
    
        // Crear matriz esperada Eij con valores esperados
        double[][] Eij = new double[n][n];
        double esperado = (double) N / (n * n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Eij[i][j] = esperado;
            }
        }
    
        // Calcular chi-cuadrado
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
    
        // Crear encabezados para la tabla
        String[] headers = new String[n];
        for (int j = 0; j < n; j++) {
            double ini = j * intervalo;
            double fin = (j + 1) * intervalo;
            headers[j] = String.format("[%.2f - %.2f)", ini, fin);
        }
    
        // Mostrar las tablas
        Integer[] ubicacion1 = {100,100};
        new TablaGenerica("Tabla Oij", Oij, headers, ubicacion1).setVisible(true);
        ubicacion1[0] = 700;
        new TablaGenerica("Tabla Eij", Eij, headers, ubicacion1).setVisible(true);
        ubicacion1[0] = 100;
        ubicacion1[1] = 550;
        new TablaGenerica("Tabla (Oij - Eij)", diff, headers, ubicacion1).setVisible(true);
        ubicacion1[0] = 700;
        new TablaGenerica("Tabla (Oij - Eij)^2 / Eij", chi, headers, ubicacion1).setVisible(true);
    
        // Mostrar el valor de chi-cuadrado
        JOptionPane.showMessageDialog(null, "Chi-cuadrado total: " + String.format("%.4f", chiCuadrado));
        JOptionPane.showMessageDialog(null, "Grados de libertad: " + (n * n - 1));
    
        // Comparar con el valor crítico
        int gl = n * n - 1;
        double critico = obtenerValorCritico(gl);
        String mensaje = (chiCuadrado < critico) 
            ? "Existe evidencia suficiente para decir que la muestra NO está distribuida uniformemente"
            : "No existe evidencia suficiente para decir que la muestra NO está distribuida uniformemente";
        JOptionPane.showMessageDialog(null, mensaje);
    }
    public static String distancias(ArrayList<Float> numeros, Float alpha, Float theta) {
        System.out.println("Metodo distancias");
        float beta = alpha + theta;
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

        HashMap<Integer, Integer> listaUnicos = new HashMap<>();
        for (Integer r : listaArreglada) {
            if (!listaUnicos.containsKey(r)) {
                listaUnicos.put(r, 1);
            } else {
                listaUnicos.put(r, listaUnicos.get(r) + 1);
            }
        }

        AtomicInteger counter = new AtomicInteger(0);
        listaUnicos.forEach((n, i) -> {
            counter.addAndGet(i);
        });

        ArrayList<RenglonDistancia2> tabla2 = new ArrayList<>();
        AtomicReference<Float> sumaEimoicei = new AtomicReference<>(0f);
        AtomicReference<Float> sumaEi = new AtomicReference<>(0f);
        listaArreglada = new ArrayList<>(listaUnicos.keySet());
        Collections.sort(listaArreglada);
        for (Integer a : listaArreglada) {
            System.out.println(a);
        }
        int max  = getMax(listaArreglada) + 1;
        System.out.println("max"+ max);
        AtomicBoolean x = new AtomicBoolean(true);
        listaUnicos.forEach((n, oi) -> {
            RenglonDistancia2 r = new RenglonDistancia2();
            r.setI(n);
            r.setOi(oi);
            float eii;
            if (n == 0) {
                eii = (float) (counter.get() * theta);
                r.setPi(theta);
                float Eimoicei = (float) Math.pow((eii - oi), 2) / eii;
                sumaEimoicei.updateAndGet(v -> v + Eimoicei);
                sumaEi.updateAndGet(v -> v + eii);
                r.setEi(eii);
                r.setEimoi(eii - oi);
                r.setEimoicei(Eimoicei);
                tabla2.add(r); 
            }else if (n >= max && x.get()) {
                float pi = (float) (Math.pow((1 - theta), max));
                eii = (float) (counter.get() * Math.pow((1 - theta), max));
                float Eimoicei = (float) Math.pow((eii - oi), 2) / eii;
                sumaEimoicei.updateAndGet(v -> v + Eimoicei);
                sumaEi.updateAndGet(v -> v + eii);
                x.set(false);
                r.setPi(pi);
                r.setEi(eii);
                r.setEimoi(eii - oi);
                r.setEimoicei(Eimoicei);
                tabla2.add(r);
            } else {
                float pi = (float) (Math.pow((1 - theta), n) * theta);
                eii = (float) (counter.get() * Math.pow((1 - theta), n) * theta);
                r.setPi(pi);
                float Eimoicei = (float) Math.pow((eii - oi), 2) / eii;
                sumaEimoicei.updateAndGet(v -> v + Eimoicei);
                sumaEi.updateAndGet(v -> v + eii);
                r.setEi(eii);
                r.setEimoi(eii - oi);
                r.setEimoicei(Eimoicei);
                tabla2.add(r); 
            }
        });
        for (RenglonDistancia2 renglonDistancia2 : tabla2) {
            System.out.println(renglonDistancia2.toString());
        }
        System.out.println(sumaEi);
        System.out.println(sumaEimoicei);
        tabla2.add(new RenglonDistancia2(null, 1f, counter.get(), sumaEi.get(), null, sumaEimoicei.get()));
        new TablaDistancias2(tabla2);
        double valorCritico = obtenerValorCritico(listaUnicos.size()-1);
        if (sumaEimoicei.get() > valorCritico) {
            return "Existe evidencia suficiente para decir que la muestra NO está distribuida uniformemente";
        } else {
            return "No existe evidencia suficiente para decir que la muestra NO está distribuida uniformemente";
        }
    }

    public static int getMax(ArrayList<Integer> datos) {
        for (int i = 0; i < datos.size() - 1; i++) {
            if (datos.get(i) + 1 != datos.get(i + 1)) {
                return datos.get(i);
            }
        }
        return datos.get(datos.size() - 1);
    }
}
