package com.opentravelsoft.entity;

import com.opentravelsoft.model.BaseObject;

/**
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:33 $
 */
public class ProductTmpl extends BaseObject {
  private static final long serialVersionUID = -2338663186387390196L;

  private long id;

  private Team team;

  private ProductItem item;

  private String type;

  private String subject;

  private String content;

  private Integer sortOrder;

  private String destCode;

  private Integer include;

  public long getId() {
    return this.id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String item) {
    this.subject = item;
  }

  public String getContent() {
    return this.content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Integer getSortOrder() {
    return sortOrder;
  }

  public void setSortOrder(Integer sortOrder) {
    this.sortOrder = sortOrder;
  }

  public Integer getInclude() {
    return include;
  }

  public void setInclude(Integer include) {
    this.include = include;
  }

  public ProductItem getItem() {
    return item;
  }

  public void setItem(ProductItem item) {
    this.item = item;
  }

  public Team getTeam() {
    return team;
  }

  public void setTeam(Team team) {
    this.team = team;
  }

  public String getDestCode() {
    return destCode;
  }

  public void setDestCode(String destCode) {
    this.destCode = destCode;
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
