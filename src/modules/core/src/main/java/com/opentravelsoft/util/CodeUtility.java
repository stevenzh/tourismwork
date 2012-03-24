package com.opentravelsoft.util;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

/**
 * 
 * @author <a herf="mailto:zhangsitao@gmail.com">Steven Zhang</a>
 * @version $Revision: 1.1 $ $Date: 2009/03/01 16:23:40 $
 */
public class CodeUtility {
	
	/** 文字列エンコード */
	private static final String ENCODE	= "UTF-8";
	/** escape */
	private static final byte[] SEEDS	= {8,1,7,8,9,9,1,5,5,4,8,8,1,7,8,9,9,1,1,6,7,4,6,5,1,2,2,7,1,5,3,1,0,0,0,0,0,0,0,0}; 
	
	/**
	 * 文字列を暗号化します．
	 * @param s					暗号化対象文字列
	 * @return						暗号化された文字列
	 */
	public static String encode(String s) {
		
		// null，空文字の場合は，空文字を返却
		if ( s == null || "".equals(s) ) {
			return "";
		}
		
		// 暗号化
		byte[] encryptByte = CodeUtility.encrypt(s);
		
		// BASE64 エンコード
		return new String(Base64.encodeBase64(encryptByte));
	}
	
	/**
	 * 文字列を復号化します．
	 * @param s					復号化対象文字列
	 * @return						復号化された文字列
	 */
	public static String decode(String s) {
		
		// null，空文字の場合は，空文字を返却
		if ( s == null || "".equals(s) ) {
			return "";
		}
		
		// BASE64 デコード
		byte[] decodedByte = Base64.decodeBase64(s.getBytes());
		
		// 複合化
		return CodeUtility.decrypt(decodedByte);
	}
	
	/**
	 * 与えられた文字列をパスワードベースの暗号化 (PBE) で暗号化します．
	 * @param targetString		暗号化する文字列
	 * @return					暗号化されたbyte配列
	 */
	private static final byte[] encrypt(String targetString) {

		byte[] encryptedBytes = null;
		try {
			encryptedBytes = targetString.getBytes(ENCODE);
		} catch (UnsupportedEncodingException ignore) {
		}
		for(int i = 0; i < encryptedBytes.length && i < SEEDS.length; ++i) {
			encryptedBytes[i] += SEEDS[i];
		}
		return encryptedBytes;
	}
	
	/**
	 * 与えられたパスワードベースの暗号化 (PBE) で暗号化された byte 配列を復号します．
	 * @param targetBytes		復号するbyte配列
	 * @return					復号された文字列
	 */
	private static final String decrypt(byte[] targetBytes) {
		
		for(int i = 0; i < targetBytes.length && i < SEEDS.length; ++i) {
			targetBytes[i] -= SEEDS[i];
		}
		String decryptedString = null;
		try {
			decryptedString = new String(targetBytes, ENCODE);
		} catch (UnsupportedEncodingException ignore) {
		}

		return decryptedString;
	}
}
