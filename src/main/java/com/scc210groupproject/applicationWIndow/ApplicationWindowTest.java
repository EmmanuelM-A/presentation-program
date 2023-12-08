/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.scc210groupproject.applicationWIndow;

import com.scc210groupproject.applicationWIndow.toolBars.FileToolBar;
import com.scc210groupproject.applicationWIndow.toolBars.HomeToolBar;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author madukaag
 */
public class ApplicationWindowTest extends JFrame {
    private final Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

    private JPanel applicationWindowPanel;
    private JTabbedPane menuBarTabs;
    private JButton newBtn, openBtn, saveBtn, btn1, btn2, btn3;

    private JPanel contextMenu, mainDisplay, slideManager;

    private FileToolBar fileToolBar;

    private HomeToolBar homeToolBar;

    /**
     * Creates new form NewJFrame
     */
    public ApplicationWindowTest() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        applicationWindowPanel = new JPanel();
        menuBarTabs = new JTabbedPane();
        fileToolBar = new FileToolBar();
        newBtn = new JButton();
        openBtn = new JButton();
        saveBtn = new JButton();
        homeToolBar = new HomeToolBar();
        btn1 = new JButton();
        btn2 = new JButton();
        btn3 = new JButton();
        /*insertToolBar = new javax.swing.JToolBar();
        viewToolBar = new javax.swing.JToolBar();
        shareToolBar = new javax.swing.JToolBar();
        aboutToolBar = new javax.swing.JToolBar();*/
        contextMenu = new JPanel();
        mainDisplay = new JPanel();
        slideManager = new JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        menuBarTabs.setBackground(new java.awt.Color(255, 255, 255));
        menuBarTabs.setPreferredSize(new java.awt.Dimension(149, 121));

        fileToolBar.setRollover(true);

        newBtn.setText("NEW");
        newBtn.setFocusable(false);
        newBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        newBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        newBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newBtnActionPerformed(evt);
            }
        });
        fileToolBar.add(newBtn);

        openBtn.setText("OPEN");
        openBtn.setFocusable(false);
        openBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        openBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        fileToolBar.add(openBtn);

        saveBtn.setText("SAVE");
        saveBtn.setFocusable(false);
        saveBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        saveBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        fileToolBar.add(saveBtn);

        menuBarTabs.addTab("File", fileToolBar);

        homeToolBar.setRollover(true);

        btn1.setText("Btn1");
        btn1.setFocusable(false);
        btn1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        homeToolBar.add(btn1);

        btn2.setText("Btn2");
        btn2.setFocusable(false);
        btn2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        homeToolBar.add(btn2);

        btn3.setText("Btn3");
        btn3.setFocusable(false);
        btn3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        homeToolBar.add(btn3);

        menuBarTabs.addTab("Home", homeToolBar);

        /*insertToolBar.setBackground(new java.awt.Color(255, 255, 255));
        insertToolBar.setRollover(true);
        menuBarTabs.addTab("Insert", insertToolBar);

        viewToolBar.setRollover(true);
        menuBarTabs.addTab("View", viewToolBar);

        shareToolBar.setRollover(true);
        menuBarTabs.addTab("Share", shareToolBar);

        aboutToolBar.setRollover(true);
        menuBarTabs.addTab("About", aboutToolBar);*/

        contextMenu.setBackground(new java.awt.Color(102, 153, 255));

        javax.swing.GroupLayout contextMenuLayout = new javax.swing.GroupLayout(contextMenu);
        contextMenu.setLayout(contextMenuLayout);
        contextMenuLayout.setHorizontalGroup(
            contextMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 193, Short.MAX_VALUE)
        );
        contextMenuLayout.setVerticalGroup(
            contextMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        mainDisplay.setBackground(new java.awt.Color(255, 102, 102));

        javax.swing.GroupLayout mainDisplayLayout = new javax.swing.GroupLayout(mainDisplay);
        mainDisplay.setLayout(mainDisplayLayout);
        mainDisplayLayout.setHorizontalGroup(
            mainDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        mainDisplayLayout.setVerticalGroup(
            mainDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 396, Short.MAX_VALUE)
        );

        slideManager.setBackground(new java.awt.Color(255, 153, 51));

        javax.swing.GroupLayout slideManagerLayout = new javax.swing.GroupLayout(slideManager);
        slideManager.setLayout(slideManagerLayout);
        slideManagerLayout.setHorizontalGroup(
            slideManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        slideManagerLayout.setVerticalGroup(
            slideManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 145, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(applicationWindowPanel);
        applicationWindowPanel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(slideManager, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(contextMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mainDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(menuBarTabs, javax.swing.GroupLayout.PREFERRED_SIZE, 760, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(6, 6, 6))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(menuBarTabs, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(mainDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(contextMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(slideManager, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(applicationWindowPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void newBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ApplicationWindowTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ApplicationWindowTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ApplicationWindowTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ApplicationWindowTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ApplicationWindowTest().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    /*private javax.swing.JToolBar aboutToolBar;
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn3;
    private javax.swing.JPanel contextMenu;
    private javax.swing.JToolBar fileToolBar;
    private javax.swing.JToolBar homeToolBar;
    private javax.swing.JToolBar insertToolBar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel mainDisplay;
    private javax.swing.JTabbedPane menuBarTabs;
    private javax.swing.JButton newBtn;
    private javax.swing.JButton openBtn;
    private javax.swing.JButton saveBtn;
    private javax.swing.JToolBar shareToolBar;
    private javax.swing.JPanel slideManager;
    private javax.swing.JToolBar viewToolBar;*/
    // End of variables declaration//GEN-END:variables
}