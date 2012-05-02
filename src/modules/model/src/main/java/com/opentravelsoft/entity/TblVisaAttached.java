package com.opentravelsoft.entity;

import java.util.Date;

public class TblVisaAttached implements java.io.Serializable
{

    private static final long serialVersionUID = -4267725237621480730L;

    private int visaAttachedId;

    private String recNo;

    private String note;

    private String filePath;

    private Integer fileSize;

    private Date created;

    private String createdby;

    public TblVisaAttached()
    {
    }

    public TblVisaAttached(int visaAttachedId, String recNo)
    {
        this.visaAttachedId = visaAttachedId;
        this.recNo = recNo;
    }

    public int getVisaAttachedId()
    {
        return this.visaAttachedId;
    }

    public void setVisaAttachedId(int visaAttachedId)
    {
        this.visaAttachedId = visaAttachedId;
    }

    public String getRecNo()
    {
        return this.recNo;
    }

    public void setRecNo(String recNo)
    {
        this.recNo = recNo;
    }

    public String getNote()
    {
        return this.note;
    }

    public void setNote(String note)
    {
        this.note = note;
    }

    public String getFilePath()
    {
        return this.filePath;
    }

    public void setFilePath(String filePath)
    {
        this.filePath = filePath;
    }

    public Integer getFileSize()
    {
        return this.fileSize;
    }

    public void setFileSize(Integer fileSize)
    {
        this.fileSize = fileSize;
    }

    public Date getCreated()
    {
        return this.created;
    }

    public void setCreated(Date created)
    {
        this.created = created;
    }

    public String getCreatedBy()
    {
        return this.createdby;
    }

    public void setCreatedBy(String createdby)
    {
        this.createdby = createdby;
    }

}
