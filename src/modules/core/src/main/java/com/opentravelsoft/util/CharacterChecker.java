package com.opentravelsoft.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.Hashtable;


public class CharacterChecker {
    
	/**
	 * 外字ファイル
	 */
	private static final String EXT_CHARACTER_FILE	= "ExternalCheck.txt";
	
    /**
     * 外字用 Hashtable
     */
    private static Hashtable extcharHash = null;
    
    /**
     * 文字種タイプ
     */
    public static final int	TYPE_PASSWORD		= 0;
    public static final int	TYPE_EMAIL			= 1;
    public static final int  TYPE_TEL				= 2;
    public static final int	TYPE_NUMBER			= 3;
    public static final int	TYPE_ALPHABET		= 4;
    public static final int	TYPE_KATAKANA		= 5;
    
    /**
     * 文字種チェック正規表現
     */
    private static final String regWord		= "^[\\w]*";
    private static final String regNumber		= "^\\d*$";
    private static final String regInAlphabet	= "^\\w*[A-Za-z]+\\w*$";
    private static final String regAlphabet	= "^[A-Za-z]*$";
    private static final String regInNumber	= "^\\w*[0-9]+\\w*$";
    private static final String regMailPre	= "^[A-Za-z0-9_@\\.\\-]*";
    private static final String regMail		= "^[A-Za-z0-9]+[A-Za-z0-9_\\-\\.]*@[A-Za-z0-9]+[A-Za-z0-9_\\-\\.]*\\.[A-Za-z0-9]+[A-Za-z0-9_\\-\\.]*$";
    private static final String regKatakana	= "^[アイウエオカキクケコサシスセソタチツテトナニヌネノハヒフヘホマミムメモヤユヨラリルレロワヲンガギグゲゴザジズゼゾダヂヅデドバビブベボパピプペポァィゥェォャュョッ゛゜１２３４５６７８９０－ー　]+$";
    private static final String regTel		= "^[\\d\\-]*$";

    
    /**
     * コンストラクタ．使用不可．
     */
    private CharacterChecker() {
    }
    
    
    /**
     * 文字列の文字種チェックをします。
     * @param str				チェック対象文字列
     * @param type				チェックタイプ
     * @return					チェック結果
     */
    public static boolean checkCharaType(String str, int type) {
    	
    	boolean bResult = false;
    	
    	switch(type){
    		case 0:
    			bResult = str.matches(regWord);
    			if( bResult){
    				if(!(str.matches(regInAlphabet) && str.matches(regInNumber))){
    					bResult = false;
    				}
    			}
    			break;
    		case 1:
    			bResult = str.matches(regMail);
    			break;
    		case 2:
    			bResult = str.matches(regTel);
    			break;
    		case 3:
    			bResult = str.matches(regNumber);
    			break;
    		case 4:
    			bResult = str.matches(regAlphabet);
    			break;
    		case 5:
    			bResult = str.matches(regKatakana);
    			break;
    		default:
    			break;

    	}
    	return bResult;
    }
    
    /**
     * 文字列の長さをチェックします。
     * @param str				チェック対象文字列
     * @param min				最小の長さ
     * @param max				最大の長さ
     * @param byteFlag			バイト計算のフラグ ( true : バイト，false : 文字数 )
     * @return					チェック結果
     */
    public static boolean checkLength(String str, int min, int max, boolean byteFlag) {
    	
    	boolean bResult = false;
    	
    	if( min == 0 ){
    		if( str == null || "".equals(str)){
            	bResult = true;
    		}else{
    			if( byteFlag ){
    	    		
    	    		byte[] bStr = str.getBytes();
    	    		if( min <=  bStr.length && max >= bStr.length ){
    	    			bResult = true;
    	    		}
    	    		
    	    	}else{
    	    		if( min <= str.length() && max >= str.length() ){
    	    			bResult = true;
    	    		}
    	    	}
    		}
    	} else {
    		if( str == null || "".equals(str)){
            	bResult = false;
    		}else{
    			if( byteFlag ){
    	    		
    	    		byte[] bStr = str.getBytes();
    	    		if( min <=  bStr.length && max >= bStr.length ){
    	    			bResult = true;
    	    		}
    	    		
    	    	}else{
    	    		if( min <= str.length() && max >= str.length() ){
    	    			bResult = true;
    	    		}
    	    	}
    		}	
    	}
    	return bResult;
    }
    
    /**
     * 文字列が全て全角であるかチェックします．
     * @param str				チェック対象文字列
     * @return					チェック結果
     */
    public static boolean isZenkaku(String str) {
        
        boolean ret = true;
        
//        CharacterIterator ci = new StringCharacterIterator(str);
//        for ( char c = ci.first(); c != CharacterIterator.DONE; c = ci.next() ) {
//            if ( c > 0x0020 && c < 0x007F ) {
//                ret = false;
//                break;
//            }
//        }
        
        byte[] bytes = str.getBytes();
        int stlen = str.length() * 2;
        StringBuffer sb = new StringBuffer(str);

        for (int i = 0; i < str.length(); i++) {
            if ('\n' == sb.charAt(i)) {
            	stlen = stlen - 2;
            }
        }

        if (stlen != bytes.length) {
        	ret = false;
        }
        return ret;
    }
    
    /**
     * 文字列が外字を含むかどうかをチェックします．
     * @param str				チェック対象文字列
     * @return					チェック結果
     */
    public static boolean hasGaiji(String str) {
        
        boolean ret = false;
        
        if ( extcharHash == null ) {
            synchronized(CharacterChecker.class) {
                if ( extcharHash == null ) {
                    initExtcharHash();
                }
            }
        }
        
        CharacterIterator ci = new StringCharacterIterator(str);
        for ( char c = ci.first(); c != CharacterIterator.DONE; c = ci.next() ) {
            
			String d = "0x"+addBit((Integer.toHexString(c)).toUpperCase());
            if ( !extcharHash.containsKey(d) ) {
                ret = true;
                break;
            }
        }
        
        return ret;
    }
    
	/**
	 * 文字列が４桁になるように左 0 パディングします．
	 * @param str				対象文字列
	 * @return					変換後文字列
	 */
	private static String addBit(String str) {
	    
		if ( str.length() == 4 ) {
			return str;
			
		} else if ( str.length() < 4 ) {
			if ( str.length() == 3 ) {
				return "0"+str;
			}
			if ( str.length() == 2 ) {
				return "00"+str;
			}
			if ( str.length() == 1 ) {
				return "000"+str;
			}
		}
		
		return str;
	}
	
    /**
     * 外字用テーブルを初期化します．
     */
    private static void initExtcharHash() {
        
        extcharHash = new Hashtable();
        
        String fileName = EXT_CHARACTER_FILE;
        
        InputStreamReader	isr	= null;
        BufferedReader		br	= null;
        
        try {
            isr = new InputStreamReader(CharacterChecker.class.getResourceAsStream(fileName));
            br = new BufferedReader(isr);
	        
            String strLine = "";
            while ( (strLine = br.readLine()) != null) {
                extcharHash.put(strLine, "true");
            }
            
        } catch (IOException ignore) {
        } finally {
            if ( br != null ) {
                try {
                    br.close();
                } catch (IOException e1) {
                }
            }
            if ( isr != null ) {
                try {
                    isr.close();
                } catch (IOException e1) {
                }
            }

        }
        
    }
}