package com.opentravelsoft.providers.hibernate;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Repository;

import com.opentravelsoft.entity.Pinyin;
import com.opentravelsoft.providers.PinyinDao;

@Repository("PinyinDao")
public class PinyinDaoHibernate extends GenericDaoHibernate<Pinyin, String>
    implements PinyinDao {
  public PinyinDaoHibernate() {
    super(Pinyin.class);
  }

  @SuppressWarnings("unchecked")
  public String getPinyinByName(String name) {
    StringBuilder sql = new StringBuilder();
    sql.append("from Pinyin ");
    sql.append("where chinese in (");

    StringBuilder py = new StringBuilder();
    char[] ch = name.toCharArray();
    StringBuilder sb = new StringBuilder();
    int count = 0;

    for (char c : ch) {
      sb.append("'" + c + "',");
    }

    if (sb.length() > 0) {
      List<Pinyin> pys = getHibernateTemplate().find(
          sql.toString() + sb.substring(0, sb.length() - 1) + ")");
      Map<String, String> map = new TreeMap<String, String>();
      for (Pinyin tfj112 : pys) {
        map.put(tfj112.getChinese(), tfj112.getEnglish());
      }

      for (char c : ch) {
        if (null == map.get(String.valueOf(c)))
          py.append(c);
        else {
          py.append(map.get(String.valueOf(c)));
          count++;
          if (count == 1)
            py.append(' ');
        }
      }

    }
    return py.toString().trim();
  }
}
