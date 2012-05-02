package com.opentravelsoft.entity.product;

import java.util.Date;

/**
 * 提醒
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:33 $
 */
public class Remind
{
    /** 内容 */
    private String context;

    /** 订单号 */
    private String bookingNo;

    /** 付款申请书 */
    private int outcomeId;

    /** 团号 */
    private String tourNo;

    /** 配送单ID */
    private String expressId;

    // -------------------------------------------------------------------------

    /** 线路号 */
    private String lineNo;

    /** 线路名 */
    private String lineName;

    /** 出发日期 */
    private Date outDate;

    /** 客户名称 */
    private String customerName;

    /** 未审核客户数 */
    private long count;

    /** 人数 */
    private int pax;

    /** 费用 */
    private double expense;

    public String getBookingNo()
    {
        return bookingNo;
    }

    public void setBookingNo(String bookingNo)
    {
        this.bookingNo = bookingNo;
    }

    public String getRouteNo()
    {
        return lineNo;
    }

    public void setRouteNo(String lineNo)
    {
        this.lineNo = lineNo;
    }

    public Date getOutDate()
    {
        return outDate;
    }

    public void setOutDate(Date outDate)
    {
        this.outDate = outDate;
    }

    public String getRouteName()
    {
        return lineName;
    }

    public void setRouteName(String routeName)
    {
        this.lineName = routeName;
    }

    public String getContext()
    {
        return context;
    }

    public void setContext(String context)
    {
        this.context = context;
    }

    public String getCustomerName()
    {
        return customerName;
    }

    public void setCustomerName(String customerName)
    {
        this.customerName = customerName;
    }

    public int getPax()
    {
        return pax;
    }

    public void setPax(int pax)
    {
        this.pax = pax;
    }

    public String getTourNo()
    {
        return tourNo;
    }

    public void setTourNo(String tourNo)
    {
        this.tourNo = tourNo;
    }

    public double getExpense()
    {
        return expense;
    }

    public void setExpense(double expense)
    {
        this.expense = expense;
    }

    public int getOutcomeId()
    {
        return outcomeId;
    }

    public void setOutcomeId(int outcomeId)
    {
        this.outcomeId = outcomeId;
    }

    public long getCount()
    {
        return count;
    }

    public void setCount(long count)
    {
        this.count = count;
    }

    public String getExpressId()
    {
        return expressId;
    }

    public void setExpressId(String expressId)
    {
        this.expressId = expressId;
    }
}
