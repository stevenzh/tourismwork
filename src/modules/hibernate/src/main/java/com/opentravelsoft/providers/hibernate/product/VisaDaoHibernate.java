package com.opentravelsoft.providers.hibernate.product;

import java.util.ArrayList;
import java.util.List;

import com.opentravelsoft.util.LabelValueBean;
import org.hibernate.LockMode;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.opentravelsoft.entity.Country;
import com.opentravelsoft.entity.TblVisaAttached;
import com.opentravelsoft.entity.TblVisaData;
import com.opentravelsoft.entity.TblVisaItem;
import com.opentravelsoft.entity.VisaHelp;
import com.opentravelsoft.entity.product.VisaItem;
import com.opentravelsoft.providers.hibernate.GenericDaoHibernate;
import com.opentravelsoft.providers.product.VisaDao;
import com.opentravelsoft.util.RowDataUtil;
import com.opentravelsoft.util.StringUtil;

/**
 * 签证服务
 */
@Repository("VisaDao")
public class VisaDaoHibernate extends GenericDaoHibernate<VisaHelp, String>
    implements VisaDao {

  public VisaDaoHibernate() {
    super(VisaHelp.class);
  }

  @SuppressWarnings("unchecked")
  public List<LabelValueBean> getAreas() {
    StringBuilder sql = new StringBuilder();
    sql.append("select distinct b.country,a.name ");
    sql.append("from Country a,");
    sql.append("VisaHelp b ");
    sql.append("where b.country=a.countryId ");
    List<Object[]> list = getHibernateTemplate().find(sql.toString());

    List<LabelValueBean> vehiclelist = new ArrayList<LabelValueBean>();
    for (Object[] obj : list) {
      vehiclelist.add(new LabelValueBean(RowDataUtil.getString(obj[0]),
          RowDataUtil.getString(obj[1])));
    }
    return vehiclelist;
  }

  @SuppressWarnings("unchecked")
  public List<VisaHelp> getUsableItems(String visaKind) {
    StringBuilder sql = new StringBuilder();

    sql.append("select a.recNo,b.countryId,b.name,a.subject,a.price1,");
    sql.append("a.price2,a.cost,a.unit,a.SDate,a.EDate,a.visaKind,");
    sql.append("a.stayDays,a.transactDays,a.isOpen,a.canQuick ");
    sql.append("from VisaHelp a,");
    sql.append("Country b ");
    sql.append("where a.country=b.countryId and a.opKey<>'D' ");
    sql.append(" and a.SDate<=current_date() ");
    sql.append(" and a.EDate>=current_date() ");
    if (StringUtil.hasLength(visaKind))
      sql.append(" and a.visaKind='" + visaKind + "' ");
    sql.append(" and a.isOpen=1 "); // 网站开放
    sql.append("order by b.name ");

    List<Object[]> list = getHibernateTemplate().find(sql.toString());

    List<VisaHelp> items = new ArrayList<VisaHelp>();
    VisaHelp visaItem = null;

    for (Object[] obj : list) {
      visaItem = new VisaHelp();
      visaItem.setRecordNo(RowDataUtil.getString(obj[0]));
      visaItem.setCountry(RowDataUtil.getString(obj[1]));
      visaItem.setCnName(RowDataUtil.getString(obj[2]));
      visaItem.setSubject(RowDataUtil.getString(obj[3]));
      visaItem.setMarketPrice(RowDataUtil.getBigDecimal(obj[4]));
      visaItem.setQuotedPrice(RowDataUtil.getBigDecimal(obj[5]));
      visaItem.setCostPrice(RowDataUtil.getBigDecimal(obj[6]));
      visaItem.setUnit(RowDataUtil.getString(obj[7]));
      visaItem.setStartDate(RowDataUtil.getDate(obj[8]));
      visaItem.setEndDate(RowDataUtil.getDate(obj[9]));
      visaItem.setVisaKind(RowDataUtil.getChar(obj[10]));
      visaItem.setStayDays(RowDataUtil.getString(obj[11]));
      visaItem.setTransactDays(RowDataUtil.getString(obj[12]));
      visaItem.setIsOpen(RowDataUtil.getInt(obj[13]));
      visaItem.setCanQuick(RowDataUtil.getInt(obj[14]));

      items.add(visaItem);
    }

    return items;
  }

  @SuppressWarnings("unchecked")
  public List<VisaHelp> getItems(String country) {
    StringBuilder sql = new StringBuilder();

    sql.append("select a.recNo,b.name,a.subject,a.price1,a.price2,");
    sql.append("a.cost,a.unit,a.SDate,a.EDate,a.visaKind,");
    sql.append("a.stayDays,a.transactDays,a.isOpen,a.canQuick ");
    sql.append("from VisaHelp a,");
    sql.append("Country b ");
    sql.append("where a.country=b.countryId and a.opKey<>'D' ");

    if (StringUtil.hasLength(country)) {
      sql.append("and a.country='" + country + "' ");
    }
    sql.append("order by b.name ");

    List<Object[]> list = getHibernateTemplate().find(sql.toString());

    List<VisaHelp> items = new ArrayList<VisaHelp>();
    VisaHelp visaItem = null;

    for (Object[] obj : list) {
      visaItem = new VisaHelp();
      visaItem.setRecordNo(RowDataUtil.getString(obj[0]));
      visaItem.setCnName(RowDataUtil.getString(obj[1]));
      visaItem.setSubject(RowDataUtil.getString(obj[2]));
      visaItem.setMarketPrice(RowDataUtil.getBigDecimal(obj[3]));
      visaItem.setQuotedPrice(RowDataUtil.getBigDecimal(obj[4]));
      visaItem.setCostPrice(RowDataUtil.getBigDecimal(obj[5]));
      visaItem.setUnit(RowDataUtil.getString(obj[6]));
      visaItem.setStartDate(RowDataUtil.getDate(obj[7]));
      visaItem.setEndDate(RowDataUtil.getDate(obj[8]));
      visaItem.setVisaKind(RowDataUtil.getChar(obj[9]));
      visaItem.setStayDays(RowDataUtil.getString(obj[10]));
      visaItem.setTransactDays(RowDataUtil.getString(obj[11]));
      visaItem.setIsOpen(RowDataUtil.getInt(obj[12]));
      visaItem.setCanQuick(RowDataUtil.getInt(obj[13]));

      items.add(visaItem);
    }

    return items;
  }

  @SuppressWarnings("unchecked")
  public VisaHelp getItem(String recordNo) {
    HibernateTemplate template = getHibernateTemplate();

    VisaHelp visahelp = (VisaHelp) template.get(VisaHelp.class, recordNo,
        LockMode.READ);
    String code = RowDataUtil.getString(visahelp.getCountry());
    VisaHelp item = new VisaHelp();
    Country country = (Country) template
        .get(Country.class, code, LockMode.NONE);

    item.setRecordNo(recordNo);
    item.setCountry(RowDataUtil.getString(visahelp.getCountry()));
    item.setCnName(RowDataUtil.getString(country.getName()));
    item.setSubject(RowDataUtil.getString(visahelp.getSubject()));
    item.setMarketPrice(RowDataUtil.getBigDecimal(visahelp.getPrice1()));
    item.setQuotedPrice(RowDataUtil.getBigDecimal(visahelp.getPrice2()));
    item.setCostPrice(RowDataUtil.getBigDecimal(visahelp.getCost()));
    item.setUnit(RowDataUtil.getString(visahelp.getUnit()));
    item.setStartDate(RowDataUtil.getDate(visahelp.getSDate()));
    item.setEndDate(RowDataUtil.getDate(visahelp.getEDate()));
    item.setNote(RowDataUtil.getString(visahelp.getNote()));
    item.setVisaKind(RowDataUtil.getChar(visahelp.getVisaKind()));
    item.setStayDays(RowDataUtil.getString(visahelp.getStayDays()));
    item.setTransactDays(RowDataUtil.getString(visahelp.getTransactDays()));
    item.setIsOpen(visahelp.getIsOpen());
    item.setCanQuick(visahelp.getCanQuick());

    StringBuilder sb = new StringBuilder();
    sb.append("from TblVisaItem ");
    sb.append("where recNo=? order by sortOrder ");

    Object[] param = { recordNo };

    List<TblVisaItem> list = getHibernateTemplate().find(sb.toString(), param);
    int idx = 0;
    for (TblVisaItem tblVisaItem : list) {
      VisaItem ite = new VisaItem();
      ite.setIdx(idx++);
      ite.setVisaitemId(tblVisaItem.getVisaitemId());
      ite.setItemId(tblVisaItem.getItemId());
      ite.setNum(tblVisaItem.getNum());
      ite.setOutline(tblVisaItem.getOutline());
      item.getItems().add(ite);
    }

    sb = new StringBuilder();
    sb.append("from TblVisaAttached ");
    sb.append("where id.recNo = ?");

    List<TblVisaAttached> list1 = getHibernateTemplate().find(sb.toString(),
        param);
    for (TblVisaAttached tblVisaItem : list1) {
      VisaItem ite = new VisaItem();
      ite.setAttachedId(tblVisaItem.getVisaAttachedId());
      ite.setFilePath(tblVisaItem.getFilePath());
      ite.setNote(tblVisaItem.getNote());
      item.getFileItems().add(ite);
    }

    return item;
  }

  @SuppressWarnings("unchecked")
  public boolean editItem(VisaHelp visaItem, List<VisaItem> items,
      List<VisaItem> fileItems, String target) {
    HibernateTemplate template = getHibernateTemplate();
    String recordNo = visaItem.getRecordNo();
    VisaHelp visahelp = (VisaHelp) template.get(VisaHelp.class, recordNo,
        LockMode.PESSIMISTIC_WRITE);

    if (target.equals("insert")) {
      if (visahelp != null) {
        return false;
      } else {
        visahelp = new VisaHelp();
        visahelp.setRecNo(visaItem.getRecordNo());
      }
    }

    if (target.equals("update") && (visahelp == null))
      return false;

    visahelp.setRecNo(visaItem.getRecordNo());
    visahelp.setCountry(visaItem.getCountry());
    visahelp.setSubject(visaItem.getSubject());
    visahelp.setPrice1(visaItem.getMarketPrice());
    visahelp.setPrice2(visaItem.getQuotedPrice());
    visahelp.setCost(visaItem.getCostPrice());
    visahelp.setUnit(visaItem.getUnit());
    visahelp.setSDate(visaItem.getStartDate());
    visahelp.setEDate(visaItem.getEndDate());
    visahelp.setNote(visaItem.getNote());
    visahelp.setOpKey('M');
    visahelp.setOpUser(visaItem.getOpUser());
    visahelp.setVisaKind(visaItem.getVisaKind());
    visahelp.setStayDays(visaItem.getStayDays());
    visahelp.setTransactDays(visaItem.getTransactDays());
    visahelp.setIsOpen(visaItem.getIsOpen());
    visahelp.setCanQuick(visaItem.getCanQuick());

    template.saveOrUpdate(visahelp);

    StringBuilder sb = new StringBuilder();
    sb.append("from TblVisaItem ");
    sb.append("where recNo = ?");
    Object[] param = { recordNo };
    List<TblVisaItem> list = getHibernateTemplate().find(sb.toString(), param);
    template.deleteAll(list);

    int idx = 0;
    for (VisaItem tblVisaItem : items) {
      TblVisaItem visaitem = new TblVisaItem();
      visaitem.setRecNo(visaItem.getRecordNo());
      visaitem.setItemId(tblVisaItem.getItemId());
      visaitem.setNum(tblVisaItem.getNum());
      visaitem.setOutline(tblVisaItem.getOutline());
      visaitem.setSortOrder(idx++);
      getHibernateTemplate().save(visaitem);
    }

    for (VisaItem tblVisaItem : fileItems) {
      TblVisaAttached visaAtta = new TblVisaAttached();
      visaAtta.setRecNo(visaItem.getRecordNo());
      visaAtta.setFilePath(tblVisaItem.getFilePath());
      visaAtta.setNote(tblVisaItem.getNote());
      getHibernateTemplate().save(visaAtta);
    }

    return true;
  }

  public boolean deleteItem(String recordNo, Integer opUser) {
    HibernateTemplate template = getHibernateTemplate();
    VisaHelp visahelp = (VisaHelp) template.get(VisaHelp.class, recordNo,
        LockMode.PESSIMISTIC_WRITE);
    if (visahelp != null) {
      visahelp.setOpUser(opUser);
      visahelp.setOpKey('D');
      template.update(visahelp);
    }
    return false;
  }

  @SuppressWarnings("unchecked")
  public boolean delAttached(int attachedId) {
    StringBuilder sb = new StringBuilder();
    HibernateTemplate template = getHibernateTemplate();
    sb.append("from TblVisaAttached ");
    sb.append("where visaAttachedId = ?");
    Object[] param = { attachedId };
    List<TblVisaAttached> list1 = getHibernateTemplate().find(sb.toString(),
        param);
    template.deleteAll(list1);

    return true;
  }

  public VisaItem getVisaAttached(int attachedId) {
    HibernateTemplate template = getHibernateTemplate();
    TblVisaAttached attached = (TblVisaAttached) template.get(
        TblVisaAttached.class, attachedId);

    VisaItem item = null;
    if (attached != null) {
      item = new VisaItem();
      item.setFilePath(attached.getFilePath());
    }
    return item;
  }

  @SuppressWarnings("unchecked")
  public List<LabelValueBean> getDatas() {
    StringBuilder sb = new StringBuilder();
    sb.append("from com.opentravelsoft.entity.TblVisaData ");
    List<TblVisaData> list1 = getHibernateTemplate().find(sb.toString());

    List<LabelValueBean> list = new ArrayList<LabelValueBean>();
    for (TblVisaData tblVisaAttached : list1) {
      list.add(new LabelValueBean(String.valueOf(tblVisaAttached.getItemId()),
          tblVisaAttached.getName()));
    }

    return list;
  }
}
