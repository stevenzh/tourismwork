package com.opentravelsoft.entity;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.opentravelsoft.model.BaseObject;

/**
 * 产品分类
 * 
 * @author zhangst
 * 
 */
public class Category extends BaseObject {

  private static final long serialVersionUID = 2476134054380727845L;

  private long catId;

  private Category parent;

  private String code;

  private String name;

  private String keywords;

  private String description;

  private String metaKeywords;

  private String metaDesc;

  private Set<Category> children = new HashSet<Category>(0);

  public Category() {
  }

  public Category(String catName) {
    this.name = catName;
  }

  public long getCatId() {
    return this.catId;
  }

  public void setCatId(long catId) {
    this.catId = catId;
  }

  public String getKeywords() {
    return this.keywords;
  }

  public void setKeywords(String keywords) {
    this.keywords = keywords;
  }

  public String getMetaKeywords() {
    return this.metaKeywords;
  }

  public void setMetaKeywords(String metaKeywords) {
    this.metaKeywords = metaKeywords;
  }

  public String getMetaDesc() {
    return this.metaDesc;
  }

  public void setMetaDesc(String metaDesc) {
    this.metaDesc = metaDesc;
  }

  public Category getParent() {
    return parent;
  }

  public void setParent(Category parent) {
    this.parent = parent;
  }

  public Set<Category> getChildren() {
    return children;
  }

  public void setChildren(Set<Category> children) {
    this.children = children;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Category)) {
      return false;
    }

    final Category category = (Category) o;
    return this.hashCode() == category.hashCode();
  }

  @Override
  public int hashCode() {
    int result;
    result = (name != null ? name.hashCode() : 0);
    result = result * 29 + (int) catId;
    result = result * 29 + (description != null ? description.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("Id", this.catId).append("code", this.code)
        .append("name", this.name).toString();
  }

}
