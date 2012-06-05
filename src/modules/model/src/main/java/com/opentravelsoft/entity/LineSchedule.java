package com.opentravelsoft.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_line_schedule")
public class LineSchedule implements java.io.Serializable {

  private LineScheduleId id;
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
  /** 日程 */
  private String program;
  /** 住宿 */
  private String quarter;
  private String outCity;
  private String toCity;

  public LineSchedule() {
    id = new LineScheduleId();
  }

  public LineSchedule(LineScheduleId id) {
    this.id = id;
  }

  @EmbeddedId
  @AttributeOverrides({
      @AttributeOverride(name = "lineNo", column = @Column(name = "LINE_NO", nullable = false, length = 8)),
      @AttributeOverride(name = "day", column = @Column(name = "DAY", nullable = false)) })
  public LineScheduleId getId() {
    return this.id;
  }

  public void setId(LineScheduleId id) {
    this.id = id;
  }

  @Column(name = "TRAFFIC", length = 200)
  public String getTraffic() {
    return this.traffic;
  }

  public void setTraffic(String traffic) {
    this.traffic = traffic;
  }

  @Column(name = "TRAFFIC1", length = 100)
  public String getTraffic1() {
    return this.traffic1;
  }

  public void setTraffic1(String traffic1) {
    this.traffic1 = traffic1;
  }

  @Column(name = "BREAKFAST", length = 50)
  public String getBreakfast() {
    return this.breakfast;
  }

  public void setBreakfast(String breakfast) {
    this.breakfast = breakfast;
  }

  @Column(name = "LUNCH", length = 50)
  public String getLunch() {
    return this.lunch;
  }

  public void setLunch(String lunch) {
    this.lunch = lunch;
  }

  @Column(name = "SUPPER", length = 50)
  public String getSupper() {
    return this.supper;
  }

  public void setSupper(String supper) {
    this.supper = supper;
  }

  @Column(name = "PROGRAM", length = 3000)
  public String getProgram() {
    return this.program;
  }

  public void setProgram(String program) {
    this.program = program;
  }

  @Column(name = "QUARTER", length = 100)
  public String getQuarter() {
    return this.quarter;
  }

  public void setQuarter(String quarter) {
    this.quarter = quarter;
  }

  @Column(name = "OUT_CITY", length = 10)
  public String getOutCity() {
    return this.outCity;
  }

  public void setOutCity(String outCity) {
    this.outCity = outCity;
  }

  @Column(name = "TO_CITY", length = 10)
  public String getToCity() {
    return this.toCity;
  }

  public void setToCity(String toCity) {
    this.toCity = toCity;
  }

}
