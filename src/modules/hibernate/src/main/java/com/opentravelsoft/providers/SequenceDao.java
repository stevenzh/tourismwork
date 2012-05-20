package com.opentravelsoft.providers;

import com.opentravelsoft.entity.Sequence;
import com.opentravelsoft.entity.SequenceId;

public interface SequenceDao extends GenericDao<Sequence, SequenceId> {

  public String getComputerNo(String type, int userId);

  public String[] getComputerNo(String type, int count, int userId);
}
