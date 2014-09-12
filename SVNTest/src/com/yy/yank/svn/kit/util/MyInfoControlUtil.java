package com.yy.yank.svn.kit.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.tmatesoft.svn.core.SVNDirEntry;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNNodeKind;

/**
 * リポジトリ情報管理クラス
 * 
 * @author yy_yank
 * @version 1.0
 */
public class MyInfoControlUtil {

	/** プライベートコンストラクタ */
	private MyInfoControlUtil() {
	}

	/**
	 * ディレクトリ直下を検索して、子ディレクトリがあった場合、そのリストを返す
	 * 
	 * @param dirEntries
	 *            　SVNDirEntryのリスト
	 * @return 子ディレクトリ（リスト）
	 */
	public static List<SVNDirEntry> getIsDirectoryList(List<SVNDirEntry> dirEntries) {
		List<SVNDirEntry> list = new ArrayList<SVNDirEntry>();
		for (SVNDirEntry dirEntry : dirEntries) {
			if (dirEntry.getKind() == SVNNodeKind.DIR) {
				list.add(dirEntry);
			}
		}
		return list;
	}

	/**
	 * ディレクトリ直下を検索して、子ディレクトリがあった場合、そのリストを返す
	 * 
	 * @param dirEntries
	 *            　SVNDirEntryのリスト
	 * @return 子ファイル（リスト）
	 */
	public static List<SVNDirEntry> getIsFileList(List<SVNDirEntry> dirEntries) {
		List<SVNDirEntry> list = new ArrayList<SVNDirEntry>();
		for (SVNDirEntry dirEntry : dirEntries) {
			if (dirEntry.getKind() != SVNNodeKind.DIR) {
				list.add(dirEntry);
			}
		}
		return list;
	}

	/**
	 * ディレクトリ情報を元にファイルに情報を返す
	 * 
	 * @param entry
	 *            　SVNDirEntry
	 * @return 出力データ
	 */
	public static String getEntryData(SVNDirEntry entry) throws SVNException {
		StringBuffer buffer = new StringBuffer();
		if (entry.getKind() == SVNNodeKind.FILE) {
			buffer.append("File名..." + entry.getName());
			buffer.append("\n");
			buffer.append("author名" + entry.getAuthor());
			buffer.append("\n");
			buffer.append("リビジョン = " + entry.getRevision());
			buffer.append("\n");
			buffer.append("最終更新日付 = " + entry.getDate());
			buffer.append("\n");
			buffer.append("URL = " + entry.getURL());
			buffer.append("\n");
			buffer.append("---------------------------------------------------");
			buffer.append("\n");
		}
		return buffer.toString();
	}

	/**
	 * プロパティズファイルを動的に書き換えます
	 * 
	 * @param text
	 *            String　プロパティズファイルの値（key=value）
	 * 
	 */
	public static void createLogFile(String fileName, String text) throws IOException {
		PrintWriter pw = null;
		try {
			String fileNameSplit[] = fileName.split("/");
			int last = fileNameSplit.length - 1;

			File newfile = new File("C:/Users/yyyank/Desktop/" + fileNameSplit[last] + ".txt");
			if (newfile.exists()) {
				newfile.createNewFile();
			}
			pw = new PrintWriter(new BufferedWriter(new FileWriter(newfile, true)));
			pw.println(text);
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
	}

	// ディレクトリ直下を出力する
	public static String getDirPath(String baseUrl, SVNDirEntry entry) throws SVNException {
		return entry.getURL().toString().replace(baseUrl, "");
	}
}
