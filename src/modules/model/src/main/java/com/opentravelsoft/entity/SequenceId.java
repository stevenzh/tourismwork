package com.opentravelsoft.entity;

/**
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:32 $
 */
public class SequenceId implements java.io.Serializable
{
    private static final long serialVersionUID = -1788635624789101761L;

    private char rectype;

    private String year;

    private String month;

    public SequenceId()
    {
    }

    public SequenceId(char rectype, String year, String month)
    {
        this.rectype = rectype;
        this.year = year;
        this.month = month;
    }

    public char getRectype()
    {
        return this.rectype;
    }

    public void setRectype(char rectype)
    {
        this.rectype = rectype;
    }

    public String getYear()
    {
        return this.year;
    }

    public void setYear(String year)
    {
        this.year = year;
    }

    public String getMonth()
    {
        return this.month;
    }

    public void setMonth(String month)
    {
        this.month = month;
    }

    public boolean equals(Object other)
    {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof SequenceId))
            return false;
        SequenceId castOther = (SequenceId) other;

        return (this.getRectype() == castOther.getRectype())
                && ((this.getYear() == castOther.getYear()) || (this.getYear() != null
                        && castOther.getYear() != null && this.getYear()
                        .equals(castOther.getYear())))
                && ((this.getMonth() == castOther.getMonth()) || (this
                        .getMonth() != null
                        && castOther.getMonth() != null && this.getMonth()
                        .equals(castOther.getMonth())));
    }

    public int hashCode()
    {
        int result = 17;

        result = 37 * result + this.getRectype();
        result = 37 * result
                + (getYear() == null ? 0 : this.getYear().hashCode());
        result = 37 * result
                + (getMonth() == null ? 0 : this.getMonth().hashCode());
        return result;
    }

}
