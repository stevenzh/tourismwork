package com.opentravelsoft.service;

import java.util.List;

import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opentravelsoft.entity.Country;
import com.opentravelsoft.entity.VisaHelp;
import com.opentravelsoft.entity.product.VisaItem;
import com.opentravelsoft.providers.CountryDao;
import com.opentravelsoft.providers.SequenceDao;
import com.opentravelsoft.providers.product.VisaDao;
import com.opentravelsoft.util.StringUtil;

@Service("VisaHelpService")
public class VisaHelpServiceImpl implements VisaHelpService {

  @Autowired
  private VisaDao visaDao;

  @Autowired
  private CountryDao countryDao;

  @Autowired
  private SequenceDao sequenceDao;

  public List<Country> roGetCountrys() {
    return countryDao.getCountry();
  }

  public List<VisaHelp> roGetVisaItems(String country) {
    return visaDao.getItems(country);
  }

  public VisaHelp roGetVisaItem(String recordNo) {
    return visaDao.getItem(recordNo);
  }

  public boolean txDeleteVisaItem(String recordNo, int opUser) {
    return visaDao.deleteItem(recordNo, opUser);
  }

  public boolean txEditVisaItem(VisaHelp visaItem, List<VisaItem> items,
      List<VisaItem> fileItems) {
    String target = "update";
    if (!StringUtil.hasLength(visaItem.getRecordNo())) {
      String recNo = sequenceDao.getComputerNo("Q", 0);
      visaItem.setRecordNo(recNo);
      target = "insert";
    }

    return visaDao.editItem(visaItem, items, fileItems, target);
  }

  public boolean txDelAttached(int attachedId) {
    return visaDao.delAttached(attachedId);
  }

  public VisaItem roGetVisaAttached(int attachedId) {
    return visaDao.getVisaAttached(attachedId);
  }

  public List<LabelValueBean> roGetDatas() {
    return visaDao.getDatas();
  }

  public List<VisaHelp> roGetUsableVisaItems(String visaKind) {
    return visaDao.getUsableItems(visaKind);
  }

  @Override
  public List<LabelValueBean> getAreas() {
    return visaDao.getAreas();
  }

}
