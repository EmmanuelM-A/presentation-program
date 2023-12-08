package com.scc210groupproject.ui.toolBars;

import com.scc210groupproject.ui.toolBars.helper.ResourceReference;
import com.scc210groupproject.ui.toolBars.helper.Utility;

public class InsertToolBar extends TemplateToolBar
{
    @Override
    protected void setInitialName()
    {
        super.setName("Insert");
    }

    @Override
    protected void populate()
    {
        super.add(Utility.createSingleWidthButton(ResourceReference.ADD_TEXT, null));
        super.add(Utility.createSingleWidthButton(ResourceReference.ADD_IMAGE, null));
        super.add(Utility.createSingleWidthButton(ResourceReference.ADD_VIDEO, null));
    }
}
