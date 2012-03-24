package com.opentravelsoft.entity;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 航空公司
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:32 $
 */
public class Airways extends Company {
  private static final long serialVersionUID = -6356146173650163949L;

  /** 编码 */
  private String code;

  private String name;

  private String contact;

  private boolean isActive;

  public Airways() {
    isActive = true;
  }

  public String getFullname() {
    return this.name;
  }

  public void setFullname(String fullname) {
    this.name = fullname;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getContact() {
    return contact;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }

  public boolean getIsActive() {
    return isActive;
  }

  public void setIsActive(boolean isActive) {
    this.isActive = isActive;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Airways)) {
      return false;
    }

    final Airways airways = (Airways) o;

    return !(code != null ? !code.equals(airways.getCode())
        : airways.getCode() != null);
  }

  @Override
  public int hashCode() {
    int result;
    result = (code != null ? code.hashCode() : 0);
    result = 29 * result + (name != null ? name.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("code", this.code)
        .append("name", this.name)
        .append("contact", this.contact)
        .append("isActive", this.isActive)
        .toString();
  }
}
