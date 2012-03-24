package com.opentravelsoft.service.finance;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opentravelsoft.entity.Line;
import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.entity.finance.Reckoning;
import com.opentravelsoft.entity.finance.ReckoningAcct;
import com.opentravelsoft.providers.ReckoningDao;
import com.opentravelsoft.providers.PlanDao;
import com.opentravelsoft.providers.product.LineDao;

@Service("ReckoningMakeService")
public class ReckoningServiceImpl implements ReckoningService {
  private LineDao lineDao;

  private ReckoningDao reckoningDao;

  private PlanDao planDao;

  @Autowired
  public void setLineDao(LineDao lineDao) {
    this.lineDao = lineDao;
  }

  @Autowired
  public void setReckoningDao(ReckoningDao reckoningDao) {
    this.reckoningDao = reckoningDao;
  }

  @Autowired
  public void setPlanDao(PlanDao tourDao) {
    this.planDao = tourDao;
  }

  public List<Reckoning> roGetReckoning(String reserveNo) {
    return reckoningDao.getReckoning(reserveNo);
  }

  public Plan roGetTourInfo(String tourNo) {
    return planDao.getTourInfo(tourNo, false, false);
  }

  public Reckoning txWholeReckoningMake(Reckoning reckoning) {
    return reckoningDao.wholeReckoningMake(reckoning);
  }

  public Reckoning roGetReckoningInfo(long reckoningId) {
    return reckoningDao.getReckoningInfo(reckoningId);
  }

  public int txWholeReckoningModify(Reckoning reckoning) {
    return reckoningDao.wholeReckoningModify(reckoning);
  }

  public List<ReckoningAcct> roGetCustomerList(String reserveNo) {
    return reckoningDao.getCustomerList(reserveNo);
  }

  public int txSetPrint(int reckoningId) {
    return reckoningDao.setPrint(reckoningId);
  }

  public Line getLineInfo(String lineNo) {
    return lineDao.get(lineNo);
  }

  public List<ReckoningAcct> roGetTourReckoningAcctList(String tourNo) {
    return reckoningDao.getTourReckoningAcctList(tourNo);
  }

}
