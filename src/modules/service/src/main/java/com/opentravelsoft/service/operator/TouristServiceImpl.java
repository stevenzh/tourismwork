package com.opentravelsoft.service.operator;

import java.util.List;

import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.entity.Tourist;
import com.opentravelsoft.providers.ListDao;
import com.opentravelsoft.providers.PlanDao;
import com.opentravelsoft.providers.TouristDao;

@Service("TouristService")
public class TouristServiceImpl implements TouristService {

  @Autowired
  private TouristDao touristDao;

  @Autowired
  private ListDao listDao;

  @Autowired
  private PlanDao planDao;

  public Tourist roFindCustomerByNmno(String nmno) {
    return touristDao.findCustomerByNmno(nmno);
  }

  public List<LabelValueBean> roGetPassportPlaceList() {
    return listDao.getList("Homeplace");
  }

  public Plan roGetPlanDetail(String tourNo) {
    return planDao.getTourInfo(tourNo, false, false);
  }

  public int txModifyCustomerInfo(List<Tourist> customerList, String tourNo,
      String note, int uid) {
    return planDao.modifyCustomerInfo(customerList, tourNo, note, uid);
  }
}
