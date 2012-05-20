package com.opentravelsoft.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class LineScheduleId implements java.io.Serializable {

  private String lineNo;
  private int day;

  public LineScheduleId() {
  }

  public LineScheduleId(String lineNo, int day) {
    this.lineNo = lineNo;
    this.day = day;
  }

  @Column(name = "LINE_NO", nullable = false, length = 8)
  public String getLineNo() {
    return this.lineNo;
  }

  public void setLineNo(String lineNo) {
    this.lineNo = lineNo;
  }

  @Column(name = "DAY", nullable = false)
  public int getDay() {
    return this.day;
  }

  public void setDay(int day) {
    this.day = day;
  }

  public boolean equals(Object other) {
    if ((this == other))
      return true;
    if ((other == null))
      return false;
    if (!(other instanceof LineScheduleId))
      return false;
    LineScheduleId castOther = (LineScheduleId) other;

    return ((this.getLineNo() == castOther.getLineNo()) || (this.getLineNo() != null
        && castOther.getLineNo() != null && this.getLineNo().equals(
        castOther.getLineNo())))
        && (this.getDay() == castOther.getDay());
  }

  public int hashCode() {
    int result = 17;

    result = 37 * result
        + (getLineNo() == null ? 0 : this.getLineNo().hashCode());
    result = 37 * result + this.getDay();
    return result;
  }

}
