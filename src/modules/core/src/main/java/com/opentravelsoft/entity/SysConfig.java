package com.opentravelsoft.entity;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.opentravelsoft.model.BaseObject;

/**
 * 系统设置参数
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:32 $
 */
public class SysConfig extends BaseObject {
  private static final long serialVersionUID = 5032068897606000462L;

  private long id;

  private String type;

  private String name;

  private String category;

  private String storeRange;

  private String value;

  public SysConfig() {
    name = "";
    category = "";
    storeRange = "";
  }

  public SysConfig(long configId) {
    this.id = configId;
  }

  public long getId() {
    return this.id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getStoreRange() {
    return this.storeRange;
  }

  public void setStoreRange(String storeRange) {
    this.storeRange = storeRange;
  }

  public String getValue() {
    return this.value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
    .append("Id", this.getId())
    .append("Category", this.getCategory())
    .append("Name", this.getName())
    .append("Value", this.getValue()).toString();
}

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof SysConfig)) {
      return false;
    }

    final SysConfig t = (SysConfig) o;
    return id == t.getId();
  }

  @Override
  public int hashCode() {
    int result;
    result = (int)id;
    result = 29 * result + (name != null ? name.hashCode() : 0);
    result = 29 * result + (category != null ? category.hashCode() : 0);
    return result;
  }

}
