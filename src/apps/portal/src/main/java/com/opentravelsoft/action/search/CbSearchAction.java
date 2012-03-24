package com.opentravelsoft.action.search;

import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.ChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopFieldDocs;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import com.opentravelsoft.util.LabelValueBean;
import org.mira.lucene.analysis.IK_CAnalyzer;

import com.opentravelsoft.common.SearchParams;
import com.opentravelsoft.entity.Line;
import com.opentravelsoft.webapp.action.PortalAction;

/**
 * 线路搜索
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 */
public class CbSearchAction extends PortalAction {

	private static final long serialVersionUID = -774045944843957271L;

	protected static final Log logger = LogFactory.getLog(CbSearchAction.class);

	protected SimpleDateFormat SDF_1 = new SimpleDateFormat("yyyyMMdd");

	/** 查询字符串 */
	private String queryString;

	/** 出发城市 */
	private String outCity = "All";

	private int length;

	/** 搜索类型:0-所有,1-出境,2-自由行,3-国内长线,4-国内短线 */
	private int searchType = 0;

	private List<LabelValueBean> outCityList = new ArrayList<LabelValueBean>();

	private List<Line> lineIndexList = new ArrayList<Line>();

	/** 特价线路 */
	private List<Line> preferLineIndexList = new ArrayList<Line>();

	/**
	 * 初始化
	 * 
	 * @return
	 */
	protected String initInput() {
		// outCityList = getCodeList("ebiz_outcity_sky");
		return SUCCESS;
	}

	/**
	 * 分类搜索
	 * 
	 * @return
	 */
	public String execute() {
		initInput();
		if (null == queryString || queryString.equals(""))
			return INPUT;

		IndexSearcher searcher = null;
		// 多域
		String[] fields = { "routeName", "districtName", "districtCountryName",
				"districtProvinceName", "sightName", "region", "classifyRegion" };
		QueryParser queryParser = new MultiFieldQueryParser(fields,
				new IK_CAnalyzer());
		Query query = null;
		BooleanQuery booleanQuery = new BooleanQuery();
		TopFieldDocs preferhits;
		TopFieldDocs docs;

		try {
			searcher = new IndexSearcher(
					IndexReader.open(SearchParams.INDEX_STORE_PATH));
			query = queryParser.parse(queryString.trim());

			if (null != outCity && !outCity.equals("All")) {
				Term t = new Term("outCity", outCity);
				TermQuery termQuery = new TermQuery(t);
				booleanQuery.add(termQuery, BooleanClause.Occur.MUST);
			}

			if (searchType != 0) {
				classify(booleanQuery);
			}
			Query[] qs = new Query[2];
			qs[0] = booleanQuery;
			qs[1] = query;
			Query qa = Query.mergeBooleanQueries(qs);

			docs = searcher.search(qa, null, 1000, new Sort(new SortField[] {
					new SortField("outDateStr"), SortField.FIELD_SCORE }));

			ScoreDoc[] hits = docs.scoreDocs;
			int current = currentPage;

			for (int i = (current - 1) * getMoveCount(); i < current
					* getMoveCount()
					&& i < hits.length; i++) {
				Line routeIndex = new Line();
				int docId = hits[i].doc;
				Document d = searcher.doc(docId);

				//
				routeIndex = covert(d);
				lineIndexList.add(routeIndex);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ParseException pe) {
			logger.error("", pe);
		}
		currentPage(length);

		// 特价
		// -----------------------------------------------------------------------------------
		try {
			Term tp = new Term("isPrefer", "Y");
			TermQuery termQueryp = new TermQuery(tp);
			booleanQuery.add(termQueryp, BooleanClause.Occur.MUST);

			preferhits = searcher.search(booleanQuery, null, 1000, new Sort(
					new SortField[] { new SortField("outDateStr"),
							SortField.FIELD_SCORE }));

			ScoreDoc[] hits = preferhits.scoreDocs;
			for (int i = 0; i < hits.length; i++) {
				int docId = hits[i].doc;
				Document d = searcher.doc(docId);
				//
				Line routeIndex = covert(d);
				preferLineIndexList.add(routeIndex);
			}
		} catch (Exception e) {
			logger.error("", e);
		}

		// ------------------------------------------------------------------------------------

		return SUCCESS;
	}

	// ----------------------------------------------------------------------------------------------
	/**
	 * 高亮显示
	 */
	public String highLighter(String str1, String filedName,
			String queryString, String cssClassName) {
		if (null == str1 || str1.trim().equals(""))
			return "";
		String highlightStr = null;
		Query highlighterQuery = null;
		QueryParser highlighterQueryParser = new QueryParser(filedName,
				new ChineseAnalyzer());
		try {
			highlighterQuery = highlighterQueryParser.parse(queryString.trim());
		} catch (Exception e) {
			logger.error("", e);
		}
		QueryScorer scorer = new QueryScorer(highlighterQuery);
		SimpleHTMLFormatter formatter = new SimpleHTMLFormatter(
				"<span class=\"" + cssClassName + "\">", "</span>");
		Highlighter highlighter = new Highlighter(formatter, scorer);

		try {
			TokenStream stream = new ChineseAnalyzer().tokenStream(filedName,
					new StringReader(str1));
			highlightStr = highlighter.getBestFragment(stream, str1);
		} catch (Exception e) {
			logger.error("", e);
		}
		return highlightStr;
	}

	// ----------------------------------------------------------------------------------------------

	/**
	 * 分类
	 */
	public void classify(BooleanQuery booleanQuery) {
		if (searchType == 1) {
			// 出境
			TermQuery termQuery1 = new TermQuery(new Term("classKey1", "1"));
			TermQuery termQuery2 = new TermQuery(new Term("classKey1", "2"));
			booleanQuery.add(termQuery1, BooleanClause.Occur.SHOULD);
			booleanQuery.add(termQuery2, BooleanClause.Occur.SHOULD);
		} else if (searchType == 2) {
			// 自由行
			TermQuery termQuery1 = new TermQuery(new Term("classKey2", "2"));
			booleanQuery.add(termQuery1, BooleanClause.Occur.MUST);
		} else if (searchType == 3) {
			// 国内长线
			TermQuery termQuery1 = new TermQuery(new Term("classKey1", "3"));
			TermQuery termQuery2 = new TermQuery(new Term("classKey6", "06"));
			booleanQuery.add(termQuery1, BooleanClause.Occur.MUST);
			booleanQuery.add(termQuery2, BooleanClause.Occur.MUST_NOT);
		} else if (searchType == 4) {
			// 国内短线
			TermQuery termQuery1 = new TermQuery(new Term("classKey1", "3"));
			TermQuery termQuery2 = new TermQuery(new Term("classKey6", "06"));
			booleanQuery.add(termQuery1, BooleanClause.Occur.MUST);
			booleanQuery.add(termQuery2, BooleanClause.Occur.MUST);
		}
	}

	// ----------------------------------------------------------------------------------------------

	/**
	 * 赋值转换
	 */
	private Line covert(Document doc) {
		Line routeIndex = new Line();
		String fragment = highLighter(doc.get("routeName"), "routeName",
				queryString, "highlight");
		if (null != fragment)
			routeIndex.setLineName(fragment);
		else
			routeIndex.setLineName(doc.get("routeName"));
		routeIndex.setLineNo(doc.get("routeNo"));
		routeIndex.getOutCity().setCitynm(doc.get("outCityName"));

		fragment = highLighter(doc.get("routeTrait"), "routeTrait",
				queryString, "highlight1");
		if (null != fragment)
			routeIndex.setRouteTrait(fragment);
		else
			routeIndex.setRouteTrait(doc.get("routeTrait"));
		routeIndex.getOutCity().setCitycd(doc.get("outCity"));
		routeIndex.setClassKeyContent(doc.get("classKey2"));
		routeIndex.setClassKeyVehicle(doc.get("classKey6"));
		routeIndex.setOutDateStr(doc.get("outDateCH"));
		routeIndex.setPlanPax(doc.get("planPax"));
		routeIndex.setPrice1Str(doc.get("price1"));
		routeIndex.setPrice2Str(doc.get("price2"));
		fragment = highLighter(doc.get("districtName"), "districtName",
				queryString, "highlight");
		if (null != fragment)
			routeIndex.setDistrictName(fragment);
		else
			routeIndex.setDistrictName(doc.get("districtName"));

		fragment = highLighter(doc.get("sightName"), "sightName", queryString,
				"highlight");
		if (null != fragment)
			routeIndex.setSightName(fragment);
		else
			routeIndex.setSightName(doc.get("sightName"));

		return routeIndex;
	}

	// ----------------------------------------------------------------------------------------------
	/**
	 * 取页显示记录数
	 */
	protected int getMoveCount() {
		return 10;
	}

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	public List<Line> getRouteIndexList() {
		return lineIndexList;
	}

	public String getOutCity() {
		return outCity;
	}

	public void setOutCity(String outCity) {
		this.outCity = outCity;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getSearchType() {
		return searchType;
	}

	public void setSearchType(int searchType) {
		this.searchType = searchType;
	}

	public List<LabelValueBean> getOutCityList() {
		return outCityList;
	}

	public List<Line> getPreferRouteIndexList() {
		return preferLineIndexList;
	}

}
