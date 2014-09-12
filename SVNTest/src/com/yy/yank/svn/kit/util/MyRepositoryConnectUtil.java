package com.yy.yank.svn.kit.util;

import java.util.ArrayList;
import java.util.List;

import org.tmatesoft.svn.core.SVNDirEntry;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNProperties;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;

import com.yy.yank.svn.kit.MySVNKitExecuter;

/**
 * リポジトリ接続クラス
 * 
 * @author yy_yank
 * @version 1.0
 */
public class MyRepositoryConnectUtil {

	/ **プライベートコンストラクタ* /
	private MyRepositoryConnectUtil() {
	}

	/**
	 * ディレクトリ取得
	 * 
	 * @param path
	 *            　ディレクトリの相対パス
	 * @return ディレクトリ情報 List<SVNDirEntry>
	 */
	public static List<SVNDirEntry> getDir(String path) throws SVNException {
		SVNProperties dirProps = new SVNProperties();
		List<SVNDirEntry> dirEntries = new ArrayList<SVNDirEntry>();
		int headRevision = -1;
		MySVNKitExecuter.repository.getDir(path, headRevision, dirProps, dirEntries);
		return dirEntries;
	}

	/**
	 * リポジトリ作成
	 * 
	 * @param repoURL
	 *            　リポジトリのURL文字列
	 * @return リポジトリ SVNRepository
	 */
	public static SVNRepository createRepository(String repoUrl) throws SVNException {
		SVNURL url = SVNURL.parseURIEncoded(repoUrl);
		DAVRepositoryFactory.setup();
		return SVNRepositoryFactory.create(url);
	}
}
