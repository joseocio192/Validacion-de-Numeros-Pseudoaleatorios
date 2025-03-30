package com.mycompany.app.View;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;

public class GraficaJiCuadrado extends JFrame {
    double[] categorias;
    double[] valores;
    double E;


    public GraficaJiCuadrado(double[] categorias, double[] valores, double E) {
        setTitle("Gráfico de Barras con Línea");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        this.categorias = categorias;
        this.valores = valores;
        this.E = E;
        JFreeChart chart = createChart();
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(780, 460));
        setContentPane(chartPanel);
    }

    private JFreeChart createChart() {
        CategoryDataset dataset1 = createBarDataset();
        CategoryDataset dataset2 = createLineDataset();

        JFreeChart chart = ChartFactory.createBarChart(
                "Gráfico JiCuadrado",
                "Valores Intervalo",
                "Valores Frecuencia",
                dataset1,
                PlotOrientation.VERTICAL,
                false, true, false);

        CategoryPlot plot = (CategoryPlot) chart.getPlot();

        BarRenderer barRenderer = new BarRenderer();
        barRenderer.setSeriesPaint(0, new Color(255, 255, 128));
        plot.setRenderer(0, barRenderer);

        LineAndShapeRenderer lineRenderer = new LineAndShapeRenderer();
        lineRenderer.setSeriesPaint(0, Color.RED);
        lineRenderer.setSeriesShapesVisible(0, true);
        lineRenderer.setSeriesShape(0, new java.awt.geom.Ellipse2D.Double(-3, -3, 6, 6));

        plot.setDataset(1, dataset2);
        plot.mapDatasetToRangeAxis(1, 0);
        plot.setRenderer(1, lineRenderer);

        plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);

        return chart;
    }

    private CategoryDataset createBarDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (int i = 0; i < valores.length; i++) {
            dataset.addValue(valores[i], "Barras", String.valueOf(categorias[i]));
        }

        return dataset;
    }

    private CategoryDataset createLineDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        double promedio = E;

        for (double categoria : categorias) {
            dataset.addValue(promedio, "Línea", String.valueOf(categoria));
        }

        return dataset;
    }

    public static void main(String[] args) {
        
    }
}
