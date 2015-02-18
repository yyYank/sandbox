package com.yy.yank.svn.kit.test;

import junit.framework.Assert;
import org.junit.Test;
import com.yy.yank.svn.kit.MySVNKitExecuter;

/**
 * 実行テストクラス
 * 
 * @author yy_yank
 * @version 1.0
 */
public class SVNTest {

	@Test
	public void excecuteTest() {
		try {
			// メインメソッド用の引数
			String str[] = new String[2];
			// リポジトリのURL
			str[0] = "http://yyyank/test.com";
			// 最終更新者名(検索で絞り込み用)
			str[1] = "yy_yunk";
			MySVNKitExecuter.main(str);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

}
