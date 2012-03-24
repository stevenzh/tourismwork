package com.opentravelsoft.json;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.Action;
import com.opentravelsoft.action.ManageAction;
import com.opentravelsoft.providers.PinyinDao;

/**
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:24:16 $
 */
public class PinyinAction extends ManageAction {
  private static final long serialVersionUID = -5840178415466047565L;

  private String pinyin;

  private String name;

  private PinyinDao pinyinDao;

  @Autowired
  public void setPinyinDao(PinyinDao pinyinDao) {
    this.pinyinDao = pinyinDao;
  }

  public String execute() {
    pinyin = pinyinDao.getPinyinByName(name);
    return Action.SUCCESS;
  }

  public String getPinyin() {
    return pinyin;
  }

  public void setPinyin(String pinyin) {
    this.pinyin = pinyin;
  }

  public void setName(String name) {
    this.name = name;
  }

}
