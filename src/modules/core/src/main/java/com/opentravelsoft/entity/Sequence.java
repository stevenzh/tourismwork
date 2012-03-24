package com.opentravelsoft.entity;

/**
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:32 $
 */
public class Sequence implements java.io.Serializable {
  private static final long serialVersionUID = 5826990362652489874L;

  private SequenceId id;

  private String regPlcd;

  private Long cptno;

  public Sequence() {
  }

  public Sequence(SequenceId id) {
    this.id = id;
  }

  public SequenceId getId() {
    return this.id;
  }

  public void setId(SequenceId id) {
    this.id = id;
  }

  public String getRegPlcd() {
    return this.regPlcd;
  }

  public void setRegPlcd(String regPlcd) {
    this.regPlcd = regPlcd;
  }

  public Long getCptno() {
    return this.cptno;
  }

  public void setCptno(Long cptno) {
    this.cptno = cptno;
  }

}
