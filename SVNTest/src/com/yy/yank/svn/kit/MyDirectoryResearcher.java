package com.yy.yank.svn.kit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.tmatesoft.svn.core.SVNDirEntry;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNProperties;

import com.yy.yank.svn.kit.bean.MyDirectoryStackBean;
import com.yy.yank.svn.kit.util.MyInfoControlUtil;

/**
 * ディレクトリ検索クラス
 * 
 * @author yy_yank
 * @version 1.0
 */
public class MyDirectoryResearcher {

	/** ディレクトリのリスト */
	private List<MyDirectoryStackBean> dirStack;
	/** リポジトリのURL */
	private String repoURL;
	/** 出力条件属性(最終更新者) */
	private List<String> attributeAuthorList;

	/** コンストラクタ */
	public MyDirectoryResearcher(String url) {
		init(url);
	}

	/**
	 * 初期化メソッド
	 * 
	 * @param url
	 * 
	 */
	private void init(String url) {
		if (!url.endsWith("/")) {
			url += "/";
		}
		repoURL = url;
		// インスタンスを生成するたびにスタックを初期化
		dirStack = new ArrayList<MyDirectoryStackBean>();
	}

	/**
	 * 親子ディレクトリを取得します
	 * 
	 * @param dirName
	 *            ディレクトリ相対パス
	 * @return 親子ディレクトリ情報 MyDirectoryStack
	 */
	public MyDirectoryStackBean getCurrentAndChild(String dirName) {
		SVNProperties dirProps = new SVNProperties();
		List<SVNDirEntry> dirEntries = new ArrayList<SVNDirEntry>();
		int headRevision = -1;
		// 指定ディレクトリの取得
		try {
			MySVNKitExecuter.repository.getDir(dirName, headRevision, dirProps, dirEntries);
		} catch (SVNException e) {
			// ここでは例外は流す
		}
		// 取得したディレクトリからディレクトリの情報のみ抽出
		List<SVNDirEntry> parent = MyInfoControlUtil.getIsDirectoryList(dirEntries);
		List<SVNDirEntry> parentFile = MyInfoControlUtil.getIsFileList(dirEntries);
		// カレントディレクトリ情報の設定
		MyDirectoryStackBean dir = new MyDirectoryStackBean();
		dir.setDirEntry(parent);
		dir.setCurrentFile(parentFile);
		// 子ディレクトリ情報の設定
		MyDirectoryStackBean childDir = new MyDirectoryStackBean();
		if (parent.size() != 0) {
			childDir.setDirEntry(MyInfoControlUtil.getIsDirectoryList(parent));
			childDir.setCurrentFile(MyInfoControlUtil.getIsFileList(childDir.getDirEntry()));
		}
		dir.setChildDir(childDir);

		return dir;
	}

	/**
	 * 再帰処理を行います<br>
	 * 子ディレクトリがある限りループします
	 * 
	 * @param dir
	 *            親子ディレクトリ情報
	 */
	public void recursiveDir(MyDirectoryStackBean dir) throws SVNException {
		MyDirectoryStackBean childDir = dir.getChildDir();
		List<MyDirectoryStackBean> local = new ArrayList<MyDirectoryStackBean>();
		if (null != childDir && null != childDir.getDirEntry()) {
			List<SVNDirEntry> entrys = childDir.getDirEntry();
			for (SVNDirEntry dirEntry : entrys) {
				MyDirectoryStackBean result = this.getCurrentAndChild(MyInfoControlUtil.getDirPath(repoURL, dirEntry));
				local.add(result);
				dirStack.add(result);
			}
			// 再帰処理
			this.childLoop(local);
		}
	}

	/**
	 * 再帰処理を行います<br>
	 * 親子ディレクトリ情報のリストでループし探索します
	 * 
	 * @param local
	 *            List<MyDirectoryStack> 親子ディレクトリ情報のリスト
	 */
	public void childLoop(List<MyDirectoryStackBean> local) throws SVNException {
		for (MyDirectoryStackBean dir : local) {
			this.recursiveDir(dir);
		}
	}

	/**
	 * 出力を行います<br>
	 * 
	 * @throws IOException
	 */
	public void output() throws SVNException, IOException {
		StringBuffer buffer = new StringBuffer();
		for (MyDirectoryStackBean dir : dirStack) {
			List<SVNDirEntry> fileEntries = dir.getCurrentFile();
			for (SVNDirEntry fileEntry : fileEntries) {
				if (isAuthor(fileEntry.getAuthor())) {
					buffer.append(MyInfoControlUtil.getEntryData(fileEntry));
				}
			}
		}
		MyInfoControlUtil.createLogFile(repoURL, buffer.toString());
	}

	/**
	 * dirStackを設定します。
	 * 
	 * @param dirStack
	 *            dirStack
	 */
	public void initDirectoryStack(MyDirectoryStackBean dirStack) {
		this.dirStack.add(dirStack);
	}

	/**
	 * 出力条件属性(最終更新者)を取得します。
	 * 
	 * @return 出力条件属性(最終更新者)
	 */
	public List<String> getAttributeAuthorList() {
		return attributeAuthorList;
	}

	/**
	 * 出力条件属性(最終更新者)を設定します。
	 * 
	 * @param attributeAuthorList
	 *            出力条件属性(最終更新者)
	 */
	public void setAttributeAuthorList(List<String> attributeAuthorList) {
		this.attributeAuthorList = attributeAuthorList;
	}

	/**
	 * 出力条件属性(最終更新者)を設定します。
	 * 
	 * @param current
	 *            最終更新者名
	 * @return boolean 最終更新者かどうか
	 */
	public boolean isAuthor(String current) {
		// 属性条件を付けていない場合は全てtrueで返す
		if (attributeAuthorList == null) {
			return true;
		}

		// 　最終更新者との比較
		for (String authorName : attributeAuthorList) {
			if (authorName != null && authorName.equals(current)) {
				return true;
			}
		}
		return false;
	}
}
