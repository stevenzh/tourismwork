package com.opentravelsoft.service;

import java.util.List;

import com.opentravelsoft.util.LabelValueBean;

/**
 * Business Service Interface to talk to persistence layer and
 * retrieve values for drop-down choice lists.
 *
 */
public interface LookupManager {
    /**
     * Retrieves all possible roles from persistence layer
     * @return List of LabelValue objects
     */
    List<LabelValueBean> getAllRoles();
}
