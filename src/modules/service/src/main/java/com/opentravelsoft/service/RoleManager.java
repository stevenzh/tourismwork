package com.opentravelsoft.service;

import com.opentravelsoft.entity.PortalRole;

import java.util.List;

/**
 * Business Service Interface to handle communication between web and
 * persistence layer.
 *
 * @author <a href="mailto:dan@getrolling.com">Dan Kibler </a>
 */
public interface RoleManager extends GenericManager<PortalRole, Long> {
    /**
     * {@inheritDoc}
     */
    List getRoles(PortalRole role);

    /**
     * {@inheritDoc}
     */
    PortalRole getRole(String rolename);

    /**
     * {@inheritDoc}
     */
    PortalRole saveRole(PortalRole role);

    /**
     * {@inheritDoc}
     */
    void removeRole(String rolename);
}
