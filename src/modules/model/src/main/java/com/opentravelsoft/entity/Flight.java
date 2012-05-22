package com.opentravelsoft.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "tbl_flight")
public class Flight implements java.io.Serializable {
  /** 编码 */
  private String recNo;

  private Airways airways;
  private String fromCity;
  private String toCity;
  /** 出发机场 */
  private Airport leavingAirport;
  /** 抵达机场 */
  private Airport goingAirport;
  /** 航班号 */
  private String flightNo;
  private String weekbit;
  private String fromTime;
  private String toTime;
  private String nights;
  private Integer stopStation;
  private String airModel;

  /** 座位数 */
  private Integer airSeat;
  private Date startDate;
  private Date endDate;
  /** 删除标记 */
  private Byte isDelete;

  public Flight() {
  }

  @Id
  @Column(name = "REC_NO", unique = true, nullable = false, length = 10)
  public String getRecNo() {
    return this.recNo;
  }

  public void setRecNo(String recNo) {
    this.recNo = recNo;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "AIR_CODE", nullable = false)
  public Airways getAirways() {
    return airways;
  }

  public void setAirways(Airways airways) {
    this.airways = airways;
  }

  @Column(name = "FROM_CITY", length = 6)
  public String getFromCity() {
    return this.fromCity;
  }

  public void setFromCity(String fromCity) {
    this.fromCity = fromCity;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "FROM_AIRPORT", nullable = false)
  public Airport getLeavingAirport() {
    return leavingAirport;
  }

  public void setLeavingAirport(Airport leavingAirport) {
    this.leavingAirport = leavingAirport;
  }

  @Column(name = "TO_CITY", length = 6)
  public String getToCity() {
    return this.toCity;
  }

  public void setToCity(String toCity) {
    this.toCity = toCity;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "TO_AIRPORT", nullable = false)
  public Airport getGoingAirport() {
    return goingAirport;
  }

  public void setGoingAirport(Airport goingAirport) {
    this.goingAirport = goingAirport;
  }

  @Column(name = "FLIGHT_NO", nullable = false, length = 6)
  public String getFlightNo() {
    return this.flightNo;
  }

  public void setFlightNo(String flightNo) {
    this.flightNo = flightNo;
  }

  @Column(name = "WEEKBIT", length = 20)
  public String getWeekbit() {
    return this.weekbit;
  }

  public void setWeekbit(String weekbit) {
    this.weekbit = weekbit;
  }

  @Column(name = "FROM_TIME", length = 12)
  public String getFromTime() {
    return this.fromTime;
  }

  public void setFromTime(String fromTime) {
    this.fromTime = fromTime;
  }

  @Column(name = "TO_TIME", length = 12)
  public String getToTime() {
    return this.toTime;
  }

  public void setToTime(String toTime) {
    this.toTime = toTime;
  }

  @Column(name = "NIGHTS", length = 12)
  public String getNights() {
    return this.nights;
  }

  public void setNights(String nights) {
    this.nights = nights;
  }

  @Column(name = "STOP_STATION")
  public Integer getStopStation() {
    return this.stopStation;
  }

  public void setStopStation(Integer stopStation) {
    this.stopStation = stopStation;
  }

  @Column(name = "AIR_MODEL", length = 12)
  public String getAirModel() {
    return this.airModel;
  }

  public void setAirModel(String airModel) {
    this.airModel = airModel;
  }

  @Column(name = "AIR_SEAT")
  public Integer getAirSeat() {
    return this.airSeat;
  }

  public void setAirSeat(Integer airSeat) {
    this.airSeat = airSeat;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "START_DATE", length = 19)
  public Date getStartDate() {
    return this.startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "END_DATE", length = 19)
  public Date getEndDate() {
    return this.endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  @Column(name = "IS_DELETE")
  public Byte getIsDelete() {
    return this.isDelete;
  }

  public void setIsDelete(Byte isDelete) {
    this.isDelete = isDelete;
  }

  /** 出发国家 */
  private String leavingCountry;

  /** 出发城市 */
  private String leavingFrom;

  /** 抵达国家 */
  private String goingCountry;

  /** 抵达城市 */
  private String goingTo;

  /** 出发时间 */
  private String departureTime;

  /** 到达时间 */
  private String arrivalTime;

  /** 途中时间 */
  private int totalTravelTime;

  /** 操作人 */
  private String opUser;

  /** 操作时间 */
  private Date opDate;

  private String fromCity1;

  private String toCity1;

  @Transient
  public String getFromCity1() {
    return this.fromCity1;
  }

  public void setFromCity1(String fromCity1) {
    this.fromCity1 = fromCity1;
  }

  @Transient
  public String getToCity1() {
    return this.toCity1;
  }

  public void setToCity1(String toCity1) {
    this.toCity1 = toCity1;
  }

  @Transient
  public String getLeavingCountry() {
    return leavingCountry;
  }

  public void setLeavingCountry(String leavingCountry) {
    this.leavingCountry = leavingCountry;
  }

  @Transient
  public String getGoingCountry() {
    return goingCountry;
  }

  public void setGoingCountry(String goingCountry) {
    this.goingCountry = goingCountry;
  }

  @Transient
  public Date getOpDate() {
    return opDate;
  }

  public void setOpDate(Date opDate) {
    this.opDate = opDate;
  }

  @Transient
  public String getOpUser() {
    return opUser;
  }

  public void setOpUser(String opUser) {
    this.opUser = opUser;
  }

  @Transient
  public String getLeavingFrom() {
    return leavingFrom;
  }

  public void setLeavingFrom(String leavingFrom) {
    this.leavingFrom = leavingFrom;
  }

  @Transient
  public String getGoingTo() {
    return goingTo;
  }

  public void setGoingTo(String goingTo) {
    this.goingTo = goingTo;
  }

  @Transient
  public String getDepartureTime() {
    return departureTime;
  }

  public void setDepartureTime(String departureTime) {
    this.departureTime = departureTime;
  }

  @Transient
  public String getArrivalTime() {
    return arrivalTime;
  }

  public void setArrivalTime(String arrivalTime) {
    this.arrivalTime = arrivalTime;
  }

  @Transient
  public int getTotalTravelTime() {
    return totalTravelTime;
  }

  public void setTotalTravelTime(int totalTravelTime) {
    this.totalTravelTime = totalTravelTime;
  }

}
