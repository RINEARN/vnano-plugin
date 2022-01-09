/*
 * ==================================================
 * External Namespace Connector Interface 1 (XNCI 1)
 * ( for VCSSL / Vnano Plug-in Development )
 * --------------------------------------------------
 * This file is released under CC0.
 * Written in 2019-2022 by RINEARN (Fumihiro Matsui)
 * ==================================================
 */

package org.vcssl.connect;


/**
 * <p>
 * <span class="lang-en">
 * An interface (abbreviated as XNCI1) for implementing namespace plug-ins 
 * which provides multiple functions and variables available in scripts
 * </span>
 * <span class="lang-ja">
 * スクリプト内で使用可能な, 複数の関数/変数をまとめて提供する, 
 * 名前空間プラグインを実装するためのインターフェース（略称 XNCI 1）です
 * </span>
 * .
 * </p>
 * 
 * <p>
 * <span class="lang-en">
 * Currently, this interface is supported on the Vnano Engine, 
 * and has not been supported on the VCSSL Engine yet.
 * </span>
 * <span class="lang-ja">
 * このインターフェースは現在, Vnano 処理系では既にサポートされていますが, 
 * VCSSL 処理系ではまだサポートされていません.
 * </span>
 * </p>
 */
public interface ExternalNamespaceConnectorInterface1 {

	/**
	 * <span class="lang-en">The type ID of this interface (value: "XNCI") referred when the plug-in will be loaded</span>
	 * <span class="lang-ja">プラグインのロード時に参照される, このインターフェースの形式ID（値: "XNCI"）です</span>
	 * .
	 */
	public static final String INTERFACE_TYPE_ID = "XNCI";

	/**
	 * <span class="lang-en">The generation of this interface (value: "1")</span>
	 * <span class="lang-ja">このインターフェースの世代名です（値: "1"）</span>
	 * .
	 */
	public static final String INTERFACE_GENERATION = "1";


	/**
	 * <span class="lang-en">Returns the name of this namespace</span>
	 * <span class="lang-ja">この名前空間の名称を返します</span>
	 * .
	 * @return
	 *     <span class="lang-en">The name of this namespace</span>
	 *     <span class="lang-ja">この名前空間の名称</span>
	 */
	public abstract String getNamespaceName();


	/**
	 * <p>
	 * <span class="lang-en">
	 * Returns whether it is mandatory to specify of this namespace explicitly 
	 * when accessing member functions/variables
	 * </span>
	 * <span class="lang-ja">
	 * この名前空間に属する関数/変数にアクセスする際に, 名前空間の明示的な指定が必須であるかどうかを返します
	 * </span>
	 * .
	 * </p>
	 * 
	 * <p>
	 * <span class="lang-en">
	 * This feature may be ignored on the script engine which does not support it.
	 * </span>
	 * <span class="lang-ja">
	 * この機能は, 未対応の処理系においては無視される可能性があります.
	 * </span>
	 * </p>
	 * 
	 * @return
	 *     <span class="lang-en">Returns "true" if this namespace is mandatory to be specified to access members</span>
	 *     <span class="lang-ja">メンバーへのアクセスの際に, この名前空間の指定が必須である場合は true が返されます</span>
	 * 
	 */
	public abstract boolean isMandatoryToAccessMembers();


	/**
	 * <span class="lang-en">Returns all functions belong to this namespace</span>
	 * <span class="lang-ja">この名前空間に属する全ての関数を返します</span>
	 * .
	 * @return
	 *     <span class="lang-en">An array storing all functions belong to this namespace</span>
	 *     <span class="lang-ja">この名前空間に属する全ての関数を格納する配列</span>
	 */
	public abstract ExternalFunctionConnectorInterface1[] getFunctions();


	/**
	 * <span class="lang-en">Returns all variables belong to this namespace</span>
	 * <span class="lang-ja">この名前空間に属する全ての変数を返します</span>
	 * .
	 * @return
	 *     <span class="lang-en">An array storing all variables belong to this namespace</span>
	 *     <span class="lang-ja">この名前空間に属する全ての変数を格納する配列</span>
	 */
	public abstract ExternalVariableConnectorInterface1[] getVariables();


	/**
	 * <span class="lang-en">Returns all structs belong to this namespace</span>
	 * <span class="lang-ja">この名前空間に属する全ての構造体を返します</span>
	 * .
	 * @return
	 *     <span class="lang-en">An array storing all structs belong to this namespace</span>
	 *     <span class="lang-ja">この名前空間に属する全ての構造体を格納する配列</span>
	 */
	public abstract ExternalStructConnectorInterface1[] getStructs();


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
	 * {@link preInitializeForConnection(Object)}, {@link preInitializeForExecution(Object)},
	 * {@link preFinalizeForTermination(Object)}, {@link preFinalizeForDisconnection(Object)},
	 * {@link postInitializeForConnection(Object)}, {@link postInitializeForExecution(Object)},
	 * {@link postFinalizeForTermination(Object)}, {@link postFinalizeForDisconnection(Object)} methods.
	 * </span>
	 * <span class="lang-ja">
	 * このメソッドで戻り値として指定したインターフェースまたはクラスの実装インスタンスが, 
	 * {@link preInitializeForConnection(Object)}, {@link preInitializeForExecution(Object)},
	 * {@link preFinalizeForTermination(Object)}, {@link preFinalizeForDisconnection(Object)},
	 * {@link postInitializeForConnection(Object)}, {@link postInitializeForExecution(Object)},
	 * {@link postFinalizeForTermination(Object)}, {@link postFinalizeForDisconnection(Object)} 
	 * メソッドの引数として渡されます.
	 * </span>
	 * </p>
	 * 
	 * <p>
	 * <span class="lang-en">
	 * Available interfaces depend on the script engine, but at least, 
	 * {@link EngineConnectorInterface1 ECI 1} is guaranteed to be available by the specification of XNCI 1.
	 * </span>
	 * <span class="lang-ja">
	 * どのようなインターフェース/クラスが利用可能かはスクリプトエンジンに依存しますが, 
	 * 少なくとも {@link EngineConnectorInterface1 ECI 1} は利用可能である事が, XNCI 1 の仕様上保証されます. 
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
	 * On this interface, two connection-initialization (pre- and post-) processes can be implemented.
	 * This process (pre-) will be performed before when all member variables/functions are initialized.
	 * </span>
	 * <span class="lang-ja">
	 * このインターフェースでは, 接続初期化処理は 2 通り（pre- と post-）実装可能です. 
	 * こちらの処理（pre-）は, この名前空間に属する全ての変数/関数の初期化処理よりも前に実行されます.
	 * </span>
	 * </p>
	 *
	 * <p>
	 * <span class="lang-en">
	 * As an argument "engineConnector", an object for communicating with the script engine will be passed.
	 * The type of the object is specified by {@link getEngineConnectorClass()} method.
	 * At least, {@link EngineConnectorInterface1 ECI 1} is guaranteed to be available by the specification of XNCI 1.
	 * </span>
	 * <span class="lang-ja">
	 * 引数 engineConnector には, スクリプトエンジンと情報をやり取りする際に使用するオブジェクトが渡されます. 
	 * そのオブジェクトの型は, {@link getEngineConnectorClass()} メソッドの戻り値として指定します.
	 * 少なくとも {@link EngineConnectorInterface1 ECI 1} は利用可能である事が, XNCI 1 の仕様上保証されます.
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
	public abstract void preInitializeForConnection(Object engineConnector) throws ConnectorException;


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
	 * On this interface, two connection-initialization (pre- and post-) processes can be implemented.
	 * This process (post-) will be performed after when all member variables/functions are initialized.
	 * </span>
	 * <span class="lang-ja">
	 * このインターフェースでは, 接続初期化処理は 2 通り（pre- と post-）実装可能です. 
	 * こちらの処理（post-）は, この名前空間に属する全ての変数/関数の接続初期化処理よりも後に実行されます.
	 * </span>
	 * </p>
	 *
	 * <p>
	 * <span class="lang-en">
	 * As an argument "engineConnector", an object for communicating with the script engine will be passed.
	 * The type of the object is specified by {@link getEngineConnectorClass()} method.
	 * At least, {@link EngineConnectorInterface1 ECI 1} is guaranteed to be available by the specification of XNCI 1.
	 * </span>
	 * <span class="lang-ja">
	 * 引数 engineConnector には, スクリプトエンジンと情報をやり取りする際に使用するオブジェクトが渡されます. 
	 * そのオブジェクトの型は, {@link getEngineConnectorClass()} メソッドの戻り値として指定します.
	 * 少なくとも {@link EngineConnectorInterface1 ECI 1} は利用可能である事が, XNCI 1 の仕様上保証されます.
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
	public abstract void postInitializeForConnection(Object engineConnector) throws ConnectorException;


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
	 * On this interface, two connection-finalization (pre- and post-) processes can be implemented.
	 * This process (pre-) will be performed before when all member variables/functions are finalized.
	 * </span>
	 * <span class="lang-ja">
	 * このインターフェースでは, 接続終了時処理は 2 通り（pre- と post-）実装可能です. 
	 * こちらの処理（pre-）は, この名前空間に属する全ての変数/関数の接続終了時処理よりも前に実行されます.
	 * </span>
	 * </p>
	 *
	 * <p>
	 * <span class="lang-en">
	 * As an argument "engineConnector", an object for communicating with the script engine will be passed.
	 * The type of the object is specified by {@link getEngineConnectorClass()} method.
	 * At least, {@link EngineConnectorInterface1 ECI 1} is guaranteed to be available by the specification of XNCI 1.
	 * </span>
	 * <span class="lang-ja">
	 * 引数 engineConnector には, スクリプトエンジンと情報をやり取りする際に使用するオブジェクトが渡されます. 
	 * そのオブジェクトの型は, {@link getEngineConnectorClass()} メソッドの戻り値として指定します.
	 * 少なくとも {@link EngineConnectorInterface1 ECI 1} は利用可能である事が, XNCI 1 の仕様上保証されます.
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
	public abstract void preFinalizeForDisconnection(Object engineConnector) throws ConnectorException;


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
	 * On this interface, two connection-finalization (pre- and post-) processes can be implemented.
	 * This process (post-) will be performed after when all member variables/functions are finalized.
	 * </span>
	 * <span class="lang-ja">
	 * このインターフェースでは, 接続終了時処理は 2 通り（pre- と post-）実装可能です. 
	 * こちらの処理（post-）は, この名前空間に属する全ての変数/関数の接続終了時処理よりも後に実行されます.
	 * </span>
	 * </p>
	 *
	 * <p>
	 * <span class="lang-en">
	 * As an argument "engineConnector", an object for communicating with the script engine will be passed.
	 * The type of the object is specified by {@link getEngineConnectorClass()} method.
	 * At least, {@link EngineConnectorInterface1 ECI 1} is guaranteed to be available by the specification of XNCI 1.
	 * </span>
	 * <span class="lang-ja">
	 * 引数 engineConnector には, スクリプトエンジンと情報をやり取りする際に使用するオブジェクトが渡されます. 
	 * そのオブジェクトの型は, {@link getEngineConnectorClass()} メソッドの戻り値として指定します.
	 * 少なくとも {@link EngineConnectorInterface1 ECI 1} は利用可能である事が, XNCI 1 の仕様上保証されます.
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
	public abstract void postFinalizeForDisconnection(Object engineConnector) throws ConnectorException;


	/**
	 * <p>
	 * <span class="lang-en">Performs the initialization process necessary for each execution of a script</span>
	 * <span class="lang-ja">スクリプトの実行毎に必要な初期化処理を実行します</span>
	 * .
	 * </p>
	 * 
	 * <p>
	 * <span class="lang-en">
	 * On this interface, two execution-initialization (pre- and post-) processes can be implemented.
	 * This process (pre-) will be performed before when all member variables/functions are initialized.
	 * </span>
	 * <span class="lang-ja">
	 * このインターフェースでは, 実行毎初期化処理は 2 通り（pre- と post-）実装可能です. 
	 * こちらの処理（pre-）は, この名前空間に属する全ての変数/関数の初期化処理よりも前に実行されます.
	 * </span>
	 * </p>
	 *
	 * <p>
	 * <span class="lang-en">
	 * As an argument "engineConnector", an object for communicating with the script engine will be passed.
	 * The type of the object is specified by {@link getEngineConnectorClass()} method.
	 * At least, {@link EngineConnectorInterface1 ECI 1} is guaranteed to be available by the specification of XNCI 1.
	 * </span>
	 * <span class="lang-ja">
	 * 引数 engineConnector には, スクリプトエンジンと情報をやり取りする際に使用するオブジェクトが渡されます. 
	 * そのオブジェクトの型は, {@link getEngineConnectorClass()} メソッドの戻り値として指定します.
	 * 少なくとも {@link EngineConnectorInterface1 ECI 1} は利用可能である事が, XNCI 1 の仕様上保証されます.
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
	public abstract void preInitializeForExecution(Object engineConnector) throws ConnectorException;


	/**
	 * <p>
	 * <span class="lang-en">Performs the initialization process necessary for each execution of a script</span>
	 * <span class="lang-ja">スクリプトの実行毎に必要な初期化処理を実行します</span>
	 * .
	 * </p>
	 * 
	 * <p>
	 * <span class="lang-en">
	 * On this interface, two execution-initialization (pre- and post-) processes can be implemented.
	 * This process (post-) will be performed after when all member variables/functions are initialized.
	 * </span>
	 * <span class="lang-ja">
	 * このインターフェースでは, 実行毎初期化処理は 2 通り（pre- と post-）実装可能です. 
	 * こちらの処理（post-）は, この名前空間に属する全ての変数/関数の初期化処理よりも後に実行されます.
	 * </span>
	 * </p>
	 *
	 * <p>
	 * <span class="lang-en">
	 * As an argument "engineConnector", an object for communicating with the script engine will be passed.
	 * The type of the object is specified by {@link getEngineConnectorClass()} method.
	 * At least, {@link EngineConnectorInterface1 ECI 1} is guaranteed to be available by the specification of XNCI 1.
	 * </span>
	 * <span class="lang-ja">
	 * 引数 engineConnector には, スクリプトエンジンと情報をやり取りする際に使用するオブジェクトが渡されます. 
	 * そのオブジェクトの型は, {@link getEngineConnectorClass()} メソッドの戻り値として指定します.
	 * 少なくとも {@link EngineConnectorInterface1 ECI 1} は利用可能である事が, XNCI 1 の仕様上保証されます.
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
	public abstract void postInitializeForExecution(Object engineConnector) throws ConnectorException;


	/**
	 * <p>
	 * <span class="lang-en">Performs the finalization process necessary for each execution of a script</span>
	 * <span class="lang-ja">スクリプトの実行毎に必要な終了時処理を実行します</span>
	 * .
	 * </p>
	 * 
	 * <p>
	 * <span class="lang-en">
	 * On this interface, two execution-finalization (pre- and post-) processes can be implemented.
	 * This process (pre-) will be performed before when all member variables/functions are finalized.
	 * </span>
	 * <span class="lang-ja">
	 * このインターフェースでは, 実行毎終了時処理は 2 通り（pre- と post-）実装可能です. 
	 * こちらの処理（pre-）は, この名前空間に属する全ての変数/関数の終了時処理よりも前に実行されます.
	 * </span>
	 * </p>
	 *
	 * <p>
	 * <span class="lang-en">
	 * As an argument "engineConnector", an object for communicating with the script engine will be passed.
	 * The type of the object is specified by {@link getEngineConnectorClass()} method.
	 * At least, {@link EngineConnectorInterface1 ECI 1} is guaranteed to be available by the specification of XNCI 1.
	 * </span>
	 * <span class="lang-ja">
	 * 引数 engineConnector には, スクリプトエンジンと情報をやり取りする際に使用するオブジェクトが渡されます. 
	 * そのオブジェクトの型は, {@link getEngineConnectorClass()} メソッドの戻り値として指定します.
	 * 少なくとも {@link EngineConnectorInterface1 ECI 1} は利用可能である事が, XNCI 1 の仕様上保証されます.
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
	public abstract void preFinalizeForTermination(Object engineConnector) throws ConnectorException;


	/**
	 * <p>
	 * <span class="lang-en">Performs the finalization process necessary for each execution of a script</span>
	 * <span class="lang-ja">スクリプトの実行毎に必要な終了時処理を実行します</span>
	 * .
	 * </p>
	 * 
	 * <p>
	 * <span class="lang-en">
	 * On this interface, two execution-finalization (pre- and post-) processes can be implemented.
	 * This process (post-) will be performed after when all member variables/functions are finalized.
	 * </span>
	 * <span class="lang-ja">
	 * このインターフェースでは, 実行毎終了時処理は 2 通り（pre- と post-）実装可能です. 
	 * こちらの処理（post-）は, この名前空間に属する全ての変数/関数の終了時処理よりも後に実行されます.
	 * </span>
	 * </p>
	 *
	 * <p>
	 * <span class="lang-en">
	 * As an argument "engineConnector", an object for communicating with the script engine will be passed.
	 * The type of the object is specified by {@link getEngineConnectorClass()} method.
	 * At least, {@link EngineConnectorInterface1 ECI 1} is guaranteed to be available by the specification of XNCI 1.
	 * </span>
	 * <span class="lang-ja">
	 * 引数 engineConnector には, スクリプトエンジンと情報をやり取りする際に使用するオブジェクトが渡されます. 
	 * そのオブジェクトの型は, {@link getEngineConnectorClass()} メソッドの戻り値として指定します.
	 * 少なくとも {@link EngineConnectorInterface1 ECI 1} は利用可能である事が, XNCI 1 の仕様上保証されます.
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
	public abstract void postFinalizeForTermination(Object engineConnector) throws ConnectorException;

}
