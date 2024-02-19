package com.scc210groupproject.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.scc210groupproject.structure.ChartElement;
import com.scc210groupproject.ui.SlideManager;
import com.scc210groupproject.ui.contextMenu.ContextMenuPanel;

public class NewBarChartAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent arg0) {
        ChartElement element = (ChartElement)ContextMenuPanel.currentMenuOwner;
        ChartElement tempChartElement = new ChartElement();
        tempChartElement.makeBarChart();

        if (element == null) {
            SlideManager.slideManager.getCurrentSlide().add(tempChartElement);
            ContextMenuPanel.currentMenuOwner = tempChartElement;
        } else if(element != null) {
            SlideManager.slideManager.getCurrentSlide().remove(element);
            SlideManager.slideManager.getCurrentSlide().add(tempChartElement);
            ContextMenuPanel.currentMenuOwner = tempChartElement;
        }
    }

}
