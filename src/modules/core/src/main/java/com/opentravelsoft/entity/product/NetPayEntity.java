package com.opentravelsoft.entity.product;

import com.opentravelsoft.util.MD5;

/**
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:32 $
 */
public class NetPayEntity
{
    /**
     * 商户号，这里为测试商户号1001，替换为自己的商户号(老版商户号为4位或5位,新版为8位)即可
     */
    private String mid;

    /**
     * 如果您还没有设置MD5密钥请登陆我们为您提供商户后台，<br>
     * 地址：https://merchant3.chinabank.com.cn/
     * 登陆后在上面的导航栏里可能找到“B2C”，在二级导航栏里有“MD5密钥设置”
     * 建议您设置一个16位以上的密钥或更高，密钥最多64位，但设置16位已经足够了
     */
    private String key;

    /** 商户自定义返回接收支付结果的页面 */
    private String url;

    /** 订单号 */
    private String oid;

    /** 订单金额 */
    private String amount;

    /** 币种 */
    private String moneytype;

    /** 对拼凑串MD5私钥加密后的值 */
    private String md5info;

    /** 收货人 */
    private String rcvname;

    /** 收货地址 */
    private String rcvaddr;

    /** 收货人电话 */
    private String rcvtel;

    /** 收货人邮编 */
    private String rcvpost;

    /** 收货人邮件 */
    private String rcvemail;

    /** 收货人手机号 */
    private String rcvmobile;

    // -------------------------------------------------------------------------
    /** 订货人姓名 */
    private String ordername;

    /** 订货人地址 */
    private String orderaddr;

    /** 订货人电话 */
    private String ordertel;

    /** 订货人邮编 */
    private String orderpost;

    /** 订货人邮件 */
    private String orderemail;

    /** 订货人手机号 */
    private String ordermobile;

    // -------------------------------------------------------------------------
    /** 备注字段1 */
    private String remark1;

    /** 备注字段2 */
    private String remark2;

    public NetPayEntity()
    {
        // 初始化定义参数
        mid = "1001";
        url = "http://www.opentravelsoft.com/ReceivePay.action";
        key = "test";
        moneytype = "CNY";
    }

    public String getMid()
    {
        return mid;
    }

    public void setMid(String mid)
    {
        this.mid = mid;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getOid()
    {
        return oid;
    }

    public void setOid(String oid)
    {
        this.oid = oid;
    }

    public String getAmount()
    {
        return amount;
    }

    public void setAmount(String amount)
    {
        this.amount = amount;
    }

    public String getMoneytype()
    {
        return moneytype;
    }

    public void setMoneytype(String moneytype)
    {
        this.moneytype = moneytype;
    }

    public String getMd5info()
    {
        return md5info;
    }

    public void setMd5info(String md5info)
    {
        this.md5info = md5info;
    }

    public String getRcvname()
    {
        return rcvname;
    }

    public void setRcvname(String rcvname)
    {
        this.rcvname = rcvname;
    }

    public String getRcvaddr()
    {
        return rcvaddr;
    }

    public void setRcvaddr(String rcvaddr)
    {
        this.rcvaddr = rcvaddr;
    }

    public String getRcvtel()
    {
        return rcvtel;
    }

    public void setRcvtel(String rcvtel)
    {
        this.rcvtel = rcvtel;
    }

    public String getRcvpost()
    {
        return rcvpost;
    }

    public void setRcvpost(String rcvpost)
    {
        this.rcvpost = rcvpost;
    }

    public String getRcvemail()
    {
        return rcvemail;
    }

    public void setRcvemail(String rcvemail)
    {
        this.rcvemail = rcvemail;
    }

    public String getRcvmobile()
    {
        return rcvmobile;
    }

    public void setRcvmobile(String rcvmobile)
    {
        this.rcvmobile = rcvmobile;
    }

    public String getOrdername()
    {
        return ordername;
    }

    public void setOrdername(String ordername)
    {
        this.ordername = ordername;
    }

    public String getOrderaddr()
    {
        return orderaddr;
    }

    public void setOrderaddr(String orderaddr)
    {
        this.orderaddr = orderaddr;
    }

    public String getOrdertel()
    {
        return ordertel;
    }

    public void setOrdertel(String ordertel)
    {
        this.ordertel = ordertel;
    }

    public String getOrderpost()
    {
        return orderpost;
    }

    public void setOrderpost(String orderpost)
    {
        this.orderpost = orderpost;
    }

    public String getOrderemail()
    {
        return orderemail;
    }

    public void setOrderemail(String orderemail)
    {
        this.orderemail = orderemail;
    }

    public String getOrdermobile()
    {
        return ordermobile;
    }

    public void setOrdermobile(String ordermobile)
    {
        this.ordermobile = ordermobile;
    }

    public String getRemark1()
    {
        return remark1;
    }

    public void setRemark1(String remark1)
    {
        this.remark1 = remark1;
    }

    public String getRemark2()
    {
        return remark2;
    }

    public void setRemark2(String remark2)
    {
        this.remark2 = remark2;
    }

    public void refreshMd5key()
    {
        String text = amount + moneytype + oid + mid + url + key;
        // 拼凑加密串
        md5info = new MD5().getMD5ofStr(text); //
        // 网银支付平台对MD5值只认大写字符串，所以小写的MD5值得转换为大写
    }

}
