package com.opentravelsoft.workflow;

public interface WorkFlowKeyParams
{

    /** 工作流任务分配角色（不是用户） */
    public static final String WORKFLOW_ACTOR = "WORKFLOW_ACTOR";

    /** 订单ID */
    public static final String WORKFLOW_ORDER_ID = "WORKFLOW_ORDER_ID";

    // -------------------------------------------------------------------------

    /** 配送单ID */
    public static final String WORKFLOW_EXPRESS_ID = "WORKFLOW_EXPRESS_ID";

    /** 配送单包含团款 */
    public static final String WORKFLOW_EXPRESS_IN_ACCOUNT = "WORKFLOW_EXPRESS_IN_ACCOUNT";

    /** 配送单包含签证资料 */
    public static final String WORKFLOW_EXPRESS_IN_VISA = "WORKFLOW_EXPRESS_IN_VISA";

    // ------------------------------------------------------------------------
    // 订单任务

    /** 订单审核任务 */
    public static final String ORDER_TASK_CONFIRM = "ORDER_CONFIRM";

    /** 签单付款任务 */
    public static final String ORDER_AGREEON = "ORDER_AGREEON";

    // -------------------------------------------------------------------------
    // 配送任务

    /** 安排配送任务 */
    public static final String EXPRESS_TASK_ASSIGN = "assignExpress";

    /** 签单部审核配送任务 */
    public static final String EXPRESS_TASK_CHECK = "renderPayments";

    /** 签单部缴款任务 */
    public static final String EXPRESS_TASK_ACCOUNT = "renderAccount";

    /** 审核部审核材料任务 */
    public static final String EXPRESS_TASK_EXAMINE = "examineData";

    /** 材料审核失败再次配送任务 */
    public static final String EXPRESS_TASK_AGAIN = "expressAgain";

    // -----------------------------------------------------------------------------
    // 
    /** 计划号 */
    public static final String WORKFLOW_PLAN_NO = "WORKFLOW_PLAN_NO";

    /** 团号 */
    public static final String WORKFLOW_TOUR_NO = "WORKFLOW_TOUR_NO";

    /** 订单 */
    public static final String TOUR_TASK_AUTO_BOOK = "AUTO_BOOK";

    /** 成团任务 */
    public static final String TOUR_TASK_GROUPTOUR = "groupTour";

    /** 制作单团核算任务 */
    public static final String CALCULATION_TASK_MAKE = "CALCULATION_MAKE";

    /** 计调审核单团核算 */
    public static final String CALCULATION_TASK_AUDITING = "CALCULATION_AUDITING";

    /** 财务审核单团核算 */
    public static final String FINANCE_CALCULATION_TASK_AUDITING = "FINANCE_CALCULATION_AUDITING";

    // -------------------------------------------------------------------------
    // 付款申请
    /** 是否需要计调审核 */
    public static final String OP_AUDITING = "OP_AUDITING";

    /** 是否需要安排领队 */
    public static final String TOUR_ARRANGE_LEADER = "TOUR_ARRANGE_LEADER";

    /** 是否机票配送 */
    public static final String AIR_TICKET_DISTRIBUTION = "AIR_TICKET_DISTRIBUTION";

    /** 付款申请书号 */
    public static final String PAY_REQUISITION_ID = "PAY_REQUISITION_ID";

    /** 计调审核付款申请 */
    public static final String CALCULATION_REQUISITION_TASK_AUDITING = "CALCULATION_REQUISITION_TASK_AUDITING";

    /** 财务审核付款申请 */
    public static final String FINANCE_REQUISITION_TASK_AUDITING = "FINANCE_REQUISITION_TASK_AUDITING";

    /** 机票配送　 */
    public static final String AIR_TICKET_TASK_DISTRIBUTION = "AIR_TICKET_TASK_DISTRIBUTION";

    /** 安排领队任务 */
    public static final String TOUR_TASK_ARRANGE_LEADER = "arrangeLeader";

    /** 制作出团通知 */
    public static final String TOUR_TASK_OUTTOURNOTIFY = "outTourNotify";

}
