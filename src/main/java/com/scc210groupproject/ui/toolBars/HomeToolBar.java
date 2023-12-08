package com.scc210groupproject.ui.toolBars;

import com.scc210groupproject.action.NewSlideAction;
import com.scc210groupproject.ui.toolBars.helper.ResourceReference;
import com.scc210groupproject.ui.toolBars.helper.Utility;

public class HomeToolBar extends TemplateToolBar
{
    @Override
    protected void setInitialName()
    {
        super.setName("Home");
    }

    @Override
    protected void populate()
    {
        super.add(Utility.createSingleWidthButton(ResourceReference.CREATE_SLIDE, new NewSlideAction()));
    }
}
