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
                Controller.procesarJi_Cuadrado(vista);
                break;
            case "kolmogorovSmirnov":
                Controller.procesaKolmogorov_Smirnov(vista);
                break;
            case "series":
                Controller.procesarSeries(vista);   
                break;
            case "distancias":
                Controller.procesarDistancias(vista);
            break;
            default:
                throw new IllegalArgumentException("Acción no reconocida: " + action);
        }
    }
}