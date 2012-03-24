package com.opentravelsoft.service.finance;

import java.util.List;

import com.opentravelsoft.entity.Line;
import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.entity.finance.Reckoning;
import com.opentravelsoft.entity.finance.ReckoningAcct;

public interface ReckoningService {

  public List<Reckoning> roGetReckoning(String reserveNo);

  public Plan roGetTourInfo(String tourNo);

  public Reckoning txWholeReckoningMake(Reckoning reckoning);

  public Reckoning roGetReckoningInfo(long reckoningId);

  public int txWholeReckoningModify(Reckoning reckoning);

  public List<ReckoningAcct> roGetCustomerList(String reserveNo);

  public int txSetPrint(int reckoningId);

  public Line getLineInfo(String lineNo);

  public List<ReckoningAcct> roGetTourReckoningAcctList(String tourNo);

}
