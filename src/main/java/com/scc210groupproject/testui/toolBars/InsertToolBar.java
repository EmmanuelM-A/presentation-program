package com.scc210groupproject.testui.toolBars;

import com.scc210groupproject.action.DeleteFirstAction;
import com.scc210groupproject.action.NewBoxAction;
import com.scc210groupproject.testui.toolBars.helper.ResourceReference;
import com.scc210groupproject.testui.toolBars.helper.Utility;

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
        super.add(Utility.createSingleWidthButton(ResourceReference.ADD_BOX, new NewBoxAction()));
        super.add(Utility.createSingleWidthButton(ResourceReference.DELETE_FIRST, new DeleteFirstAction()));
    }
}
