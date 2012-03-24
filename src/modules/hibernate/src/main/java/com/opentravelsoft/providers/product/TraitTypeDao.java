package com.opentravelsoft.providers.product;

import java.util.List;

import com.opentravelsoft.entity.TraitType;
import com.opentravelsoft.providers.GenericDao;

public interface TraitTypeDao extends GenericDao<TraitType, Long>
{
    public List<TraitType> getTypeList();
}
