package com.opentravelsoft.entity;

public class TblVisaListId implements java.io.Serializable
{

    private static final long serialVersionUID = -4474100189386013975L;

    private String nmno;

    private int applyId;

    public TblVisaListId()
    {
    }

    public TblVisaListId(String nmno, int applyId)
    {
        this.nmno = nmno;
        this.applyId = applyId;
    }

    public String getNmno()
    {
        return this.nmno;
    }

    public void setNmno(String nmno)
    {
        this.nmno = nmno;
    }

    public int getApplyId()
    {
        return this.applyId;
    }

    public void setApplyId(int applyId)
    {
        this.applyId = applyId;
    }

    public boolean equals(Object other)
    {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof TblVisaListId))
            return false;
        TblVisaListId castOther = (TblVisaListId) other;

        return ((this.getNmno() == castOther.getNmno()) || (this.getNmno() != null
                && castOther.getNmno() != null && this.getNmno().equals(
                castOther.getNmno())))
                && (this.getApplyId() == castOther.getApplyId());
    }

    public int hashCode()
    {
        int result = 17;

        result = 37 * result
                + (getNmno() == null ? 0 : this.getNmno().hashCode());
        result = 37 * result + this.getApplyId();
        return result;
    }

}
