package com.opentravelsoft.entity;

import java.util.Set;
import java.util.TreeSet;


/**
 * 目的地
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:32 $
 */
public class Destination extends BaseObject implements Comparable<Destination> {
  private static final long serialVersionUID = -6861307843240952269L;

  /** 目的地ID */
  private long destId;

  /** 目的地编号 */
  private String code;

  /** 中文名称 */
  private String cnName;

  /** 区域 1：国外 2：国内 */
  private String classType;

  private String imagePath;

  /** 中文全称 */
  private String fullName;

  /** 关键字 内部搜索 */
  private String keywords;

  /** 描述 */
  private String destDesc;

  /** 网页 META */
  private String metaKeywords;

  /** 网页 META */
  private String metaDesc;

  /** 级别 */
  private String level;

  private Destination parent = null;

  private Set<Destination> children = new TreeSet<Destination>();

  public Destination() {
    code = "";
    cnName = "";
    fullName = "";
    imagePath = "";
  }

  public Destination(long destId) {
    this();
    this.destId = destId;
  }

  public Destination(long destId, String code, String name) {
    this.code = code;
    this.cnName = name;
  }

  public long getDestId() {
    return destId;
  }

  public void setDestId(long destId) {
    this.destId = destId;
  }

  public String getLevel() {
    return this.level;
  }

  public void setLevel(String level) {
    this.level = level;
  }

  public String getKeywords() {
    return keywords;
  }

  public void setKeywords(String keywords) {
    this.keywords = keywords;
  }

  public String getDestDesc() {
    return destDesc;
  }

  public void setDestDesc(String destDesc) {
    this.destDesc = destDesc;
  }

  public String getMetaKeywords() {
    return metaKeywords;
  }

  public void setMetaKeywords(String metaKeywords) {
    this.metaKeywords = metaKeywords;
  }

  public String getMetaDesc() {
    return metaDesc;
  }

  public void setMetaDesc(String metaDesc) {
    this.metaDesc = metaDesc;
  }

  public Destination getParent() {
    return parent;
  }

  public void setParent(Destination parent) {
    this.parent = parent;
  }

  public Set<Destination> getChildren() {
    return children;
  }

  public void setChildren(Set<Destination> children) {
    this.children = children;
  }

  public void addChildren(Destination child) {
    children.add(child);
    child.setParent(this);
  }

  public String getClassType() {
    return classType;
  }

  public void setClassType(String classType) {
    this.classType = classType;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getCnName() {
    return cnName;
  }

  public void setCnName(String name) {
    this.cnName = name;
  }

  public String getImagePath() {
    return imagePath;
  }

  public void setImagePath(String imagePath) {
    this.imagePath = imagePath;
  }

  public int compareTo(Destination o) {
    if (this.destId == o.getDestId())
      return 0;
    else
      return 1;
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
