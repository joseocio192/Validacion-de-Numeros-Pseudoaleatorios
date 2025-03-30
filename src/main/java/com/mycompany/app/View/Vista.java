package com.mycompany.app.View;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.util.ArrayList;

import com.mycompany.app.Controller.Controller;

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
        disableButtons(button, button2, button3, button4);
        panel.add(button);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);

        button.addActionListener(new ButtonActionListener(this, "jiCuadrado"));
        button2.addActionListener(new ButtonActionListener(this, "kolmogorovSmirnov"));
        button3.addActionListener(new ButtonActionListener(this, "series"));
        button4.addActionListener(new ButtonActionListener(this, "distancias"));

        openItem.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("csv and xlsx files", "csv", "xlsx"));
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                numeros = Controller.procesarArchivo(fileChooser.getSelectedFile().getAbsolutePath());
            }
            if (numeros == null) {
                JOptionPane.showMessageDialog(null, "No se pudo procesar el archivo", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                button.setEnabled(true);
                button2.setEnabled(true);
                button3.setEnabled(true);
                button4.setEnabled(true);
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

    private void disableButtons(JButton button, JButton button2, JButton button3, JButton button4) {
        button.setEnabled(false);
        button2.setEnabled(false);
        button3.setEnabled(false);
        button4.setEnabled(false);
    }
}
