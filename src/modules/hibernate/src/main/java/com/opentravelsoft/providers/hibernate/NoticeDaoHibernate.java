package com.opentravelsoft.providers.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.opentravelsoft.entity.product.Notice;
import com.opentravelsoft.providers.NoticeDao;
import com.opentravelsoft.util.RowDataUtil;

@Repository("NoticeDao")
public class NoticeDaoHibernate extends GenericDaoHibernate<Notice, Long>
    implements NoticeDao {

  public NoticeDaoHibernate() {
    super(Notice.class);
  }

  @SuppressWarnings("unchecked")
  public List<Notice> getValidNotices() {
    StringBuilder sql = new StringBuilder();
    sql.append("select a.recNo,a.title,a.text,a.groupId,b.name,");
    sql.append("a.opDate,a.savingDate ");
    sql.append("from Tfx005 a,");
    sql.append("Team b ");
    sql.append("where a.teamId=b.teamId ");
    sql.append("and a.savingDate >= current_date() ");
    sql.append("order by a.opDate desc");

    List<Object[]> list = getHibernateTemplate().find(sql.toString());
    List<Notice> ret = new ArrayList<Notice>();

    for (Object[] obj : list) {
      Notice notice = new Notice();
      notice.setNoticeId(RowDataUtil.getString(obj[0]));
      notice.setSubject(RowDataUtil.getString(obj[1]));
      notice.setText(RowDataUtil.getString(obj[2]));
      notice.getDepartment().setGroupId(RowDataUtil.getInt(obj[3]));
      notice.getDepartment().setName(RowDataUtil.getString(obj[4]));
      notice.setStartingTime(RowDataUtil.getDate(obj[5]));
      notice.setDeadline(RowDataUtil.getDate(obj[6]));
      ret.add(notice);
    }

    return ret;
  }

}
