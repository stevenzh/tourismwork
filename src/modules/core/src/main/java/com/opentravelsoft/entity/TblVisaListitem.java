package com.opentravelsoft.entity;

public class TblVisaListitem implements java.io.Serializable
{

    private static final long serialVersionUID = 9028345538444905894L;

    private TblVisaListitemId id;

    private int checkIn;

    private String note;

    public TblVisaListitem()
    {
    }

    public TblVisaListitem(TblVisaListitemId id, int checkIn)
    {
        this.id = id;
        this.checkIn = checkIn;
    }

    public TblVisaListitem(TblVisaListitemId id, int checkIn, String note)
    {
        this.id = id;
        this.checkIn = checkIn;
        this.note = note;
    }

    public TblVisaListitemId getId()
    {
        return this.id;
    }

    public void setId(TblVisaListitemId id)
    {
        this.id = id;
    }

    public int getCheckIn()
    {
        return this.checkIn;
    }

    public void setCheckIn(int checkIn)
    {
        this.checkIn = checkIn;
    }

    public String getNote()
    {
        return this.note;
    }

    public void setNote(String note)
    {
        this.note = note;
    }

}
