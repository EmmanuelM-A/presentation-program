package com.scc210groupproject.testui.toolBars;

import com.scc210groupproject.testui.toolBars.helper.ResourceReference;
import com.scc210groupproject.testui.toolBars.helper.Utility;

public class ShareToolBar extends TemplateToolBar
{
    @Override
    protected void setInitialName() {
        super.setName("Share");
    }

    @Override
    protected void populate() {
        super.add(Utility.createSingleWidthButton(ResourceReference.EXPORT, null));
        super.add(Utility.createSingleWidthButton(ResourceReference.IMPORT, null));
        super.add(Utility.createSingleWidthButton(ResourceReference.SHARE, null));
    }
}
