package com.opentravelsoft.providers;

import com.opentravelsoft.entity.Sequence;

public interface SequenceDao extends GenericDao<Sequence, Long> {

  public String getComputerNo(String type, long userId);

  public String[] getComputerNo(String type, int count, long userId);
}
