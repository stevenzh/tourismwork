package com.opentravelsoft.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.security.core.GrantedAuthority;

/**
 * This class is used to represent available roles in the database.
 * 
 */
@Entity
@Table(name = "tbl_portal_role")
@NamedQueries({ @NamedQuery(name = "findRoleByName", query = "select r from PortalRole r where r.name = :name ") })
public class PortalRole extends BaseObject implements Serializable,
    GrantedAuthority {
  private static final long serialVersionUID = 3690197650654049848L;
  private Long id;
  private String name;
  private String description;

  /**
   * Default constructor - creates a new instance with no values set.
   */
  public PortalRole() {
  }

  /**
   * Create a new instance and set the name.
   * 
   * @param name
   *          name of the role.
   */
  public PortalRole(final String name) {
    this.name = name;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public Long getId() {
    return id;
  }

  /**
   * @return the name property (getAuthority required by Acegi's
   *         GrantedAuthority interface)
   * @see org.springframework.security.GrantedAuthority#getAuthority()
   */
  @Transient
  public String getAuthority() {
    return getName();
  }

  @Column(length = 20)
  public String getName() {
    return this.name;
  }

  @Column(length = 64)
  public String getDescription() {
    return this.description;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * {@inheritDoc}
   */
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof PortalRole)) {
      return false;
    }

    final PortalRole role = (PortalRole) o;

    return !(name != null ? !name.equals(role.name) : role.name != null);
  }

  /**
   * {@inheritDoc}
   */
  public int hashCode() {
    return (name != null ? name.hashCode() : 0);
  }

  /**
   * {@inheritDoc}
   */
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
    .append(this.name).toString();
  }

  /**
   * {@inheritDoc}
   */
  public int compareTo(Object o) {
    return (equals(o) ? 0 : -1);
  }
}
