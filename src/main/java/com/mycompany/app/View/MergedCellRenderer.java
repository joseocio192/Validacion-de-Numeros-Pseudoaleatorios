package com.mycompany.app.View;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import com.mycompany.app.Model.RenglonDistancia;

import java.awt.*;
import java.util.ArrayList;

class MergedCellRenderer extends DefaultTableCellRenderer {
    private ArrayList<Integer> mergeIndexes;

    public MergedCellRenderer(ArrayList<RenglonDistancia> tabla) {
        mergeIndexes = new ArrayList<>();
        for (int i = 0; i < tabla.size(); i++) {
            if (tabla.get(i).getI() > 0) {
                mergeIndexes.add(i);
            }
        }
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        ((javax.swing.JLabel) c).setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        
        return c;
    }
}
