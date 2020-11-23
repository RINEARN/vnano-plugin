/*
 * ================================================================
 * Scalar Data Accessor Interface 1 (SDAI 1) for Int64 Type Data
 * ( for VCSSL / Vnano Plug-in Development )
 * ----------------------------------------------------------------
 * This file is released under CC0.
 * Written in 2020 by RINEARN (Fumihiro Matsui)
 * ================================================================
 */

package org.vcssl.connect;

/**
 * <p>
 * 主に処理系のデータコンテナが実装してサポートする、
 * 64bit整数型用のSDAI 1 (Scalar Data Accessor Interface 1) 形式のデータ入出力インターフェースです。
 * </p>
 */
public interface Int64ScalarDataAccessorInterface1 {

	/** 動的ロード時などに処理系側から参照される、インターフェースの形式名（値は"INT64_SDAI"）です。*/
	public static final String INTERFACE_TYPE = "INT64_SDAI";

	/** 動的ロード時などに処理系側から参照される、インターフェースの世代名（値は"1"）です。*/
	public static final String INTERFACE_GENERATION = "1";


	/**
	 * 値を設定します。
	 *
	 * @param data 設定値
	 */
	public abstract void setInt64ScalarData(long data);


	/**
	 * 値を取得します。
	 *
	 * @return 取得値
	 */
	public abstract long getInt64ScalarData();
}
