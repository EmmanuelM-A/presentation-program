package com.scc210groupproject.testui.toolBars;

import com.scc210groupproject.action.DeleteSlideAction;
import com.scc210groupproject.action.NewSlideAction;
import com.scc210groupproject.testui.toolBars.helper.ResourceReference;
import com.scc210groupproject.testui.toolBars.helper.Utility;

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
        super.add(Utility.createSingleWidthButton(ResourceReference.DELETE_SLIDE, new DeleteSlideAction()));
    }
}
