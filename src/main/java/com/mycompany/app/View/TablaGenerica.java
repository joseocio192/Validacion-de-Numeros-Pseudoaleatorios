package com.mycompany.app.View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TablaGenerica extends JFrame {

    public TablaGenerica(String title, double[][] data, String[] headers) {
        super(title);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        DefaultTableModel model = new DefaultTableModel();

       
        model.addColumn("");

     
        for (String header : headers) {
            model.addColumn(header);
        }

        int n = data.length;
        double intervalo = 1.0 / n;

   
        for (int i = 0; i < n; i++) {
            double ini = i * intervalo;
            double fin = (i + 1) * intervalo;
            String rowLabel = String.format("[%.2f - %.2f)", ini, fin);
            Object[] row = new Object[headers.length + 1];
            row[0] = rowLabel;
            for (int j = 0; j < headers.length; j++) {
                row[j + 1] = data[i][j];
            }
            model.addRow(row);
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }
}
