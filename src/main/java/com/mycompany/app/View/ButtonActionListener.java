package com.mycompany.app.View;

import com.mycompany.app.Model.Modelo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonActionListener implements ActionListener {
    private Vista vista;
    private String action;

    public ButtonActionListener(Vista vista, String action) {
        this.vista = vista;
        this.action = action;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (action) {
            case "jiCuadrado":
                Modelo.ji_Cuadrado(vista.numeros);
                break;
            case "kolmogorovSmirnov":
                Modelo.kolmogorov_Smirnov(vista.numeros);
                break;
            case "series":
                Modelo.series(vista.numeros);
                break;
            case "distancias":
                String input;
                double number = 0;
                boolean validInput = false;
                do {
                    input = JOptionPane.showInputDialog(vista, "Ingrese θ:", "Entrada requerida", JOptionPane.PLAIN_MESSAGE);
                    if (input == null) {
                        return; // Cancel or close dialog
                    }
                    try {
                        number = Double.parseDouble(input);
                        validInput = true;
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(vista, "Por favor, ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } while (!validInput);

                Modelo.distancias(vista.numeros, number);
                break;
            default:
                throw new IllegalArgumentException("Acción no reconocida: " + action);
        }
    }
}