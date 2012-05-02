package com.opentravelsoft.entity;

public class LineVisaId implements java.io.Serializable
{

    private static final long serialVersionUID = 7028831864835953916L;

    private String lineNo;

    private String recNo;

    public LineVisaId()
    {
    }

    public LineVisaId(String lineNo, String recNo)
    {
        this.lineNo = lineNo;
        this.recNo = recNo;
    }

    public String getLineNo()
    {
        return this.lineNo;
    }

    public void setLineNo(String lineNo)
    {
        this.lineNo = lineNo;
    }

    public String getRecNo()
    {
        return this.recNo;
    }

    public void setRecNo(String recNo)
    {
        this.recNo = recNo;
    }

    public boolean equals(Object other)
    {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof LineVisaId))
            return false;
        LineVisaId castOther = (LineVisaId) other;

        return ((this.getLineNo() == castOther.getLineNo()) || (this
                .getLineNo() != null
                && castOther.getLineNo() != null && this.getLineNo().equals(
                castOther.getLineNo())))
                && ((this.getRecNo() == castOther.getRecNo()) || (this
                        .getRecNo() != null
                        && castOther.getRecNo() != null && this.getRecNo()
                        .equals(castOther.getRecNo())));
    }

    public int hashCode()
    {
        int result = 17;

        result = 37 * result
                + (getLineNo() == null ? 0 : this.getLineNo().hashCode());
        result = 37 * result
                + (getRecNo() == null ? 0 : this.getRecNo().hashCode());
        return result;
    }

}
