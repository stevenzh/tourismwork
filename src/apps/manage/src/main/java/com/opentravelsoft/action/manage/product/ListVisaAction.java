package com.opentravelsoft.action.manage.product;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.common.SessionKeyParams;
import com.opentravelsoft.entity.Country;
import com.opentravelsoft.entity.Employee;
import com.opentravelsoft.entity.Line;
import com.opentravelsoft.entity.VisaHelp;
import com.opentravelsoft.service.VisaHelpService;

/**
 * 签证服务
 * 
 * @author zhangst
 * 
 */
public class ListVisaAction extends ManageAction {
  private static final long serialVersionUID = 1082931041437688165L;

  private VisaHelpService visaService;

  /** 国家列表 */
  private List<Country> countrys;

  /** 签证列表 */
  private List<VisaHelp> visaList;

  /** 具体的签证国家 */
  private String country;

  /** 具体签证国家的记录号 */
  private String recordNo;

  private Line line;

  @Autowired
  public void setVisaService(VisaHelpService visaService) {
    this.visaService = visaService;
  }

  /**
   * 查询签证国家
   * 
   * @return
   */
  public String find() {
    Map<String, Object> session = ActionContext.getContext().getSession();
    countrys = visaService.roGetCountrys();
    visaList = visaService.roGetVisaItems(country);
    line = (Line) session.get(SessionKeyParams.EBIZ_CURRENT_ROUTE);

    return SUCCESS;
  }

  /**
   * 删除签证
   * 
   * @return
   */
  public String delete() {
    Employee user = getUser();
    visaService.txDeleteVisaItem(recordNo, user.getUserId());
    return SUCCESS;
  }

  public List<Country> getCountrys() {
    return countrys;
  }

  public List<VisaHelp> getVisaList() {
    return visaList;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getRecordNo() {
    return recordNo;
  }

  public void setRecordNo(String recordNo) {
    this.recordNo = recordNo;
  }

  public Line getLine() {
    return line;
  }

}
