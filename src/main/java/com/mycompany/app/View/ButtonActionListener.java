package com.mycompany.app.View;

import com.mycompany.app.Model.Modelo;
import com.mycompany.app.Controller.Controller;
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
                Controller.procesarDistancias(vista);
            break;
            default:
                throw new IllegalArgumentException("Acción no reconocida: " + action);
        }
    }
}