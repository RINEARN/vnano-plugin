/*
 * ==================================================
 * Connector Exception
 * ( for VCSSL / Vnano Plug-in Development )
 * --------------------------------------------------
 * This file is released under CC0.
 * Written in 2019-2022 by RINEARN (Fumihiro Matsui)
 * ==================================================
 */

package org.vcssl.connect;

/**
 * <span class="lang-en">
 * An Exception class for notifying language processor systems of errors occurred in plug-ins
 * </span>
 * <span class="lang-ja">
 * プラグインにおいて, 実行時に生じた例外をラップし, 処理系側に通知するための例外です
 * </span>
 * .
 */
@SuppressWarnings("serial")
public class ConnectorException extends Exception {

	/**
	 * <span class="lang-en">
	 * Creates a new ConnectorException having no error message
	 * </span>
	 * <span class="lang-ja">
	 * 何もエラーメッセージを持たない, 新しい ConnectorException を生成します
	 * </span>
	 * .
	 */
	public ConnectorException() {
	}
	
	/**
	 * <span class="lang-en">
	 * Creates a new ConnectorException wrapping the specified Throwable
	 * </span>
	 * <span class="lang-ja">
	 * 指定された Throwable をラップする, 新しい ConnectorException を生成します
	 * </span>
	 * .
	 * @param wrappedThrowable
	 *     <span class="lang-en">The Throwable to be wrapped</span>
	 *     <span class="lang-ja">ラップする Throwable</span>
	 */
	public ConnectorException(Throwable wrappedThrowable) {
		super(wrappedThrowable);
	}

	/**
	 * <span class="lang-en">
	 * Creates a new ConnectorException having the specified error message
	 * </span>
	 * <span class="lang-ja">
	 * 指定されたエラーメッセージを持つ, 新しい ConnectorException を生成します
	 * </span>
	 * .
	 * @param errorMessage
	 *     <span class="lang-en">The error message</span>
	 *     <span class="lang-ja">エラーメッセージ</span>
	 */
	public ConnectorException(String errorMessage) {
		super(errorMessage);
	}
	
	/**
	 * <span class="lang-en">
	 * Creates a new ConnectorException having the specified error message, and wrapping the specified Throwable
	 * </span>
	 * <span class="lang-ja">
	 * 指定されたエラーメッセージを持ち, 指定された Throwable をラップする, 新しい ConnectorException を生成します
	 * </span>
	 * .
	 * @param errorMessage
	 *     <span class="lang-en">The error message</span>
	 *     <span class="lang-ja">エラーメッセージ</span>
	 * @param wrappedThrowable
	 *     <span class="lang-en">The Throwable to be wrapped</span>
	 *     <span class="lang-ja">ラップする Throwable</span>
	 */
	public ConnectorException(String errorMessage, Throwable wrappedThrowable) {
		super(errorMessage, wrappedThrowable);
	}
}
