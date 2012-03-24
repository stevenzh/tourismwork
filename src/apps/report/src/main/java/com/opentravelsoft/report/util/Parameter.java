package com.opentravelsoft.report.util;

import java.io.Serializable;

public class Parameter implements Serializable {
  private static final long serialVersionUID = 2510796338142608593L;

  private Integer id;

  private String name;

  private String type;

  private String data;

  private String description;

  private boolean required;

  public Parameter() {
  }

  public String getName() {
    return name;
  }

  public String getType() {
    return type;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isRequired() {
    return required;
  }

  public void setRequired(boolean required) {
    this.required = required;
  }

}
