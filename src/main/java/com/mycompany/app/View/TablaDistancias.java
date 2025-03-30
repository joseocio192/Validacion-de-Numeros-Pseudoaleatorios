package com.mycompany.app.View;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mycompany.app.Model.RenglonDistancia;

public class TablaDistancias extends JFrame {
    ArrayList<RenglonDistancia> tabla;

    public TablaDistancias(ArrayList<RenglonDistancia> tabla) {
        this.tabla = tabla;
        Interfaz();
    };

    public void Interfaz() {
        setTitle("Tabla distancias");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 600);
        String[] columns = {"n", "Ui", "C", "i"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
    
        for (RenglonDistancia row : tabla) {
            Object iValue = (row.getI() > 0) ? row.getI() : "";
            model.addRow(new Object[]{row.getN(), row.getUi(), row.getC(), iValue});
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
        table.getColumnModel().getColumn(3).setCellRenderer(new MergedCellRenderer(tabla));
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
        setVisible(true);
    }
}
