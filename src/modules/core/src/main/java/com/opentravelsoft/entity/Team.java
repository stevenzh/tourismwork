package com.opentravelsoft.entity;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.opentravelsoft.model.BaseObject;

/**
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.2 $ $Date: 2009/04/10 07:47:28 $
 */
public class Team extends BaseObject {

  private static final long serialVersionUID = -5274339129650665170L;

  private long teamId;

  protected String id;

  private String name;

  private int type;

  private boolean active;

  private String description;

  private Boolean private_;

  protected Team parent;

  public Team() {
    this.active = true;
  }

  public Team(Integer teamId2) {
    this();
    this.teamId = teamId2;
  }

  public long getTeamId() {
    return teamId;
  }

  public void setTeamId(long teamId) {
    this.teamId = teamId;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public int getType() {
    return type;
  }

  public void setType(int orgType) {
    this.type = orgType;
  }

  public boolean isActive() {
    return this.active;
  }

  public void setActive(boolean deleted) {
    this.active = deleted;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Boolean getPrivate_() {
    return this.private_;
  }

  public void setPrivate_(Boolean private_) {
    this.private_ = private_;
  }

  public Team getParent() {
    return parent;
  }

  public void setParent(Team parent) {
    this.parent = parent;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Team)) {
      return false;
    }

    final Team t = (Team) o;
    return teamId == t.getTeamId();
  }

  @Override
  public int hashCode() {
    int result;
    result = (int)teamId;
    result = 29 * result + (name != null ? name.hashCode() : 0);
    result = 29 * result + (description != null ? description.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("Id", this.getTeamId())
        .append("Name", this.getName())
        .append("Description", this.getDescription()).toString();
  }
}
