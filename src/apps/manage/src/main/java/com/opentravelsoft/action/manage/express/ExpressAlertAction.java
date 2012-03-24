package com.opentravelsoft.action.manage.express;

import java.util.ArrayList;
import java.util.List;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.entity.Express;
import com.opentravelsoft.service.MyPageService;
import com.opentravelsoft.workflow.WorkFlowKeyParams;

/**
 * 任务列表
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:24:16 $
 */
public class ExpressAlertAction extends ManageAction
{

    private static final long serialVersionUID = 1L;

    private MyPageService myPageService;

    /** 配送任务列表 */
    private List<Express> arrangeExpressList = new ArrayList<Express>();

    /** 审核材料任务列表（签单部） */
    private List<Express> checkExpressList = new ArrayList<Express>();

    /** 缴纳团款（签单部） */
    private List<Express> accountExpressList = new ArrayList<Express>();

    /** 审核材料任务列表（材料审核部） */
    private List<Express> examineExpressList = new ArrayList<Express>();

    /** 是否需要再次配送列表 */
    private List<Express> expressAgainList = new ArrayList<Express>();

    public String execute() throws Exception
    {
        arrangeExpressList = myPageService
                .roGetExpressTask(WorkFlowKeyParams.EXPRESS_TASK_ASSIGN);
        checkExpressList = myPageService
                .roGetExpressTask(WorkFlowKeyParams.EXPRESS_TASK_CHECK);
        accountExpressList = myPageService
                .roGetExpressTask(WorkFlowKeyParams.EXPRESS_TASK_ACCOUNT);
        examineExpressList = myPageService
                .roGetExpressTask(WorkFlowKeyParams.EXPRESS_TASK_EXAMINE);
        expressAgainList = myPageService
                .roGetExpressTask(WorkFlowKeyParams.EXPRESS_TASK_AGAIN);

        return SUCCESS;
    }

    public MyPageService getMyPageService()
    {
        return myPageService;
    }

    public List<Express> getCheckExpressList()
    {
        return checkExpressList;
    }

    public List<Express> getArrangeExpressList()
    {
        return arrangeExpressList;
    }

    public void setMyPageService(MyPageService myPageService)
    {
        this.myPageService = myPageService;
    }

    public List<Express> getExpressAgainList()
    {
        return expressAgainList;
    }

    public List<Express> getExamineExpressList()
    {
        return examineExpressList;
    }

    public List<Express> getAccountExpressList()
    {
        return accountExpressList;
    }

}
