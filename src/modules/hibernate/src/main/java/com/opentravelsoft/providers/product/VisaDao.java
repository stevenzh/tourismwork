package com.opentravelsoft.providers.product;

import java.util.List;

import com.opentravelsoft.util.LabelValueBean;

import com.opentravelsoft.entity.VisaHelp;
import com.opentravelsoft.entity.product.VisaItem;
import com.opentravelsoft.providers.GenericDao;

public interface VisaDao extends GenericDao<VisaHelp, String> {

  /**
   * 办理签证的国家地区
   * @return
   */
  List<LabelValueBean> getAreas();

  List<VisaHelp> getItems(String country);

  VisaHelp getItem(String recordNo);

  boolean deleteItem(String recordNo, long opUser);

  /**
   * 取得可用签证列表,网站使用
   * 
   * @param visaKind 签证类型
   * @return
   */
  List<VisaHelp> getUsableItems(String visaKind);

  boolean editItem(VisaHelp visaItem, List<VisaItem> items,
      List<VisaItem> fileItems, String target);

  boolean delAttached(int attachedId);

  VisaItem getVisaAttached(int attachedId);

  /**
   * 取得签证材料模板
   * 
   * @return
   */
  List<LabelValueBean> getDatas();

}
