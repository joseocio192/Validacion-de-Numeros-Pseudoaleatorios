package com.mycompany.app.View;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;

public class Vista extends JFrame {
    public ArrayList<Float> numeros;
    JButton button;
    JButton button2;
    JButton button3;
    JButton button4;

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
        JMenu vista = new JMenu("Vista");
        JMenuItem modoOscuro = new JMenuItem("Modo oscuro");
        JMenuItem modoClaro = new JMenuItem("Modo Claro");
        vista.add(modoOscuro);
        vista.add(modoClaro);
        menuBar.add(vista);
        
        JPanel panel = new JPanel();
        button = new JButton("Prueba Ji-Cuadrado");
        button2 = new JButton("Prueba Kolmogorov-Smirnov");
        button3 = new JButton("Prueba de las Series");
        button4 = new JButton("Prueba de las Distancias");
        disableButtons(button, button2, button3, button4);
        panel.add(button);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);

        button.addActionListener(new ButtonActionListener(this, "jiCuadrado"));
        button2.addActionListener(new ButtonActionListener(this, "kolmogorovSmirnov"));
        button3.addActionListener(new ButtonActionListener(this, "series"));
        button4.addActionListener(new ButtonActionListener(this, "distancias"));

        openItem.addActionListener(new ButtonActionListener(this, "abrir"));
        exitItem.addActionListener(new ButtonActionListener(this, "salir"));

        modoOscuro.addActionListener(new ButtonActionListener(this,"modoOscuro"));
        modoClaro.addActionListener(new ButtonActionListener(this, "modoClaro"));

        JPanel textPanel = new JPanel();
        JLabel label = new JLabel("Pruebas para muestras de números aleatorios");
        textPanel.add(label);

        setLayout(new BorderLayout());
        setJMenuBar(menuBar);
        add(panel, BorderLayout.CENTER);
        add(textPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void disableButtons(JButton button, JButton button2, JButton button3, JButton button4) {
        button.setEnabled(false);
        button2.setEnabled(false);
        button3.setEnabled(false);
        button4.setEnabled(false);
    }
}
