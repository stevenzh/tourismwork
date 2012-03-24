package com.opentravelsoft.entity;

/**
 * 旅游行程
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:32 $
 */
public class LineSchedule implements java.io.Serializable
{

    private static final long serialVersionUID = -6036563166038951217L;

    /** 线路编号 */
    private String lineNo;

    private LineScheduleId id;

    /** 日程 */
    private String program;

    /** 交通说明 */
    private String traffic;

    /** 交通 */
    private String traffic1;

    /** 早餐 ◆: 包含正餐 ◇: 包含飞机餐 ×: 不包含 */
    private String breakfast;

    /** 午餐 ◆: 包含正餐 ◇: 包含飞机餐 ×: 不包含 */
    private String lunch;

    /** 晚餐 ◆: 包含正餐 ◇: 包含飞机餐 ×: 不包含 */
    private String supper;

    /** 住宿 */
    private String quarter;

    public LineSchedule()
    {
        this.id = new LineScheduleId();
    }

    public LineSchedule(LineScheduleId id)
    {
        this();
        this.id = id;
    }

    public LineScheduleId getId()
    {
        return this.id;
    }

    public void setId(LineScheduleId id)
    {
        this.id = id;
    }

    public int getDay()
    {
        return getId().getDay();
    }

    public void setDay(int day)
    {
        this.getId().setDay(day);
    }

    public String getRouteNo()
    {
        return lineNo;
    }

    public void setRouteNo(String lineNo)
    {
        this.lineNo = lineNo;
    }

    public String getProgram()
    {
        return program;
    }

    public void setProgram(String program)
    {
        this.program = program;
    }

    public String getTraffic()
    {
        return traffic;
    }

    public void setTraffic(String traffic)
    {
        this.traffic = traffic;
    }

    public String getTraffic1()
    {
        return traffic1;
    }

    public void setTraffic1(String traffic1)
    {
        this.traffic1 = traffic1;
    }

    public String getBreakfast()
    {
        return breakfast;
    }

    public void setBreakfast(String breakfast)
    {
        this.breakfast = breakfast;
    }

    public String getLunch()
    {
        return lunch;
    }

    public void setLunch(String lunch)
    {
        this.lunch = lunch;
    }

    public String getSupper()
    {
        return supper;
    }

    public void setSupper(String supper)
    {
        this.supper = supper;
    }

    public String getQuarter()
    {
        return quarter;
    }

    public void setQuarter(String quarter)
    {
        this.quarter = quarter;
    }

}
