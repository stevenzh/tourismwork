package com.opentravelsoft.service;

import java.util.List;

import com.opentravelsoft.util.LabelValueBean;

import com.opentravelsoft.entity.Country;
import com.opentravelsoft.entity.VisaHelp;
import com.opentravelsoft.entity.product.VisaItem;

public interface VisaHelpService {
  /**
   * 网站使用
   * 
   * @param visaKind
   * @return
   */
  List<VisaHelp> roGetUsableVisaItems(String visaKind);

  List<Country> roGetCountrys();

  List<VisaHelp> roGetVisaItems(String country);

  VisaHelp roGetVisaItem(String recordNo);

  boolean txEditVisaItem(VisaHelp visaItem, List<VisaItem> items,
      List<VisaItem> fileItems);

  boolean txDeleteVisaItem(String recordNo, long userId);

  boolean txDelAttached(int attachedId);

  VisaItem roGetVisaAttached(int attachedId);

  /**
   * 取得签证材料模板
   * 
   * @return
   */
  List<LabelValueBean> roGetDatas();
  /**
   * 办理签证的国家地区
   * @return
   */
  List<LabelValueBean> getAreas();
}
