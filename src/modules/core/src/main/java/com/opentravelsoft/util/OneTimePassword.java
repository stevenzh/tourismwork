package com.opentravelsoft.util;

import java.text.SimpleDateFormat;
import java.util.Date;




/**
 * ワンタイムパスワード の処理．
 * @author nokami
 */
public class OneTimePassword {
    
    /**
     * 乱数部分の長さ
     */
    private static final int RND_LENGTH = 5;
    
	/**
	 * ワンタイムパスワードを生成します．
	 * @return					ワンタイムパスワード文字列
	 */
	public static String genaratePassword() {
		
        String password = null;
        
        // 現在時刻を取得
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSS");
        String strTime = sdf.format(date);
        
        // 乱数を取得
        int rnd = Randam.getRnd();
        
        // 0 パディング処理
        String strRnd = StringUtil.padding(rnd, RND_LENGTH);
        
        // 文字列の並び替え
        password = strRnd.substring(1, 2)
				+ strTime.substring(0, 1)
				+ strTime.substring(9, 10)
				+ strTime.substring(14, 15)
				+ strTime.substring(8, 9)
				+ strRnd.substring(2, 3)
				+ strTime.substring(3, 4)
				+ strTime.substring(12, 13)
				+ strTime.substring(7, 8)
				+ strTime.substring(4, 5)
				+ strRnd.substring(4, 5)
				+ strTime.substring(13, 14)
				+ strTime.substring(1, 2)
				+ strTime.substring(10, 11)
				+ strRnd.substring(0, 1)
				+ strTime.substring(6, 7)
				+ strTime.substring(5, 6)
				+ strTime.substring(2, 3)
				+ strTime.substring(11, 12)
				+ strRnd.substring(3, 4);
        
        password = CodeUtility.encode(password);
        
        return password.replaceAll("\\+", "_");
	}

}
