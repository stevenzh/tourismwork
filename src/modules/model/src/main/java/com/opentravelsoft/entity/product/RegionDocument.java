package com.opentravelsoft.entity.product;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;

import com.opentravelsoft.entity.Destination;

/**
 * 目的地索引文档
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 */
public class RegionDocument
{

	public RegionDocument()
	{
	}
	
	public static Document documemt(Destination region)
    {
        Document doc = new Document();
        
        /*
        Field.Index         Field.Store             说明 
        TOKENIZED(分词)      YES                 　　文章的标题或内容(如果是内容的话不能太长)是可以被搜索的 
        TOKENIZED           NO                      文章的标题或内容(内容可以很长)也是可以被看过的 
        NO                  YES                     这是不能被搜索的，它只是被搜索内容的附属物。如URL等 
        UN_TOKENIZED        YES/NO                  不被分词，它作为一个整体被搜索,搜一部分是搜不出来的 
        NO                  NO                      没有这种用法
        */
        
        //目的地代码
        doc.add(new Field("code",region.getCode(),Field.Store.YES,Field.Index.NOT_ANALYZED));
        //目的地中文名
        doc.add(new Field("name",region.getCnName(),Field.Store.YES,Field.Index.ANALYZED));
        if (region.getParent() != null)
        {
            //目的地所属区域
            doc.add(new Field("region",region.getParent().getCnName(),Field.Store.YES,Field.Index.ANALYZED));
    
            doc.add(new Field("regionId",String.valueOf(region.getParent().getDestId()),Field.Store.YES,Field.Index.NOT_ANALYZED));
        }
        //类型（1为境外，2为国内）
        doc.add(new Field("type",region.getClassType(),Field.Store.YES,Field.Index.NOT_ANALYZED));
        
        //用于删除
        doc.add(new Field("deleteKey","deleteKey",Field.Store.YES,Field.Index.NOT_ANALYZED));
        
        return doc;
        
    }

}
