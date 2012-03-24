package com.opentravelsoft.json;

import java.util.ArrayList;
import java.util.List;

import com.opentravelsoft.util.LabelValueBean;

import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.providers.ListDao;

public class BirthPlaceAction extends ManageAction {
  private static final long serialVersionUID = 3925524176994117115L;

  private List<LabelValueBean> map = new ArrayList<LabelValueBean>();

  private ListDao listDao;

  public String execute() {
    map = listDao.getList("HomePlace");
    return SUCCESS;
  }

  public List<LabelValueBean> getMap() {
    return map;
  }

  public void setListDao(ListDao listDao) {
    this.listDao = listDao;
  }

}
