package com.scc210groupproject.applicationWIndow.toolBars;

import com.scc210groupproject.applicationWIndow.toolBarOptions.ToolBarOptions;

import javax.swing.*;

public class InsertToolBar extends JToolBar {
    private final JButton addTextBtn, addImageBtn, addVideoBtn;

    public InsertToolBar() {
        addTextBtn = new JButton(ToolBarOptions.ADD_TEXT.getIcon());
        addImageBtn = new JButton(ToolBarOptions.ADD_IMAGE.getIcon());
        addVideoBtn = new JButton(ToolBarOptions.ADD_VIDEO.getIcon());

        this.setRollover(true);

        addTextBtn.setText(ToolBarOptions.ADD_TEXT.getTitle());
        addTextBtn.setFocusable(false);
        addTextBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        addTextBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.add(addTextBtn);

        addImageBtn.setText(ToolBarOptions.ADD_IMAGE.getTitle());
        addImageBtn.setFocusable(false);
        addImageBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        addImageBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.add(addImageBtn);

        addVideoBtn.setText(ToolBarOptions.ADD_VIDEO.getTitle());
        addVideoBtn.setFocusable(false);
        addVideoBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        addVideoBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.add(addVideoBtn);
    }
}
