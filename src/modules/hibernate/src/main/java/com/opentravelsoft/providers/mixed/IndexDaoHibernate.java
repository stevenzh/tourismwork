package com.opentravelsoft.providers.mixed;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.opentravelsoft.common.KeyParams;
import com.opentravelsoft.entity.Destination;
import com.opentravelsoft.entity.LineDescription;
import com.opentravelsoft.entity.LinePrice;
import com.opentravelsoft.entity.Plan;
import com.opentravelsoft.entity.Province;
import com.opentravelsoft.entity.Line;
import com.opentravelsoft.entity.Sight;
import com.opentravelsoft.providers.SimpleHibernateDaoSupport;
import com.opentravelsoft.util.RowDataUtil;

/**
 * 索引数据库查询
 * 
 */
public class IndexDaoHibernate extends SimpleHibernateDaoSupport implements
    CbIndexDao {

  /**
   * 单个索引数据
   */
  @SuppressWarnings("unchecked")
  public Line getLineIndex(String lineNo) {
    SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMdd");
    StringBuilder sql = new StringBuilder();
    HibernateTemplate template = getHibernateTemplate();
    sql.append("from Line ");
    sql.append("where lineNo=? and delKey='N' ");
    sql.append("and isActive=true ");
    Object[] param = { lineNo };
    List<Line> list = template.find(sql.toString(), param);

    if (!list.isEmpty()) {
      Line lineIndex = list.get(0);

      // 线路特色
      sql = new StringBuilder();
      sql.append("fromLineDescription ");
      sql.append("where lineNo=? and type='" + KeyParams.EBIZ_TYPE_LINE_FEATURE
          + "' ");
      List<LineDescription> list1 = template.find(sql.toString(), lineNo);

      for (LineDescription trait : list1) {
        lineIndex.setRouteTrait(lineIndex.getRouteTrait() + "*"
            + RowDataUtil.getString(trait.getServiceDetail()) + ";");
      }

      // 出团计划和报价
      sql = new StringBuilder();
      sql.append("from Plan ");
      sql.append("where line.lineNo=? ");
      sql.append("and deployFlag='Y' ");
      sql.append("and outDate>current_date() ");
      // sql.append("and deadline>=current_date() ");
      sql.append("order by outDate ");

      List<Plan> planList = template
          .find(sql.toString(), lineIndex.getLineNo());
      if (!planList.isEmpty()) {
        // 是否有特惠团
        for (Plan objects : planList) {
          if ('Y' == RowDataUtil.getChar(objects.getFavourable())) {
            lineIndex.setIsPrefer("Y");
            break;
          }
        }

        // 此线路对应的最近的未出发的团的出团日期
        Date date1 = new Date();
        String str1 = "";
        String str2 = "";
        for (Plan planTemp : planList) {
          date1 = RowDataUtil.getDate(planTemp.getOutDate());
          if (null != date1) {
            str1 = str1 + SDF.format(date1) + ",";
            str2 = str2 + SDF.format(date1) + ",";
          }
        }
        lineIndex.setOutDate_price1(str1);
        lineIndex.setOutDate_price2(str2);

        // 出团计划的个数
        lineIndex.setPlanPax(String.valueOf(planList.size()));
        // 取最近的出团计划和对应报价
        Plan plan = planList.get(0);
        lineIndex.setOutDate(RowDataUtil.getDate(plan.getOutDate()));
        lineIndex.setOutDateStr(SDF.format(lineIndex.getOutDate()));

        // 如果tfj005的字段priceNo不为"00000000"时,其对应有相应的价格
        LinePrice tfa606 = (LinePrice) template.get(LinePrice.class, plan
            .getPackagePrice().getRecNo());
        if (null != tfa606) {
          // 直客价
          lineIndex.setPrice1(RowDataUtil.getDouble(tfa606.getPrice()));
          // 同行价
          lineIndex.setPrice2(RowDataUtil.getDouble(tfa606.getPriceOther()));
          String price1S = String.valueOf(lineIndex.getPrice1());
          String price2S = String.valueOf(lineIndex.getPrice2());
          // 将字符串补足8位,以利于搜索时价格的比较
          String[] strs = { "", "0", "00", "000", "0000", "00000", "000000",
              "0000000" };
          if (price1S.length() < 8)
            lineIndex.setPrice1Str(strs[8 - price1S.length()].concat(price1S));
          if (price2S.length() < 8)
            lineIndex.setPrice2Str(strs[8 - price2S.length()].concat(price2S));
        }
      }

      // 线路目的地
      sql = new StringBuilder();
      sql.append("select a.id.districtNo,b.cnName,b.country, ");
      sql.append("b.duchy.code,c.name ");
      sql.append("from LineDistrict a,");
      sql.append("District b, ");
      sql.append("Country c ");
      sql.append("where a.id.lineNo=? ");
      sql.append("and a.id.districtNo=b.districtNo ");
      sql.append("and b.country=c.countryId ");

      List<Object[]> list4 = template.find(sql.toString(), lineNo);
      for (Object[] obj4 : list4) {
        String str4 = RowDataUtil.getString(obj4[3]).trim();
        if (null != obj4[3] && !str4.equals("")) {
          Province province = (Province) getHibernateTemplate().get(
              Province.class, str4);
          if (null != province)
            lineIndex
                .setDistrictProvinceName(province.getCnName().trim() + " ");
        }
        lineIndex.setDistrictNo(lineIndex.getDistrictNo()
            + RowDataUtil.getString(obj4[0]) + " ");
        lineIndex.setDistrictName(lineIndex.getDistrictName()
            + RowDataUtil.getString(obj4[1]).trim() + " ");
        lineIndex.setDistrictCountry(lineIndex.getDistrictCountry()
            + RowDataUtil.getString(obj4[2]) + " ");
        lineIndex.setDistrictProvince(lineIndex.getDistrictProvince()
            + RowDataUtil.getString(obj4[3]) + " ");
        lineIndex.setDistrictCountryName(lineIndex.getDistrictCountryName()
            + RowDataUtil.getString(obj4[4]).trim() + " ");

      }

      // 景点
      sql = new StringBuilder();
      sql.append("select select l.sights from Line l where l.lineNo=? ");

      List<Sight> list5 = template.find(sql.toString(), lineNo);
      for (Sight obj5 : list5) {
        lineIndex.setSightNo(lineIndex.getSightNo()
            + RowDataUtil.getString(obj5.getSightNo()) + " ");
        lineIndex.setSightName(lineIndex.getSightName()
            + RowDataUtil.getString(obj5.getName()) + " ");
      }

      // 找目的地所在的区域
      lineIndex.setClassifyRegion(RowDataUtil.getString(lineIndex
          .getDestination().getCnName()));
      lineIndex.setClassifyRegionId(RowDataUtil.getString(lineIndex
          .getDestination().getCode()));

      return lineIndex;
    }
    return null;
  }

  /**
   * 所有附合条件和索引
   */
  @SuppressWarnings("unchecked")
  public List<Line> getAllLineIndex() {
    SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMdd");
    HibernateTemplate template = getHibernateTemplate();
    StringBuilder sql = new StringBuilder();
    sql.append("from Line ");
    sql.append("where delKey='N' and isActive=true ");
    sql.append("order by lineNo ");
    List<Line> list = getHibernateTemplate().find(sql.toString());

    // 线路特色
    sql = new StringBuilder();
    sql.append("from LineDescription ");
    sql.append("where lineNo=? and type='" + KeyParams.EBIZ_TYPE_LINE_FEATURE
        + "' ");

    // 出团计划和报价
    StringBuilder sql1 = new StringBuilder();
    sql1.append("from Plan ");
    sql1.append("where line.lineNo=? ");
    sql1.append("and deployFlag='Y' ");
    sql1.append("and out_date>current_date() ");
    // sql1.append("and deadline>=current_date() ");
    sql1.append("order by outDate ");

    // 最近的报价
    // StringBuilder sql2 = new StringBuilder();
    // sql2.append("from LinePrice ");
    // sql2.append("where lineNo=? ");
    // sql2.append("and startDate<=? and a.endDate>=? ");
    // sql2.append("order by startDate desc ");

    // 线路目的地
    StringBuilder sql4 = new StringBuilder();
    sql4.append("select a.id.districtNo,b.cnName,b.country.countryId, ");
    sql4.append("b.province.code,b.country.name ");
    sql4.append("from LineDistrict a,");
    sql4.append("District b ");
    sql4.append("where a.id.lineNo=? ");
    sql4.append("and a.id.districtNo=b.districtNo ");

    // 景点
    StringBuilder sql5 = new StringBuilder();
    sql5.append("select l.sights from Line l where l.lineNo=? ");

    List<Line> routeIndexList = new ArrayList<Line>();
    if (!list.isEmpty()) {
      for (Line lineIndex : list) {
        // 线路特色
        List<LineDescription> list1 = getHibernateTemplate().find(
            sql.toString(), lineIndex.getLineNo());

        for (LineDescription trait : list1) {
          lineIndex.setRouteTrait(lineIndex.getRouteTrait() + "*"
              + RowDataUtil.getString(trait.getServiceDetail()) + ";");
        }

        // 出团计划和报价
        List<Plan> planList = template.find(sql1.toString(),
            lineIndex.getLineNo());
        if (!planList.isEmpty()) {
          // 是否有特惠团
          for (Plan objects : planList) {
            if ('Y' == RowDataUtil.getChar(objects.getFavourable())) {
              lineIndex.setIsPrefer("Y");
              break;
            }
          }

          // 此线路对应的最近的未出发的团的出团日期
          Date date1 = new Date();
          String str1 = "";
          String str2 = "";
          for (Plan planTmp : planList) {
            date1 = RowDataUtil.getDate(planTmp.getOutDate());
            if (null != date1) {
              str1 = str1 + SDF.format(date1) + ",";
              str2 = str2 + SDF.format(date1) + ",";
            }
          }
          lineIndex.setOutDate_price1(str1);
          lineIndex.setOutDate_price2(str2);

          // 出团计划的个数
          lineIndex.setPlanPax(String.valueOf(planList.size()));
          // 取最近的出团计划和对应报价
          Plan plan = planList.get(0);
          lineIndex.setOutDate(RowDataUtil.getDate(plan.getOutDate()));
          lineIndex.setOutDateStr(SDF.format(lineIndex.getOutDate()));

          // 如果tfj005的字段priceNo不为"00000000"时,其对应有相应的价格
          LinePrice tfa606 = (LinePrice) template.get(LinePrice.class, plan
              .getPackagePrice().getRecNo());
          if (null != tfa606) {
            // 直客价
            lineIndex.setPrice1(RowDataUtil.getDouble(tfa606.getPrice()));
            // 同行价
            lineIndex.setPrice2(RowDataUtil.getDouble(tfa606.getPriceOther()));
            String price1S = String.valueOf(lineIndex.getPrice1());
            String price2S = String.valueOf(lineIndex.getPrice2());
            // 将字符串补足8位,以利于搜索时价格的比较
            String[] strs = { "", "0", "00", "000", "0000", "00000", "000000",
                "0000000" };
            if (price1S.length() < 8)
              lineIndex
                  .setPrice1Str(strs[8 - price1S.length()].concat(price1S));
            if (price2S.length() < 8)
              lineIndex
                  .setPrice2Str(strs[8 - price2S.length()].concat(price2S));
          }
        }

        // 线路目的地
        List<Object[]> list4 = getHibernateTemplate().find(sql4.toString(),
            lineIndex.getLineNo());
        for (Object[] obj4 : list4) {
          String str4 = RowDataUtil.getString(obj4[3]).trim();
          if (null != obj4[3] && !str4.equals("")) {
            Province province = (Province) getHibernateTemplate().get(
                Province.class, str4);
            if (null != province)
              lineIndex.setDistrictProvinceName(province.getCnName().trim()
                  + " ");
          }
          lineIndex.setDistrictNo(lineIndex.getDistrictNo()
              + RowDataUtil.getString(obj4[0]) + " ");
          lineIndex.setDistrictName(lineIndex.getDistrictName()
              + RowDataUtil.getString(obj4[1]).trim() + " ");
          lineIndex.setDistrictCountry(lineIndex.getDistrictCountry()
              + RowDataUtil.getString(obj4[2]) + " ");
          lineIndex.setDistrictProvince(lineIndex.getDistrictProvince()
              + RowDataUtil.getString(obj4[3]) + " ");
          lineIndex.setDistrictCountryName(lineIndex.getDistrictCountryName()
              + RowDataUtil.getString(obj4[4]).trim() + " ");
        }

        // 景点
        List<Sight> list5 = getHibernateTemplate().find(sql5.toString(),
            lineIndex.getLineNo());
        for (Sight obj5 : list5) {
          lineIndex.setSightNo(lineIndex.getSightNo()
              + RowDataUtil.getString(obj5.getSightNo()) + " ");
          lineIndex.setSightName(lineIndex.getSightName()
              + RowDataUtil.getString(obj5.getName()) + " ");
        }

        // 找目的地所在的区域

        lineIndex.setClassifyRegion(RowDataUtil.getString(lineIndex
            .getDestination().getCnName()));
        lineIndex.setClassifyRegionId(RowDataUtil.getString(lineIndex
            .getDestination().getCode()));

        routeIndexList.add(lineIndex);
      }
    }
    return routeIndexList;
  }

  @SuppressWarnings("unchecked")
  public List<Destination> getRegionList() {
    List<Object[]> list;

    StringBuilder sql = new StringBuilder();
    sql.append("select distinct code, cnName, classType ");
    sql.append("from Destination ");
    sql.append("where length(trim(code)) <= 4 order by code");
    list = getHibernateTemplate().find(sql.toString());

    List<Destination> regionList = new ArrayList<Destination>();

    Destination region = null;
    for (Object[] obj : list) {
      String code = RowDataUtil.getString(obj[0]);
      // code.length() == 2为区域代码，比如code为11时即为东南亚
      if (code.length() == 2) {
        String regionName = RowDataUtil.getString(obj[1]);
        for (Object[] obj4 : list) {
          String code4 = RowDataUtil.getString(obj4[0]);
          // code.length() == 4为具体的目的地代码，比如code为1101时即为泰国
          if (code4.length() == 4 && code4.substring(0, 2).equals(code)) {
            region = new Destination();
            region.setCode(RowDataUtil.getString(obj4[0]));
            region.setCnName(RowDataUtil.getString(obj4[1]));
            region.setClassType(RowDataUtil.getString(obj4[2]));
            region.setParent(new Destination());
            region.getParent().setCnName(regionName);
            region.getParent().setCode(code);

            regionList.add(region);
          }
        }
      }
    }

    return regionList;
  }

}
