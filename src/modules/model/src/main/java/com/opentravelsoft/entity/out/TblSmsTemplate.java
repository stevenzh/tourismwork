package com.opentravelsoft.entity.out;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_sms_template")
public class TblSmsTemplate implements java.io.Serializable {

  private int id;
  private String contents;

  public TblSmsTemplate() {
  }

  public TblSmsTemplate(int id, String contents) {
    this.id = id;
    this.contents = contents;
  }

  @Id
  @Column(name = "ID", unique = true, nullable = false)
  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Column(name = "CONTENTS", nullable = false, length = 200)
  public String getContents() {
    return this.contents;
  }

  public void setContents(String contents) {
    this.contents = contents;
  }

}
