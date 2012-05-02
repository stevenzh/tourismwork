package com.opentravelsoft.action.account;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opentravelsoft.common.SessionKeyParams;
import com.opentravelsoft.entity.City;
import com.opentravelsoft.entity.Country;
import com.opentravelsoft.entity.Province;
import com.opentravelsoft.model.Member;
import com.opentravelsoft.service.account.MemberService;
import com.opentravelsoft.service.resource.CityService;
import com.opentravelsoft.service.resource.CountryService;
import com.opentravelsoft.service.resource.ProvinceService;
import com.opentravelsoft.webapp.action.PortalAction;

/**
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:59 $
 */
public class MyInfoAction extends PortalAction {

  private static final long serialVersionUID = 8322093981248763392L;

  protected static final Log logger = LogFactory.getLog(MyInfoAction.class);

  @Autowired
  private MemberService memberService;

  @Autowired
  private ProvinceService provinceService;

  @Autowired
  private CountryService countryService;

  @Autowired
  private CityService cityService;

  private Member member;

  /** 国家列表 */
  private List<Country> nations;

  /** 省份列表 */
  private List<Province> provinces;

  /** 城市列表 */
  private List<City> citys;

  /** 职业列表 */
  private List<LabelValueBean> vocations;

  /** 性别列表 */
  private List<LabelValueBean> sexs;

  /** 教育程度列表 */
  private List<LabelValueBean> educates;

  /** 是否 */
  private List<LabelValueBean> yesOrNo;

  public Member getCustomer() {
    return member;
  }

  public void setCustomer(Member customer) {
    this.member = customer;
  }

  public List<Country> getNations() {
    return nations;
  }

  public List<Province> getProvinces() {
    return provinces;
  }

  public List<LabelValueBean> getVocations() {
    return vocations;
  }

  public List<LabelValueBean> getEducates() {
    return educates;
  }

  public List<LabelValueBean> getSexs() {
    return sexs;
  }

  public List<LabelValueBean> getYesOrNo() {
    return yesOrNo;
  }

  public List<City> getCitys() {
    return citys;
  }

  public String input() {

    member = (Member) ActionContext.getContext().getSession()
        .get(SessionKeyParams.EBIZ_USER);

    member = memberService.roGetMemberById(member.getId());
    provinces = provinceService.roGetProvinces();
    nations = countryService.roGetNations();
    citys = cityService.roGetCitysByCountry(member.getNation());
    vocations = memberService.roGetVocations();
    educates = memberService.roGetEducates();

    sexs = getSysList("DOM_FormalSex");
    yesOrNo = getCodeList("ebiz_yes_no");

    return SUCCESS;
  }

  public String submit() {
    Map<String, Object> session = ActionContext.getContext().getSession();
    int result = memberService.txInfoUpdate(member);

    Member temp = (Member) session.get(SessionKeyParams.EBIZ_USER);
    member.setLastLogindate(temp.getLastLogindate());
    if (result < 0) {
      addActionError("更新失败");
      return INPUT;
    }
    session.put(SessionKeyParams.EBIZ_USER, member);

    return SUCCESS;
  }

  public String modifyPassword() {
    Map<String, Object> session = ActionContext.getContext().getSession();
    member = (Member) session.get(SessionKeyParams.EBIZ_USER);
    int result = memberService.txModifyPassword(member);

    Member temp = (Member) session.get(SessionKeyParams.EBIZ_USER);
    member.setLastLogindate(temp.getLastLogindate());
    if (result < 0) {
      addActionMessage("修改失败");
      return INPUT;
    }
    session.put(SessionKeyParams.EBIZ_USER, member);

    return SUCCESS;
  }

}
