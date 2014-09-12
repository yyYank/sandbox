package com.yy.yank.svn.kit.bean;

import java.util.List;

import org.tmatesoft.svn.core.SVNDirEntry;

/**
 * ディレクトリクラス
 * 
 * @author yy_yank
 * @version 1.0
 */
public class MyDirectoryStackBean {

	/** カレントディレクトリの実体 */
	private List<SVNDirEntry> dirEntry;

	/** カレントディレクトリのファイル情報 */
	private List<SVNDirEntry> currentFile;

	/** 子ディレクトリインスタンス */
	private MyDirectoryStackBean childDir;

	/**
	 * カレントディレクトリの実体を取得します。
	 * 
	 * @return カレントディレクトリの実体
	 */
	public List<SVNDirEntry> getDirEntry() {
		return dirEntry;
	}

	/**
	 * カレントディレクトリの実体を設定します。
	 * 
	 * @param dirEntry
	 *            カレントディレクトリの実体
	 */
	public void setDirEntry(List<SVNDirEntry> dirEntry) {
		this.dirEntry = dirEntry;
	}

	/**
	 * カレントディレクトリのファイル情報を取得します。
	 * 
	 * @return カレントディレクトリのファイル情報
	 */
	public List<SVNDirEntry> getCurrentFile() {
		return currentFile;
	}

	/**
	 * カレントディレクトリのファイル情報を設定します。
	 * 
	 * @param currentFile
	 *            カレントディレクトリのファイル情報
	 */
	public void setCurrentFile(List<SVNDirEntry> currentFile) {
		this.currentFile = currentFile;
	}

	/**
	 * 子ディレクトリインスタンスを取得します。
	 * 
	 * @return 子ディレクトリインスタンス
	 */
	public MyDirectoryStackBean getChildDir() {
		return childDir;
	}

	/**
	 * 子ディレクトリインスタンスを設定します。
	 * 
	 * @param childDir
	 *            子ディレクトリインスタンス
	 */
	public void setChildDir(MyDirectoryStackBean childDir) {
		this.childDir = childDir;
	}

}
