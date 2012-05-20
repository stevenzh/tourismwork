package com.opentravelsoft.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_line_traffic")
public class LineTraffic implements java.io.Serializable {
  /** 自动编号 */
  private Integer id;
  /** 线路号 */
  private String lineNo;
  /** 天数 */
  private Integer day;
  /** 起始城市 */
  private String fromCity;
  /** 抵达城市 */
  private String toCity;
  /** 时间 */
  private String travelTime;
  /** 交通方式 */
  private Character traffic;
  private Integer step;

  public LineTraffic() {
  }

  public LineTraffic(String lineNo, Integer day, String fromCity,
      String toCity, String travelTime, Character traffic, Integer step) {
    this.lineNo = lineNo;
    this.day = day;
    this.fromCity = fromCity;
    this.toCity = toCity;
    this.travelTime = travelTime;
    this.traffic = traffic;
    this.step = step;
  }

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "ID", unique = true, nullable = false)
  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Column(name = "LINE_NO", length = 50)
  public String getLineNo() {
    return this.lineNo;
  }

  public void setLineNo(String lineNo) {
    this.lineNo = lineNo;
  }

  @Column(name = "DAY")
  public Integer getDay() {
    return this.day;
  }

  public void setDay(Integer day) {
    this.day = day;
  }

  @Column(name = "FROM_CITY", length = 50)
  public String getFromCity() {
    return this.fromCity;
  }

  public void setFromCity(String fromCity) {
    this.fromCity = fromCity;
  }

  @Column(name = "TO_CITY", length = 50)
  public String getToCity() {
    return this.toCity;
  }

  public void setToCity(String toCity) {
    this.toCity = toCity;
  }

  @Column(name = "TRAVEL_TIME", length = 50)
  public String getTravelTime() {
    return this.travelTime;
  }

  public void setTravelTime(String travelTime) {
    this.travelTime = travelTime;
  }

  @Column(name = "TRAFFIC", length = 1)
  public Character getTraffic() {
    return this.traffic;
  }

  public void setTraffic(Character traffic) {
    this.traffic = traffic;
  }

  @Column(name = "STEP")
  public Integer getStep() {
    return this.step;
  }

  public void setStep(Integer step) {
    this.step = step;
  }

}
