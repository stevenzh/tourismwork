package com.opentravelsoft.providers.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.hibernate.LockMode;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.opentravelsoft.entity.Guide;
import com.opentravelsoft.entity.Pinyin;
import com.opentravelsoft.providers.GuideDao;
import com.opentravelsoft.util.RowDataUtil;
import com.opentravelsoft.util.StringUtil;

@Repository("GuideDao")
public class GuideDaoImpl extends GenericDaoHibernate<Guide, String> implements
    GuideDao {

  public GuideDaoImpl() {
    super(Guide.class);
  }

  public int deleteGuide(String accCd) {
    HibernateTemplate template = getHibernateTemplate();
    Guide guide = (Guide) template.get(Guide.class, accCd, LockMode.PESSIMISTIC_WRITE);
    template.delete(guide);

    return 0;
  }

  public Guide getGuideDetail(String accCd) {
    Guide guide = null;
    HibernateTemplate template = getHibernateTemplate();
    Guide wlt = (Guide) template.get(Guide.class, accCd, LockMode.PESSIMISTIC_WRITE);
    if (wlt == null) {
      return wlt;
    } else {
      guide = new Guide();
      guide.setAccCd(RowDataUtil.getString(wlt.getAccCd()));
      guide.setAccNm(RowDataUtil.getString(wlt.getAccNm()));
      guide.setPinyin(wlt.getPinyin());
      guide.setAccSex(RowDataUtil.getString(wlt.getAccSex()));
      guide.setTeamId(RowDataUtil.getInt(wlt.getTeamId()));
      guide.setIdCard(RowDataUtil.getString(wlt.getIdCard()));
      guide.setLeadCard(RowDataUtil.getString(wlt.getLeadCard()));
      guide.setBusiness(RowDataUtil.getString(wlt.getBusiness()));
      guide.setBloodType(RowDataUtil.getString(wlt.getBloodType()));
      guide.setHigh(RowDataUtil.getString(wlt.getHigh()));
      guide.setLeaderKey(RowDataUtil.getString(wlt.getLeaderKey()));
      guide.setDateStart(RowDataUtil.getDate(wlt.getDateStart()));
      guide.setDateEnd(RowDataUtil.getDate(wlt.getDateEnd()));
      guide.setWeight(RowDataUtil.getString(wlt.getWeight()));
      guide.setDuty(RowDataUtil.getString(wlt.getDuty()));
      guide.setTourCard(RowDataUtil.getString(wlt.getTourCard()));
      guide.setTourKey(RowDataUtil.getString(wlt.getTourKey()));
      guide.setCheckDate(RowDataUtil.getDate(wlt.getCheckDate()));
      guide.setIcCard(RowDataUtil.getString(wlt.getIcCard()));
      guide.setRemarks1(RowDataUtil.getString(wlt.getRemarks1()));
      guide.setWorkType1(RowDataUtil.getString(wlt.getWorkType1()));
      guide.setWorkType2(RowDataUtil.getString(wlt.getWorkType2()));
      guide.setTel(RowDataUtil.getString(wlt.getTel()));
      guide.setBp(RowDataUtil.getString(wlt.getBp()));
      guide.setMobile(RowDataUtil.getString(wlt.getMobile()));
      guide.setEMail(RowDataUtil.getString(wlt.getEMail()));
      guide.setAddress(RowDataUtil.getString(wlt.getAddress()));
      guide.setCharacter(RowDataUtil.getString(wlt.getCharacter()));
      guide.setTaste(RowDataUtil.getString(wlt.getTaste()));
      guide.setSpeciality(RowDataUtil.getString(wlt.getSpeciality()));
      guide.setWorkRemark(RowDataUtil.getString(wlt.getWorkRemark()));
      guide.setFinishSchool(RowDataUtil.getString(wlt.getFinishSchool()));
      guide.setFinishDate(RowDataUtil.getDate(wlt.getFinishDate()));
      guide.setBirthday(RowDataUtil.getDate(wlt.getBirthday()));
      guide.setBirthplace(RowDataUtil.getString(wlt.getBirthplace()));
      guide.setPassportType(RowDataUtil.getString(wlt.getPassportType()));
      guide.setPassportNo(RowDataUtil.getString(wlt.getPassportNo()));
      guide.setPassportPlace(RowDataUtil.getString(wlt.getPassportPlace()));
      guide.setPassportDate(RowDataUtil.getDate(wlt.getPassportDate()));
      guide.setPassportExpiry(RowDataUtil.getDate(wlt.getPassportExpiry()));
      guide.setPhotoFile(RowDataUtil.getString(wlt.getPhotoFile()));
      guide.setHkPass(RowDataUtil.getString(wlt.getHkPass()));
    }

    return guide;
  }

  public int insertGuide(Guide guide) {
    HibernateTemplate template = getHibernateTemplate();
    Guide wlt = (Guide) template.get(Guide.class, guide.getAccCd(),
        LockMode.PESSIMISTIC_WRITE);
    if (null != wlt) {
      return -1;
    }
    wlt = new Guide();
    wlt.setAccCd(guide.getAccCd());
    wlt.setAccNm(guide.getAccNm());
    if (!StringUtil.hasLength(guide.getPinyin())) {
      guide.setPinyin(supplyYin(guide.getAccNm()));
    }
    wlt.setPinyin(guide.getPinyin());
    wlt.setAccSex(guide.getAccSex());
    wlt.setTeamId(guide.getTeamId());
    wlt.setIdCard(guide.getIdCard());
    wlt.setLeadCard(guide.getLeadCard());
    wlt.setBusiness(guide.getBusiness());
    wlt.setBloodType(guide.getBloodType());
    wlt.setHigh(guide.getHigh());
    wlt.setLeaderKey(guide.getLeaderKey());
    wlt.setDateStart(guide.getDateStart());
    wlt.setDateEnd(guide.getDateEnd());
    wlt.setWeight(guide.getWeight());
    wlt.setDuty(guide.getDuty());
    wlt.setTourCard(guide.getTourCard());
    wlt.setTourKey(guide.getTourKey());
    wlt.setCheckDate(guide.getCheckDate());
    wlt.setIcCard(guide.getIcCard());
    wlt.setRemarks1(guide.getRemarks1());
    wlt.setWorkType1(guide.getWorkType1());
    wlt.setWorkType2(guide.getWorkType2());
    wlt.setSignKey(guide.getSignKey());
    wlt.setTel(guide.getTel());
    wlt.setMobile(guide.getMobile());
    wlt.setEMail(guide.getEMail());
    wlt.setAddress(guide.getAddress());
    wlt.setCharacter(guide.getCharacter());
    wlt.setTaste(guide.getTaste());
    wlt.setSpeciality(guide.getSpeciality());
    wlt.setWorkRemark(guide.getWorkRemark());
    wlt.setFinishSchool(guide.getFinishSchool());
    wlt.setFinishDate(guide.getFinishDate());
    wlt.setBirthday(guide.getBirthday());
    wlt.setBirthplace(guide.getBirthplace());
    wlt.setPassportType(guide.getPassportType());
    wlt.setPassportNo(guide.getPassportNo());
    wlt.setPassportPlace(guide.getPassportPlace());
    wlt.setPassportDate(guide.getPassportDate());
    wlt.setPassportExpiry(guide.getPassportExpiry());
    wlt.setPhotoFile(guide.getPhotoFile());
    wlt.setHkPass(guide.getHkPass());
    template.save(wlt);

    return 0;
  }

  public int updateGuide(Guide guide) {
    HibernateTemplate template = getHibernateTemplate();
    Guide wlt = (Guide) template.get(Guide.class, guide.getAccCd(),
        LockMode.PESSIMISTIC_WRITE);
    if (null == wlt) {
      return -1;
    }
    wlt.setAccNm(guide.getAccNm());
    if (!StringUtil.hasLength(guide.getPinyin())) {
      guide.setPinyin(supplyYin(guide.getAccNm()));
    }
    wlt.setPinyin(guide.getPinyin());
    wlt.setAccSex(guide.getAccSex());
    wlt.setTeamId(guide.getTeamId());
    wlt.setIdCard(guide.getIdCard());
    wlt.setLeadCard(guide.getLeadCard());
    wlt.setBusiness(guide.getBusiness());
    wlt.setBloodType(guide.getBloodType());
    wlt.setHigh(guide.getHigh());
    wlt.setLeaderKey(RowDataUtil.getString(guide.getLeaderKey()));
    wlt.setDateStart(guide.getDateStart());
    wlt.setDateEnd(guide.getDateEnd());
    wlt.setWeight(guide.getWeight());
    wlt.setDuty(guide.getDuty());
    wlt.setTourCard(guide.getTourCard());
    wlt.setTourKey(RowDataUtil.getString(guide.getTourKey()));
    wlt.setCheckDate(guide.getCheckDate());
    wlt.setIcCard(guide.getIcCard());
    wlt.setRemarks1(guide.getRemarks1());
    wlt.setWorkType1(RowDataUtil.getString(guide.getWorkType1()));
    wlt.setWorkType2(RowDataUtil.getString(guide.getWorkType2()));
    wlt.setSignKey(RowDataUtil.getString(guide.getSignKey()));
    wlt.setTel(guide.getTel());
    wlt.setMobile(guide.getMobile());
    wlt.setEMail(guide.getEMail());
    wlt.setAddress(guide.getAddress());
    wlt.setCharacter(guide.getCharacter());
    wlt.setTaste(guide.getTaste());
    wlt.setSpeciality(guide.getSpeciality());
    wlt.setWorkRemark(guide.getWorkRemark());
    wlt.setFinishSchool(guide.getFinishSchool());
    wlt.setFinishDate(guide.getFinishDate());
    wlt.setBirthday(guide.getBirthday());
    wlt.setBirthplace(guide.getBirthplace());
    wlt.setPassportType(guide.getPassportType());
    wlt.setPassportNo(guide.getPassportNo());
    wlt.setPassportPlace(guide.getPassportPlace());
    wlt.setPassportDate(guide.getPassportDate());
    wlt.setPassportExpiry(guide.getPassportExpiry());
    wlt.setPhotoFile(guide.getPhotoFile());
    wlt.setHkPass(guide.getHkPass());
    template.update(wlt);

    return 0;
  }

  @SuppressWarnings("unchecked")
  public List<Guide> queryGuide(String accNm) {
    Guide newguide = null;
    HibernateTemplate template = getHibernateTemplate();
    StringBuilder sql = new StringBuilder();
    sql.append("from Guide ");
    if (StringUtil.hasLength(accNm))
      sql.append("where accNm like '%" + accNm + "%'");
    List<Guide> list = template.find(sql.toString());
    List<Guide> guide = new ArrayList<Guide>();
    for (Guide obj : list) {
      newguide = new Guide();
      newguide.setAccCd(RowDataUtil.getString(obj.getAccCd()));
      newguide.setAccNm(RowDataUtil.getString(obj.getAccNm()));
      newguide.setPinyin(obj.getPinyin());
      newguide.setAccSex(RowDataUtil.getString(obj.getAccSex()));
      newguide.setTeamId(RowDataUtil.getInt(obj.getTeamId()));
      newguide.setIdCard(RowDataUtil.getString(obj.getIdCard()));
      newguide.setLeadCard(RowDataUtil.getString(obj.getLeadCard()));
      newguide.setBusiness(RowDataUtil.getString(obj.getBusiness()));
      newguide.setBloodType(RowDataUtil.getString(obj.getBloodType()));
      newguide.setHigh(RowDataUtil.getString(obj.getHigh()));
      newguide.setLeaderKey(RowDataUtil.getString(obj.getLeaderKey()));
      newguide.setDateStart(RowDataUtil.getDate(obj.getDateStart()));
      newguide.setDateEnd(RowDataUtil.getDate(obj.getDateEnd()));
      newguide.setWeight(RowDataUtil.getString(obj.getWeight()));
      newguide.setDuty(RowDataUtil.getString(obj.getDuty()));
      newguide.setTourCard(RowDataUtil.getString(obj.getTourCard()));
      newguide.setTourKey(RowDataUtil.getString(obj.getTourKey()));
      newguide.setCheckDate(RowDataUtil.getDate(obj.getCheckDate()));
      newguide.setIcCard(RowDataUtil.getString(obj.getIcCard()));
      newguide.setRemarks1(RowDataUtil.getString(obj.getRemarks1()));
      newguide.setWorkType1(RowDataUtil.getString(obj.getWorkType1()));
      newguide.setWorkType2(RowDataUtil.getString(obj.getWorkType2()));
      newguide.setTel(RowDataUtil.getString(obj.getTel()));
      newguide.setBp(RowDataUtil.getString(obj.getBp()));
      newguide.setMobile(RowDataUtil.getString(obj.getMobile()));
      newguide.setEMail(RowDataUtil.getString(obj.getEMail()));
      newguide.setAddress(RowDataUtil.getString(obj.getAddress()));
      newguide.setCharacter(RowDataUtil.getString(obj.getCharacter()));
      newguide.setTaste(RowDataUtil.getString(obj.getTaste()));
      newguide.setSpeciality(RowDataUtil.getString(obj.getSpeciality()));
      newguide.setWorkRemark(RowDataUtil.getString(obj.getWorkRemark()));
      newguide.setFinishSchool(RowDataUtil.getString(obj.getFinishSchool()));
      newguide.setFinishDate(RowDataUtil.getDate(obj.getFinishDate()));
      newguide.setBirthday(RowDataUtil.getDate(obj.getBirthday()));
      newguide.setBirthplace(RowDataUtil.getString(obj.getBirthplace()));
      newguide.setPassportType(RowDataUtil.getString(obj.getPassportType()));
      newguide.setPassportNo(RowDataUtil.getString(obj.getPassportNo()));
      newguide.setPassportPlace(RowDataUtil.getString(obj.getPassportPlace()));
      newguide.setPassportDate(RowDataUtil.getDate(obj.getPassportDate()));
      newguide.setPassportExpiry(RowDataUtil.getDate(obj.getPassportExpiry()));
      newguide.setPhotoFile(RowDataUtil.getString(obj.getPhotoFile()));
      newguide.setHkPass(RowDataUtil.getString(obj.getHkPass()));
      guide.add(newguide);
    }

    return guide;
  }

  @SuppressWarnings("unchecked")
  private String supplyYin(String str) {
    StringBuilder sql = new StringBuilder();
    sql.append("from Pinyin ");
    sql.append("where chinese in (");

    String pinyin = "";
    StringBuilder py = new StringBuilder();
    char[] ch = str.toCharArray();
    StringBuilder sb = new StringBuilder();
    int count = 0;
    for (char c : ch) {
      sb.append("'" + c + "',");
    }
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
    pinyin = py.toString().trim();

    return pinyin;
  }

}
