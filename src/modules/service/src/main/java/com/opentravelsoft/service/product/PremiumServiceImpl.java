package com.opentravelsoft.service.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opentravelsoft.entity.Premium;
import com.opentravelsoft.providers.product.PremiumDao;

@Service("PremiumService")
public class PremiumServiceImpl implements PremiumService {
  
  @Autowired
  private PremiumDao premiumDao;

  public List<Premium> roFind() {
    return premiumDao.findAll();
  }

  public int txDeletePrem(String preminuCode) {
    return premiumDao.deletePrem(preminuCode);
  }

  public int txEditPrem(Premium tblPremium) {
    return premiumDao.editPrem(tblPremium);
  }

  public Premium roGetPrem(String preminuCode) {
    return premiumDao.get(preminuCode);
  }

}
