package com.opentravelsoft.providers.hibernate.product;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.LockMode;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.opentravelsoft.entity.District;
import com.opentravelsoft.entity.LineDistrict;
import com.opentravelsoft.entity.LineDistrictId;
import com.opentravelsoft.providers.hibernate.GenericDaoHibernate;
import com.opentravelsoft.providers.product.LineDistrictDao;
import com.opentravelsoft.util.RowDataUtil;
import com.opentravelsoft.util.StringUtil;

/**
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 */
@Repository("RouteDistrictDao")
public class LineDistrictDaoImpl extends
    GenericDaoHibernate<LineDistrict, Long> implements LineDistrictDao {

  public LineDistrictDaoImpl() {
    super(LineDistrict.class);
  }

  @SuppressWarnings("unchecked")
  public List<District> getDomesticLineDistrict(String lineNo) {
    StringBuilder sql = new StringBuilder();
    sql.append("select a.id.districtNo,b.cnName,b.province.cnName,");
    sql.append("b.cnNote ");
    sql.append("from LineDistrict as a,");
    sql.append("District as b ");
    sql.append("where a.id.lineNo=? and a.id.districtNo=b.districtNo ");

    Object[] params = { lineNo };
    List<Object[]> list = getHibernateTemplate().find(sql.toString(), params);
    District district = null;
    List<District> districtList = new ArrayList<District>();
    for (Object[] obj : list) {
      district = new District();
      district.setDistrictNo(RowDataUtil.getString(obj[0]));
      district.setCnName(RowDataUtil.getString(obj[1]).trim());
      district.getProvince().setCnName(RowDataUtil.getString(obj[2]).trim());
      district.setCnNote(RowDataUtil.getString(obj[3]));
      districtList.add(district);
    }

    return districtList;
  }

  @SuppressWarnings("unchecked")
  public List<District> getOverseaLineDistrict(String lineNo) {
    StringBuilder sql = new StringBuilder();
    sql.append("select a.id.districtNo,b.cnName,b.country.name,b.cnNote ");
    sql.append("from LineDistrict as a,");
    sql.append("District as b ");
    sql.append("where a.id.lineNo=? and a.id.districtNo=b.districtNo ");

    Object[] params = { lineNo };
    List<Object[]> list = getHibernateTemplate().find(sql.toString(), params);
    District district = null;
    List<District> districtList = new ArrayList<District>();
    for (Object[] obj : list) {
      district = new District();
      district.setDistrictNo(RowDataUtil.getString(obj[0]));
      district.setCnName(RowDataUtil.getString(obj[1]));
      district.getCountry().setName(RowDataUtil.getString(obj[2]));
      district.setCnNote(RowDataUtil.getString(obj[3]));
      districtList.add(district);
    }
    return districtList;
  }

  @SuppressWarnings("unchecked")
  public List<District> findOverseaLineDistrict(String countryNo,
      String provinceNo, String lineNo) {
    StringBuilder sql = new StringBuilder();
    sql.append("from District ");
    sql.append("where districtNo not in ");
    sql.append("(select id.districtNo ");
    sql.append("from LineDistrict ");
    sql.append("where id.lineNo=? ) ");

    Object[] params = { lineNo };

    if (StringUtil.hasLength(provinceNo)) {
      sql.append("and province.code=? ");
      params = new Object[2];
      params[0] = lineNo;
      params[1] = provinceNo;
    } else if (StringUtil.hasLength(countryNo)) {
      sql.append("and country.countryId=? ");
      params = new Object[2];
      params[0] = lineNo;
      params[1] = countryNo;
    }

    List<District> list = getHibernateTemplate().find(sql.toString(), params);
    return list;
  }

  @SuppressWarnings("unchecked")
  public void saveLineDistrict(List<District> list, String lineNo) {
    HibernateTemplate template = getHibernateTemplate();
    List<String> keyList = new ArrayList<String>();
    for (District obj : list) {
      LineDistrictId districtId = new LineDistrictId();
      districtId.setLineNo(lineNo);
      districtId.setDistrictNo(obj.getDistrictNo());
      LineDistrict lineDistrict = (LineDistrict) template.get(
          LineDistrict.class, districtId, LockMode.PESSIMISTIC_WRITE);
      if (lineDistrict == null) {
        lineDistrict = new LineDistrict();
        lineDistrict.setId(districtId);
        template.save(lineDistrict);
      }
      keyList.add(obj.getDistrictNo());
    }
    StringBuilder sql = new StringBuilder();
    sql.append("from LineDistrict where id.lineNo=? ");
    Object[] params = { lineNo };
    List<LineDistrict> oldList = template.find(sql.toString(), params);
    for (LineDistrict ooj : oldList) {
      if (!keyList.contains((ooj.getId()).getDistrictNo())) {
        template.delete(ooj);
      }
    }
  }

}
