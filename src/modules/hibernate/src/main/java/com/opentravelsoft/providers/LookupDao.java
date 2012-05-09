package com.opentravelsoft.providers;

import com.opentravelsoft.entity.PortalRole;

import java.util.List;

/**
 * Lookup Data Access Object (GenericDao) interface. This is used to lookup
 * values in the database (i.e. for drop-downs).
 * 
 */
public interface LookupDao
{

    /**
     * Returns all Roles ordered by name
     * 
     * @return populated list of roles
     */
    List<PortalRole> getRoles();
}
