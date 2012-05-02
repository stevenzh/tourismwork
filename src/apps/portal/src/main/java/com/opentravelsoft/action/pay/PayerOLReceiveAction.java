package com.opentravelsoft.action.pay;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.EbizException;
import com.opentravelsoft.common.EbizCommon;
import com.opentravelsoft.entity.Booking;
import com.opentravelsoft.entity.finance.Income;
import com.opentravelsoft.service.order.BookingService;
import com.opentravelsoft.util.MD5;
import com.opentravelsoft.util.StringUtil;
import com.opentravelsoft.webapp.action.PortalAction;

/**
 * 网上付款返回页面
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 */
public class PayerOLReceiveAction extends PortalAction {
  private static final long serialVersionUID = 8812505304255485849L;

  @Autowired
  private BookingService bookingService;

  /** 订单号 */
  private String v_oid;

  /** 支付方式中文说明，如"中行长城信用卡" */
  private String v_pmode;

  /** 支付结果，20支付完成；30支付失败； */
  private String v_pstatus;

  /** 对支付结果的说明，成功时（v_pstatus=20）为"支付成功"，支付失败时（v_pstatus=30）为"支付失败" */
  private String v_pstring;

  /** 订单实际支付金额 */
  private String v_amount;

  /** 币种 */
  private String v_moneytype;

  /** MD5校验码 */
  private String v_md5str;

  /** 备注1 */
  private String remark1;

  /** 备注1 */
  private String remark2;

  // --------------------------------------------------------------------------------

  private Booking booking;

  private List<Income> payments;

  public String execute() throws NumberFormatException, EbizException {
    // 数据验证
    if (!StringUtil.hasLength(v_oid)) {
      addActionMessage("数据错误，订单号不存在.");
    }
    if (!StringUtil.hasLength(v_pmode)) {
      addActionMessage("数据错误，支付方式中文说明不存在.");
    }

    String text = v_oid + v_pstatus + v_amount + v_moneytype
        + EbizCommon.CHINA_BANK_KEY;

    String v_md5text = new MD5().getMD5ofStr(text).toUpperCase();

    if (v_md5str.equals(v_md5text)) {
      if ("30".equals(v_pstatus)) {
        addActionMessage("网银返回数据: " + v_pstring);
      } else if ("20".equals(v_pstatus)) {
        addActionMessage("网银返回数据: 支付成功");
        // 支付成功，商户 根据自己业务做相应逻辑处理
        // 此处加入商户系统的逻辑处理（例如判断金额，判断支付状态，更新订单状态等等）......
        int result = bookingService.txNetPay(v_oid, v_pmode,
            Double.parseDouble(v_amount), v_moneytype);

        if (result < 0) {
          addActionMessage("数据更新失败: 支付成功");
        }
      }
    } else {
      addActionMessage("数据错误，支付失败");
    }

    booking = bookingService.roGetReserve(v_oid);

    payments = bookingService.roGetPayments(v_oid);

    return SUCCESS;
  }

  public String getV_oid() {
    return v_oid;
  }

  public void setV_oid(String v_oid) {
    this.v_oid = v_oid;
  }

  public String getV_pmode() {
    return v_pmode;
  }

  public void setV_pmode(String v_pmode) {
    this.v_pmode = v_pmode;
  }

  public String getV_pstatus() {
    return v_pstatus;
  }

  public void setV_pstatus(String v_pstatus) {
    this.v_pstatus = v_pstatus;
  }

  public String getV_pstring() {
    return v_pstring;
  }

  public void setV_pstring(String v_pstring) {
    this.v_pstring = v_pstring;
  }

  public String getV_amount() {
    return v_amount;
  }

  public void setV_amount(String v_amount) {
    this.v_amount = v_amount;
  }

  public String getV_moneytype() {
    return v_moneytype;
  }

  public void setV_moneytype(String v_moneytype) {
    this.v_moneytype = v_moneytype;
  }

  public String getV_md5str() {
    return v_md5str;
  }

  public void setV_md5str(String v_md5str) {
    this.v_md5str = v_md5str;
  }

  public String getRemark1() {
    return remark1;
  }

  public void setRemark1(String remark1) {
    this.remark1 = remark1;
  }

  public String getRemark2() {
    return remark2;
  }

  public void setRemark2(String remark2) {
    this.remark2 = remark2;
  }

  public Booking getBooking() {
    return booking;
  }

  public List<Income> getPayments() {
    return payments;
  }

}
