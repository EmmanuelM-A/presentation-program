package com.scc210groupproject.structure;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.List;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

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
    private String chartType;
    private ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();

public ChartElement() {
        ChartElement self = this;
        super.addInputListener(new IMouseClicked() {
    
            @Override
            public void mouseClicked(Object target, InputState state) {
                ContextMenuPanel.setMenu(self, new ChartContextMenu(self));
            }
        });
        this.chart = new JPanel();
    }

    private void parseTable() {
        JTable table = ((ChartContextMenu)ContextMenuPanel.menu).getTable();
        for(int i = 0; i < table.getRowCount(); i++) {
            for(int j = 0; j < table.getColumnCount(); j++) {
                this.data[i][j] = (String)table.getValueAt(i, j);
            }
        }
    }

    private void makeChart(String chartType, Boolean load) {
        if (!load) { this.parseTable(); }

        JFreeChart chart;

        switch (chartType) {
            case "PIE":
                DefaultPieDataset pieDataset = new DefaultPieDataset();

                for(int i = 0; i < this.data[0].length; i++) {
                    if (this.data[i][0] != null && this.data[i][1] != null) {
                        try {
                            pieDataset.setValue(data[i][0], Double.parseDouble(data[i][1]));
                        } catch (NumberFormatException e) {
                            pieDataset.setValue(data[i][1], 0);
                        }
                    } 
                }

                chart = ChartFactory.createPieChart("Chart1", pieDataset, true, true, false);
                break;

            case "LINE":  
            case "BAR":
                DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset();
                
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
            
                chart = ChartFactory.createLineChart("Chart1", "x", "y", categoryDataset,
                 PlotOrientation.VERTICAL, true, true, false);
                break;

            case "SCATTER":
                XYSeries series = new XYSeries("Series1");

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

                XYSeriesCollection xyDataset = new XYSeriesCollection();
                xyDataset.addSeries(series);
                chart = ChartFactory.createXYLineChart("Chart1", "x", "y", xyDataset,
                 PlotOrientation.VERTICAL, true, true, false);
                break;
        }
            
        this.chart = new ChartPanel(chart);
        this.chart.setSize(new Dimension(400, 400));
    }

    public String[][] getData() {
        return data;
    }

    public static ChartElement createEmpty() { return new ChartElement(); }

    @Override
    protected void writeSelf(Writer writer) throws IOException {
        super.writeSelfExtended(writer);

        writer.writeString("data", Arrays.deepToString(getData()));
        writer.writeString("chartType", this.chartType);

    }

    @Override
    protected void readSelf(Reader reader) throws IOException {

        String[] rows = reader.readString("data").split("],");

        for(int i = 0; i < rows.length; i++){
            rows[i] = rows[i].replace("[\\[]", "");
            String[] rowValues = rows[i].split(",");

            for(int j = 0; j < rowValues.length; j++) {
                this.data[i][j] = rowValues[j];
            }
        }

        switch(reader.readString("chartType")) {
            case ("PIE"):
                this.makePieChart();
                break;
            case ("LINE"):
                this.makeLineChart();
                break;
            case ("BAR"):
                this.makeBarChart();
                break;
            case ("SCATTER"):
                this.makeScatterChart();
                break;
        }
        super.readSelfExtended(reader);
    }

    @Override
    public Component asComp() {
        return chart;
    }
    
}