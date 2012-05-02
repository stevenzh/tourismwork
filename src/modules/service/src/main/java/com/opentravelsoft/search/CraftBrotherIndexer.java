package com.opentravelsoft.search;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.mira.lucene.analysis.IK_CAnalyzer;

import com.opentravelsoft.common.SearchParams;
import com.opentravelsoft.entity.Destination;
import com.opentravelsoft.entity.Line;
import com.opentravelsoft.entity.product.RegionDocument;
import com.opentravelsoft.entity.product.LineDocument;

/**
 * 建立数据库线路索引
 * 
 * @author Steven Zhang
 */
public class CraftBrotherIndexer {

  public static int beginIndexList(List<Line> lineIndexList) throws IOException {
    if (!new File(SearchParams.INDEX_STORE_PATH).exists())
      new File(SearchParams.INDEX_STORE_PATH).mkdirs();

    File indexDir = new File(SearchParams.INDEX_STORE_PATH);
    String[] filename = indexDir.list();
    // 内存
    Directory ramDir = new RAMDirectory();
    IndexWriter ramWriter;
    IndexWriter fsWriter;
    if (filename.length == 0) {
      fsWriter = new IndexWriter(indexDir, new IK_CAnalyzer(), true,
          IndexWriter.MaxFieldLength.LIMITED);
      ramWriter = new IndexWriter(ramDir, new IK_CAnalyzer(), true,
          IndexWriter.MaxFieldLength.LIMITED);

    } else {
      IndexReader reader = IndexReader.open(indexDir);
      // 删除所有索引
      reader.deleteDocuments(new Term("deleteKey", "deleteKey"));
      // 关闭释放锁
      reader.close();

      fsWriter = new IndexWriter(indexDir, new IK_CAnalyzer(), false,
          IndexWriter.MaxFieldLength.LIMITED);
      ramWriter = new IndexWriter(ramDir, new IK_CAnalyzer(), true,
          IndexWriter.MaxFieldLength.LIMITED);

    }

    for (Line routeIndex : lineIndexList) {
      Document doc = LineDocument.documemt(routeIndex);
      ramWriter.addDocument(doc);
    }

    ramWriter.optimize();
    ramWriter.close();
    Directory[] directorys = { ramDir };
    // 将内存中的索引存入磁盘
    fsWriter.addIndexesNoOptimize(directorys);
    fsWriter.optimize();
    fsWriter.close();

    return lineIndexList.size();
  }

  public static int beginIndex(Line lineIndex) throws IOException {
    if (!new File(SearchParams.INDEX_STORE_PATH).exists())
      new File(SearchParams.INDEX_STORE_PATH).mkdirs();

    File indexDir = new File(SearchParams.INDEX_STORE_PATH);
    String[] filename = indexDir.list();
    IndexWriter writer;
    if (filename.length == 0) {
      writer = new IndexWriter(indexDir, new IK_CAnalyzer(), true,
          IndexWriter.MaxFieldLength.LIMITED);
    } else {
      IndexReader reader = IndexReader.open(indexDir);
      reader.deleteDocuments(new Term("routeNo", lineIndex.getLineNo()));
      reader.close();
      writer = new IndexWriter(indexDir, new IK_CAnalyzer(), false,
          IndexWriter.MaxFieldLength.LIMITED);
    }

    Document doc = LineDocument.documemt(lineIndex);
    writer.addDocument(doc);

    int numIndexed = writer.maxDoc();
    writer.optimize();
    writer.close();

    return numIndexed;
  }

  public static int beginAllRegionIndex(List<Destination> regionList)
      throws IOException {
    if (!new File(SearchParams.INDEX_TOPIC_PATH).exists())
      new File(SearchParams.INDEX_TOPIC_PATH).mkdirs();

    File indexDir = new File(SearchParams.INDEX_TOPIC_PATH);
    String[] filename = indexDir.list();
    // 内存
    Directory ramDir = new RAMDirectory();
    IndexWriter ramWriter;
    IndexWriter fsWriter;
    if (filename.length == 0) {
      fsWriter = new IndexWriter(indexDir, new IK_CAnalyzer(), true,
          IndexWriter.MaxFieldLength.LIMITED);
      ramWriter = new IndexWriter(ramDir, new IK_CAnalyzer(), true,
          IndexWriter.MaxFieldLength.LIMITED);

    } else {
      IndexReader reader = IndexReader.open(indexDir);
      // 删除所有索引
      reader.deleteDocuments(new Term("deleteKey", "deleteKey"));
      // 关闭释放锁
      reader.close();

      fsWriter = new IndexWriter(indexDir, new IK_CAnalyzer(), false,
          IndexWriter.MaxFieldLength.LIMITED);
      ramWriter = new IndexWriter(ramDir, new IK_CAnalyzer(), true,
          IndexWriter.MaxFieldLength.LIMITED);

    }

    for (Destination region : regionList) {
      Document doc = RegionDocument.documemt(region);
      ramWriter.addDocument(doc);
    }

    ramWriter.optimize();
    ramWriter.close();
    Directory[] directorys = { ramDir };
    // 将内存中的索引存入磁盘
    fsWriter.addIndexesNoOptimize(directorys);
    fsWriter.optimize();
    fsWriter.close();

    return regionList.size();
  }
}
