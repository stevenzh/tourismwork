package com.opentravelsoft.providers;

import com.opentravelsoft.model.PortalRole;


/**
 * Role Data Access Object (DAO) interface.
 *
 */
public interface MemberRoleDao extends GenericDao<PortalRole, Long> {
    /**
     * Gets role information based on rolename
     * @param rolename the rolename
     * @return populated role object
     */
    PortalRole getRoleByName(String rolename);

    /**
     * Removes a role from the database by name
     * @param rolename the role's rolename
     */
    void removeRole(String rolename);
}
