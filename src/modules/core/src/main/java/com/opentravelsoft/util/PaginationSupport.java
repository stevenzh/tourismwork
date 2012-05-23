package com.opentravelsoft.util;

import java.util.List;

public class PaginationSupport {

  public final static int PAGESIZE = 18;

  private int pageSize = PAGESIZE;

  private List items;

  private long totalCount;

  private int[] indexes = new int[0];

  private long startIndex = 0;

  public PaginationSupport(List items, long totalCount) {
    setPageSize(PAGESIZE);
    setTotalCount(totalCount);
    setItems(items);
    setStartIndex(0);
  }

  public PaginationSupport(List items, long totalCount, int startIndex) {
    setPageSize(PAGESIZE);
    setTotalCount(totalCount);
    setItems(items);
    setStartIndex(startIndex);
  }

  public PaginationSupport(List items, long totalCount, int pageSize,
      int startIndex) {
    setPageSize(pageSize);
    setTotalCount(totalCount);
    setItems(items);
    setStartIndex(startIndex);
  }

  public List getItems() {
    return items;
  }

  public void setItems(List items) {
    this.items = items;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public long getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(long totalCount) {
    if (totalCount > 0) {
      this.totalCount = totalCount;
      long count = totalCount / pageSize;
      if (totalCount % pageSize > 0)
        count++;
      indexes = new int[(int) count];
      for (int i = 0; i < count; i++) {
        indexes[i] = pageSize * i;
      }
    } else {
      this.totalCount = 0;
    }
  }

  public int[] getIndexes() {
    return indexes;
  }

  public void setIndexes(int[] indexes1) {
    this.indexes = indexes1.clone();
  }

  public long getStartIndex() {
    return startIndex;
  }

  public void setStartIndex(int startIndex) {
    if (totalCount <= 0)
      this.startIndex = 0;
    else if (startIndex >= totalCount)
      this.startIndex = indexes[indexes.length - 1];
    else if (startIndex < 0)
      this.startIndex = 0;
    else {
      this.startIndex = indexes[startIndex / pageSize];
    }
  }

  public long getNextIndex() {
    long nextIndex = getStartIndex() + pageSize;
    if (nextIndex >= totalCount)
      return getStartIndex();
    else
      return nextIndex;
  }

  public long getPreviousIndex() {
    long previousIndex = getStartIndex() - pageSize;
    if (previousIndex < 0)
      return 0;
    else
      return previousIndex;
  }

}
