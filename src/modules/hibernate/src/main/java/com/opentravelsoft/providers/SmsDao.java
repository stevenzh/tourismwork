package com.opentravelsoft.providers;

import java.util.Date;

import com.opentravelsoft.entity.TblSmsSend;

public interface SmsDao extends GenericDao<TblSmsSend, Integer> {
  int reserve(String seqno, String mob, String msg, String srcMsg);

  int send(String seqno, String msg, String mob, int userId);

  int sendResult(String seqno, String mob, String stat, String status);

  Date getSysdate();

}
