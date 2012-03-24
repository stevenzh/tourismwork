package com.opentravelsoft.service;

import java.util.Date;

public interface SmsService
{
    public int txReceive(String seqno, String mob, String msg, String srcMsg);

    public int txSend(String seqno, String msg, String mob, long userId);

    public int txSendResult(String seqno, String extno, String mob,
            String stat, String status);

    public Date roGetSysdate();
}
