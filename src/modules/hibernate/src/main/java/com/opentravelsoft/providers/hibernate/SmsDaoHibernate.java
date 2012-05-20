package com.opentravelsoft.providers.hibernate;

import org.springframework.stereotype.Repository;

import com.opentravelsoft.entity.TblSmsReceive;
import com.opentravelsoft.entity.TblSmsSend;
import com.opentravelsoft.providers.SmsDao;

@Repository("SmsDao")
public class SmsDaoHibernate extends GenericDaoHibernate<TblSmsSend, Integer>
    implements SmsDao {
  public SmsDaoHibernate() {
    super(TblSmsSend.class);
  }

  public int receive(String seqno, String mob, String msg, String msgSrc) {
    TblSmsReceive record = new TblSmsReceive();
    record.setSeqno(seqno);
    record.setMessage(msg);
    record.setMobile(mob);
    record.setReply('N');
    record.setMsgSrc(msgSrc);
    getHibernateTemplate().save(record);

    return 0;
  }

  public int send(String seqno, String msg, String mob, long userId) {
    TblSmsSend record = new TblSmsSend();
    record.setSeqno(seqno);
    record.setMessage(msg);
    record.setMobile(mob);
    record.setOpId(userId);
    getHibernateTemplate().save(record);
    return 0;
  }

  public int sendResult(String seqno, String mob, String stat, String status) {
    return 0;
  }
}
