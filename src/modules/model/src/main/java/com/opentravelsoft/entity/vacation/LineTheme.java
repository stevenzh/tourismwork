package com.opentravelsoft.entity.vacation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 线路主题
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 */
@Entity
@Table(name = "tbl_line_theme")
public class LineTheme implements java.io.Serializable {

  /** 分类代码 */
  private String code;
  /** 分类中文名 */
  private String cnName;
  /** 分类英文名 */
  private String enName;
  /** 图文件地址 */
  private String mapFile;

  public LineTheme() {
  }

  public LineTheme(String code) {
    this.code = code;
  }

  @Id
  @Column(name = "CODE", unique = true, nullable = false, length = 8)
  public String getCode() {
    return this.code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  @Column(name = "CN_NAME", length = 40)
  public String getCnName() {
    return this.cnName;
  }

  public void setCnName(String cnName) {
    this.cnName = cnName;
  }

  @Column(name = "EN_NAME", length = 80)
  public String getEnName() {
    return this.enName;
  }

  public void setEnName(String enName) {
    this.enName = enName;
  }

  @Column(name = "MAP_FILE", length = 50)
  public String getMapFile() {
    return this.mapFile;
  }

  public void setMapFile(String mapFile) {
    this.mapFile = mapFile;
  }

}
