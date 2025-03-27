package com.mycompany.app.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import com.mycompany.app.Controller.Controller;
import com.mycompany.app.Model.Modelo;

public class Vista extends JFrame {
    public ArrayList<Float> numeros;

    public Vista() {
        numeros = new ArrayList<Float>();
        Interfaz();
    }

    public void Interfaz() {
        setTitle("Proyecto Simulacion");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Menu");
        JMenuItem openItem = new JMenuItem("Abrir");
        JMenuItem exitItem = new JMenuItem("Salir");
        fileMenu.add(openItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);

        JPanel panel = new JPanel();
        JButton button = new JButton("Prueba Ji-Cuadrado");
        JButton button2 = new JButton("Prueba Kolmogorov-Smirnov");
        JButton button3 = new JButton("Prueba de las Series");
        JButton button4 = new JButton("Prueba de las Distancias");
        panel.add(button);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Vista.this, "Ejecutando...");
                Modelo.ji_Cuadrado(numeros);
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Vista.this, "Ejecutando...");
                Modelo.kolmogorov_Smirnov(numeros);
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Vista.this, "Ejecutando...");
                Modelo.series(numeros);
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Vista.this, "Ejecutando...");
                Modelo.distancias(numeros);
            }
        });

        openItem.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                JOptionPane.showMessageDialog(Vista.this, "File selected: " + fileChooser.getSelectedFile().getName());
                numeros = Controller.procesarArchivo(fileChooser.getSelectedFile().getAbsolutePath());
            }
        });

        exitItem.addActionListener(e -> System.exit(0));

        JPanel textPanel = new JPanel();
        JLabel label = new JLabel("Pruebas para muestras de números aleatorios");
        textPanel.add(label);

        setLayout(new BorderLayout());
        setJMenuBar(menuBar);
        add(panel, BorderLayout.CENTER);
        add(textPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
