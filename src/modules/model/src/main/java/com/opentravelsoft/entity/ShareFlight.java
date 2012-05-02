package com.opentravelsoft.entity;

import java.util.Date;

/**
 * 共享航班信息
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:32 $
 */
public class ShareFlight implements java.io.Serializable
{

    private static final long serialVersionUID = -4247242209825687032L;

    private int shareFlightId;

    private String flightNo;

    private String airwaysCode;

    private Date departureDate;

    private Integer seating;

    private Integer handle;

    private String note;

    private String airwaysName;

    public String getAirwaysName()
    {
        return airwaysName;
    }

    public void setAirwaysName(String airwaysName)
    {
        this.airwaysName = airwaysName;
    }

    public ShareFlight(int shareFlightId, String flightNo, Date departureDate)
    {
        this.shareFlightId = shareFlightId;
        this.flightNo = flightNo;
        this.departureDate = departureDate;
    }

    public int getShareFlightId()
    {
        return this.shareFlightId;
    }

    public void setShareFlightId(int shareFlightId)
    {
        this.shareFlightId = shareFlightId;
    }

    public String getFlightNo()
    {
        return this.flightNo;
    }

    public void setFlightNo(String flightNo)
    {
        this.flightNo = flightNo;
    }

    public String getAirwaysCode()
    {
        return this.airwaysCode;
    }

    public void setAirwaysCode(String airwaysCode)
    {
        this.airwaysCode = airwaysCode;
    }

    public Date getDepartureDate()
    {
        return this.departureDate;
    }

    public void setDepartureDate(Date departureDate)
    {
        this.departureDate = departureDate;
    }

    public Integer getSeating()
    {
        return this.seating;
    }

    public void setSeating(Integer seating)
    {
        this.seating = seating;
    }

    public Integer getHandle()
    {
        return this.handle;
    }

    public void setHandle(Integer handle)
    {
        this.handle = handle;
    }

    public String getNote()
    {
        return this.note;
    }

    public void setNote(String note)
    {
        this.note = note;
    }

    public ShareFlight()
    {
    }

}
