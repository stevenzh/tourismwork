package com.opentravelsoft.entity;

public class LineDistrictId implements java.io.Serializable
{
    private static final long serialVersionUID = -5517581619212261013L;

    private String lineNo;

    private String districtNo;

    public LineDistrictId()
    {
    }

    public LineDistrictId(String lineNo, String districtNo)
    {
        this.lineNo = lineNo;
        this.districtNo = districtNo;
    }

    public String getLineNo()
    {
        return this.lineNo;
    }

    public void setLineNo(String lineNo)
    {
        this.lineNo = lineNo;
    }

    public String getDistrictNo()
    {
        return this.districtNo;
    }

    public void setDistrictNo(String districtNo)
    {
        this.districtNo = districtNo;
    }

    public boolean equals(Object other)
    {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof LineDistrictId))
            return false;
        LineDistrictId castOther = (LineDistrictId) other;

        return ((this.getLineNo() == castOther.getLineNo()) || (this
                .getLineNo() != null
                && castOther.getLineNo() != null && this.getLineNo().equals(
                castOther.getLineNo())))
                && ((this.getDistrictNo() == castOther.getDistrictNo()) || (this
                        .getDistrictNo() != null
                        && castOther.getDistrictNo() != null && this
                        .getDistrictNo().equals(castOther.getDistrictNo())));
    }

    public int hashCode()
    {
        int result = 17;

        result = 37 * result
                + (getLineNo() == null ? 0 : this.getLineNo().hashCode());
        result = 37
                * result
                + (getDistrictNo() == null ? 0 : this.getDistrictNo()
                        .hashCode());
        return result;
    }
}
