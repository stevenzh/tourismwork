package com.opentravelsoft.action.manage.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.common.SessionKeyParams;
import com.opentravelsoft.entity.Country;
import com.opentravelsoft.entity.Line;
import com.opentravelsoft.entity.LineVisa;
import com.opentravelsoft.service.product.LineVisaService;

/**
 * 线路所需签证
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:54 $
 */
public class LineVisaAction extends ManageAction {
  private static final long serialVersionUID = 7671898914387730451L;

  @Autowired
  private LineVisaService routeVisaService;

  private List<LineVisa> visaList = new ArrayList<LineVisa>();

  private List<Country> countrys;

  /** 序号 */
  private int refNo;

  private String countryCode;

  public String input() {
    Map<String, Object> session = ActionContext.getContext().getSession();
    Line line = (Line) session.get(SessionKeyParams.EBIZ_CURRENT_ROUTE);
    countrys = routeVisaService.getCountrys();
    visaList = routeVisaService.getVisaList(line.getLineNo());

    for (int i = 0; i < visaList.size(); i++) {
      visaList.get(i).setRefNo(i);
      visaList.get(i).setChecked("true");
    }

    return INPUT;
  }

  public String changeCountry() {
    for (int i = visaList.size() - 1; i >= 0; i--) {
      String checked = visaList.get(i).getChecked();
      if (checked.equals("false")) {
        visaList.remove(i);
      }
    }

    List<LineVisa> visaTl = routeVisaService.getVisaByCountry(countryCode);

    for (LineVisa routeVisa : visaTl) {
      for (int i = 0; i < visaList.size(); i++) {
        if (routeVisa.getId().getRecNo()
            .equals(visaList.get(i).getId().getRecNo())) {
          visaList.remove(i);
          break;
        }
      }
    }

    visaList.addAll(visaTl);

    for (int i = 0; i < visaList.size(); i++) {
      visaList.get(i).setRefNo(i);
    }
    countrys = routeVisaService.getCountrys();

    return SUCCESS;
  }

  public String save() {
    Line line = (Line) ActionContext.getContext().getSession()
        .get(SessionKeyParams.EBIZ_CURRENT_ROUTE);
    for (int i = visaList.size() - 1; i >= 0; i--) {
      String checked = visaList.get(i).getChecked();
      if (checked.equals("false")) {
        visaList.remove(i);
      }
    }
    routeVisaService.txSaveVisa(line.getLineNo(), visaList);

    addActionMessage("线路所需签证保存成功.");
    return SUCCESS;
  }

  public int getRefNo() {
    return refNo;
  }

  public void setRefNo(int refNo) {
    this.refNo = refNo;
  }

  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  public List<Country> getCountrys() {
    return countrys;
  }

  public List<LineVisa> getVisaList() {
    return visaList;
  }

  public void setVisaList(List<LineVisa> visaList) {
    this.visaList = visaList;
  }

}
