package com.opentravelsoft.action.region;

import java.util.List;

import com.opentravelsoft.entity.Destination;
import com.opentravelsoft.service.resource.DestinationService;
import com.opentravelsoft.webapp.action.PortalAction;

public class CategoryListAction extends PortalAction
{

    private static final long serialVersionUID = -6317494752381582812L;

    private DestinationService categoryService;

    private List<Destination> lds;

    public void setCategoryService(DestinationService categoryService)
    {
        this.categoryService = categoryService;
    }

    @Override
    public String execute() throws Exception
    {
        lds = categoryService.getCategirys();
        return super.execute();
    }

    public List<Destination> getLds()
    {
        return lds;
    }

}
