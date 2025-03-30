package com.mycompany.app.View;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mycompany.app.Model.RenglonDistancia2;

public class TablaDistancias2 extends JFrame {
    ArrayList<RenglonDistancia2> tabla;

    public TablaDistancias2(ArrayList<RenglonDistancia2> tabla) {
        this.tabla = tabla;
        Interfaz();
    };

    public void Interfaz() {
        setTitle("Tabla distancias");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 600);
        String[] columns = {"I", "pi", "oi", "ei", "eimoi", "eimoicei"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
    
        for (RenglonDistancia2 row : tabla) {
            model.addRow(new Object[]{row.getI(), row.getPi(), row.getOi(), row.getEi(), row.getEimoi(), row.getEimoicei()});
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
