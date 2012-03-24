package com.opentravelsoft.util;

import java.util.Random;

/**
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:41 $
 */
public class Randam {

    /**
     * 乱数生成用の定数１ ( 8n + 5 の数 )
     */
    private static final int RND_A = 77;

    /**
     * 乱数生成用の定数２ ( 2n + 1 の数 )
     */
    private static final int RND_B = 1243;

    /**
     * 乱数生成用の定数３ ( 2^n の数 )
     */
    private static final int RND_C = 65536;

    /**
     * 生成する乱数の桁数
     */
    private static final int FIGURE = 5;

    /**
     * 前回生成した乱数
     */
    private static int rnd = 0;

    private static String password = "1235678acefghkpqrstuvwxyz";

    /**
     * コンストラクタ<BR/> 使用不可<BR/>
     */
    private Randam() {

    }

    /**
     * 線形合同法による乱数を取得する．<BR/>
     * 
     * @return 生成された乱数
     */
    public static int getRnd() {

        // 乱数の初期化
        if (rnd == 0) {
            // カレントタイムの下 <FIGURE> 桁を初期値とする
            String currentTime = String.valueOf(System.currentTimeMillis());
            rnd = Integer.parseInt(currentTime.substring(currentTime.length()
                    - FIGURE));
        }

        long ret = 0;

        // synchronized
        synchronized (Randam.class) {
            // 線形合同法により乱数を生成
            ret = (((RND_A * rnd + RND_B) % RND_C) * (long) Math
                    .pow(10, FIGURE))
                    / RND_C;
            // static 変数に格納
            rnd = (int) ret;
        }

        return (int) ret;
    }

    /**
     * 8桁の英数字パスワードを生成する
     * 
     * @return パスワード
     */
    public static String getPassword() {
        Random random = new Random();
        char[] chr = password.toCharArray();
        StringBuffer sbTemp = new StringBuffer();

        for (int i = 0; i < 8; i++) {
            sbTemp.append(chr[random.nextInt(chr.length)]);
        }
        return sbTemp.toString();
    }
}
