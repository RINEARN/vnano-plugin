/*
 * ==================================================
 * Connector Parmission
 * ( for VCSSL / Vnano Plug-in Development )
 * --------------------------------------------------
 * This file is released under CC0.
 * Written in 2020-2022 by RINEARN (Fumihiro Matsui)
 * ==================================================
 */

package org.vcssl.connect;

/**
 * <p>
 * <span class="lang-en">A class for defining permission values</span>
 * <span class="lang-ja">パーミッションの値が定義されたクラスです</span>
 * .
 * </p>
 *
 * <p>
 * <span class="lang-en">
 * Fields of this class are natural to be defined as elements of an enum, 
 * however, they are defined as "public static final String" fields, 
 * instead of enum elements.
 * This is to prevent unexpected behaviour when definition order of them are changed, 
 * and plug-ins referencing them have not been re-compiled.
 * </span>
 * <span class="lang-ja">
 * このクラスのフィールドは, 本来は enum の要素として定義されるのが自然ですが, 
 * 実際にはそうではなく "public static final String" のフィールドが列挙的に定義されています. 
 * これは, それらの定義順序が変更された場合における, 
 * それらを参照しているプラグインの（再コンパイルされない状態での）予期しない動作を防ぐためです. 
 * </span>
 * </p>
 * 
 * <p>
 * <span class="lang-en">
 * However, it is not recommended to describe values of them directly (as String literals).
 * Refer fields of this class as possible as.
 * </span>
 * <span class="lang-ja">
 * ただし, 各フィールドの文字列値を直接（文字列リテラルなどとして）記述する事は推奨されません. 
 * なるべくこのクラスのフィールドを参照してください. 
 * </span>
 * </p>
 */
public class ConnectorPermissionValue {

	/**
	 * <span class="lang-en">
	 * Requests for permission items having this value will always be allowed
	 * </span>
	 * <span class="lang-ja">
	 * この値を持つパーミッション項目は, リクエストが常に許可されます
	 * </span>
	 * .
	 */
	public static final String ALLOW = "ALLOW";

	/**
	 * <span class="lang-en">
	 * Requests for permission items having this value will always be denied
	 * </span>
	 * <span class="lang-ja">
	 * この値を持つパーミッション項目は, リクエストが常に拒否されます
	 * </span>
	 * .
	 */
	public static final String DENY = "DENY";

	/**
	 * <span class="lang-en">
	 * When permission items having this value are requested,
	 * the script engine asks the user whether allows it or not
	 * </span>
	 * <span class="lang-ja">
	 * この値を持つパーミッション項目においては, スクリプトエンジンがユーザーに対して,
	 * リクエストを許可するかどうかを尋ねた上で決定します
	 * </span>
	 * .
	 */
	public static final String ASK = "ASK";
}
