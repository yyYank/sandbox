package com.yy.yank.svn.kit;

import java.io.IOException;
import java.util.Arrays;

import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.io.SVNRepository;
import com.yy.yank.svn.kit.bean.MyDirectoryStackBean;
import com.yy.yank.svn.kit.util.MyRepositoryConnectUtil;
import com.yy.yank.svn.kit.util.MyUserAuthenticationUtil;

/**
 * メインクラス
 * 
 * @author yy_yank
 * @version 1.0
 */
public class MySVNKitExecuter {

	public static SVNRepository repository;

	/**
	 * メインメソッドです<br>
	 * リポジトリの設定、USER認証、リポジトリの情報取得の各クラスメソッドを呼び出し実行します。<br>
	 * 
	 * @param args
	 *            リポジトリのURLを引数に入れる
	 * @throws SVNException
	 * @throws IOException
	 */
	public static void main(String[] args) throws SVNException, IOException {

		// リポジトリ作成
		repository = MyRepositoryConnectUtil.createRepository(args[0]);

		// 認証
		MyUserAuthenticationUtil.auth("user", "password");
		MyDirectoryResearcher researcher = new MyDirectoryResearcher(args[0]);
		researcher.setAttributeAuthorList(Arrays.asList(args));
		
		String currentPath = "";
		// カレントディレクトリとカレントディレクトリ直下の情報を取得
		MyDirectoryStackBean dir = researcher.getCurrentAndChild(currentPath);
		researcher.initDirectoryStack(dir);

		
		// 取得した親子ディレクトリの情報を元に再帰処理をしていく
		researcher.recursiveDir(dir);

		// MyDirectoryResearcherのインスタンスにスタックされた情報を出力
		researcher.output();

	}
}
