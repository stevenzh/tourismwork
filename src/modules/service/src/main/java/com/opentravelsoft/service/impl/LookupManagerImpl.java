package com.opentravelsoft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opentravelsoft.model.PortalRole;
import com.opentravelsoft.providers.LookupDao;
import com.opentravelsoft.service.LookupManager;
import com.opentravelsoft.util.LabelValueBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of LookupManager interface to talk to the persistence layer.
 * 
 */
@Service("lookupManager")
public class LookupManagerImpl implements LookupManager
{
    @Autowired
    LookupDao dao;

    /**
     * {@inheritDoc}
     */
    public List<LabelValueBean> getAllRoles()
    {
        List<PortalRole> roles = dao.getRoles();
        List<LabelValueBean> list = new ArrayList<LabelValueBean>();

        for (PortalRole role1 : roles)
        {
            list.add(new LabelValueBean(role1.getName(), role1.getName()));
        }

        return list;
    }
}
