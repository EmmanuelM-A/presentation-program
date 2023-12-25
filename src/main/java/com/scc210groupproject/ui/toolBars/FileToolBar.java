package com.scc210groupproject.ui.toolBars;

import com.scc210groupproject.action.NewFileAction;
import com.scc210groupproject.action.OpenPlainAction;
import com.scc210groupproject.action.OpenCompressAction;
import com.scc210groupproject.action.SaveCompressAction;
import com.scc210groupproject.action.SavePlainAction;
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
        super.add(Utility.createSingleWidthButton(ResourceReference.NEW_FILE, new NewFileAction()));
        super.add(Utility.createSingleWidthButton(ResourceReference.OPEN_PLAIN, new OpenPlainAction()));
        super.add(Utility.createSingleWidthButton(ResourceReference.OPEN_COMPRESS, new OpenCompressAction()));
        super.add(Utility.createSingleWidthButton(ResourceReference.SAVE_PLAIN, new SavePlainAction()));
        super.add(Utility.createSingleWidthButton(ResourceReference.SAVE_COMPRESS, new SaveCompressAction()));
    }
}
