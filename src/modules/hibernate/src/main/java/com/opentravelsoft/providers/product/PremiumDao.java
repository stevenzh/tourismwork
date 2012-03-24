package com.opentravelsoft.providers.product;

import java.util.List;

import com.opentravelsoft.entity.Premium;
import com.opentravelsoft.providers.GenericDao;

/**
 * 保险
 * 
 * @author zhangst
 * 
 */
public interface PremiumDao extends GenericDao<Premium, String>
{
    List<Premium> findAll();

    int deletePrem(String preminuCode);

    int editPrem(Premium premiumRule);

}
