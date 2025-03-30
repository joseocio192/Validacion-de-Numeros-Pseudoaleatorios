package com.mycompany.app.View;

import com.mycompany.app.Controller.Controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.themes.*;

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
            case"abrir":
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("csv and xlsx files", "csv", "xlsx"));
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    vista.numeros = Controller.procesarArchivo(fileChooser.getSelectedFile().getAbsolutePath());
                }
                if (vista.numeros == null) {
                    JOptionPane.showMessageDialog(null, "No se pudo procesar el archivo", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    vista.button.setEnabled(true);
                    vista.button2.setEnabled(true);
                    vista.button3.setEnabled(true);
                    vista.button4.setEnabled(true);
                }
            break;
            case "salir":
                System.exit(0);
            break;
            case "modoOscuro":
            EventQueue.invokeLater(() -> {
                FlatAnimatedLafChange.showSnapshot();
                FlatMacDarkLaf.setup();
                FlatLaf.updateUI();
                FlatAnimatedLafChange.hideSnapshotWithAnimation();
            });
            break;
            case "modoClaro":
            EventQueue.invokeLater(() -> {
                FlatAnimatedLafChange.showSnapshot();
                FlatMacLightLaf.setup();
                FlatLaf.updateUI();
                FlatAnimatedLafChange.hideSnapshotWithAnimation();
            });
            break;
            default:
                throw new IllegalArgumentException("Acción no reconocida: " + action);
        }
    }

}