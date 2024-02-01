package com.scc210groupproject.testui.toolBars;

import javax.swing.JButton;

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
        DeleteSlideAction deleteAction = new DeleteSlideAction();
        JButton deleteButton = Utility.createSingleWidthButton(ResourceReference.DELETE_SLIDE, deleteAction);
        deleteAction.setButton(deleteButton);
        super.add(deleteButton);
    }
}
