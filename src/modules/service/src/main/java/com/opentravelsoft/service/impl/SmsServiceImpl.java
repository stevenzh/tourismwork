package com.opentravelsoft.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opentravelsoft.providers.SmsDao;
import com.opentravelsoft.service.SmsService;

@Service("SmsService")
public class SmsServiceImpl implements SmsService {
  
  @Autowired
  private SmsDao smsDao;

  public int txReceive(String seqno, String mob, String msg, String srcMsg) {
    return smsDao.receive(seqno, mob, msg, srcMsg);
  }

  public int txSend(String seqno, String msg, String mob, long userId) {
    return smsDao.send(seqno, msg, mob, userId);
  }

  public int txSendResult(String seqno, String extno, String mob, String stat,
      String status) {
    return smsDao.sendResult(seqno, mob, stat, status);
  }

  public Date roGetSysdate() {
    return smsDao.getSysdate();
  }

}
