package com.opentravelsoft.entity;

/**
 * 
 * @author zhangst
 * 
 */
public class Permission implements java.io.Serializable
{

    private static final long serialVersionUID = 2965607205737704598L;

    private int permissionId;

    private String permissionCode;

    private String permissionKey;

    private String permissionName;

    public Permission()
    {
    }

    public int getPermissionId()
    {
        return this.permissionId;
    }

    public void setPermissionId(int permissionId)
    {
        this.permissionId = permissionId;
    }

    public String getPermissionCode()
    {
        return this.permissionCode;
    }

    public void setPermissionCode(String permissionCode)
    {
        this.permissionCode = permissionCode;
    }

    public String getPermissionKey()
    {
        return this.permissionKey;
    }

    public void setPermissionKey(String permissionKey)
    {
        this.permissionKey = permissionKey;
    }

    public String getPermissionName()
    {
        return this.permissionName;
    }

    public void setPermissionName(String permissionName)
    {
        this.permissionName = permissionName;
    }

}
