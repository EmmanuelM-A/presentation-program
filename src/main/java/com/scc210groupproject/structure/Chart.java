package com.scc210groupproject.structure;

import java.awt.Component;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import com.scc210groupproject.readwrite.FileDeserializer.Reader;
import com.scc210groupproject.readwrite.FileSerializer.Writer;

public class Chart extends ExtendedElement {

    private JPanel chart;

    public Chart() {
        createDemoPanel();
    }

    private PieDataset createDataset( ) {
        DefaultPieDataset dataset = new DefaultPieDataset( );
        dataset.setValue( "IPhone 5s" , 20 );  
        dataset.setValue( "SamSung Grand" , 20 );   
        dataset.setValue( "MotoG" , 40 );    
        dataset.setValue( "Nokia Lumia" , 10 );  
        return dataset;         
    }
   
    private JFreeChart createChart( PieDataset dataset ) {
        JFreeChart chart = ChartFactory.createPieChart(      
        "Mobile Sales",   // chart title 
        dataset,          // data    
        true,             // include legend   
        true, 
        false);

        return chart;
    }
   
    private void createDemoPanel() {
        JFreeChart chart = createChart(createDataset( ) );  
        this.chart = new ChartPanel(chart);
        this.chart.setSize(new Dimension(400, 400));
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