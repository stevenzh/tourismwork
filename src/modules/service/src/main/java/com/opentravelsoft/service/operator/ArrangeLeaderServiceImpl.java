package com.opentravelsoft.service.operator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.entity.product.Leader;
import com.opentravelsoft.providers.SequenceDao;
import com.opentravelsoft.providers.PlanDao;

@Service("ArrangeLeaderService")
public class ArrangeLeaderServiceImpl implements ArrangeLeaderService {
  
  @Autowired
  private PlanDao planDao;

  @Autowired
  private SequenceDao sequenceDao;

  public Plan roGetDetail(String tourNo) {
    // 取得团信息，人名单
    return planDao.getTourInfo(tourNo, true, false);
  }

  public int txArrangeLeader(String tourNo, String[] nameKey, long operator) {
    // TODO WorkFLow
    return planDao.arrangeLeader(tourNo, nameKey, operator);
  }

  public List<Leader> roGetLeaderList() {
    return planDao.getLeaderList();
  }

  public int txArrangeFromLeader(String tourNo, String[] nameKey, long userId) {
    String[] sqe = sequenceDao.getComputerNo("H", nameKey.length, userId);
    // TODO WorkFLow
    return planDao.arrangeFromLeader(tourNo, nameKey, sqe, userId);
  }

  public int txCancelLeader(String tourNo, String[] nameKey, long uid) {
    return planDao.cancelLeader(tourNo, nameKey, uid);
  }
}
