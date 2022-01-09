/*
 * ==================================================
 * External Variable Connector Interface 1 (XVCI 1)
 * ( for VCSSL / Vnano Plug-in Development )
 * --------------------------------------------------
 * This file is released under CC0.
 * Written in 2017-2022 by RINEARN (Fumihiro Matsui)
 * ==================================================
 */

package org.vcssl.connect;


/**
 * <p>
 * <span class="lang-en">
 * An interface (abbreviated as XVCI1) for implementing external variable plug-ins 
 * which provide variables available in scripts
 * </span>
 * <span class="lang-ja">
 * スクリプト内で使用可能な変数を提供する, 
 * 外部変数プラグインを実装するためのインターフェースの一つ（略称 XVCI 1）です
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
public interface ExternalVariableConnectorInterface1 {

	/**
	 * <span class="lang-en">The type ID of this interface (value: "XVCI") referred when the plug-in will be loaded</span>
	 * <span class="lang-ja">プラグインのロード時に参照される, このインターフェースの形式ID（値: "XVCI"）です</span>
	 * .
	 */
	public static final String INTERFACE_TYPE_ID = "XVCI";

	/**
	 * <span class="lang-en">The generation of this interface (value: "1")</span>
	 * <span class="lang-ja">このインターフェースの世代名です（値: "1"）</span>
	 * .
	 */
	public static final String INTERFACE_GENERATION = "1";


	/**
	 * <span class="lang-en">Gets the name of the variable</span>
	 * <span class="lang-ja">変数名を取得します</span>
	 * .
	 * @return
	 *     <span class="lang-en">The name of the variable</span>
	 *     <span class="lang-ja">変数名</span>
	 */
	public abstract String getVariableName();


	/**
	 * <p>
	 * <span class="lang-en">
	 * Returns a Class-instance representing the data-type and the array-rank of this variable
	 * </span>
	 * <span class="lang-ja">
	 * この変数のデータ型と配列次元数を表す Class インスタンスを返します
	 * </span>
	 * .
	 * </p>
	 * 
	 * <p>
	 * <span class="lang-en">
	 * For example, 
	 * returns double.class for double-type ("float" in scripts) variable, 
	 * and returns long[][].class for long[][]-type ("int[][]" in scripts) variable.
	 * </span>
	 * <span class="lang-ja">
	 * 例えば, データが double 型（スクリプト内では float 型）の変数の場合は double.class を, 
	 * long[][] 型（スクリプト内では int[][] 型）の場合には long[][].class を返します.
	 * </span>
	 * </p>
	 * 
	 * @return
	 *     <span class="lang-en">
	 *         A Class-instance representing the data-type and the array-rank of this variable
	 *     </span>
	 *     <span class="lang-ja">
	 *         この変数のデータ型と配列次元数を表す Class インスタンス
	 *     </span>
	 */
	public abstract Class<?> getDataClass();


	/**
	 * <p>
	 * <span class="lang-en">
	 * Returns Class-instances representing data-I/O interfaces for accessing to data of this variable, 
	 * when {@link isDataConversionNecessary() data-conversion feature} is disabled
	 * </span>
	 * <span class="lang-ja">
	 * {@link isDataConversionNecessary() データ変換機能} 
	 * を無効化している場合において, 変数のデータの読み書きに用いる, データ入出力インターフェースの型を表す 
	 * Class インスタンスを返します
	 * </span>
	 * .
	 * </p>
	 * 
	 * <p>
	 * <span class="lang-en">
	 * As interfaces for accessing to data of a scalar variable, 
	 * {@link Int64ScalarDataAccessorInterface1 Int64 SDAI} (for long-type variable),
	 * {@link Float64ScalarDataAccessorInterface1 Float64 SDAI} (for double-type variable),
	 * {@link BoolScalarDataAccessorInterface1 Bool SDAI} (for boolean-type variable),
	 * {@link StringScalarDataAccessorInterface1 String SDAI} (for String-type variable),
	 * and
	 * {@link ArrayDataAccessorInterface1 ADAI} (generic) 
	 * are available.
	 * For an array variable, only 
	 * {@link ArrayDataAccessorInterface1 ADAI}
	 * is available for accessing to data.
	 * </span>
	 * <span class="lang-ja">
	 * スカラの変数に対する入出力インターフェースとしては, 
	 * {@link Int64ScalarDataAccessorInterface1 Int64 SDAI}（long 型変数用）,
	 * {@link Float64ScalarDataAccessorInterface1 Float64 SDAI}（double 型変数用）,
	 * {@link BoolScalarDataAccessorInterface1 Bool SDAI}（boolean 型変数用）,
	 * {@link StringScalarDataAccessorInterface1 String SDAI}（String 型変数用）,
	 * および
	 * {@link ArrayDataAccessorInterface1 ADAI}（ジェネリック）
	 * が利用可能です.
	 * 配列変数に対しては, 
	 * {@link ArrayDataAccessorInterface1 ADAI} 
	 * のみが利用可能です.
	 * </span>
	 * </p>
	 * 
	 * @return
	 *     <span class="lang-en">
	 *         A Class-instance representing the I/O interface for accessing to the data of this variable
	 *     </span>
	 *     <span class="lang-ja">
	 *         この変数のデータの読み書きに使用する, データ入出力インターフェースの型を表す Class インスタンス
	 *     </span>
	 */
	public abstract Class<?> getDataUnconvertedClass();


	/**
	 * <span class="lang-en">Returns whether this variable is a constant</span>
	 * <span class="lang-ja">この変数が定数であるかどうかを返します</span>
	 * .
	 * @return
	 *     <span class="lang-en">Returns "true" if this variable is a constant</span>
	 *     <span class="lang-ja">この変数が定数でれば true が返されます</span>
	 */
	public abstract boolean isConstant();


	/**
	 * <span class="lang-en">Unsupported feature on the current version of VCSSL/Vnano Engine</span>
	 * <span class="lang-ja">現在のVCSSL/Vnano処理系では未サポートの機能です</span>
	 * .
	 * @return
	 *     <span class="lang-en">Returns "true" if this variable is a reference</span>
	 *     <span class="lang-ja">この関数が参照である場合に true が返されます</span>
	 */
	public abstract boolean isReference();


	/**
	 * <span class="lang-en">Unsupported feature on the current version of VCSSL/Vnano Engine</span>
	 * <span class="lang-ja">現在のVCSSL/Vnano処理系では未サポートの機能です</span>
	 * .
	 * @return
	 *     <span class="lang-en">Returns "true" if the data-type of this variable varies arbitrary</span>
	 *     <span class="lang-ja">この変数のデータ型が任意に変化する場合に true が返されます</span>
	 */
	public abstract boolean isDataTypeArbitrary();


	/**
	 * <span class="lang-en">Unsupported feature on the current version of VCSSL/Vnano Engine</span>
	 * <span class="lang-ja">現在のVCSSL/Vnano処理系では未サポートの機能です</span>
	 * .
	 * @return
	 *     <span class="lang-en">Returns "true" if the array-rank of this variable varies arbitrary</span>
	 *     <span class="lang-ja">この変数の配列次元数が任意に変化する場合に true が返されます</span>
	 */
	public abstract boolean isArrayRankArbitrary();


	/**
	 * <p>
	 * <span class="lang-en">
	 * Returns whether the data-conversions for accessing data of this variable is necessary
	 * </span>
	 * <span class="lang-ja">
	 * この変数の値の読み書きに際して, データ型の変換が必要かどうかを返します
	 * </span>
	 * .
	 * </p>
	 * 
	 * <p>
	 * <span class="lang-en">
	 * When this feature is enabled (when this method returns "true"),
	 * you can set/get data of this variable by using simple data-types.
	 * For example, you can get/set Double instance for double-type ("float" in scripts) variable, 
	 * long[][] instance for long[][]-type ("int[][]" in scripts) variable, and so on.
	 * </span>
	 * <span class="lang-ja">
	 * この機能が有効化されている（このメソッドが true を返すようにした）場合, 
	 * 変数のデータを, 単純なデータ型で読み書きする事ができます. 例えば, 
	 * double 型（スクリプト内では float 型）変数のデータにおいては Double 型インスタンス, 
	 * long[][] 型（スクリプト内では int[][] 型）変数データに対しては long[][] 型インスタンス
	 * などとして読み書きできます. 
	 * </span>
	 * </p>
	 *
	 * <p>
	 * <span class="lang-en">
	 * On the other hand, when this feature is disabled (when this method retunrs "false"),
	 * it is necessary to access to data through a data-I/O interface, 
	 * of which type is specified as a return values of {@link getDataUnconvertedClass()} method. 
	 * </span>
	 * <span class="lang-ja">
	 * 一方, この機能が無効化されている（このメソッドが false を返すようにした）場合, 
	 * 変数のデータに対しては, 各種のデータ入出力インターフェースを介してアクセスする必要があります. 
	 * その際, 使用するデータ入出力インターフェースは, 
	 * {@link getDataUnconvertedClass()} メソッドの戻り値として指定します. 
	 * </span>
	 * </p>
	 *
	 * <p>
	 * <span class="lang-en">
	 * As interfaces for accessing to data of a scalar variable, 
	 * {@link Int64ScalarDataAccessorInterface1 Int64 SDAI} (for long-type variable),
	 * {@link Float64ScalarDataAccessorInterface1 Float64 SDAI} (for double-type variable),
	 * {@link BoolScalarDataAccessorInterface1 Bool SDAI} (for boolean-type variable),
	 * {@link StringScalarDataAccessorInterface1 String SDAI} (for String-type variable),
	 * and
	 * {@link ArrayDataAccessorInterface1 ADAI} (generic) 
	 * are available.
	 * For an array variable, only 
	 * {@link ArrayDataAccessorInterface1 ADAI}
	 * is available for accessing to data.
	 * </span>
	 * <span class="lang-ja">
	 * スカラの変数に対する入出力インターフェースとしては, 
	 * {@link Int64ScalarDataAccessorInterface1 Int64 SDAI}（long 型変数用）,
	 * {@link Float64ScalarDataAccessorInterface1 Float64 SDAI}（double 型変数用）,
	 * {@link BoolScalarDataAccessorInterface1 Bool SDAI}（boolean 型変数用）,
	 * {@link StringScalarDataAccessorInterface1 String SDAI}（String 型変数用）,
	 * および
	 * {@link ArrayDataAccessorInterface1 ADAI}（ジェネリック）
	 * が利用可能です.
	 * 配列変数に対しては, 
	 * {@link ArrayDataAccessorInterface1 ADAI} 
	 * のみが利用可能です.
	 * </span>
	 * </p>
	 * 
	 * @return
	 *     <span class="lang-en">Returns "true" if the data-conversions are necessary</span>
	 *     <span class="lang-ja">データ変換が必要なら true を返します</span>
	 */
	public abstract boolean isDataConversionNecessary();


	/**
	 * <p>
	 * <span class="lang-en">Returns the data of this variable</span>
	 * <span class="lang-ja">この変数のデータを返します</span>
	 * .
	 * </p>
	 *
	 * <p>
	 * <span class="lang-en">
	 * This method is used when the data-conversion feature is disabled 
	 * (when {@link isDataConversionNecessary()} returns "false").
	 * </span>
	 * <span class="lang-ja">
	 * このメソッドは, データ変換機能無効時
	 * （{@link isDataConversionNecessary()} メソッドが false を返す場合）に使用されます. 
	 * </span>
	 * </p>
	 *
	 * @return
	 *     <span class="lang-en">Data of this variable</span>
	 *     <span class="lang-ja">この変数のデータ</span>
	 * 
	 * @throws ConnectorException
	 *     <span class="lang-en">Thrown when failed to access to data</span>
	 *     <span class="lang-ja">データへのアクセスに失敗した場合にスローされます</span>
	 */
	public abstract Object getData() throws ConnectorException;


	/**
	 * <p>
	 * <span class="lang-en">Gets the data of this variable through an argument</span>
	 * <span class="lang-ja">引数を介して, この変数のデータを取得します</span>
	 * .
	 * </p>
	 *
	 * <p>
	 * <span class="lang-en">
	 * This method is used when the data-conversion feature is enabled 
	 * (when {@link isDataConversionNecessary()} returns "true").
	 * </span>
	 * <span class="lang-ja">
	 * このメソッドは, データ変換機能有効時
	 * （{@link isDataConversionNecessary()} メソッドが true を返す場合）に使用されます. 
	 * </span>
	 * </p>
	 *
	 * @param dataContainer
	 *     <span class="lang-en">A data container object for storing data to be gotten</span>
	 *     <span class="lang-ja">取得するデータを格納する, データコンテナオブジェクト</span>
	 * 
	 * @throws ConnectorException
	 *     <span class="lang-en">Thrown when failed to access to data</span>
	 *     <span class="lang-ja">データへのアクセスに失敗した場合にスローされます</span>
	 */
	public abstract void getData(Object dataContainer) throws ConnectorException;


	/**
	 * <span class="lang-en">Sets data of this variable</span>
	 * <span class="lang-ja">この変数のデータを設定します</span>
	 * .
	 * @param data
	 *     <span class="lang-en">Data of this variable to be set</span>
	 *     <span class="lang-ja">この変数に設定するデータ</span>
	 * 
	 * @throws ConnectorException
	 *     <span class="lang-en">Thrown when failed to access to data</span>
	 *     <span class="lang-ja">データへのアクセスに失敗した場合にスローされます</span>
	 */
	public abstract void setData(Object data) throws ConnectorException;


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
	 * {@link EngineConnectorInterface1 ECI 1} is guaranteed to be available by the specification of XVCI 1.
	 * </span>
	 * <span class="lang-ja">
	 * どのようなインターフェース/クラスが利用可能かはスクリプトエンジンに依存しますが, 
	 * 少なくとも {@link EngineConnectorInterface1 ECI 1} は利用可能である事が, XVCI 1 の仕様上保証されます. 
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
	 * At least, {@link EngineConnectorInterface1 ECI 1} is guaranteed to be available by the specification of XVCI 1.
	 * </span>
	 * <span class="lang-ja">
	 * 引数 engineConnector には, スクリプトエンジンと情報をやり取りする際に使用するオブジェクトが渡されます. 
	 * そのオブジェクトの型は, {@link getEngineConnectorClass()} メソッドの戻り値として指定します.
	 * 少なくとも {@link EngineConnectorInterface1 ECI 1} は利用可能である事が, XVCI 1 の仕様上保証されます.
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
	 * At least, {@link EngineConnectorInterface1 ECI 1} is guaranteed to be available by the specification of XVCI 1.
	 * </span>
	 * <span class="lang-ja">
	 * 引数 engineConnector には, スクリプトエンジンと情報をやり取りする際に使用するオブジェクトが渡されます. 
	 * そのオブジェクトの型は, {@link getEngineConnectorClass()} メソッドの戻り値として指定します.
	 * 少なくとも {@link EngineConnectorInterface1 ECI 1} は利用可能である事が, XVCI 1 の仕様上保証されます.
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
	 * At least, {@link EngineConnectorInterface1 ECI 1} is guaranteed to be available by the specification of XVCI 1.
	 * </span>
	 * <span class="lang-ja">
	 * 引数 engineConnector には, スクリプトエンジンと情報をやり取りする際に使用するオブジェクトが渡されます. 
	 * そのオブジェクトの型は, {@link getEngineConnectorClass()} メソッドの戻り値として指定します.
	 * 少なくとも {@link EngineConnectorInterface1 ECI 1} は利用可能である事が, XVCI 1 の仕様上保証されます.
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
	 * At least, {@link EngineConnectorInterface1 ECI 1} is guaranteed to be available by the specification of XVCI 1.
	 * </span>
	 * <span class="lang-ja">
	 * 引数 engineConnector には, スクリプトエンジンと情報をやり取りする際に使用するオブジェクトが渡されます. 
	 * そのオブジェクトの型は, {@link getEngineConnectorClass()} メソッドの戻り値として指定します.
	 * 少なくとも {@link EngineConnectorInterface1 ECI 1} は利用可能である事が, XVCI 1 の仕様上保証されます.
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
