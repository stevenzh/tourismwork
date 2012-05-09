package com.opentravelsoft.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


@Entity
@Table(name = "tbl_shortcut")
public class Shortcut extends BaseObject implements java.io.Serializable
{

    private static final long serialVersionUID = 6129655387608594110L;

    private Long id;

    private String moduleName;

    private String displayName;

    private String relativePath;

    private String imageName;

    private boolean shortcutEnabled;

    private short shortcutOrder;
    
    private String roles;

    public Shortcut()
    {
        shortcutEnabled = false;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    public Long getId()
    {
        return this.id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    @Column(name = "MODULE_NAME", nullable = false, length = 25)
    public String getModuleName()
    {
        return this.moduleName;
    }

    public void setModuleName(String moduleName)
    {
        this.moduleName = moduleName;
    }

    @Column(name = "DISPLAY_NAME", nullable = false, length = 150)
    public String getDisplayName()
    {
        return this.displayName;
    }

    public void setDisplayName(String displayName)
    {
        this.displayName = displayName;
    }

    @Column(name = "RELATIVE_PATH", nullable = false, length = 120)
    public String getRelativePath()
    {
        return this.relativePath;
    }

    public void setRelativePath(String relativePath)
    {
        this.relativePath = relativePath;
    }

    @Column(name = "IMAGE_NAME", nullable = false, length = 50)
    public String getImageName()
    {
        return this.imageName;
    }

    public void setImageName(String imageName)
    {
        this.imageName = imageName;
    }

    @Column(name = "SHORTCUT_ENABLED", nullable = false)
    public boolean isShortcutEnabled()
    {
        return this.shortcutEnabled;
    }

    public void setShortcutEnabled(boolean shortcutEnabled)
    {
        this.shortcutEnabled = shortcutEnabled;
    }

    @Column(name = "SHORTCUT_ORDER", nullable = false)
    public short getShortcutOrder()
    {
        return this.shortcutOrder;
    }

    public void setShortcutOrder(short shortcutOrder)
    {
        this.shortcutOrder = shortcutOrder;
    }

    @Column(name = "ROLES")
    public String getRoles() {
      return roles;
    }

    public void setRoles(String roles) {
      this.roles = roles;
    }

    /**
     * {@inheritDoc}
     */
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof Shortcut)) {
        return false;
      }

      final Shortcut role = (Shortcut) o;
      return this.hashCode() == role.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    public int hashCode() {
      int result = 0;
      result = (moduleName != null ? moduleName.hashCode() : 0);
      result = 29 * result + (displayName != null ? displayName.hashCode() : 0);
      return result;
    }

    /**
     * {@inheritDoc}
     */
    public String toString() {
      return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
      .append("moduleName", this.getModuleName())
      .append("displayName", this.displayName).toString();
    }

}
