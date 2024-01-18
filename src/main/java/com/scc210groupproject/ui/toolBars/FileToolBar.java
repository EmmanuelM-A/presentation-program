package com.scc210groupproject.ui.toolBars;

import com.scc210groupproject.action.NewFileAction;
import com.scc210groupproject.action.OpenAction;
import com.scc210groupproject.action.SaveAction;
import com.scc210groupproject.ui.toolBars.helper.ResourceReference;
import com.scc210groupproject.ui.toolBars.helper.Utility;

public class FileToolBar extends TemplateToolBar {
    @Override
    protected void setInitialName() {
        super.setName("File");
    }

    @Override
    protected void populate() {
        super.add(Utility.createSingleWidthButton(ResourceReference.NEW_FILE, new NewFileAction()));
        super.add(Utility.createSingleWidthButton(ResourceReference.OPEN, new OpenAction()));
        super.add(Utility.createSingleWidthButton(ResourceReference.SAVE, new SaveAction()));
    }
}
