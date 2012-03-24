package com.opentravelsoft.action.search;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.RangeQuery;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopFieldDocs;
import org.apache.lucene.search.WildcardQuery;
import com.opentravelsoft.util.LabelValueBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.opentravelsoft.common.SearchParams;
import com.opentravelsoft.entity.Country;
import com.opentravelsoft.entity.District;
import com.opentravelsoft.entity.Province;
import com.opentravelsoft.entity.Line;
import com.opentravelsoft.service.resource.CountryService;
import com.opentravelsoft.webapp.action.PortalAction;

/**
 * 高级搜索
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 */

public class CbAdvancedSearchAction extends PortalAction {

  private static final long serialVersionUID = -2065746950723194204L;

  protected static final Log logger = LogFactory
      .getLog(CbAdvancedSearchAction.class);

  private CountryService countryService;

  /** 开始日期 */
  private Date sDate;

  /** 结束日期 */
  private Date eDate;

  private String outCity = "All";

  /** 目的地国家 */
  private String country;

  /** 目的地省份 */
  private String province;

  /** 目的地 */
  private String district;

  private double price1;

  private double price2;

  private int length;

  private List<Country> countryList = new ArrayList<Country>();

  private List<Province> provinceList = new ArrayList<Province>();

  private List<District> districtList = new ArrayList<District>();

  List<Line> LineIndexList = new ArrayList<Line>();

  private List<LabelValueBean> outCityList = new ArrayList<LabelValueBean>();

  /** 特价线路 */
  private List<Line> preferRouteIndexList = new ArrayList<Line>();

  private String queryString;

  @Autowired
  public void setCountryService(CountryService countryService) {
    this.countryService = countryService;
  }

  /**
   * 初始化
   * 
   * @return
   */
  protected String initInput() {
    return SUCCESS;
  }

  /**
   * 进入高级搜索
   */
  public String input() {
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);
    sDate = cal.getTime();
    cal.add(Calendar.MONTH, 2);
    eDate = cal.getTime();

    countryList = countryService.getCountryList();

    return INPUT;
  }

  /**
   * 默认高级搜索
   * 
   * @return
   */
  public String advanceSearch() {
    initInput();
    IndexSearcher searcher = null;
    BooleanQuery booleanQuery = new BooleanQuery();
    TopFieldDocs preferhits = null;
    TopFieldDocs docs;
    if (!(null == sDate && null == eDate))
      if (null == sDate && null != eDate) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        sDate = cal.getTime();
      } else if (null != sDate && null == eDate) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.YEAR, 2);
        eDate = cal.getTime();
      }

    try {
      searcher = new IndexSearcher(
          IndexReader.open(SearchParams.INDEX_STORE_PATH));

      // 组合查询
      booleanQuery = new BooleanQuery();
      if (null != outCity && !outCity.equals("All")) {
        Term t = new Term("outCity", outCity);
        TermQuery termQuery = new TermQuery(t);
        // BooleanClause.Occur.MUST为必须匹配
        booleanQuery.add(termQuery, BooleanClause.Occur.MUST);
      }
      // 分类
      classify(booleanQuery);
      // 目的地
      if (null == district || district.equals("")) {
        if (null == province || province.equals("")) {
          if (null != country && !country.equals("")) {// 国家
            Term cou = new Term("districtCountry", "*" + country + "*");
            // TermQuery countryTQ = new TermQuery(cou);
            WildcardQuery countryTQ = new WildcardQuery(cou);
            booleanQuery.add(countryTQ, BooleanClause.Occur.MUST);
          }
        } else {// 省
          Term cou = new Term("districtProvince", province);
          TermQuery countryTQ = new TermQuery(cou);
          booleanQuery.add(countryTQ, BooleanClause.Occur.MUST);
        }
      } else {// 目的地
        Term cou = new Term("districtNo", district);
        TermQuery countryTQ = new TermQuery(cou);
        booleanQuery.add(countryTQ, BooleanClause.Occur.MUST);
      }

      // 出发日期
      if (null != sDate || null != eDate) {
        String strO = "outDate_price1";
        // if (null != user) // 同业价
        // strO = "outDate_price2";
        SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMdd");
        Term beginDate = new Term(strO, SDF.format(sDate));
        Term endDate = new Term(strO, SDF.format(eDate));
        // 范围匹配
        RangeQuery dateQuery = new RangeQuery(beginDate, endDate, true);
        booleanQuery.add(dateQuery, BooleanClause.Occur.MUST);

      }
      // 出发时间为空时的报价
      if ((0 != price1 || 0 != price2)) {
        String price1S = String.valueOf(price1);
        String price2S = String.valueOf(price2);
        String[] strs = { "", "0", "00", "000", "0000", "00000", "000000",
            "0000000" };
        if (price1S.length() < 8)
          price1S = strs[8 - price1S.length()].concat(price1S);
        if (price2S.length() < 8)
          price2S = strs[8 - price2S.length()].concat(price2S);
        String strP = "price1Str";
        // 登陆后搜索同行价
        // if (null != user) // 同业价
        // strP = "price2Str";
        Term beginPrice = new Term(strP, String.valueOf(price1S));
        Term endPrice = new Term(strP, String.valueOf(price2S));
        // 范围匹配
        RangeQuery priceQuery = new RangeQuery(beginPrice, endPrice, true);
        booleanQuery.add(priceQuery, BooleanClause.Occur.MUST);
      }

      docs = searcher.search(booleanQuery, null, 10000,
          new Sort(new SortField[] { new SortField("outDateStr"),
              SortField.FIELD_SCORE }));
      ScoreDoc[] hits = docs.scoreDocs;
      int current = currentPage;
      length = hits.length;
      for (int i = (current - 1) * getMoveCount(); i < current * getMoveCount()
          && i < hits.length && i < length; i++) {
        Line routeIndex = new Line();
        int docId = hits[i].doc;
        Document doc = searcher.doc(docId);
        //
        routeIndex.setLineName(doc.get("routeName"));
        routeIndex.setLineNo(doc.get("routeNo"));
        routeIndex.getOutCity().setCitynm(doc.get("outCityName"));
        routeIndex.setRouteTrait(doc.get("routeTrait"));
        routeIndex.getOutCity().setCitycd(doc.get("outCity"));
        routeIndex.setClassKeyContent(doc.get("classKey2"));
        routeIndex.setClassKeyVehicle(doc.get("classKey6"));
        routeIndex.setOutDateStr(doc.get("outDateCH"));
        routeIndex.setPrice1Str(doc.get("price1"));
        routeIndex.setPrice2Str(doc.get("price2"));
        routeIndex.setDistrictName(doc.get("districtName"));
        routeIndex.setDistrictCountry(doc.get("districtCountry"));
        routeIndex.setSightName(doc.get("sightName"));
        routeIndex.setPlanPax(doc.get("planPax"));
        LineIndexList.add(routeIndex);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    currentPage(length);

    // 特价
    // -----------------------------------------------------------------------------------
    try {
      Term tp = new Term("isPrefer", "Y");
      TermQuery termQueryp = new TermQuery(tp);
      booleanQuery.add(termQueryp, BooleanClause.Occur.MUST);

      preferhits = searcher.search(booleanQuery, null, 1000,
          new Sort(new SortField[] { new SortField("outDateStr"),
              SortField.FIELD_SCORE }));

      ScoreDoc[] hits = preferhits.scoreDocs;
      for (int i = 0; i < hits.length; i++) {
        int docId = hits[i].doc;
        Document d = searcher.doc(docId);
        //
        Line routeIndex = covert(d);
        preferRouteIndexList.add(routeIndex);
      }
    } catch (Exception e) {
      logger.error("", e);
    }

    // ------------------------------------------------------------------------------------

    return SUCCESS;
  }

  // ----------------------------------------------------------------------------------------------

  /**
   * 分类
   */
  public void classify(BooleanQuery booleanQuery) {
    // if (searchType == 1)
    // {
    TermQuery termQuery1 = new TermQuery(new Term("classKey1", "1"));
    TermQuery termQuery2 = new TermQuery(new Term("classKey1", "2"));
    booleanQuery.add(termQuery1, BooleanClause.Occur.SHOULD);
    booleanQuery.add(termQuery2, BooleanClause.Occur.SHOULD);
    // } else if (searchType == 2)
    // {
    // TermQuery termQuery1 = new TermQuery(new Term("classKey2", "2"));
    // booleanQuery.add(termQuery1, BooleanClause.Occur.MUST);
    // } else if (searchType == 3)
    // {
    // TermQuery termQuery1 = new TermQuery(new Term("classKey1", "3"));
    // TermQuery termQuery2 = new TermQuery(new Term("classKey6", "06"));
    // booleanQuery.add(termQuery1, BooleanClause.Occur.MUST);
    // booleanQuery.add(termQuery2, BooleanClause.Occur.MUST_NOT);
    // } else if (searchType == 4)
    // {
    // TermQuery termQuery1 = new TermQuery(new Term("classKey1", "3"));
    // TermQuery termQuery2 = new TermQuery(new Term("classKey6", "06"));
    // booleanQuery.add(termQuery1, BooleanClause.Occur.MUST);
    // booleanQuery.add(termQuery2, BooleanClause.Occur.MUST);
    // }
  }

  // ----------------------------------------------------------------------------------------------

  /**
   * 赋值转换
   */
  public Line covert(Document doc) {
    Line routeIndex = new Line();

    routeIndex.setLineName(doc.get("routeName"));
    routeIndex.setLineNo(doc.get("routeNo"));
    routeIndex.getOutCity().setCitynm(doc.get("outCityName"));
    routeIndex.setRouteTrait(doc.get("routeTrait"));
    routeIndex.getOutCity().setCitycd(doc.get("outCity"));
    routeIndex.setClassKeyContent(doc.get("classKey2"));
    routeIndex.setClassKeyVehicle(doc.get("classKey6"));
    routeIndex.setOutDateStr(doc.get("outDateCH"));
    routeIndex.setPrice1Str(doc.get("price1"));
    routeIndex.setPrice2Str(doc.get("price2"));
    routeIndex.setDistrictName(doc.get("districtName"));

    return routeIndex;
  }

  // ----------------------------------------------------------------------------------------------
  /**
   * 取页显示记录数
   */
  protected int getMoveCount() {
    return 10;
  }

  public List<Country> getCountryList() {
    return countryList;
  }

  public void setCountryList(List<Country> countryList) {
    this.countryList = countryList;
  }

  public Date getS_date() {
    return sDate;
  }

  public void setS_date(Date s_date) {
    this.sDate = s_date;
  }

  public Date getE_date() {
    return eDate;
  }

  public void setE_date(Date e_date) {
    this.eDate = e_date;
  }

  public String getOutCity() {
    return outCity;
  }

  public void setOutCity(String outCity) {
    this.outCity = outCity;
  }

  public double getPrice1() {
    return price1;
  }

  public void setPrice1(double price1) {
    this.price1 = price1;
  }

  public double getPrice2() {
    return price2;
  }

  public void setPrice2(double price2) {
    this.price2 = price2;
  }

  public List<Province> getProvinceList() {
    return provinceList;
  }

  public void setProvinceList(List<Province> provinceList) {
    this.provinceList = provinceList;
  }

  public List<District> getDistrictList() {
    return districtList;
  }

  public void setDistrictList(List<District> districtList) {
    this.districtList = districtList;
  }

  public String getDistrict() {
    return district;
  }

  public void setDistrict(String district) {
    this.district = district;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public int getLength() {
    return length;
  }

  public void setLength(int length) {
    this.length = length;
  }

  public List<Line> getRouteIndexList() {
    return LineIndexList;
  }

  public String getQueryString() {
    return queryString;
  }

  public void setQueryString(String queryString) {
    this.queryString = queryString;
  }

  public List<Line> getPreferRouteIndexList() {
    return preferRouteIndexList;
  }

  public List<LabelValueBean> getOutCityList() {
    return outCityList;
  }
}
