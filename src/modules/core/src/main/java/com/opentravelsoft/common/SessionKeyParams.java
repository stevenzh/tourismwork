package com.opentravelsoft.common;

/**
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:59 $
 */
public interface SessionKeyParams
{
    /** 登陆用户信息 */
    public static final String EBIZ_USER = "EBIZ_USER";

    /** 当前线路 */
    public static final String EBIZ_CURRENT_ROUTE = "EBIZ_CURRENT_ROUTE";

    /** 当前订单 */
    public static final String EBIZ_CURRENT_BOOKING = "EBIZ_CURRENT_BOOKING";

    /** 当前付款单 */
    public static final String EBIZ_CURRENT_PAYMENT = "EBIZ_CURRENT_PAYMENT";

    /** 当前团 */
    public static final String EBIZ_CURRENT_TOUR = "EBIZ_CURRENT_TOUR";

    // -------------------------------------------------------------------------

    /** 查询条件-部门 */
    public static final String EBIZ_KEN_TEAM = "EBIZ_KEN_TEAM";

    /** 查询条件-专管员 */
    public static final String EBIZ_KEN_EMPLOYEE = "EBIZ_KEN_EMPLOYEE";

    /** 查询条件-出发时间 */
    public static final String EBIZ_KEN_OUTDATE_S = "EBIZ_KEN_OUTDATE_S";

    /** 查询条件-出发时间 */
    public static final String EBIZ_KEN_OUTDATE_E = "EBIZ_KEN_OUTDATE_E";
}
