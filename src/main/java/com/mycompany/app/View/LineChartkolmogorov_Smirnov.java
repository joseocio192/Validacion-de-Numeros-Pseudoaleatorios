package com.mycompany.app.View;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

public class LineChartkolmogorov_Smirnov extends JFrame {
    XYSeries series1;
    XYSeries series2;

    public LineChartkolmogorov_Smirnov(XYSeries series1, XYSeries series2) {
        setTitle("kolmogorov_Smirnov");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.series1 = series1;
        this.series2 = series2;

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Kolmogorov_Smirnov",
                "X", "Y",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        XYPlot plot = (XYPlot) chart.getPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(1.5f));
        renderer.setSeriesPaint(1, Color.BLACK);
        renderer.setSeriesStroke(1, new BasicStroke(2.0f));

        plot.setRenderer(renderer);

        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LineChartkolmogorov_Smirnov example = new LineChartkolmogorov_Smirnov(null, null);
            example.setVisible(true);
        });
    }
}