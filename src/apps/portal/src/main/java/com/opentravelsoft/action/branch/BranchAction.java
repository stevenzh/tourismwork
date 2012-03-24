package com.opentravelsoft.action.branch;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opentravelsoft.entity.Branch;
import com.opentravelsoft.service.portal.BranchService;
import com.opentravelsoft.webapp.action.PortalAction;

/**
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:42 $
 */
public class BranchAction extends PortalAction
{
    private static final long serialVersionUID = 1868553276414702703L;

    protected static final Log logger = LogFactory.getLog(BranchAction.class);

    private BranchService branchService;

    private Branch branch;

    public void setBranchService(BranchService branchService)
    {
        this.branchService = branchService;
    }

    public String execute() throws Exception
    {

        int branchId = 0;
        branch = branchService.roGetBranch(branchId);
        if (null == branch)
        {
            addActionError("门店不存在");
        }

        return SUCCESS;
    }

    public Branch getBranch()
    {
        return branch;
    }

}
