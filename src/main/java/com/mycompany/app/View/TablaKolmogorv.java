package com.mycompany.app.View;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mycompany.app.Model.RenglonKolmogorov;

public class TablaKolmogorv extends JFrame {
    ArrayList<RenglonKolmogorov> tabla;

    public TablaKolmogorv(ArrayList<RenglonKolmogorov> tabla) {
        this.tabla = tabla;
        Interfaz();
    };

    public void Interfaz() {
        setTitle("Tabla distancias");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 600);
        String[] columns = {"i", "Ui", "Fi", "Di"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);


        for (RenglonKolmogorov row : tabla) {
            model.addRow(new Object[]{row.getI(), row.getUi(), row.getFi(), row.getDi()});
        }
    
        JTable table = new JTable(model);

        table.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                java.awt.Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
                        column);
                ((javax.swing.JLabel) cell).setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                return cell;
            }
        });

        table.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
        setVisible(true);
    }
}
