/*
 * ==================================================
 * Connector Parmission
 * ( for VCSSL / Vnano Plug-in Development )
 * --------------------------------------------------
 * This file is released under CC0.
 * Written in 2017-2022 by RINEARN (Fumihiro Matsui)
 * ==================================================
 */

package org.vcssl.connect;

/**
 * <p>
 * <span class="lang-en">A class for defining names of permission items</span>
 * <span class="lang-ja">パーミッションの項目名が定義されたクラスです</span>
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
public class ConnectorPermissionName {

	
	/**
	 * <span class="lang-en">The name of the meta item representing all permission items</span>
	 * <span class="lang-ja">全てのパーミッション項目を表す, メタ項目名です</span>
	 * .
	 */
	public static final String ALL = "ALL";

	/**
	 * <span class="lang-en">The name of the meta item representing no permission item</span>
	 * <span class="lang-ja">パーミッション項目が無い事を表す, メタ項目名です</span>
	 * .
	 */
	public static final String NONE = "NONE";

	/**
	 * <p>
	 * <span class="lang-en">The name of the meta item storing the default permission value</span>
	 * <span class="lang-ja">全パーミッション項目における未設定時のデフォルト値を保持する, メタ項目名です</span>
	 * .
	 * </p>
	 * 
	 * <p>
	 * <span class="lang-en">
	 * For permission items of which values are not specified explicitly, 
	 * a default value (e.g. {@link ConnectorPermissionValue#DENY DENY}) will be set automatically.
	 * You can change that default value by setting the value to this meta permission item.
	 * For example, if you set the value {@link ConnectorPermissionValue#ASK ASK} to this permission item "DEFAULT", 
	 * the script engine will ask to the user when non-specified permissions are required.
	 * </span>
	 * <span class="lang-ja">
	 * 明示的に設定されていないパーミッション項目には, デフォルト値（例えば {@link ConnectorPermissionValue#DENY DENY} など）
	 * が自動的に設定されます. このデフォルト値は, このメタパーミッション項目「 DEFAULT 」の値を変える事で変更できます. 
	 * 例えば, この「 DEFAULT 」項目に値 {@link ConnectorPermissionValue#ASK ASK} を設定すると, 
	 * スクリプトエンジンは, 未設定のパーミッションが要求された際に, ユーザーに許可/拒否を尋ねるようになります. 
	 * </span>
	 * </p>
	 */
	public static final String DEFAULT = "DEFAULT";

	/**
	 * <span class="lang-en">The permission to exit the currently executed program (script)</span>
	 * <span class="lang-ja">現在実行中のプログラム（スクリプト）を終了させる事に対するパーミッションです</span>
	 * .
	 */
	public static final String PROGRAM_EXIT = "PROGRAM_EXIT";

	/**
	 * <span class="lang-en">The permission to reset/restart the currently executed program (script)</span>
	 * <span class="lang-ja">現在実行中のプログラム（スクリプト）をリセット/再実行する事に対するパーミッションです</span>
	 * .
	 */
	public static final String PROGRAM_RESET = "PROGRAM_RESET";

	/**
	 * <span class="lang-en">The permission to change the currently executed program (script)</span>
	 * <span class="lang-ja">現在実行中のプログラム（スクリプト）を変更する事に対するパーミッションです</span>
	 * .
	 */
	public static final String PROGRAM_CHANGE = "PROGRAM_CHANGE";

	/**
	 * <span class="lang-en">The permission to execute commands or other programs through the Operating System and so on</span>
	 * <span class="lang-ja">オペレーティングシステム等を介して, コマンドや別のプログラム等を実行する事に対するパーミッションです</span>
	 * .
	 */
	public static final String SYSTEM_PROCESS = "SYSTEM_PROCESS";

	/**
	 * <span class="lang-en">The permission to create a new directory (folder)</span>
	 * <span class="lang-ja">ディレクトリ（フォルダ）の新規作成に対するパーミッションです</span>
	 * .
	 */
	public static final String DIRECTORY_CREATE = "DIRECTORY_CREATE";

	/**
	 * <span class="lang-en">The permission to delete a directory (folder)</span>
	 * <span class="lang-ja">ディレクトリ（フォルダ）の削除に対するパーミッションです</span>
	 * .
	 */
	public static final String DIRECTORY_DELETE = "DIRECTORY_DELETE";

	/**
	 * <span class="lang-en">The permission to get the list of files in a directory (folder)</span>
	 * <span class="lang-ja">ディレクトリ（フォルダ）内のファイル一覧取得に対するパーミッションです</span>
	 * .
	 */
	public static final String DIRECTORY_LIST = "DIRECTORY_LIST";

	/**
	 * <span class="lang-en">The permission to create a new file</span>
	 * <span class="lang-ja">ファイルの新規作成に対するパーミッションです</span>
	 * .
	 */
	public static final String FILE_CREATE = "FILE_CREATE";

	/**
	 * <span class="lang-en">The permission to delete a file</span>
	 * <span class="lang-ja">ファイルの削除に対するパーミッションです</span>
	 * .
	 */
	public static final String FILE_DELETE = "FILE_DELETE";

	/**
	 * <span class="lang-en">The permission to write contents of a file</span>
	 * <span class="lang-ja">ファイルへの書き込みに対するパーミッションです</span>
	 * .
	 */
	public static final String FILE_WRITE = "FILE_WRITE";

	/**
	 * <span class="lang-en">The permission to read contents of a file</span>
	 * <span class="lang-ja">ファイルからの読み込みに対するパーミッションです</span>
	 * .
	 */
	public static final String FILE_READ = "FILE_READ";

	/**
	 * <span class="lang-en">The permission to overwrite contents of a file</span>
	 * <span class="lang-ja">ファイルへの上書きに対するパーミッションです</span>
	 * .
	 */
	public static final String FILE_OVERWRITE = "FILE_OVERWRITE";

	/**
	 * <span class="lang-en">The permission to change information (last modified date, and so on) of a file</span>
	 * <span class="lang-ja">ファイルの情報変更（更新日時など）に対するパーミッションです</span>
	 * .
	 */
	public static final String FILE_INFORMATION_CHANGE = "FILE_INFORMATION_CHANGE";
}
