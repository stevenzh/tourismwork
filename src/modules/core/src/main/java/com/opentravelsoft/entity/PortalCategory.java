package com.opentravelsoft.entity;

import com.opentravelsoft.model.BaseObject;

/**
 * 
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:32 $
 */
public class PortalCategory extends BaseObject {
  private static final long serialVersionUID = 1783523957523738545L;

  private String level;

  private String code;

  private String cnName;

  private String upCode;

  private String nextKey;

  private String fullName;

  private String mapFile1;

  public PortalCategory() {
  }

  public String getCode() {
    return this.code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getCnName() {
    return this.cnName;
  }

  public void setCnName(String cnName) {
    this.cnName = cnName;
  }

  public String getUpCode() {
    return this.upCode;
  }

  public void setUpCode(String upCode) {
    this.upCode = upCode;
  }

  public String getNextKey() {
    return this.nextKey;
  }

  public void setNextKey(String nextKey) {
    this.nextKey = nextKey;
  }

  public String getFullName() {
    return this.fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getMapFile1() {
    return this.mapFile1;
  }

  public void setMapFile1(String mapFile1) {
    this.mapFile1 = mapFile1;
  }

  public String getLevel() {
    return level;
  }

  public void setLevel(String level) {
    this.level = level;
  }

  @Override
  public String toString() {
    return null;
  }

  @Override
  public boolean equals(Object o) {
    return false;
  }

  @Override
  public int hashCode() {
    return 0;
  }

}
