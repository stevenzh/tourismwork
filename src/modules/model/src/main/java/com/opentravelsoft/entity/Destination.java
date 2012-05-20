package com.opentravelsoft.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_destination")
public class Destination implements java.io.Serializable {
  /** 目的地ID */
  private Integer destId;
  private Destination parent;
  /** 目的地编号 */
  private String code;
  /** 中文名称 */
  private String cnName;
  /** 中文全称 */
  private String fullName;
  /** 级别 */
  private Character level;
  private Character nextKey;
  /** 关键字 内部搜索 */
  private String keywords;
  /** 描述 */
  private String destDesc;
  /** 网页 META */
  private String metaKeywords;
  /** 网页 META */
  private String metaDesc;
  /** 区域 1：国外 2：国内 */
  private Character classType;
  private Set<Destination> children = new HashSet<Destination>(0);

  public Destination() {
    code = "";
    cnName = "";
    fullName = "";
    imagePath = "";
  }

  public Destination(Integer destId) {
    this();
    this.destId = destId;
  }

  public Destination(long destId, String code, String name) {
    this.code = code;
    this.cnName = name;
  }

  public Destination(String code) {
    this.code = code;
  }

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "DEST_ID", unique = true, nullable = false)
  public Integer getDestId() {
    return this.destId;
  }

  public void setDestId(Integer destId) {
    this.destId = destId;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "PARENT_ID")
  public Destination getParent() {
    return parent;
  }

  public void setParent(Destination parent) {
    this.parent = parent;
  }

  @Column(name = "CODE", nullable = false, length = 6)
  public String getCode() {
    return this.code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  @Column(name = "CN_NAME", length = 30)
  public String getCnName() {
    return this.cnName;
  }

  public void setCnName(String cnName) {
    this.cnName = cnName;
  }

  @Column(name = "FULL_NAME", length = 120)
  public String getFullName() {
    return this.fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  @Column(name = "LEVEL", length = 1)
  public Character getLevel() {
    return this.level;
  }

  public void setLevel(Character level) {
    this.level = level;
  }

  @Column(name = "NEXT_KEY", length = 1)
  public Character getNextKey() {
    return this.nextKey;
  }

  public void setNextKey(Character nextKey) {
    this.nextKey = nextKey;
  }

  @Column(name = "KEYWORDS", length = 50)
  public String getKeywords() {
    return this.keywords;
  }

  public void setKeywords(String keywords) {
    this.keywords = keywords;
  }

  @Column(name = "DEST_DESC", length = 50)
  public String getDestDesc() {
    return this.destDesc;
  }

  public void setDestDesc(String destDesc) {
    this.destDesc = destDesc;
  }

  @Column(name = "META_KEYWORDS", length = 50)
  public String getMetaKeywords() {
    return this.metaKeywords;
  }

  public void setMetaKeywords(String metaKeywords) {
    this.metaKeywords = metaKeywords;
  }

  @Column(name = "META_DESC", length = 50)
  public String getMetaDesc() {
    return this.metaDesc;
  }

  public void setMetaDesc(String metaDesc) {
    this.metaDesc = metaDesc;
  }

  @Column(name = "CLASS_TYPE", length = 1)
  public Character getClassType() {
    return this.classType;
  }

  public void setClassType(Character classType) {
    this.classType = classType;
  }

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "destination")
  public Set<Destination> getChildren() {
    return children;
  }

  public void setChildren(Set<Destination> children) {
    this.children = children;
  }

  private String imagePath;

  public String getImagePath() {
    return imagePath;
  }

  public void setImagePath(String imagePath) {
    this.imagePath = imagePath;
  }

  public void addChildren(Destination child) {
    children.add(child);
    child.setParent(this);
  }

}
