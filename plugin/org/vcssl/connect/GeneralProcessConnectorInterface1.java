/*
 * ==================================================
 * General Process Connector Interface 1 (GPCI 1)
 * ( for VCSSL / Vnano Plug-in Development )
 * --------------------------------------------------
 * This file is released under CC0.
 * Written in 2017-2022 by RINEARN (Fumihiro Matsui)
 * ==================================================
 */

package org.vcssl.connect;

/**
 * <p>
 * <span class="lang-en">A legacy interface for implementing GPCI 1 plug-ins</span>
 * <span class="lang-ja">GPCI 1 形式のプラグインを実装するためのレガシーインターフェースです</span>
 * .
 * </p>
 * 
 * <p>
 * <span class="lang-en">
 * GPCI series are old interfaces, and is only available on VCSSL Engine.
 * Vnano Engine does not support this interface. 
 * </span>
 * <span class="lang-ja">
 * GPCI系は古いインターフェースで, VCSSL処理系においてのみ使用できます. 
 * Vnano処理系ではサポートされません. 
 * </span>
 * </p>
 */
public interface GeneralProcessConnectorInterface1 {

	/**
	 * <span class="lang-en">Returns whether this plug-in can process the function having the specified name</span>
	 * <span class="lang-ja">このプラグインが, 指定された名前を持つ関数を実行可能かどうかを返します</span>
	 * .
	 * @param functionName
	 *     <span class="lang-en">The name of the function</span>
	 *     <span class="lang-ja">関数の名前</span>
	 * 
	 * @return
	 *     <span class="lang-en">
	 *     Returns "true" if this plug-in can process the function having the specified name
	 *     </span>
	 *     <span class="lang-ja">
	 *     指定された名前を持つ関数を実行可能な場合に true が返されます
	 *     </span>
	 */
	public boolean isProcessable(String functionName);


	/**
	 * <span class="lang-en">Processes the function having the spacified name</span>
	 * <span class="lang-ja">指定された名前を持つ関数を実行します</span>
	 * .
	 * @param functionName
	 *     <span class="lang-en">The name of the function to be processed</span>
	 *     <span class="lang-ja">実行する関数の名前</span>
	 * 
	 * @param arguments
	 *     <span class="lang-en">An array storing values of all actual arguments</span>
	 *     <span class="lang-ja">全ての実引数の値を格納する配列</span>
	 * 
	 * @return
	 *     <span class="lang-en">An array storing return value of the function</span>
	 *     <span class="lang-ja">関数の戻り値を格納する配列</span>
	 */
	public String[] process(String functionName, String[] arguments);
}
