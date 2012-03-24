package com.opentravelsoft.service.product;

import java.util.List;

import com.opentravelsoft.entity.Premium;

public interface PremiumService
{

    List<Premium> roFind();

    Premium roGetPrem(String preminuCode);

    int txEditPrem(Premium tblPremium);

    int txDeletePrem(String preminuCode);

}
