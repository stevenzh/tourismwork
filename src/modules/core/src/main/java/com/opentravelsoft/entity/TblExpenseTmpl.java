package com.opentravelsoft.entity;

public class TblExpenseTmpl implements java.io.Serializable {

  private static final long serialVersionUID = 8525470570020944642L;

  private int id;

  private int number;

  private String item;

  private String content;

  private String include;

  public TblExpenseTmpl() {
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getNumber() {
    return this.number;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  public String getItem() {
    return this.item;
  }

  public void setItem(String item) {
    this.item = item;
  }

  public String getContent() {
    return this.content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getInclude() {
    return this.include;
  }

  public void setInclude(String include) {
    this.include = include;
  }

}
