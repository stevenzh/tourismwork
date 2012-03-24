package com.opentravelsoft.action.sms;


import com.opentravelsoft.service.SmsService;
import com.opentravelsoft.webapp.action.PortalAction;

public class SendResultAction extends PortalAction
{

    private static final long serialVersionUID = -8845055926235599326L;

    private SmsService smsService;

    private String seqno;

    private String extno;

    private String mob;

    /**
     * 处理状态
     * <ul>
     * <li> 0 未定义
     * <li> 1 已接受，等待后续结果
     * <li> 2 得到成功状态报告
     * <li> 3 后续提交失败
     * <li> 4 状态报告失败
     * </ul>
     */
    private String stat;

    /**
     * 状态/错误码
     * 
     * stat=3时表示提交失败码<br>
     * 
     * 依据承载通道有不同的定义，不作详细说明。<br>
     * 
     * stat=4时表示原始状态报告<br>
     * 
     * 常见SMPP标准状态报告有以下一些：
     * <ul>
     * <li> DELIVRD 下发成功
     * <li> EXPIRED 超过短信中心短信存活期仍然未下发成功
     * <li> REJECTD 拒绝发送
     * <li>UNDELIV 下发失败
     * </ul>
     * 其他网关/厂商扩展码非常复杂，不作详细说明。
     */
    private String status;

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

    public String getSeqno()
    {
        return seqno;
    }

    public void setSeqno(String seqno)
    {
        this.seqno = seqno;
    }

    public String getStat()
    {
        return stat;
    }

    public void setStat(String stat)
    {
        this.stat = stat;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    @Override
    public String execute()
    {
        // http://foo.com/path/report.php?seqno=1787309083485798406&extno=123456&mob=13501683251&stat=2&status=DELIVRD

        // http://foo.com/path/report.php
        // ?seqno=1787309083485798406
        // &extno=123456
        // &mob=13501683251
        // &stat=2
        // &status=DELIVRD

        try
        {
            int result = smsService.txSendResult(seqno, extno, mob, stat,
                    status);
        } catch (Exception e)
        {

        }

        return SUCCESS;
    }

}
