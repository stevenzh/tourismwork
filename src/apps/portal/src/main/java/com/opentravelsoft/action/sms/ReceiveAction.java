package com.opentravelsoft.action.sms;

import java.net.URLDecoder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opentravelsoft.service.SmsService;
import com.opentravelsoft.webapp.action.PortalAction;

public class ReceiveAction extends PortalAction
{

    private static final long serialVersionUID = 8042770547344853484L;

    protected static final Log logger = LogFactory.getLog(ReceiveAction.class);

    private SmsService smsService;

    private String seqno;

    private String extno;

    private String mob;

    private String msg;

    public void setSmsService(SmsService smsService)
    {
        this.smsService = smsService;
    }

    public String getExtno()
    {
        return extno;
    }

    public void setExtno(String extno)
    {
        this.extno = extno;
    }

    public String getMob()
    {
        return mob;
    }

    public void setMob(String mob)
    {
        this.mob = mob;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public String getSeqno()
    {
        return seqno;
    }

    public void setSeqno(String seqno)
    {
        this.seqno = seqno;
    }

    @Override
    public String execute()
    {
        try
        {
            // http://www.opentravelsoft.com/mms/ReceiveMms.action?seqno=4521756345893710&extno=123456&mob=13501683251&msg=%C4%E3%BA%C3
            char[] ls = msg.toCharArray();
            StringBuilder temp = new StringBuilder();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < ls.length; i++)
            {
                if (ls[i] < 127)
                {
                    sb.append(URLDecoder.decode(temp.toString()));
                    sb.append(ls[i]);
                    temp = new StringBuilder();
                } else
                {
                    temp.append("%" + Integer.toString(ls[i], 16));
                }
            }
            if (temp.length() > 0)
                sb.append(URLDecoder.decode(temp.toString()));

            // String sb = URLEncoder.encode(msg, "GB2312");
            String srcMsg = msg;
            msg = URLDecoder.decode(sb.toString());
            int result = smsService.txReceive(seqno, mob, msg, srcMsg);
            if (result < 0)
            {
                logger.error("");
            }

        } catch (Exception e)
        {

            logger.error("", e);
        }
        return NONE;
    }

}
