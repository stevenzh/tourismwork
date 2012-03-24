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
  private TouristDao touristDao;

  private ListDao listDao;

  private PlanDao tourDao;

  @Autowired
  public void setTouristDao(TouristDao touristDao) {
    this.touristDao = touristDao;
  }

  public Tourist roFindCustomerByNmno(String nmno) {
    return touristDao.findCustomerByNmno(nmno);
  }

  public void setListDao(ListDao listDao) {
    this.listDao = listDao;
  }

  public void setPlanDao(PlanDao tourDao) {
    this.tourDao = tourDao;
  }

  public List<LabelValueBean> roGetPassportPlaceList() {
    return listDao.getList("Homeplace");
  }

  public Plan roGetPlanDetail(String tourNo) {
    return tourDao.getTourInfo(tourNo, false, false);
  }

  public int txModifyCustomerInfo(List<Tourist> customerList, String tourNo,
      String note, long uid) {
    return tourDao.modifyCustomerInfo(customerList, tourNo, note, uid);
  }
}
