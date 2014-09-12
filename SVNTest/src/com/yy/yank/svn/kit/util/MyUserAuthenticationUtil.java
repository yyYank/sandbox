package com.yy.yank.svn.kit.util;

import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

import com.yy.yank.svn.kit.MySVNKitExecuter;

/**
 * ユーザー認証クラス
 * 
 * @author yy_yank
 * @version 1.0
 */
public class MyUserAuthenticationUtil {

	/** プライベートコンストラクタ */
	private MyUserAuthenticationUtil() {
	}

	/**
	 * ユーザー認証
	 * 
	 * @param name
	 * @param pass
	 */
	public static void auth(String name, String pass) {
		ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(name, pass);
		MySVNKitExecuter.repository.setAuthenticationManager(authManager);
	}
}
