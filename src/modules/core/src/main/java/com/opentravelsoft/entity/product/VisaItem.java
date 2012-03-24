package com.opentravelsoft.entity.product;

import com.opentravelsoft.entity.TblVisaItem;


/**
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:32 $
 */
public class VisaItem extends TblVisaItem
{
    private static final long serialVersionUID = 981348380430497462L;

    private int idx;

    private int attachedId;

    /** 项目类型 W -签证所须资料 F-附件 */
    private String type;

    private String fileName;

    private String filePath;

    private String note;

    public VisaItem()
    {
        super();
        type = "W";
    }

    public int getIdx()
    {
        return idx;
    }

    public void setIdx(int idx)
    {
        this.idx = idx;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getFilePath()
    {
        return filePath;
    }

    public void setFilePath(String filePath)
    {
        this.filePath = filePath;
    }

    public int getAttachedId()
    {
        return attachedId;
    }

    public void setAttachedId(int attachedId)
    {
        this.attachedId = attachedId;
    }

    public String getNote()
    {
        return note;
    }

    public void setNote(String note)
    {
        this.note = note;
    }

    public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

}
