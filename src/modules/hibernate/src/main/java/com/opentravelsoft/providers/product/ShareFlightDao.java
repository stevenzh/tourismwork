package com.opentravelsoft.providers.product;

import java.util.List;

import com.opentravelsoft.entity.ShareFlight;
import com.opentravelsoft.providers.GenericDao;

/**
 * 共享资源
 * 
 * @author zhangst
 * 
 */
public interface ShareFlightDao extends GenericDao<ShareFlight, Integer> {

  List<ShareFlight> findAll();

  int editShareFlight(ShareFlight shareFlight);

}
