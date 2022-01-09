/*
 * ==================================================
 * External Function Connector Interface 1 (XFCI 1)
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
 * An interface (abbreviated as XFCI1) for implementing external function plug-ins 
 * which provide functions available in scripts
 * </span>
 * <span class="lang-ja">
 * スクリプト内で使用可能な関数を提供する, 
 * 外部関数プラグインを実装するためのインターフェースの一つ（略称 XFCI 1）です
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
public interface ExternalFunctionConnectorInterface1 {

	/**
	 * <span class="lang-en">The type ID of this interface (value: "XFCI") referred when the plug-in will be loaded</span>
	 * <span class="lang-ja">プラグインのロード時に参照される, このインターフェースの形式ID（値: "XFCI"）です</span>
	 * .
	 */
	public static final String INTERFACE_TYPE_ID = "XFCI";

	/**
	 * <span class="lang-en">The generation of this interface (value: "1")</span>
	 * <span class="lang-ja">このインターフェースの世代名です（値: "1"）</span>
	 * .
	 */
	public static final String INTERFACE_GENERATION = "1";


	/**
	 * <span class="lang-en">Gets the name of the function</span>
	 * <span class="lang-ja">関数名を取得します</span>
	 * .
	 * @return
	 *     <span class="lang-en">The name of the function</span>
	 *     <span class="lang-ja">関数名</span>
	 */
	public abstract String getFunctionName();


	/**
	 * <span class="lang-en">Returns whether parameter names can be gotten</span>
	 * <span class="lang-ja">引数名を取得可能かどうかを判定します</span>
	 * .
	 * @return
	 *     <span class="lang-en">Returns "true" if parameter names can be gotten</span>
	 *     <span class="lang-ja">引数名が取得可能であれば true が返されます</span>
	 */
	public abstract boolean hasParameterNames();


	/**
	 * <span class="lang-en">Returns names of all parameters</span>
	 * <span class="lang-ja">全引数の名前を返します</span>
	 * .
	 * @return
	 *     <span class="lang-en">An array storing names of all parameters</span>
	 *     <span class="lang-ja">全引数の名前を格納する配列</span>
	 */
	public abstract String[] getParameterNames();


	/**
	 * <p>
	 * <span class="lang-en">Returns Class-instances representing data-types and array-ranks of all parameters</span>
	 * <span class="lang-ja">全引数のデータ型と配列次元数を表す Class インスタンスを返します</span>
	 * .
	 * </p>
	 * 
	 * <p>
	 * <span class="lang-en">
	 * For example, returns { double.class, long[][].class } when parameters of this function are: (double, long[][]).
	 * (Note that, "float" type values in scripts are handled as "double" type values in plug-ins, 
	 * and "int" type values in scripts are handled as "long" type values in plug-ins.)
	 * </span>
	 * <span class="lang-ja">
	 * 例えば, この関数の引数が (double, long[][]) の場合には { double.class, long[][].class } を返します.
	 * （なお, プラグインでは, スクリプト内での float 型は double 型, スクリプト内での int 型は long 型として扱います.）
	 * </span>
	 * </p>
	 * 
	 * @return
	 *     <span class="lang-en">
	 *         An array storing Class-instances representing data-types and array-ranks of parameters
	 *         (each element represents the data-type and array-rank of a paremeter)
	 *     </span>
	 *     <span class="lang-ja">
	 *         全ての引数における, データ型と配列次元数を表す Class インスタンスを格納する配列
	 *        （各要素が各引数のデータ型と配列次元数を表します）
	 *     </span>
	 */
	public abstract Class<?>[] getParameterClasses();


	/**
	 * <p>
	 * <span class="lang-en">
	 * Returns Class-instances representing data-I/O interfaces for accessing to data of parameters, when 
	 * {@link isDataConversionNecessary() data-conversion feature} 
	 * is disabled
	 * </span>
	 * <span class="lang-ja">
	 * {@link isDataConversionNecessary() データ変換機能} 
	 * を無効化している場合において, 引数のデータのやり取りに用いる, データ入出力インターフェースの型を表す 
	 * Class インスタンスを返します
	 * </span>
	 * .
	 * </p>
	 * 
	 * <p>
	 * <span class="lang-en">
	 * As interfaces for accessing to a scalar parameter, 
	 * {@link Int64ScalarDataAccessorInterface1 Int64 SDAI} (for long-type param),
	 * {@link Float64ScalarDataAccessorInterface1 Float64 SDAI} (for double-type param),
	 * {@link BoolScalarDataAccessorInterface1 Bool SDAI} (for boolean-type param),
	 * {@link StringScalarDataAccessorInterface1 String SDAI} (for String-type param),
	 * and
	 * {@link ArrayDataAccessorInterface1 ADAI} (generic) 
	 * are available.
	 * For an array parameter, only 
	 * {@link ArrayDataAccessorInterface1 ADAI}
	 * is available for accessing to data.
	 * </span>
	 * <span class="lang-ja">
	 * スカラの引数に対する入出力インターフェースとしては, 
	 * {@link Int64ScalarDataAccessorInterface1 Int64 SDAI}（long 型引数用）,
	 * {@link Float64ScalarDataAccessorInterface1 Float64 SDAI}（double 型引数用）,
	 * {@link BoolScalarDataAccessorInterface1 Bool SDAI}（boolean 型引数用）,
	 * {@link StringScalarDataAccessorInterface1 String SDAI}（String 型引数用）,
	 * および
	 * {@link ArrayDataAccessorInterface1 ADAI}（ジェネリック）
	 * が利用可能です.
	 * 配列引数に対しては, 
	 * {@link ArrayDataAccessorInterface1 ADAI} 
	 * のみが利用可能です.
	 * </span>
	 * </p>
	 * 
	 * @return
	 *     <span class="lang-en">
	 *         An array storing Class-instances
	 *         (each element represents the I/O interface for accessing to the data of each paremeter)
	 *     </span>
	 *     <span class="lang-ja">
	 *         Class インスタンスを格納する配列
	 *        （各要素が, 各引数のデータのやり取りに用いる, データ入出力インターフェースの型を表します）
	 *     </span>
	 */
	public abstract Class<?>[] getParameterUnconvertedClasses();


	/**
	 * <p>
	 * <span class="lang-en">Returns arbitrarinesses of data-types of parameters</span>
	 * <span class="lang-ja">全引数におけるデータ型の任意性を取得します</span>
	 * .
	 * </p>
	 * 
	 * <p>
	 * <span class="lang-en">
	 * When the data-type arbitrariness is "true" for a parameter, 
	 * any type of value can be passed as an actual argument for the parameter.
	 * </span>
	 * <span class="lang-ja">
	 * データ型の任意性として true が指定された引数に対しては, 呼び出し時に任意の型の値を受け取れるようになります.
	 * </span>
	 *
	 * @return
	 *     <span class="lang-en">
	 *         An array storing data-type arbitrarinesses of all parameters
	 *         (each element represents the data-type arbitrariness of each paremeter)
	 *     </span>
	 *     <span class="lang-ja">
	 *         全引数におけるデータ型の任意性を格納する配列
	 *        （各要素が, 各引数のデータ型の任意性を表します）
	 *     </span>
	 */
	public boolean[] getParameterDataTypeArbitrarinesses();


	/**
	 * <p>
	 * <span class="lang-en">Returns arbitrarinesses of array-ranks of parameters</span>
	 * <span class="lang-ja">全引数における配列次元数の任意性を取得します</span>
	 * .
	 * </p>
	 * 
	 * <p>
	 * <span class="lang-en">
	 * When the array-rank arbitrariness is "true" for a parameter, 
	 * an array having any rank (number of dimensions) can be passed as an actual argument for the parameter.
	 * </span>
	 * <span class="lang-ja">
	 * 配列次元数の任意性として true が指定された引数に対しては, 呼び出し時に任意次元の配列を受け取れるようになります.
	 * </span>
	 *
	 * @return
	 *     <span class="lang-en">
	 *         An array storing array-rank arbitrarinesses of all parameters
	 *         (each element represents the array-rank arbitrariness of each paremeter)
	 *     </span>
	 *     <span class="lang-ja">
	 *         全引数における配列次元数の任意性を格納する配列
	 *        （各要素が, 各引数の配列次元数の任意性を表します）
	 *     </span>
	 */
	public boolean[] getParameterArrayRankArbitrarinesses();


	/**
	 * <p>
	 * <span class="lang-en">Returns referencenesses of parameters</span>
	 * <span class="lang-ja">全引数における参照性を取得します</span>
	 * .
	 * </p>
	 * 
	 * <p>
	 * <span class="lang-en">
	 * When the referenceness is "true" for a parameter, 
	 * an actual argument of the parameter will be passed by reference.
	 * </span>
	 * <span class="lang-ja">
	 * 参照性として true が指定された引数に対しては, 呼び出し時に実引数が参照渡しされます.
	 * </span>
	 *
	 * @return
	 *     <span class="lang-en">
	 *         An array storing referencenesses of all parameters
	 *         (each element represents the referenceness of each paremeter)
	 *     </span>
	 *     <span class="lang-ja">
	 *         全引数における参照性を格納する配列
	 *        （各要素が, 各引数の参照性を表します）
	 *     </span>
	 */
	public abstract boolean[] getParameterReferencenesses();


	/**
	 * <p>
	 * <span class="lang-en">Returns constantnesses of parameters</span>
	 * <span class="lang-ja">全引数における定数性を取得します</span>
	 * .
	 * </p>
	 * 
	 * <p>
	 * <span class="lang-en">
	 * When the constantness is "true" for a parameter, 
	 * it is regarded that the value of an actual argument of the parameter will not be modified 
	 * in the process of this function.
	 * </span>
	 * <span class="lang-ja">
	 * 定数性として true が指定された引数に対しては, この関数の実行時に, 実引数の値が変更されないものと見なされます.
	 * </span>
	 *
	 * @return
	 *     <span class="lang-en">
	 *         An array storing constantnesses of all parameters
	 *         (each element represents the constantness of each paremeter)
	 *     </span>
	 *     <span class="lang-ja">
	 *         全引数における定数性を格納する配列
	 *        （各要素が, 各引数の定数性を表します）
	 *     </span>
	 */
	public abstract boolean[] getParameterConstantnesses();


	/**
	 * <p>
	 * <span class="lang-en">Returns whether the number of parameters is arbitrary</span>
	 * <span class="lang-ja">任意個の引数を受け取るかどうかを返します</span>
	 * .
	 * </p>
	 *
	 * <p>
	 * <span class="lang-en">
	 * When this feature is enabled (when this method returns "true"), 
	 * by default, it is regarded that all parameters have the same data-type and array-rank.
	 * Hence, {@link getParameterClasses()} method should return an array of which length is 1, 
	 * and it represents the data-type/array-rank of all parameters.
	 * However, if {@link getParameterDataTypeArbitrarinesses()} / {@link getParameterArrayRankArbitrarinesses()}
	 * returns { true }, data-types / array-ranks of parameters are arbitrary, so they may different each other.
	 * </span>
	 * <span class="lang-ja">
	 * この機能が有効化されている（このメソッドが true を返す）場合, 
	 * デフォルトでは, 全引数のデータ型と配列次元数は同一であると見なされます. 
	 * 従ってその場合, {@link getParameterClasses()} メソッドは要素数 1 の配列を返すようにし, 
	 * その値が, 全引数のデータ型と配列次元数を表すものと見なされます. 
	 * ただし, {@link getParameterDataTypeArbitrarinesses()} / {@link getParameterArrayRankArbitrarinesses()}
	 * メソッドが { true } を返す場合には, データ型や配列次元数は任意となるため, 各引数で異なる場合も生じ得ます. 
	 * </span>
	 * </p>
	 *
	 * <!--
	 * <p>
	 * <span class="lang-en">
	 * Also, please note that, 
	 * behaviour of this feature is similar to but different with features enabled by {@link hasVariadicParameters()}.
	 * </span>
	 * <span class="lang-ja">
	 * なお, この機能は {@link hasVariadicParameters()} メソッドで有効化される機能と似ていますが, 異なる事に留意してください. 
	 * </span>
	 * </p>
	 * -->
	 *
	 * @return
	 *     <span class="lang-en">Returns "true" if the number of parameters is arbitrary</span>
	 *     <span class="lang-ja">この関数が任意個の引数を取る場合に true が返されます</span>
	 */
	public boolean isParameterCountArbitrary();


	/**
	 * <span class="lang-en">Unsupported feature on the current version of VCSSL/Vnano Engine</span>
	 * <span class="lang-ja">現在のVCSSL/Vnano処理系では未サポートの機能です</span>
	 * .
	 * @return
	 *     <span class="lang-en">Returns "true" if this function has variadic parameters</span>
	 *     <span class="lang-ja">この関数が可変長引数を取る場合に true が返されます</span>
	 */
	public abstract boolean hasVariadicParameters();


	/**
	 * <p>
	 * <span class="lang-en">
	 * Returns a Class-instance, representing a data-type and an array-rank of the return value of this function
	 * </span>
	 * <span class="lang-ja">
	 * この関数の戻り値のデータ型と配列次元数を表す Class インスタンスを返します
	 * </span>
	 * .
	 * </p>
	 * 
	 * <p>
	 * <span class="lang-en">
	 * For example, returns double.class for double-type return value, 
	 * returns long[][].class for long[][]-type return value, and so on.
	 * (Note that, "float" type values in scripts are handled as "double" type values in plug-ins, 
	 * and "int" type values in scripts are handled as "long" type values in plug-ins.)
	 * </span>
	 * <span class="lang-ja">
	 * 例えば, double 型の戻り値に対しては double.class を返し, long[][] 型の戻り値に対しては long[][].class を返します.
	 * （なお, プラグインでは, スクリプト内での float 型は double 型, スクリプト内での int 型は long 型として扱います.）
	 * </span>
	 * </p>
	 * 
	 * @param parameterClasses
	 *     <span class="lang-en">
	 *         An array storing Class-instances representing data-types and array-ranks of actual arguments
	 *         (each element represents the data-type and array-rank of an actual argument)
	 *     </span>
	 *     <span class="lang-ja">
	 *         全ての実引数における, データ型と配列次元数を表す Class インスタンスを格納する配列
	 *         （各要素が各実引数のデータ型と配列次元数を表します）
	 *     </span>
	 * 
	 * @return
	 *     <span class="lang-en">
	 *         A Class-instances representing the data-type and the array-rank of the return value of this function
	 *     </span>
	 *     <span class="lang-ja">
	 *         この関数の戻り値におけるデータ型と配列次元数を表す Class インスタンス
	 *     </span>
	 */
	public abstract Class<?> getReturnClass(Class<?>[] parameterClasses);


	/**
	 * <p>
	 * <span class="lang-en">
	 * Returns Class-instances representing data-I/O interfaces for accessing to data 
	 * of return value of this function, when 
	 * {@link isDataConversionNecessary() data-conversion feature} 
	 * is disabled
	 * </span>
	 * <span class="lang-ja">
	 * {@link isDataConversionNecessary() データ変換機能} 
	 * を無効化している場合において, この関数の戻り値のデータのやり取りに用いる, 
	 * データ入出力インターフェースの型を表す Class インスタンスを返します
	 * </span>
	 * .
	 * </p>
	 * 
	 * <p>
	 * <span class="lang-en">
	 * As interfaces for accessing to a scalar return value, 
	 * {@link Int64ScalarDataAccessorInterface1 Int64 SDAI} (for long-type value),
	 * {@link Float64ScalarDataAccessorInterface1 Float64 SDAI} (for double-type value),
	 * {@link BoolScalarDataAccessorInterface1 Bool SDAI} (for boolean-type value),
	 * {@link StringScalarDataAccessorInterface1 String SDAI} (for String-type value),
	 * and
	 * {@link ArrayDataAccessorInterface1 ADAI} (generic) 
	 * are available.
	 * For an array return value, only 
	 * {@link ArrayDataAccessorInterface1 ADAI}
	 * is available for accessing to data.
	 * </span>
	 * <span class="lang-ja">
	 * スカラの戻り値に対する入出力インターフェースとしては, 
	 * {@link Int64ScalarDataAccessorInterface1 Int64 SDAI}（long 型の値用）,
	 * {@link Float64ScalarDataAccessorInterface1 Float64 SDAI}（double 型の値用）,
	 * {@link BoolScalarDataAccessorInterface1 Bool SDAI}（boolean 型の値用）,
	 * {@link StringScalarDataAccessorInterface1 String SDAI}（String 型の値用）,
	 * および
	 * {@link ArrayDataAccessorInterface1 ADAI}（ジェネリック）
	 * が利用可能です.
	 * 配列の戻り値に対しては, 
	 * {@link ArrayDataAccessorInterface1 ADAI} 
	 * のみが利用可能です.
	 * </span>
	 * </p>
	 * 
	 * <p>
	 * <span class="lang-en">
	 * Also, information of data-types and array-ranks of actual arguments will be passed to "parameterClasses".
	 * The above information enable to implement a kind of "generic" function 
	 * of which the data-type/array-rank varies depending on data-types/array-ranks of parameters.
	 * (To implement such functions, set the data-type/array-rank of the return value arbitrary,
	 *  by implementing {@link isReturnDataTypeArbitrary()} and {@link isReturnArrayRankArbitrary()} 
	 *  methods to return "true").
	 * </span>
	 * <span class="lang-ja">
	 * なお, 引数 parameterClasses には, スクリプト内での呼び出しにおける, 引数のデータ型情報が渡されます. 
	 * これにより, 引数の型によって戻り値の型が異なるだけの, 複数の関数に相当する処理を, まとめて提供する事ができます. 
	 * （そのような関数を実装する場合, 併せて
	 *   {@link isReturnDataTypeArbitrary()} および {@link isReturnArrayRankArbitrary()} メソッドが
	 *   true を返すように実装する事で, 戻り値のデータ型と配列次元数を「任意」に設定しておく必要があります. ）
	 * </span>
	 * </p>
	 * 
	 * @param parameterClasses
	 *     <span class="lang-en">
	 *         An array storing Class-instances representing data-types/array-ranks of actual arguments
	 *         (each element represents the data-type/array-rank of each actual argument)
	 *     </span>
	 *     <span class="lang-ja">
	 *         全ての実引数の, データ型と配列次元数を表す Class インスタンスを格納する配列
	 *        （各要素が, 各実引数のデータ型と配列次元数を表します）
	 *     </span>
	 * 
	 * @return
	 *     <span class="lang-en">
	 *         An array storing Class-instances representing the I/O interface for accessing to the data of return value
	 *     </span>
	 *     <span class="lang-ja">
	 *         戻り値のデータのやり取りに用いる, データ入出力インターフェースの型を表す Class インスタンスを格納する配列
	 *     </span>
	 */
	public abstract Class<?> getReturnUnconvertedClass(Class<?>[] parameterClasses);


	/**
	 * <p>
	 * <span class="lang-en">
	 * Returns whether the data-type of the return value of this function varies arbitrary
	 * </span>
	 * <span class="lang-ja">
	 * この関数の戻り値のデータ型が, 任意に変化するかどうかを返します
	 * </span>
	 * .
	 * </p>
	 * 
	 * <p>
	 * <span class="lang-en">
	 * This feature is for implementing a kind of "generic" function of which type of return value 
	 * varies depending on types of actual arguments.
	 * Hence, even if this feature is enabled (even if this method returns "true"), 
	 * the type of the return value must be determined at when types of actual arguments are determined.
	 * See also: exprenation of {@link getReturnClass(Class[])} method.
	 * </span>
	 * <span class="lang-ja">
	 * この機能は, 戻り値の型が引数の型に依存するような, 一種のジェネリックな関数を実装するためのものです. 
	 * 従って, この機能を有効化した場合（このメソッドが true を返す場合）でも, 
	 * 渡される引数のデータ型が確定すると, それに応じて戻り値の型も一意に決定される必要があります. 
	 * {@link getReturnClass(Class[])} メソッドの説明も併せて参照してください. 
	 * </span>
	 * </p>
	 * 
	 * @return
	 *     <span class="lang-en">Returns "true" if the data-type of the return value varies arbitrary</span>
	 *     <span class="lang-ja">戻り値のデータ型が, 任意に変化する場合に true を返します</span>
	 */
	public abstract boolean isReturnDataTypeArbitrary();


	/**
	 * <p>
	 * <span class="lang-en">
	 * Returns whether the array-rank (number of dimensions) of the return value of this function varies arbitrary
	 * </span>
	 * <span class="lang-ja">
	 * この関数の戻り値の配列次元数が, 任意に変化するかどうかを返します
	 * </span>
	 * .
	 * </p>
	 * 
	 * <p>
	 * <span class="lang-en">
	 * This feature is for implementing a kind of "generic" function of which array-rank of return value 
	 * varies depending on types/ranks of actual arguments.
	 * Hence, even if this feature is enabled (even if this method returns "true"), 
	 * the rank of the return value must be determined at when types/ranks of actual arguments are determined.
	 * See also: exprenation of {@link getReturnClass(Class[])} method.
	 * </span>
	 * <span class="lang-ja">
	 * この機能は, 戻り値の配列次元数が引数の型/次元数に依存するような, 一種のジェネリックな関数を実装するためのものです. 
	 * 従って, この機能を有効化した場合（このメソッドが true を返す場合）でも, 
	 * 渡される引数のデータ型/次元数が確定すると, それに応じて戻り値の次元数も一意に決定される必要があります. 
	 * {@link getReturnClass(Class[])} メソッドの説明も併せて参照してください. 
	 * </span>
	 * </p>
	 * 
	 * @return
	 *     <span class="lang-en">Returns "true" if the array-rank of the return value varies arbitrary</span>
	 *     <span class="lang-ja">戻り値の配列次元数が, 任意に変化する場合に true を返します</span>
	 */
	public abstract boolean isReturnArrayRankArbitrary();


	/**
	 * <p>
	 * <span class="lang-en">
	 * Returns whether the data-conversions for arguments and return value are necessary
	 * </span>
	 * <span class="lang-ja">
	 * この関数の引数や戻り値に対して, データ変換が必要かどうかを返します
	 * </span>
	 * .
	 * </p>
	 * 
	 * <p>
	 * <span class="lang-en">
	 * When this feature is enabled (when this method returns "true"),
	 * you can receive data of arguments by using simple data-types.
	 * For example, you can receive Double instance for double-type parameter, 
	 * long[][] instance for long[][]-type parameter, and so on.
	 * Also, as same as the arguments, 
	 * you can return the simple data-type value as the return value of the function.
	 * </span>
	 * <span class="lang-ja">
	 * この機能が有効化されている（このメソッドが true を返すようにした）場合, 
	 * 各引数のデータを, 単純なデータ型で受け取る事ができます. 例えば, 
	 * double 型引数においては Double 型インスタンス, long[][] 型引数に対しては long[][] 型インスタンスなど受け取れます. 
	 * 戻り値に対しても, 引数と同様に, 単純なデータ型の値で返す事ができます. 
	 * </span>
	 * </p>
	 *
	 * <p>
	 * <span class="lang-en">
	 * On the other hand, when this feature is disabled (when this method retunrs "false"),
	 * it is necessary to access to data of parameters and the return value through data-I/O interfaces, 
	 * which are specified as return values of {@link getParameterUnconvertedClasses()} 
	 * and {@link getReturnUnconvertedClass(Class<?>[])} method. 
	 * </span>
	 * <span class="lang-ja">
	 * 一方, この機能が無効化されている（このメソッドが false を返すようにした）場合, 
	 * 引数や戻り値のデータに対しては, 各種のデータ入出力インターフェースを介してアクセスする必要があります. 
	 * その際, 使用するデータ入出力インターフェースは, {@link getParameterUnconvertedClasses()} メソッドや
	 * {@link getReturnUnconvertedClass(Class<?>[])} メソッドの戻り値として指定します. 
	 * </span>
	 * </p>
	 *
	 * <p>
	 * <span class="lang-en">
	 * Enabling this feature make the implementation of the plugin simple.
	 * However, this feature takes overhead processing costs for converting data of parameters and return value.
	 * Therefore, if you want to implement a function plug-in which will be called frequently from scripts 
	 * of which processing speed is required to be fast in some degree,
	 * disabling this feature gives some advantage to reduce overhead processing costs of function calls.
	 * </span>
	 * <span class="lang-ja">
	 * この機能は, 有効化した方がプラグインの実装が単純になりますが, 
	 * 反面, 引数や戻り値のやり取りにおいて, データ変換処理のオーバーヘッドがかかるようになります. 
	 * 従って, 処理速度が要求される処理において, 頻繁に呼び出されるような関数プラグインの実装においては, 
	 * この機能を無効化する事で, 呼び出しコストを削減できる利点があります. 
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
	 * <span class="lang-en">Invoke the process of this function</span>
	 * <span class="lang-ja">この関数の処理を実行します</span>
	 * .
	 * </p>
	 *
	 * <p>
	 * <span class="lang-en">
	 * Data of actual arguments of the function will be passed to the argument of this method: "arguments", 
	 * which is an Object[] type array storing data of each actual argument as an element.
	 * </span>
	 * <span class="lang-ja">
	 * 関数呼び出し時の実引数のデータは, このメソッドの引数 arguments に渡されます. 
	 * この arguments は Object[] 型の配列で, 各要素に各実引数のデータが格納されます. 
	 * </span>
	 * </p>
	 * 
	 * <p>
	 * <span class="lang-en">
	 * When data-conversion feature is enabled (see: {@link isDataConversionNecessary()} method), 
	 * each actual argument will be stored as a simple type of data in "arguments".
	 * For example, Double instance for double-type parameter, long[][] instance for long[][]-type parameter, and so on.
	 * </span>
	 * <span class="lang-ja">
	 * データ変換機能が有効化されている場合（{@link isDataConversionNecessary()} メソッド参照）, 
	 * 各実引数は単純な型のデータとして arguments に格納されます. 例えば, 
	 * double 型引数に対しては Double インスタンス, long[][] 型引数に対しては long[][] 型インスタンスなどです. 
	 * </span>
	 * </p>
	 * 
	 * <p>
	 * <span class="lang-en">
	 * On the other hand, when data-conversion feature is disabled, in "arguments",
	 * each actual argument will be stored as a data-container object implementing data-I/O interface,
	 * specified as each element of return values of {@link getParameterUnconvertedClasses()} method.
	 * In addition, in this case, 
	 * "arguments[0]" will be a data-container object for storing data of the return value of this function,
	 * so the data of first argument will be stored at "arguments[1]", and the next arg will be stored at "argument[2]". 
	 * </span>
	 * <span class="lang-ja">
	 * 一方で, データ変換機能が無効化されている場合, arguments の各要素には, 
	 * 各種のデータ入出力インターフェースを実装したデータコンテナオブジェクトが格納されて渡されます. 
	 * それを介して, 各引数のデータにアクセスします. 
	 * 具体的なデータ入出力インターフェースは {@link getParameterUnconvertedClasses()} メソッドの戻り値として指定します. 
	 * なお, この場合, arguments[0] には, 戻り値のデータを格納するためのデータコンテナオブジェクトが割り当てられています. 
	 * 従って最初の引数は arguments[1], 次の引数は arguments[2], ... を参照する必要があります. 
	 * </span>
	 * </p>
	 *
	 * @param arguments
	 *     <span class="lang-en">An array storing all actual arguments of the function call</span>
	 *     <span class="lang-ja">関数呼び出し時における, 全ての実引数を格納する配列</span>
	 * 
	 * @return
	 *     <span class="lang-en">
	 *         The return value of the function call
	 *         (when data-conversion feature is disabled, store the return value to arguments[0] instead)
	 *     </span>
	 *     <span class="lang-ja">
	 *         関数呼び出しにおける戻り値
	 *         （データ変換機能が無効化されている場合は, 代わりに arguments[0] に戻り値を格納します）
	 *     </span>
	 * 
	 * @throws ConnectorException
	 *     <span class="lang-en">Thrown when any error occurred in the process of this function</span>
	 *     <span class="lang-ja">関数の実行処理において, 何らかのエラーが発生した場合にスローされます</span>
	 */
	public abstract Object invoke(Object[] arguments) throws ConnectorException ;


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
	 * {@link EngineConnectorInterface1 ECI 1} is guaranteed to be available by the specification of XFCI 1.
	 * </span>
	 * <span class="lang-ja">
	 * どのようなインターフェース/クラスが利用可能かはスクリプトエンジンに依存しますが, 
	 * 少なくとも {@link EngineConnectorInterface1 ECI 1} は利用可能である事が, XFCI 1 の仕様上保証されます. 
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
	 * At least, {@link EngineConnectorInterface1 ECI 1} is guaranteed to be available by the specification of XFCI 1.
	 * </span>
	 * <span class="lang-ja">
	 * 引数 engineConnector には, スクリプトエンジンと情報をやり取りする際に使用するオブジェクトが渡されます. 
	 * そのオブジェクトの型は, {@link getEngineConnectorClass()} メソッドの戻り値として指定します.
	 * 少なくとも {@link EngineConnectorInterface1 ECI 1} は利用可能である事が, XFCI 1 の仕様上保証されます.
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
	 * At least, {@link EngineConnectorInterface1 ECI 1} is guaranteed to be available by the specification of XFCI 1.
	 * </span>
	 * <span class="lang-ja">
	 * 引数 engineConnector には, スクリプトエンジンと情報をやり取りする際に使用するオブジェクトが渡されます. 
	 * そのオブジェクトの型は, {@link getEngineConnectorClass()} メソッドの戻り値として指定します.
	 * 少なくとも {@link EngineConnectorInterface1 ECI 1} は利用可能である事が, XFCI 1 の仕様上保証されます.
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
	 * At least, {@link EngineConnectorInterface1 ECI 1} is guaranteed to be available by the specification of XFCI 1.
	 * </span>
	 * <span class="lang-ja">
	 * 引数 engineConnector には, スクリプトエンジンと情報をやり取りする際に使用するオブジェクトが渡されます. 
	 * そのオブジェクトの型は, {@link getEngineConnectorClass()} メソッドの戻り値として指定します.
	 * 少なくとも {@link EngineConnectorInterface1 ECI 1} は利用可能である事が, XFCI 1 の仕様上保証されます.
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
	 * At least, {@link EngineConnectorInterface1 ECI 1} is guaranteed to be available by the specification of XFCI 1.
	 * </span>
	 * <span class="lang-ja">
	 * 引数 engineConnector には, スクリプトエンジンと情報をやり取りする際に使用するオブジェクトが渡されます. 
	 * そのオブジェクトの型は, {@link getEngineConnectorClass()} メソッドの戻り値として指定します.
	 * 少なくとも {@link EngineConnectorInterface1 ECI 1} は利用可能である事が, XFCI 1 の仕様上保証されます.
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
