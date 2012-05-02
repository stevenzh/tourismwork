package com.opentravelsoft.entity.product;

import java.text.SimpleDateFormat;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;

import com.opentravelsoft.entity.Line;



/**
 * 数据库产品线路索引文档
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 */
public class LineDocument
{

    private LineDocument()
    {
    }
    
    public static Document documemt(Line routeIndex)
    {
        SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat SDF1 = new SimpleDateFormat("yyyy年MM月dd日");
        Document doc = new Document();
        
        /*
        Field.Index         Field.Store             说明 
        TOKENIZED(分词)     YES                     文章的标题或内容(如果是内容的话不能太长)是可以被搜索的 
        TOKENIZED           NO                      文章的标题或内容(内容可以很长)也是可以被看过的 
        NO                  YES                     这是不能被搜索的，它只是被搜索内容的附属物。如URL等 
        UN_TOKENIZED        YES/NO                  不被分词，它作为一个整体被搜索,搜一部分是搜不出来的 
        NO                  NO                      没有这种用法
        */
        
        doc.add(new Field("routeNo",routeIndex.getLineNo(),Field.Store.YES,Field.Index.NOT_ANALYZED));
        doc.add(new Field("routeName",routeIndex.getLineName(),Field.Store.YES,Field.Index.ANALYZED));
        doc.add(new Field("outCityName",routeIndex.getOutCity().getCitynm(),Field.Store.YES,Field.Index.NOT_ANALYZED));
        doc.add(new Field("outCity",routeIndex.getOutCity().getCitycd(),Field.Store.YES,Field.Index.NOT_ANALYZED));
        doc.add(new Field("lineDay",routeIndex.getLineDay().toString(),Field.Store.YES,Field.Index.NOT_ANALYZED));
        //线路特色
        doc.add(new Field("routeTrait",routeIndex.getRouteTrait(),Field.Store.YES,Field.Index.ANALYZED));
        doc.add(new Field("classKey2",routeIndex.getClassKeyContent(),Field.Store.YES,Field.Index.NOT_ANALYZED));
        doc.add(new Field("classKey6",routeIndex.getClassKeyVehicle(),Field.Store.YES,Field.Index.NOT_ANALYZED));
        String d1 ="";
        String d2 ="";
        if(null !=routeIndex.getOutDate())
        {
            d1 =SDF.format(routeIndex.getOutDate());
            d2 =SDF1.format(routeIndex.getOutDate());
        }
        doc.add(new Field("outDate",d1,Field.Store.YES,Field.Index.NOT_ANALYZED));
        //用于搜索完后中文日期的显示
        doc.add(new Field("outDateCH",d2,Field.Store.YES,Field.Index.NOT_ANALYZED));
        //用于排序
        doc.add(new Field("outDateStr",routeIndex.getOutDateStr(),Field.Store.YES,Field.Index.NOT_ANALYZED));
        
        //出发时间对应的价格
        doc.add(new Field("outDate_price1",routeIndex.getOutDate_price1(),Field.Store.YES,Field.Index.ANALYZED));
        doc.add(new Field("outDate_price2",routeIndex.getOutDate_price2(),Field.Store.YES,Field.Index.ANALYZED));
        
        //出团计划的个数,用于是否在出团日期的后面显示省略号，当个数大于１时显示省略号
        doc.add(new Field("planPax",routeIndex.getPlanPax(),Field.Store.YES,Field.Index.NOT_ANALYZED));
        //是否有特价
        doc.add(new Field("isPrefer",routeIndex.getIsPrefer(),Field.Store.YES,Field.Index.NOT_ANALYZED));
        doc.add(new Field("price1",String.valueOf(routeIndex.getPrice1()),Field.Store.YES,Field.Index.NOT_ANALYZED));
        doc.add(new Field("price2",String.valueOf(routeIndex.getPrice2()),Field.Store.YES,Field.Index.NOT_ANALYZED));
        //用于高级搜索时价格的比较
        doc.add(new Field("price1Str",routeIndex.getPrice1Str(),Field.Store.YES,Field.Index.NOT_ANALYZED));
        //用于高级搜索时价格的比较
        doc.add(new Field("price2Str",routeIndex.getPrice2Str(),Field.Store.YES,Field.Index.NOT_ANALYZED));
        //线路目的地
        doc.add(new Field("districtNo",routeIndex.getDistrictNo(),Field.Store.YES,Field.Index.ANALYZED));
        doc.add(new Field("districtName",routeIndex.getDistrictName(),Field.Store.YES,Field.Index.ANALYZED));
        doc.add(new Field("districtCountry",routeIndex.getDistrictCountry(),Field.Store.YES,Field.Index.NOT_ANALYZED));
        doc.add(new Field("districtCountryName",routeIndex.getDistrictCountryName(),Field.Store.YES,Field.Index.ANALYZED));
        doc.add(new Field("districtProvince",routeIndex.getDistrictProvince(),Field.Store.YES,Field.Index.ANALYZED));
        doc.add(new Field("districtProvinceName",routeIndex.getDistrictProvinceName(),Field.Store.YES,Field.Index.ANALYZED));
        //线路景点
        doc.add(new Field("sightNo",routeIndex.getSightNo(),Field.Store.YES,Field.Index.NOT_ANALYZED));
        doc.add(new Field("sightName",routeIndex.getSightName(),Field.Store.YES,Field.Index.ANALYZED));
        
        //分类区域(用于分类搜索)
        doc.add(new Field("region",routeIndex.getRegion(),Field.Store.YES,Field.Index.ANALYZED));
        doc.add(new Field("regionId",routeIndex.getRegionId(),Field.Store.YES,Field.Index.NOT_ANALYZED));
        doc.add(new Field("classifyRegion",routeIndex.getClassifyRegion(),Field.Store.YES,Field.Index.ANALYZED));
        doc.add(new Field("classifyRegionId",routeIndex.getClassifyRegionId(),Field.Store.YES,Field.Index.NOT_ANALYZED));
        
        //用于删除
        doc.add(new Field("deleteKey","deleteKey",Field.Store.YES,Field.Index.NOT_ANALYZED));
        
        return doc;
        
    }
}
