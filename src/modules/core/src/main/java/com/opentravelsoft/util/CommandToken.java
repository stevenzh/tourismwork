package com.opentravelsoft.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * コマンドトークンを扱うクラスです．
 * @author nokami
 */
public class CommandToken {
	
	/**
	 * コマンドトークンをセットします．
	 * @param request			HttpServletRequest
	 */
	public static void set(HttpServletRequest request) {
		
		HttpSession session	= request.getSession(true);
		
		long systemTime		= System.currentTimeMillis();
		byte[] time			= String.valueOf(systemTime).getBytes();
		byte[] sessionId	= session.getId().getBytes();
		
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("SHA");
		} catch (NoSuchAlgorithmException ignore) {
		}
		md5.update(sessionId);
		md5.update(time);
		String token = toHex(md5.digest());
		request.setAttribute("SYSTEM_COMMAND_TOKEN", token);
		session.setAttribute("SYSTEM_COMMAND_TOKEN", token);
	}
	
	/**
	 * コマンドトークンが正しいかどうかチェックします．
	 * @param request			HttpServletRequest
	 * @return					チェック結果 ( true：成功，false：失敗 )
	 */
	public static boolean isValid(HttpServletRequest request) {
		
		HttpSession session	= request.getSession(true);
		
		String requestToken	= request.getParameter("SYSTEM_COMMAND_TOKEN");
		String sessionToken	= (String)session.getAttribute("SYSTEM_COMMAND_TOKEN");
		
		if ( requestToken == null || sessionToken == null ) {
			return false;
		} else {
			session.removeAttribute("SYSTEM_COMMAND_TOKEN");
			return requestToken.equals(sessionToken);
		}
		
	}
	
	/**
	 * 16 進ダンプを行います．
	 * @param digest
	 * @return
	 */
	private static String toHex(byte[] digest) {
		
		StringBuffer sb = new StringBuffer();
		for ( int i = 0; i < digest.length; i++ ) {
			sb.append(Integer.toHexString((int)digest[i] & 0x00ff));
		}
		
		return sb.toString();
	}
	
}
