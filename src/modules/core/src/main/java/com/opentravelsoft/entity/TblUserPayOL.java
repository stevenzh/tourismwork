package com.opentravelsoft.entity;

import java.io.Serializable;

public class TblUserPayOL implements Serializable
{
    private static final long serialVersionUID = 3996096996589962074L;

    private int payId;

    private String payerName;

    private String payerPhone;

    private String tourRoute;

    private String remark;

    private double payAmount;

    private String payTime;

    public int getPayId()
    {
        return payId;
    }

    public void setPayId(int payId)
    {
        this.payId = payId;
    }

    public String getPayerName()
    {
        return payerName;
    }

    public void setPayerName(String payerName)
    {
        this.payerName = payerName;
    }

    public String getPayerPhone()
    {
        return payerPhone;
    }

    public void setPayerPhone(String payerPhone)
    {
        this.payerPhone = payerPhone;
    }

    public String getTourRoute()
    {
        return tourRoute;
    }

    public void setTourRoute(String tourRoute)
    {
        this.tourRoute = tourRoute;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public double getPayAmount()
    {
        return payAmount;
    }

    public void setPayAmount(double payAmount)
    {
        this.payAmount = payAmount;
    }

    public String getPayTime()
    {
        return payTime;
    }

    public void setPayTime(String payTime)
    {
        this.payTime = payTime;
    }

}