/*
 * ==================================================
 * Engine Connector Interface 1
 * --------------------------------------------------
 * This file is released under CC0.
 * Written in 2018-2022 by RINEARN (Fumihiro Matsui)
 * ==================================================
 */

package org.vcssl.connect;

/**
 * <p>
 * <span class="lang-en">
 * An interface for mediate communication of some information between script engines and plug-ins
 * </span>
 * <span class="lang-ja">
 * スクリプトエンジンとプラグインとの間における, いくつかの情報のやり取りを仲介するためのインターフェースです
 * </span>
 * .
 * </p>
 * 
 * <p>
 * <span class="lang-en">
 * Script engines pass an object implementing this interface to arguments 
 * of initialization/finalization methods of plug-ins. 
 * </span>
 * <span class="lang-ja">
 * スクリプトエンジンは, このインターフェースを実装したオブジェクトを, 
 * 各プラグインの初期化/終了時処理のメソッドの引数として渡します. 
 * </span>
 * </p>
 */
public interface EngineConnectorInterface1 {

	/**
	 * <span class="lang-en">The type ID of this interface (value: "ECI") referred when the plug-in will be loaded</span>
	 * <span class="lang-ja">プラグインのロード時に参照される, このインターフェースの形式ID（値: "ECI"）です</span>
	 * .
	 */
	public static final String INTERFACE_TYPE_ID = "ECI";

	/**
	 * <span class="lang-en">The generation of this interface (value: "1")</span>
	 * <span class="lang-ja">このインターフェースの世代名です（値: "1"）</span>
	 * .
	 */
	public static final String INTERFACE_GENERATION = "1";

	/**
	 * <span class="lang-en">Returns whether the engine has the value of the option with the specified name</span>
	 * <span class="lang-ja">指定された名称のオプションの値が設定されているかどうかを返します</span>
	 * .
	 * @param optionName
	 *     <span class="lang-en">The name of the option</span>
	 *     <span class="lang-ja">オプションの名称</span>
	 * 
	 * @return
	 *     <span class="lang-en">Returns "true" if the engine has the value of the specified option</span>
	 *     <span class="lang-ja">指定されたオプションの値が設定されている場合に true が返されます</span>
	 */
	public abstract boolean hasOptionValue(String optionName);


	/**
	 * <span class="lang-en">Returns the value of the specified option</span>
	 * <span class="lang-ja">指定されたオプションの値を取得します</span>
	 * .
	 * @param optionName
	 *     <span class="lang-en">The name of the option</span>
	 *     <span class="lang-ja">オプションの名称</span>
	 * 
	 * @return
	 *     <span class="lang-en">The value of the specified option</span>
	 *     <span class="lang-ja">指定されたオプションの値</span>
	 */
	public abstract Object getOptionValue(String optionName);


	/**
	 * <span class="lang-en">Request the specified permission</span>
	 * <span class="lang-ja">指定されたパーミッションを要求します</span>
	 * .
	 * @param permissionName
	 *     <span class="lang-en">The name of the permission item to request</span>
	 *     <span class="lang-ja">要求するパーミッション項目の名称</span>
	 * 
	 * @param requester
	 *     <span class="lang-en">The plug-in requesting the permission</span>
	 *     <span class="lang-ja">パーミッションを要求しているプラグイン</span>
	 * 
	 * @param metaInformation
	 *     <span class="lang-en">
	 *         The information to be notified to the user 
	 *         (especially when the current value of the permission is set to {@link ConnectorPermissionValue#ASK ASK})
	 *     </span>
	 *     <span class="lang-ja">
	 *         必要に応じてユーザーに通知されるメタ情報
	 *         （特に, パーミッション値が {@link ConnectorPermissionValue#ASK ASK} に設定されている際などに表示されます）
	 *     </span>
	 * 
	 * @throws ConnectorException
	 *     <span class="lang-en">Thrown when the requested permission has been denied</span>
	 *     <span class="lang-ja">要求したパーミッションが拒否された場合にスローされます</span>
	 */
	public abstract void requestPermission(String permissionName, Object requester, Object metaInformation)
			throws ConnectorException;
}
