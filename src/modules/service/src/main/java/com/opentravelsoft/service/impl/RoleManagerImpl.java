package com.opentravelsoft.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opentravelsoft.model.PortalRole;
import com.opentravelsoft.providers.MemberRoleDao;
import com.opentravelsoft.service.RoleManager;

/**
 * Implementation of RoleManager interface.
 * 
 * @author <a href="mailto:dan@getrolling.com">Dan Kibler</a>
 */
@Service("roleManager")
public class RoleManagerImpl extends GenericManagerImpl<PortalRole, Long>
        implements RoleManager
{
    MemberRoleDao roleDao;

    @Autowired
    public RoleManagerImpl(MemberRoleDao roleDao)
    {
        super(roleDao);
        this.roleDao = roleDao;
    }

    /**
     * {@inheritDoc}
     */
    public List<PortalRole> getRoles(PortalRole role)
    {
        return dao.getAll();
    }

    /**
     * {@inheritDoc}
     */
    public PortalRole getRole(String rolename)
    {
        return roleDao.getRoleByName(rolename);
    }

    /**
     * {@inheritDoc}
     */
    public PortalRole saveRole(PortalRole role)
    {
        return dao.save(role);
    }

    /**
     * {@inheritDoc}
     */
    public void removeRole(String rolename)
    {
        roleDao.removeRole(rolename);
    }
}