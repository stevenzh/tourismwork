package com.opentravelsoft.entity;

import java.io.Serializable;

/**
 * 线路交通
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.2 $ $Date: 2009/04/10 07:47:28 $
 */
public class LineTraffic implements Serializable {
  private static final long serialVersionUID = 2113028702425886495L;

  /** 自动编号 */
  private long id;

  /** 线路号 */
  private String lineNo;

  /** 天数 */
  private int day;

  /** 起始城市 */
  private String fromCity;

  /** 抵达城市 */
  private String toCity;

  /** 时间 */
  private String travelTime;

  /** 交通方式 */
  private String traffic;

  private Integer step;

  public LineTraffic() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getLineNo() {
    return lineNo;
  }

  public void setLineNo(String lineNo) {
    this.lineNo = lineNo;
  }

  public int getDay() {
    return day;
  }

  public void setDay(int day) {
    this.day = day;
  }

  public String getFromCity() {
    return fromCity;
  }

  public void setFromCity(String fromCity) {
    this.fromCity = fromCity;
  }

  public String getToCity() {
    return toCity;
  }

  public void setToCity(String toCity) {
    this.toCity = toCity;
  }

  public String getTravelTime() {
    return travelTime;
  }

  public void setTravelTime(String travelTime) {
    this.travelTime = travelTime;
  }

  public String getTraffic() {
    return traffic;
  }

  public void setTraffic(String traffic) {
    this.traffic = traffic;
  }

  public Integer getStep() {
    return step;
  }

  public void setStep(Integer step) {
    this.step = step;
  }

}
