package com.opentravelsoft.action.pay;

import java.text.DecimalFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opentravelsoft.common.EbizCommon;
import com.opentravelsoft.entity.TblUserPayOL;
import com.opentravelsoft.entity.product.NetPayEntity;
import com.opentravelsoft.service.PaymentService;
import com.opentravelsoft.webapp.action.PortalAction;

public class PayerOLAction extends PortalAction {
  private static final long serialVersionUID = 7649730350877589492L;

  protected static final Log logger = LogFactory.getLog(PayerOLAction.class);

  @Autowired
  private PaymentService payerService;

  private TblUserPayOL tblUserPayOl;

  private NetPayEntity entity = new NetPayEntity();

  private static final DecimalFormat DF = new DecimalFormat("##.00");

  public TblUserPayOL getTblUserPayOl() {
    return tblUserPayOl;
  }

  public void setTblUserPayOl(TblUserPayOL tblUserPayOl) {
    this.tblUserPayOl = tblUserPayOl;
  }

  public NetPayEntity getEntity() {
    return entity;
  }

  public String input() {
    logger.info("execute[] start");
    tblUserPayOl = new TblUserPayOL();
    tblUserPayOl.setPayTime(new Date());
    return SUCCESS;

  }

  public String submit() {
    payerService.insertPayer(tblUserPayOl);

    ActionContext.getContext().getSession().put("payer", tblUserPayOl);
    // 商户号
    entity.setMid(EbizCommon.CHINA_BANK_MID);
    // MD5 Key
    entity.setKey(EbizCommon.CHINA_BANK_KEY);
    // 团款
    entity.setAmount(DF.format(tblUserPayOl.getPayAmount()));
    // 订单号
    entity.setOid(String.valueOf(tblUserPayOl.getPayId()));

    entity.setRcvname(tblUserPayOl.getPayerName());

    entity.setRcvmobile(tblUserPayOl.getPayerPhone());

    entity.refreshMd5key();

    return SUCCESS;
  }

}
