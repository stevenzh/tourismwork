package com.opentravelsoft.entity;

public class TblVisaList implements java.io.Serializable
{

    private static final long serialVersionUID = 2014903164457955830L;

    private TblVisaListId id;

    public TblVisaList()
    {
    }

    public TblVisaList(TblVisaListId id)
    {
        this.id = id;
    }

    public TblVisaListId getId()
    {
        return this.id;
    }

    public void setId(TblVisaListId id)
    {
        this.id = id;
    }

}
