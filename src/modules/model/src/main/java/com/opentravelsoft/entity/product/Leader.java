package com.opentravelsoft.entity.product;

import com.opentravelsoft.entity.Tourist;

/**
 * 领队
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 */
public class Leader extends Tourist {

  private String leadCard;

  public String getLeadCard() {
    return leadCard;
  }

  public void setLeadCard(String leadCard) {
    this.leadCard = leadCard;
  }

}
