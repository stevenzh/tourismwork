package com.opentravelsoft.providers;

import java.util.Date;

import com.opentravelsoft.entity.SmsMessage;

public interface SmsDao extends GenericDao<SmsMessage, Long> {
	int receive(String seqno, String mob, String msg, String srcMsg);

	int send(String seqno, String msg, String mob, long userId);

	int sendResult(String seqno, String mob, String stat, String status);

	Date getSysdate();

}
