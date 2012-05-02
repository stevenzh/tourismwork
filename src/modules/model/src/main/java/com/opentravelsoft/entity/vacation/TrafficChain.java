package com.opentravelsoft.entity.vacation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 交通链
 */
@Entity
@Table(name = "tbl_traffic_chain")
public class TrafficChain implements java.io.Serializable {

  private String id;
  private String lineNo;
  private String flightGroupId;
  private int trafficType;

  public TrafficChain() {
  }

  public TrafficChain(String id, String lineNo, String flightGroupId,
      int trafficType) {
    this.id = id;
    this.lineNo = lineNo;
    this.flightGroupId = flightGroupId;
    this.trafficType = trafficType;
  }

  @Id
  @Column(name = "ID", unique = true, nullable = false, length = 36)
  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Column(name = "LineNo", nullable = false, length = 10)
  public String getLineNo() {
    return this.lineNo;
  }

  public void setLineNo(String lineNo) {
    this.lineNo = lineNo;
  }

  @Column(name = "FlightGroupID", nullable = false, length = 36)
  public String getFlightGroupId() {
    return this.flightGroupId;
  }

  public void setFlightGroupId(String flightGroupId) {
    this.flightGroupId = flightGroupId;
  }

  @Column(name = "TrafficType", nullable = false)
  public int getTrafficType() {
    return this.trafficType;
  }

  public void setTrafficType(int trafficType) {
    this.trafficType = trafficType;
  }

}
