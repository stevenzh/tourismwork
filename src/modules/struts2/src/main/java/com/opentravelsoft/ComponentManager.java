package com.opentravelsoft;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opentravelsoft.common.LookAndFeelParams;
import com.opentravelsoft.util.StringUtil;

public class ComponentManager extends ActionSupport
{
    private static final long serialVersionUID = 4352320689579637860L;

    public String getTheme()
    {
        String skin = (String) ActionContext.getContext().getApplication().get(
                LookAndFeelParams.SKIN_THEME);
        if (!StringUtil.hasLength(skin))
            skin = "simple";

        return skin;
    }
}
