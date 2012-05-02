package com.opentravelsoft.entity;

import java.util.Date;

public class TblSmsSend implements java.io.Serializable
{

    private static final long serialVersionUID = -4418729525745689955L;

    private int id;

    private String seqno;

    private String mobile;

    private String message;

    private Date sendDate;

    private Long opId;

    private String stat;

    private String status;

    public TblSmsSend()
    {
    }

    public TblSmsSend(int id, String seqno, String mobile)
    {
        this.id = id;
        this.seqno = seqno;
        this.mobile = mobile;
    }

    public int getId()
    {
        return this.id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getSeqno()
    {
        return this.seqno;
    }

    public void setSeqno(String seqno)
    {
        this.seqno = seqno;
    }

    public String getMobile()
    {
        return this.mobile;
    }

    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }

    public String getMessage()
    {
        return this.message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public Date getSendDate()
    {
        return this.sendDate;
    }

    public void setSendDate(Date sendDate)
    {
        this.sendDate = sendDate;
    }

    public Long getOpId()
    {
        return this.opId;
    }

    public void setOpId(Long opId)
    {
        this.opId = opId;
    }

    public String getStat()
    {
        return this.stat;
    }

    public void setStat(String stat)
    {
        this.stat = stat;
    }

    public String getStatus()
    {
        return this.status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

}
