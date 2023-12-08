package com.scc210groupproject.ui.toolBars;

import javax.swing.BoxLayout;
import javax.swing.JToolBar;

public abstract class TemplateToolBar extends JToolBar
{
    public TemplateToolBar()
    {
        super();

        setInitialName();

        super.setRollover(true);
        super.setFloatable(false);
        super.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        populate();

        super.validate();
    }

    protected abstract void setInitialName();

    protected abstract void populate();
}
