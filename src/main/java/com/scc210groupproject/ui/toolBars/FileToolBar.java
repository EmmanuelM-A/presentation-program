package com.scc210groupproject.ui.toolBars;

import com.scc210groupproject.ui.toolBars.helper.ResourceReference;
import com.scc210groupproject.ui.toolBars.helper.Utility;

public class FileToolBar extends TemplateToolBar
{
    @Override
    protected void setInitialName()
    {
        super.setName("File");
    }

    @Override
    protected void populate()
    {
        super.add(Utility.createSingleWidthButton(ResourceReference.NEW_FILE, null));
        super.add(Utility.createSingleWidthButton(ResourceReference.OPEN_FILE, null));
        super.add(Utility.createSingleWidthButton(ResourceReference.SAVE_FILE, null));
    }
}
