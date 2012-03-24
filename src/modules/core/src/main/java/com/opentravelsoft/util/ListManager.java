package com.opentravelsoft.util;

import java.util.List;

/**
 * 画面表示用のリストを管理するためのクラス．
 * @author nokami
 */
public class ListManager {
    
	// レコードのリスト
    private List	recordList		= null;
    // レコードの全件数
    private int	recordCount		= 0;
    // フェッチする最大件数
    private int	maxResult		= 0;
    // フェッチした件数
    private int	fetchCount		= 0;
    // フェッチしたレコードの最初の番号
    private int	firstResult		= -1;
    // フェッチしたレコードの最後の番号
    private int	lastResult		= -1;
    // 前ページに戻る場合の最初のレコード番号
    private int	prevFirstResult	= -1;
    // 次ページに進む場合の最初のレコード番号
    private int	nextFirstResult	= -1;
    // 最後のページに進む場合のレコード番号
    private int	lastFirstResult	= -1;
    
    /**
     * デフォルトコンストラクタ．使用不可．
     */
    private ListManager() {
    }
    
    /**
     * コンストラクタ．<BR/>
     * 
     * @param list			リスト
     * @param recordCount	レコードの全件数
     * @param firstResult	フェッチしたレコードの最初の番号 ( 0 オリジン )
     * @param maxResult		フェッチするレコードの最大件数
     */
    public ListManager(List list, int recordCount, int firstResult, int maxResult) {
        
        if ( list != null ) {
            
            // リストの保存
            setRecordList(list);
            
            // レコードの全件数の保存
            setRecordCount(recordCount);
            
            // フェッチしたレコードの最初の番号の保存
            if ( list.size() != 0 ) {
                setFirstResult(firstResult);
            }
            
            // フェッチするレコードの最大件数の保存
            setMaxResult(maxResult);
            
            // フェッチしたレコード件数を計算
            setFetchCount(list.size());
            
            // フェッチしたレコードの最後の番号を計算
            setLastResult(firstResult + getFetchCount() - 1);
            
            // 前ページに戻る場合の最初のレコード番号を計算 ( 戻れない場合は -1 )
            if ( firstResult > 0 ) {
                if ( firstResult - maxResult < 0 ) {
                    setPrevFirstResult(0);
                } else {
                    setPrevFirstResult(firstResult - maxResult);
                }
            }
            
            // 次ページに進む場合の最初のレコード番号 ( 進めない場合は -1 )
            if ( firstResult + maxResult < recordCount ) {
                setNextFirstResult(firstResult + maxResult);
            }
            
            // 最後のページに進む場合のレコード番号 ( 進めない場合は -1 )
            if ( getNextFirstResult() != -1 ) {
                setLastFirstResult(recordCount - maxResult);
            }
        }
    }
    
    
    private void setFetchCount(int fetchCount) {
        this.fetchCount = fetchCount;
    }
    private void setFirstResult(int firstResult) {
        this.firstResult = firstResult;
    }
    private void setLastResult(int lastResult) {
        this.lastResult = lastResult;
    }
    private void setLastFirstResult(int lastFirstResult) {
        this.lastFirstResult = lastFirstResult;
    }
    private void setMaxResult(int maxResult) {
        this.maxResult = maxResult;
    }
    private void setNextFirstResult(int nextFirstResult) {
        this.nextFirstResult = nextFirstResult;
    }
    private void setPrevFirstResult(int prevFirstResult) {
        this.prevFirstResult = prevFirstResult;
    }
    private void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }
    private void setRecordList(List recordList) {
        this.recordList = recordList;
    }
    public int getFetchCount() {
        return fetchCount;
    }
    public int getFirstResult() {
        return firstResult;
    }
    public int getLastResult() {
        return lastResult;
    }
    public int getLastFirstResult() {
        return lastFirstResult;
    }
    public int getMaxResult() {
        return maxResult;
    }
    public int getNextFirstResult() {
        return nextFirstResult;
    }
    public int getPrevFirstResult() {
        return prevFirstResult;
    }
    public int getRecordCount() {
        return recordCount;
    }
    public List getRecordList() {
        return recordList;
    }
}
