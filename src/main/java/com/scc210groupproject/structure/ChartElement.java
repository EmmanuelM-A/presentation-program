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
            ArrayList<String> tempArrayList = new ArrayList<String>();
            for(int j = 0; j < table.getColumnCount(); j++) {
                tempArrayList.add((String)table.getValueAt(i, j));
            }
            data.add(tempArrayList);
        }
    }

    public void makeChart(String chartType, Boolean load) {
        if (!load) { this.parseTable(); }

        JFreeChart chart;

        switch (chartType) {
            case "PIE":
                DefaultPieDataset pieDataset = new DefaultPieDataset();

                for(int i = 0; i < data.size(); i++) {
                    if (data.get(i).get(0) != null && data.get(i).get(1) != null) {
                        try {
                            pieDataset.setValue(data.get(i).get(0), Double.parseDouble(data.get(i).get(1)));
                        } catch (NumberFormatException e) {
                            pieDataset.setValue(data.get(i).get(0), 0);
                        }
                    } 
                }

                chart = ChartFactory.createPieChart("Chart1", pieDataset, true, true, false);
                break;

            case "LINE":
            case "BAR":
                DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset();
                
                for(int i = 0; i < data.size(); i++) {
                    for(int j = 0; j < data.get(i).size(); j++) {
                        if (this.data.get(i).get(j) != null) {
                            try {
                                categoryDataset.addValue(Double.parseDouble(data.get(i).get(j)), "series" + j, data.get(i).get(0));
                            } catch (NumberFormatException e) {
                                categoryDataset.addValue(0, "Series" + j, data.get(i).get(0));
                            }
                        }
                    }
                }
                
                if(chartType == "LINE") {
                    chart = ChartFactory.createLineChart("Chart1", "x", "y", categoryDataset,
                     PlotOrientation.VERTICAL, true, true, false);
                } else {
                    chart = ChartFactory.createBarChart("Chart1", "x", "y", categoryDataset,
                    PlotOrientation.VERTICAL, true, true, false);
                }
                break;

            case "SCATTER":
                XYSeries series = new XYSeries("Series1");

                for(int i = 0; i < data.size(); i++) {
                    for(int j = 0; j < data.get(i).size(); j++) {
                        if (data.get(i).get(j) != null && data.get(i).get(j+1) != null) {
                            try {
                                series.add(Double.parseDouble(data.get(i).get(j)), Double.parseDouble(data.get(i).get(j+1)));
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
            default:
                return;
        }
            
        this.chart = new ChartPanel(chart);
        this.chart.setSize(new Dimension(400, 400));
    }

    public ArrayList<ArrayList<String>> getData() {
        return data;
    }

    public static ChartElement createEmpty() { return new ChartElement(); }

    @Override
    protected void writeSelf(Writer writer) throws IOException {
        super.writeSelfExtended(writer);

        // writer.writeString("data", Arrays.deepToString(getData()));
        // writer.writeString("chartType", this.chartType);

    }

    @Override
    protected void readSelf(Reader reader) throws IOException {

        // String[] rows = reader.readString("data").split("],");

        // for(int i = 0; i < rows.length; i++){
        //     rows[i] = rows[i].replace("[\\[]", "");
        //     String[] rowValues = rows[i].split(",");
        //     data.add(new ArrayList<String>(Arrays.asList(rowValues))) ;
        // }
        // this.makeChart(chartType, true);

        super.readSelfExtended(reader);
    }

    @Override
    public Component asComp() {
        return chart;
    }
    
}