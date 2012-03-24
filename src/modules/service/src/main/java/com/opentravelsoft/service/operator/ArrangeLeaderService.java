package com.opentravelsoft.service.operator;

import java.util.List;

import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.entity.product.Leader;

public interface ArrangeLeaderService {

  Plan roGetDetail(String tourNo);

  int txArrangeLeader(String tourNo, String[] nameKey, long userId);

  List<Leader> roGetLeaderList();

  int txArrangeFromLeader(String toutNo, String[] nameKey, long userId);

  int txCancelLeader(String tourNo, String[] nameKey, long userId);

}
