package com.scc210groupproject.structure;

import java.awt.Component;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JTable;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.scc210groupproject.ui.contextMenu.ContextMenuPanel;
import com.scc210groupproject.ui.contextMenu.ChartContextMenu;
import com.scc210groupproject.readwrite.FileDeserializer.Reader;
import com.scc210groupproject.readwrite.FileSerializer.Writer;
import com.scc210groupproject.structure.input.InputEmulator.InputState;
import com.scc210groupproject.structure.input.listeners.IMouseClicked;

public class ChartElement extends ExtendedElement {

    private JPanel chart;
    private String[][]data = new String[1000][1000];

    public ChartElement(String chartType) {
        ChartElement self = this;
        super.addInputListener(new IMouseClicked() {
    
            @Override
            public void mouseClicked(Object target, InputState state) {
                ContextMenuPanel.setMenu(self, new ChartContextMenu(self));
            }
        });
    }

    private void parseTable() {
        JTable table = ((ChartContextMenu)ContextMenuPanel.menu).getTable();
        for(int i = 0; i < table.getRowCount(); i++) {
            for(int j = 0; j < table.getColumnCount(); j++) {
                this.data[i][j] = (String)table.getValueAt(i, j);
            }
        }
    }

    public void makePieChart() {
        JTable table = ((ChartContextMenu)ContextMenuPanel.menu).getTable();
        DefaultPieDataset dataset = new DefaultPieDataset();
        this.parseTable();
        for(int i = 0; i < table.getRowCount(); i++) {
            if (this.data[i][0] != null && this.data[i][1] != null) {
                try {
                    dataset.setValue(data[i][0], Double.parseDouble(data[i][1]));
                } catch (NumberFormatException e) {
                    dataset.setValue(data[i][1], 0);
                }
            }
            
        }
        JFreeChart chart = ChartFactory.createPieChart("Chart1", dataset, true, true, false);

        this.chart = new ChartPanel(chart);
        this.chart.setSize(new Dimension(400, 400));
    }

    public void makeLineChart() {
        JTable table = ((ChartContextMenu)ContextMenuPanel.menu).getTable();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        this.parseTable();
        for(int j = 1; j < table.getColumnCount(); j++) {
            for(int i = 0; i < table.getRowCount(); i++) {
                if (this.data[i][j] != null) {
                    try {
                        dataset.addValue(Double.parseDouble(data[i][j]), "series" + j, data[i][0]);
                    } catch (NumberFormatException e) {
                        dataset.addValue(0, "Series" + j, data[i][0]);
                    }
                }
            }
        }
        JFreeChart chart = ChartFactory.createLineChart("Chart1", "x", "y", dataset, 
        PlotOrientation.VERTICAL, true, true, false);

        this.chart = new ChartPanel(chart);
        this.chart.setSize(new Dimension(400, 400));
    }

    public void makeBarChart() {
        JTable table = ((ChartContextMenu)ContextMenuPanel.menu).getTable();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        this.parseTable();
        for(int j = 1; j < table.getColumnCount(); j++) {
            for(int i = 0; i < table.getRowCount(); i++) {
                if (this.data[i][j] != null) {
                    try {
                        dataset.addValue(Double.parseDouble(data[i][j]), "series" + j, data[i][0]);
                    } catch (NumberFormatException e) {
                        dataset.addValue(0, "Series" + j, data[i][0]);
                    }
                }
            }
        }
        JFreeChart chart = ChartFactory.createBarChart("Chart1", "x", "y", dataset, 
        PlotOrientation.VERTICAL, true, true, false);

        this.chart = new ChartPanel(chart);
        this.chart.setSize(new Dimension(400, 400));
    }

    public void makeScatterChart() {
        JTable table = ((ChartContextMenu)ContextMenuPanel.menu).getTable();
        XYSeries series = new XYSeries("Series1");
        this.parseTable();
        for(int j = 0; j < table.getColumnCount(); j++) {
            for(int i = 0; i < table.getRowCount(); i++) {
                if (this.data[i][j] != null && this.data[i][j+1] != null) {
                    try {
                        series.add(Double.parseDouble(data[i][j]), Double.parseDouble(data[i][j+1]));
                    } catch (NumberFormatException e) {
                        series.add(0, 0);
                    }
                }
            }
        }
        XYSeriesCollection  dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        JFreeChart chart = ChartFactory.createXYLineChart("Chart1", "x", "y", dataset, 
        PlotOrientation.VERTICAL, true, true, false);

        this.chart = new ChartPanel(chart);
        this.chart.setSize(new Dimension(400, 400));
    }

    public String[][] getData() {
        return data;
    }

    @Override
    protected void writeSelf(Writer writer) throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'writeSelf'");
    }

    @Override
    protected void readSelf(Reader reader) throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readSelf'");
    }

    @Override
    public Component asComp() {
        return chart;
    }
    
}