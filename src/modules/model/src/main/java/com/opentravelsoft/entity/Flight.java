package com.opentravelsoft.entity;

import java.util.Date;


/**
 * 航班
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:34 $
 */
public class Flight extends BaseObject {
  private static final long serialVersionUID = 1520768776098497089L;

  /** 编码 */
  private String recNo;

  /** 航班号 */
  private String flightNo;

  /** 出发国家 */
  private String leavingCountry;

  /** 出发城市 */
  private String leavingFrom;

  /** 出发机场 */
  private Airport leavingAirport;

  /** 抵达国家 */
  private String goingCountry;

  /** 抵达城市 */
  private String goingTo;

  /** 抵达机场 */
  private Airport goingAirport;

  /** 出发时间 */
  private String departureTime;

  /** 到达时间 */
  private String arrivalTime;

  /** 途中时间 */
  private int totalTravelTime;

  private String weekbit;

  /** 操作人 */
  private String opUser;

  /** 操作时间 */
  private Date opDate;

  private String fromCity1;

  private String toCity1;

  private String fromTime;

  private String toTime;

  private Integer nights;

  private Integer stopStation;

  private String airModel;

  private Date startDate;

  private Date endDate;

  private Airways airways;

  /** 座位数 */
  private Integer airSeat;
  /** 删除标记 */
  private Byte isDelete;

  public String getFromCity1() {
    return this.fromCity1;
  }

  public void setFromCity1(String fromCity1) {
    this.fromCity1 = fromCity1;
  }

  public String getToCity1() {
    return this.toCity1;
  }

  public void setToCity1(String toCity1) {
    this.toCity1 = toCity1;
  }

  public String getFromTime() {
    return this.fromTime;
  }

  public void setFromTime(String fromTime) {
    this.fromTime = fromTime;
  }

  public String getToTime() {
    return this.toTime;
  }

  public void setToTime(String toTime) {
    this.toTime = toTime;
  }

  public Integer getNights() {
    return this.nights;
  }

  public void setNights(Integer nights) {
    this.nights = nights;
  }

  public Integer getStopStation() {
    return this.stopStation;
  }

  public void setStopStation(Integer stopStation) {
    this.stopStation = stopStation;
  }

  public String getAirModel() {
    return this.airModel;
  }

  public void setAirModel(String airModel) {
    this.airModel = airModel;
  }

  public Date getStartDate() {
    return this.startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return this.endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public Flight() {
    recNo = "";
  }

  public String getFlightNo() {
    return flightNo;
  }

  public void setFlightNo(String flightNo) {
    this.flightNo = flightNo;
  }

  public String getLeavingCountry() {
    return leavingCountry;
  }

  public void setLeavingCountry(String leavingCountry) {
    this.leavingCountry = leavingCountry;
  }

  public Airport getLeavingAirport() {
    return leavingAirport;
  }

  public void setLeavingAirport(Airport leavingAirport) {
    this.leavingAirport = leavingAirport;
  }

  public String getGoingCountry() {
    return goingCountry;
  }

  public void setGoingCountry(String goingCountry) {
    this.goingCountry = goingCountry;
  }

  public Airport getGoingAirport() {
    return goingAirport;
  }

  public void setGoingAirport(Airport goingAirport) {
    this.goingAirport = goingAirport;
  }

  public Date getOpDate() {
    return opDate;
  }

  public void setOpDate(Date opDate) {
    this.opDate = opDate;
  }

  public String getOpUser() {
    return opUser;
  }

  public void setOpUser(String opUser) {
    this.opUser = opUser;
  }

  public String getLeavingFrom() {
    return leavingFrom;
  }

  public void setLeavingFrom(String leavingFrom) {
    this.leavingFrom = leavingFrom;
  }

  public String getGoingTo() {
    return goingTo;
  }

  public void setGoingTo(String goingTo) {
    this.goingTo = goingTo;
  }

  public String getDepartureTime() {
    return departureTime;
  }

  public void setDepartureTime(String departureTime) {
    this.departureTime = departureTime;
  }

  public String getArrivalTime() {
    return arrivalTime;
  }

  public void setArrivalTime(String arrivalTime) {
    this.arrivalTime = arrivalTime;
  }

  public int getTotalTravelTime() {
    return totalTravelTime;
  }

  public void setTotalTravelTime(int totalTravelTime) {
    this.totalTravelTime = totalTravelTime;
  }

  public String getWeekbit() {
    return weekbit;
  }

  public void setWeekbit(String weekbit) {
    this.weekbit = weekbit;
  }

  public String getRecNo() {
    return recNo;
  }

  public void setRecNo(String recNo) {
    this.recNo = recNo;
  }

  public Airways getAirways() {
    return airways;
  }

  public void setAirways(Airways airways) {
    this.airways = airways;
  }

  public Integer getAirSeat() {
    return airSeat;
  }

  public void setAirSeat(Integer airSeat) {
    this.airSeat = airSeat;
  }

  public Byte getIsDelete() {
    return isDelete;
  }

  public void setIsDelete(Byte isDelete) {
    this.isDelete = isDelete;
  }

  @Override
  public String toString() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean equals(Object o) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public int hashCode() {
    // TODO Auto-generated method stub
    return 0;
  }

}
