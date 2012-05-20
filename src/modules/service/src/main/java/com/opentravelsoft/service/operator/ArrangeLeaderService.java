package com.opentravelsoft.service.operator;

import java.util.List;

import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.entity.product.Leader;

public interface ArrangeLeaderService {

  Plan roGetDetail(String tourNo);

  int txArrangeLeader(String tourNo, String[] nameKey, int userId);

  List<Leader> roGetLeaderList();

  int txArrangeFromLeader(String toutNo, String[] nameKey, int userId);

  int txCancelLeader(String tourNo, String[] nameKey, int userId);

}
