package com.opentravelsoft.providers;

import com.opentravelsoft.entity.Pinyin;

public interface PinyinDao extends GenericDao<Pinyin, String> {
  String getPinyinByName(String word);
}
