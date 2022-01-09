/*
 * ==================================================
 * Permission Authorizer Connector Interface 1
 * --------------------------------------------------
 * This file is released under CC0.
 * Written in 2020-2022 by RINEARN (Fumihiro Matsui)
 * ==================================================
 */

package org.vcssl.connect;

import java.util.Map;

/**
 * <p>
 * <span class="lang-en">
 * An interface (abbreviated as PACI 1) for implementing permission-based security plug-ins
 * </span>
 * <span class="lang-ja">
 * パーミッションベースのセキュリティプラグインを実装するためのインターフェースの一つ（略称 PACI 1）です
 * </span>
 * .
 * </p>
 * 
 * <p>
 * <span class="lang-en">
 * PACI 1 plug-ins receive requests of permissions from other plug-ins (through script engines),
 * and determine whether allow or deny it (or ask to the user to determine it, if necessary).
 * </span>
 * <span class="lang-ja">
 * PACI 1 形式のプラグインは, スクリプトエンジンを介して, 他のプラグインからのパーミッション要求を受け取り, 
 * それを許可/拒否するかを（必要に応じてユーザーに尋ねた上で）決定します. 
 * </span>
 * </p>
 * 
 * <p>
 * <span class="lang-en">
 * What form of UI is suitable for the above determination highly depends on 
 * the application's UI, purpose, and so on. 
 * Hence, the application-side can design UI for the above determination,
 * and can connect (and use) it to script engines, by implementing this interface.
 * </span>
 * <span class="lang-ja">
 * どういった形のUIが上記の決定処理に適しているかは, アプリケーション側のUIや用途などにかなり依存します. 
 * そのため, アプリケーション側で上記の決定処理のUIをデザインし, このインターフェースを実装する事で, 
 * それをスクリプトエンジンに接続して使用する事ができます. 
 * </span>
 * </p>
 * 
 * <p>
 * <span class="lang-en">
 * Also, on PACI 1, following two types of permission settings are assumed to exist:
 * </span>
 * </p>
 * <dl class="lang-en" style="margin-left: 30px;">
 *     <dt>Base permission settings:</dt>
 *         <dd>
 *         Settings which is set to applications/script engines explicitly, 
 *         and applied automatically to each script at the beginning time of its execution.
 *         </dd>
 *     <dt>Temporary permission settings:</dt>
 *         <dd>
 *         Temporary settings kept only during each script is running, 
 *         of which values may change by user's responses for requests of permissions.
 *         </dd>
 * </dl>
 * <p>
 * <span class="lang-en">
 * When the script is executed, at the beginning, values of the base permission settings 
 * are copied to the temporary permission settings.
 * During the script is running, values of temporary permission settings are referred 
 * (and modified if necessary), when any permission is requested.
 * When the next script is executed, values of the temporary permission settings
 * are re-initialized by copying values of the base permission settings. 
 * </span>
 * </p>
 * 
 * <p>
 * <span class="lang-ja">
 * なお, PACI 1 では, 以下の通りの 2 種類のパーミッション設定の存在を想定しています:
 * </span>
 * </p>
 * <dl class="lang-ja" style="margin-left: 30px;">
 *     <dt>基礎パーミッション設定 (Base permission settings)：</dt>
 *         <dd>
 *         アプリケーションやスクリプトエンジンに明示的に設定され, 
 *         各スクリプトの実行開始時に自動適用されるパーミッション設定です. 
 *         </dd>
 *     <dt>一時パーミッション設定 (Temporary permission settings)：</dt>
 *         <dd>
 *         各スクリプトの実行中のみ持続し, ユーザーの許可操作などによって変化し得る, 
 *         一時的なパーミッション設定です. 
 *         </dd>
 * </dl>
 * <p>
 * <span class="lang-ja">
 * スクリプトが実行される際には, まず基礎パーミッション設定の内容が一時パーミッション設定にコピーされ, 
 * その後は一時パーミッション設定を参照しながら（必要に応じて設定が変化しながら）スクリプトが実行されます. 
 * 次のスクリプトが実行される際には, 一時パーミッション設定は再度, 基礎パーミッション設定の内容でリセットされます. 
 * </span>
 * </p>
 */
public interface PermissionAuthorizerConnectorInterface1 {

	/**
	 * <span class="lang-en">The type ID of this interface (value: "PACI") referred when the plug-in will be loaded</span>
	 * <span class="lang-ja">プラグインのロード時に参照される, このインターフェースの形式ID（値: "PACI"）です</span>
	 * .
	 */
	public static final String INTERFACE_TYPE_ID = "PACI";

	/**
	 * <span class="lang-en">The generation of this interface (value: "1")</span>
	 * <span class="lang-ja">このインターフェースの世代名です（値: "1"）</span>
	 * .
	 */
	public static final String INTERFACE_GENERATION = "1";


	/**
	 * <p>
	 * <span class="lang-en">
	 * Sets values of permission items, by a Map (permission map) storing names and values of permission items
	 * </span>
	 * <span class="lang-ja">
	 * パーミッション項目の名前と値を格納するマップ（パーミッションマップ）によって, 各パーミッションの値を設定します
	 * </span>
	 * .
	 * </p>
	 * 
	 * <p>
	 * <span class="lang-en">This method will be called from script engines or applications.</span>
	 * <span class="lang-ja">このメソッドは, スクリプトエンジンやアプリケーションから呼び出されます.</span>
	 * </p>
	 * 
	 * @param permissionMap
	 *     <span class="lang-en">The Map (permission map) storing names and values of permission items</span>
	 *     <span class="lang-ja">パーミッション項目の名前と値を格納するマップ（パーミッションマップ）</span>
	 * 
	 * @param setsToBase
	 *     <span class="lang-en">
	 *         Specify "true" to set base permission settings, "false" to set temporary permission settings
	 *     </span>
	 *     <span class="lang-ja">
	 *         基礎パーミッションとして設定する場合は true, 一時パーミッションとして設定する場合は false を指定します
	 *     </span>
	 * 
	 * @throws ConnectorException
	 *     <span class="lang-en">Thrown when invalid permission settings are detected</span>
	 *     <span class="lang-ja">無効なパーミッション設定が検出された際にスローされます</span>
	 */
	public abstract void setPermissionMap(Map<String, String> permissionMap, boolean setsToBase)
			throws ConnectorException;


	/**
	 * <p>
	 * <span class="lang-en">
	 * Returns the Map (permission map) storing names and current values of permission items
	 * </span>
	 * <span class="lang-ja">
	 * パーミッション項目の名前と現在の値を格納するマップ（パーミッションマップ）を返します
	 * </span>
	 * .
	 * </p>
	 * 
	 * <p>
	 * <span class="lang-en">This method will be called from script engines or applications.</span>
	 * <span class="lang-ja">このメソッドは, スクリプトエンジンやアプリケーションから呼び出されます.</span>
	 * </p>
	 * 
	 * @param getsFromBase
	 *     <span class="lang-en">
	 *         Specify "true" to get base permission settings, "false" to get temporary permission settings
	 *     </span>
	 *     <span class="lang-ja">
	 *         基礎パーミッションを取得する場合は true, 一時パーミッションを取得する場合は false を指定します
	 *     </span>
	 * 
	 * @return
	 *     <span class="lang-en">The Map (permission map) storing names and values of permission items</span>
	 *     <span class="lang-ja">パーミッション項目の名前と値を格納するマップ（パーミッションマップ）</span>
	 * 
	 * @throws ConnectorException
	 *     <span class="lang-en">Thrown when failed to get the specified permission settings</span>
	 *     <span class="lang-ja">指定したパーミッション設定の取得に失敗した場合された際にスローされます</span>
	 */
	public abstract Map<String, String> getPermissionMap(boolean getsFromBase)
			throws ConnectorException;


	/**
	 * <p>
	 * <span class="lang-en">Receives the request of the specified permission</span>
	 * <span class="lang-ja">指定されたパーミッションの要求を受け付けます</span>
	 * .
	 * </p>
	 * 
	 * <p>
	 * <span class="lang-en">
	 * If the specified permission should be allowed, this method is required to do nothing explicitly.
	 * If the specified permission should be denied, this method throws an ConnectorException.
	 * </span>
	 * <span class="lang-ja">
	 * 指定されたパーミッションが許可されるべき場合には, このメソッドは明示的には何もする必要はありません. 
	 * 指定されたパーミッションが拒否されるべき場合には, このメソッドは ConnectorException 例外を発生させます. 
	 * </span>
	 * </p>
	 * 
	 * <p>
	 * <span class="lang-en">This method will be called from other plug-ins through engine-connector interfaces.</span>
	 * <span class="lang-ja">このメソッドは, エンジンコネクターインターフェースを介して, 他のプラグインから呼び出されます. </span>
	 * </p>
	 * 
	 * @param permissionName
	 *     <span class="lang-en">The name of the requested permission item</span>
	 *     <span class="lang-ja">要求されているパーミッション項目の名称</span>
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


	/**
	 * <p>
	 * <span class="lang-en">Sets the value of the specified permission item</span>
	 * <span class="lang-ja">指定されたパーミッション項目の値を設定します</span>
	 * .
	 * </p>
	 *
	 * <p>
	 * <span class="lang-en">
	 * This method will be called from script engines/applications, 
	 * and may be called from other plug-ins through engine-connector interfaces.
	 * In the latter case, the permission for modifying/referencing permissions is required (but unsupported yet).
	 * </span>
	 * <span class="lang-ja">
	 * このメソッドは, スクリプトエンジンおよびアプリケーションから呼ばれる場合と, 
	 * エンジンコネクターインターフェースを介して他のプラグインから呼ばれる場合があります. 
	 * 後者の場合には, パーミッション操作/参照に対するパーミッションが必要です（現状では未サポートです）. 
	 * </span>
	 * </p>
	 * 
	 * @param permissionName
	 *     <span class="lang-en">The name of the permission item to be set its value</span>
	 *     <span class="lang-ja">値を設定するパーミッション項目の名称</span>
	 * 
	 * @param value
	 *     <span class="lang-en">The value to be set</span>
	 *     <span class="lang-ja">設定する値</span>
	 * 
	 * @param setsToBase
	 *     <span class="lang-en">
	 *         Specify "true" to set the value of base permission settings,
	 *         "false" to set the value of temporary permission settings
	 *     </span>
	 *     <span class="lang-ja">
	 *         基礎パーミッションの値を設定する場合は true, 
	 *         一時パーミッションの値を設定する場合は false を指定します
	 *     </span>
	 * 
	 * @throws ConnectorException
	 *     <span class="lang-en">Thrown when the requested action has been denied, or unsupported</span>
	 *     <span class="lang-ja">要求した操作が拒否された場合や, 未サポートの場合にスローされます</span>
	 */
	public abstract void setPermissionValue(String permissionName, String value, boolean setsToBase)
			throws ConnectorException;


	/**
	 * <p>
	 * <span class="lang-en">Gets the value of the specified permission item</span>
	 * <span class="lang-ja">指定されたパーミッション項目の値を取得します</span>
	 * .
	 * </p>
	 *
	 * <p>
	 * <span class="lang-en">
	 * This method will be called from script engines/applications, 
	 * and may be called from other plug-ins through engine-connector interfaces.
	 * In the latter case, the permission for modifying/referencing permissions is required (but unsupported yet).
	 * </span>
	 * <span class="lang-ja">
	 * このメソッドは, スクリプトエンジンおよびアプリケーションから呼ばれる場合と, 
	 * エンジンコネクターインターフェースを介して他のプラグインから呼ばれる場合があります. 
	 * 後者の場合には, パーミッション操作/参照に対するパーミッションが必要です（現状では未サポートです）. 
	 * </span>
	 * </p>
	 * 
	 * @param permissionName
	 *     <span class="lang-en">The name of the permission item to be gotten its value</span>
	 *     <span class="lang-ja">値を取得するパーミッション項目の名称</span>
	 * 
	 * @param getFromBase
	 *     <span class="lang-en">
	 *         Specify "true" to get the value of base permission settings,
	 *         "false" to get the value of temporary permission settings
	 *     </span>
	 *     <span class="lang-ja">
	 *         基礎パーミッションの値を取得する場合は true, 
	 *         一時パーミッションの値を取得する場合は false を指定します
	 *     </span>
	 * 
	 * @return
	 *     <span class="lang-en">The value to be gotten</span>
	 *     <span class="lang-ja">取得する値</span>
	 * 
	 * @throws ConnectorException
	 *     <span class="lang-en">Thrown when the requested action has been denied, or unsupported</span>
	 *     <span class="lang-ja">要求した操作が拒否された場合や, 未サポートの場合にスローされます</span>
	 */
	public abstract String getPermissionValue(String permissionName, boolean getFromBase)
			throws ConnectorException;


	/**
	 * <p>
	 * <span class="lang-en">Stores current temporary permission settings</span>
	 * <span class="lang-ja">現在の一時的なパーミッション状態を保存します</span>
	 * .
	 * </p>
	 *
	 * <p>
	 * If "true" is specified to the argument "storesToPermanent",
	 * the permanent permission settings !!!
	 * </p>
	 * <span class="lang-en">
	 * Stores current temporary permission settings
	 * for restoring it later by {@link restoreTemporaryPermissionValues(boolean)} method,
	 * or overwriting permanent permission settings
	 * </span>
	 * <span class="lang-ja">
	 * 現在の一時的なパーミッション状態を保存します. 
	 * 後で {@link restoreTemporaryPermissionValues(boolean)} メソッドによって復元可能な形に, 
	 * またはパーマネント設定値を上書きする事で保存します
	 * </span>
	 * 
	 * <p>
	 * <span class="lang-en">
	 * This method will be called from script engines/applications, 
	 * and may be called from other plug-ins through engine-connector interfaces.
	 * In the latter case, the permission for modifying/referencing permissions is required (but unsupported yet).
	 * </span>
	 * <span class="lang-ja">
	 * このメソッドは, スクリプトエンジンおよびアプリケーションから呼ばれる場合と, 
	 * エンジンコネクターインターフェースを介して他のプラグインから呼ばれる場合があります. 
	 * 後者の場合には, パーミッション操作/参照に対するパーミッションが必要です（現状では未サポートです）. 
	 * </span>
	 * </p>
	 * 
	 * @param storesToBase
	 *     <span class="lang-en">
	 *         Specify "true" to store by overwriting base permission settings,
	 *         "false" to store temporary for recovering later by {@link restoreTemporaryPermissionValues(boolean)} method.
	 *     </span>
	 *     <span class="lang-ja">
	 *         基礎パーミッション設定を上書きする形で保存する場合は true を指定し, 後で 
	 *         {@link restoreTemporaryPermissionValues(boolean)} メソッドで復元するために一時保存する場合は false を指定します. 
	 *     </span>
	 * 
	 * @throws ConnectorException
	 *     <span class="lang-en">Thrown when the requested action has been denied, or unsupported</span>
	 *     <span class="lang-ja">要求した操作が拒否された場合や, 未サポートの場合にスローされます</span>
	 */
	public abstract void storeTemporaryPermissionValues(boolean storesToBase)
			throws ConnectorException;


	/**
	 * <p>
	 * <span class="lang-en">Restpres current temporary permission settings</span>
	 * <span class="lang-ja">現在の一時的なパーミッション状態を復元します</span>
	 * .
	 * </p>
	 *
	 * <p>
	 * If "true" is specified to the argument "storesToPermanent",
	 * the permanent permission settings !!!
	 * </p>
	 * <span class="lang-en">
	 * Stores current temporary permission settings
	 * for restoring it later by {@link restoreTemporaryPermissionValues(boolean)} method,
	 * or overwriting permanent permission settings
	 * </span>
	 * <span class="lang-ja">
	 * 現在の一時的なパーミッション状態を保存します. 
	 * 後で {@link restoreTemporaryPermissionValues(boolean)} メソッドによって復元可能な形に, 
	 * またはパーマネント設定値を上書きする事で保存します
	 * </span>
	 * 
	 * <p>
	 * <span class="lang-en">
	 * This method will be called from script engines/applications, 
	 * and may be called from other plug-ins through engine-connector interfaces.
	 * In the latter case, the permission for modifying/referencing permissions is required (but unsupported yet).
	 * </span>
	 * <span class="lang-ja">
	 * このメソッドは, スクリプトエンジンおよびアプリケーションから呼ばれる場合と, 
	 * エンジンコネクターインターフェースを介して他のプラグインから呼ばれる場合があります. 
	 * 後者の場合には, パーミッション操作/参照に対するパーミッションが必要です（現状では未サポートです）. 
	 * </span>
	 * </p>
	 * 
	 * @param restoresFromBase
	 *     <span class="lang-en">
	 *         Specify "true" to restore by copying values from base permission settings,
	 *         "false" to restore by loading settings stored temporary by 
	 *         {@link storeTemporaryPermissionValues(boolean)} method.
	 *     </span>
	 *     <span class="lang-ja">
	 *         基礎パーミッション設定の値を読み込んで復元する場合は true を指定し, 事前に 
	 *         {@link storeTemporaryPermissionValues(boolean)} メソッドで一時保存された設定から復元する場合は false を指定します. 
	 *     </span>
	 * 
	 * @throws ConnectorException
	 *     <span class="lang-en">Thrown when the requested action has been denied, or unsupported</span>
	 *     <span class="lang-ja">要求した操作が拒否された場合や, 未サポートの場合にスローされます</span>
	 */
	public abstract void restoreTemporaryPermissionValues(boolean restoresFromBase)
			throws ConnectorException;


	/**
	 * <p>
	 * <span class="lang-en">
	 * Returns the Class-instance representing the interface or the class for communicating with the script engine
	 * </span>
	 * <span class="lang-ja">
	 * スクリプトエンジンと情報をやり取りする際に使用するオブジェクトの, インターフェースまたはクラスを返します
	 * </span>
	 * .
	 * </p>
	 * 
	 * <p>
	 * <span class="lang-en">
	 * The instance of the specified interface/class by this method will be passed to the argument of 
	 * {@link initializeForConnection(Object)}, {@link initializeForExecution(Object)},
	 * {@link finalizeForTermination(Object)}, {@link finalizeForDisconnection(Object)} methods.
	 * </span>
	 * <span class="lang-ja">
	 * このメソッドで戻り値として指定したインターフェースまたはクラスの実装インスタンスが, 
	 * {@link initializeForConnection(Object)}, {@link initializeForExecution(Object)}, 
	 * {@link finalizeForTermination(Object)}, {@link finalizeForDisconnection(Object)}
	 * メソッドの引数として渡されます.
	 * </span>
	 * </p>
	 * 
	 * <p>
	 * <span class="lang-en">
	 * Available interfaces depend on the script engine, but at least, 
	 * {@link EngineConnectorInterface1 ECI 1} is guaranteed to be available by the specification of PACI 1.
	 * </span>
	 * <span class="lang-ja">
	 * どのようなインターフェース/クラスが利用可能かはスクリプトエンジンに依存しますが, 
	 * 少なくとも {@link EngineConnectorInterface1 ECI 1} は利用可能である事が, PACI 1 の仕様上保証されます. 
	 * </span>
	 * </p>
	 * 
	 * @return
	 *     <span class="lang-en">
	 *         Class-instances representing the interface/class for communicating with the script engine
	 *     </span>
	 *     <span class="lang-ja">
	 *         スクリプトエンジンと情報をやり取りする際に使用するインターフェースまたはクラス
	 *     </span>
	 */
	public abstract Class<?> getEngineConnectorClass();


	/**
	 * <p>
	 * <span class="lang-en">
	 * Performs the initialization process necessary when this plug-in is connected to the script engine
	 * </span>
	 * <span class="lang-ja">
	 * このプラグインが, スクリプトエンジンに接続される際に必要となる初期化処理を実行します
	 * </span>
	 * .
	 * </p>
	 *
	 * <p>
	 * <span class="lang-en">
	 * As an argument "engineConnector", an object for communicating with the script engine will be passed.
	 * The type of the object is specified by {@link getEngineConnectorClass()} method.
	 * At least, {@link EngineConnectorInterface1 ECI 1} is guaranteed to be available by the specification of PACI 1.
	 * </span>
	 * <span class="lang-ja">
	 * 引数 engineConnector には, スクリプトエンジンと情報をやり取りする際に使用するオブジェクトが渡されます. 
	 * そのオブジェクトの型は, {@link getEngineConnectorClass()} メソッドの戻り値として指定します.
	 * 少なくとも {@link EngineConnectorInterface1 ECI 1} は利用可能である事が, PACI 1 の仕様上保証されます.
	 * </span>
	 * </p>
	 *
	 * @param engineConnector
	 *     <span class="lang-en">An object for communicating with the script engine</span>
	 *     <span class="lang-ja">スクリプトエンジンと情報をやり取りする際に使用するオブジェクト</span>
	 * 
	 * @throws ConnectorException
	 *     <span class="lang-en">Thrown when the initialization has failed</span>
	 *     <span class="lang-ja">初期化処理に失敗した場合にスローされます</span>
	 */
	public abstract void initializeForConnection(Object engineConnector) throws ConnectorException;


	/**
	 * <p>
	 * <span class="lang-en">
	 * Performs the finalization process necessary when this plug-in is disconnected from the script engine
	 * </span>
	 * <span class="lang-ja">
	 * このプラグインが, スクリプトエンジンから接続解除される際に必要となる終了時処理を実行します
	 * </span>
	 * .
	 * </p>
	 *
	 * <p>
	 * <span class="lang-en">
	 * As an argument "engineConnector", an object for communicating with the script engine will be passed.
	 * The type of the object is specified by {@link getEngineConnectorClass()} method.
	 * At least, {@link EngineConnectorInterface1 ECI 1} is guaranteed to be available by the specification of PACI 1.
	 * </span>
	 * <span class="lang-ja">
	 * 引数 engineConnector には, スクリプトエンジンと情報をやり取りする際に使用するオブジェクトが渡されます. 
	 * そのオブジェクトの型は, {@link getEngineConnectorClass()} メソッドの戻り値として指定します.
	 * 少なくとも {@link EngineConnectorInterface1 ECI 1} は利用可能である事が, PACI 1 の仕様上保証されます.
	 * </span>
	 * </p>
	 *
	 * @param engineConnector
	 *     <span class="lang-en">An object for communicating with the script engine</span>
	 *     <span class="lang-ja">スクリプトエンジンと情報をやり取りする際に使用するオブジェクト</span>
	 * 
	 * @throws ConnectorException
	 *     <span class="lang-en">Thrown when the finalization has failed</span>
	 *     <span class="lang-ja">終了時処理に失敗した場合にスローされます</span>
	 */
	public abstract void finalizeForDisconnection(Object engineConnector) throws ConnectorException;


	/**
	 * <p>
	 * <span class="lang-en">Performs the initialization process necessary for each execution of a script</span>
	 * <span class="lang-ja">スクリプトの実行毎に必要な初期化処理を実行します</span>
	 * .
	 * </p>
	 *
	 * <p>
	 * <span class="lang-en">
	 * As an argument "engineConnector", an object for communicating with the script engine will be passed.
	 * The type of the object is specified by {@link getEngineConnectorClass()} method.
	 * At least, {@link EngineConnectorInterface1 ECI 1} is guaranteed to be available by the specification of PACI 1.
	 * </span>
	 * <span class="lang-ja">
	 * 引数 engineConnector には, スクリプトエンジンと情報をやり取りする際に使用するオブジェクトが渡されます. 
	 * そのオブジェクトの型は, {@link getEngineConnectorClass()} メソッドの戻り値として指定します.
	 * 少なくとも {@link EngineConnectorInterface1 ECI 1} は利用可能である事が, PACI 1 の仕様上保証されます.
	 * </span>
	 * </p>
	 *
	 * @param engineConnector
	 *     <span class="lang-en">An object for communicating with the script engine</span>
	 *     <span class="lang-ja">スクリプトエンジンと情報をやり取りする際に使用するオブジェクト</span>
	 * 
	 * @throws ConnectorException
	 *     <span class="lang-en">Thrown when the initialization has failed</span>
	 *     <span class="lang-ja">初期化処理に失敗した場合にスローされます</span>
	 */
	public abstract void initializeForExecution(Object engineConnector) throws ConnectorException;


	/**
	 * <p>
	 * <span class="lang-en">Performs the finalization process necessary for each execution of a script</span>
	 * <span class="lang-ja">スクリプトの実行毎に必要な終了時処理を実行します</span>
	 * .
	 * </p>
	 *
	 * <p>
	 * <span class="lang-en">
	 * As an argument "engineConnector", an object for communicating with the script engine will be passed.
	 * The type of the object is specified by {@link getEngineConnectorClass()} method.
	 * At least, {@link EngineConnectorInterface1 ECI 1} is guaranteed to be available by the specification of PACI 1.
	 * </span>
	 * <span class="lang-ja">
	 * 引数 engineConnector には, スクリプトエンジンと情報をやり取りする際に使用するオブジェクトが渡されます. 
	 * そのオブジェクトの型は, {@link getEngineConnectorClass()} メソッドの戻り値として指定します.
	 * 少なくとも {@link EngineConnectorInterface1 ECI 1} は利用可能である事が, PACI 1 の仕様上保証されます.
	 * </span>
	 * </p>
	 *
	 * @param engineConnector
	 *     <span class="lang-en">An object for communicating with the script engine</span>
	 *     <span class="lang-ja">スクリプトエンジンと情報をやり取りする際に使用するオブジェクト</span>
	 * 
	 * @throws ConnectorException
	 *     <span class="lang-en">Thrown when the finalization has failed</span>
	 *     <span class="lang-ja">終了時処理に失敗した場合にスローされます</span>
	 */
	public abstract void finalizeForTermination(Object engineConnector) throws ConnectorException;

}
