package com.opentravelsoft.service;

import java.util.List;

import com.opentravelsoft.util.LabelValueBean;

import com.opentravelsoft.entity.Destination;

public interface RegionService
{
    List<List<LabelValueBean>> getRegionGroupList();

    public List<Destination> getRegionList();

}
