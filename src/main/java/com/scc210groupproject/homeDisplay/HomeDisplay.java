package com.scc210groupproject.homeDisplay;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
//Splash screen on program startup with various templates and themes to choose from

//Set to jdialogue to make act as splash 

public class HomeDisplay extends JFrame {

  public static HomeDisplay instance;

  private ThemeDisplayPanel themeDisplayPanel;
  private TemplateDisplayPanel templateDisplayPanel;
  private HomeScreenToolbar homeBar;

  public HomeDisplay() {
    // add instance of tollbarPane to home screen
    this.setLayout(new GridLayout(3, 1));

    homeBar = new HomeScreenToolbar();
    themeDisplayPanel = new ThemeDisplayPanel();
    templateDisplayPanel = new TemplateDisplayPanel();
    Border templateBorder = BorderFactory.createTitledBorder("Template Presets");
    Border themeBorder = BorderFactory.createTitledBorder("Theme Presets");

    themeDisplayPanel.setBorder(templateBorder);
    templateDisplayPanel.setBorder(themeBorder);

    themeDisplayPanel.setBackground(UIManager.getColor("Main.Dim"));
    templateDisplayPanel.setBackground(UIManager.getColor("Main.Dim"));

    this.add(homeBar);
    this.add(templateDisplayPanel);
    this.add(themeDisplayPanel);

    this.setTitle("Presentation Program Homescreen ");
    this.setMinimumSize(new Dimension(1000, 700));
    this.setLocationRelativeTo(null);

    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.setResizable(true);
    this.setLocationRelativeTo(null);
    this.setVisible(true);

    instance = this;
  }

}
