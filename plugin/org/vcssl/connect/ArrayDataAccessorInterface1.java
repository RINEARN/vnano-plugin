/*
 * ==================================================
 * Array Data Accessor Interface 1 (ADAI 1)
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
 * A data-I/O interface (abbreviated as ADAI 1), mainly implemented by data container objects of language processor systems
 * </span>
 * <span class="lang-ja">
 * 主に処理系のデータコンテナが実装してサポートする, データ入出力インターフェースの一つ（略称 ADAI 1）です
 * </span>
 * .
 * </p>
 *
 * <p>
 * <span class="lang-en">
 * In this package, multiple data I/O interfaces are provided for passing/receiving data 
 * without any data-conversions, between script-engine-side and plug-in-side, if required.
 * In them, this interface ADAI 1 provides I/O methods of multi-dimensional array data.
 * </span>
 * <span class="lang-ja">
 * このパッケージ内では, スクリプトエンジン側とプラグイン側との間で, 
 * 必要に応じてデータ型の変換なしにデータを受け渡しするための, 
 * 複数のデータ入出力インターフェースが提供されています. 
 * その中でこの ADAI 1 は, 多次元配列データに対する入出力機能を提供します. 
 * </span>
 * </p>
 * 
 * @param <T>
 *     <span class="lang-en">The type of data stored in the data container implementing this interface</span>
 *     <span class="lang-ja">このインターフェースを実装するデータコンテナにおいて, 保持するデータの型</span>
 */
public interface ArrayDataAccessorInterface1<T> {

	/**
	 * <span class="lang-en">The type ID of this interface (value: "ADAI") referred when the plug-in will be loaded</span>
	 * <span class="lang-ja">プラグインのロード時に参照される、このインターフェースの形式ID（値: "ADAI"）です</span>
	 * .
	 */
	public static final String INTERFACE_TYPE_ID = "ADAI";

	/**
	 * <span class="lang-en">The generation of this interface (value: "1")</span>
	 * <span class="lang-ja">このインターフェースの世代名です（値: "1"）</span>
	 * .
	 */
	public static final String INTERFACE_GENERATION = "1";


	/**
	 * <span class="lang-en">The value of size of a scalar value (value: 1)</span>
	 * <span class="lang-ja">スカラ値におけるサイズ値です（値: 1）</span>
	 * .
	 */
	public static final int ARRAY_SIZE_OF_SCALAR = 1;

	/**
	 * <span class="lang-en">The number of dimensions (array-rank) of a scalar value (value: 0)</span>
	 * <span class="lang-ja">スカラ値における配列次元数（配列ランク値）です（値: 0）</span>
	 * .
	 */
	public static final int ARRAY_RANK_OF_SCALAR = 0;

	/**
	 * <span class="lang-en">An array storing dimension-lengths of a scalar value (value: { })</span>
	 * <span class="lang-ja">スカラ値における, 各次元の長さを格納する配列です（値: { }）</span>
	 * .
	 */
	public static final int[] ARRAY_LENGTHS_OF_SCALAR = { };


	/**
	 * <p>
	 * <span class="lang-en">Sets array data with related information</span>
	 * <span class="lang-ja">配列データを, そのデータに関する必須情報と共に設定します</span>
	 * .
	 * </p>
	 *
	 * <p>
	 * <span class="lang-en">
	 * On this interface, contents of any data including a scalar value, an 1-dimensional (1D) array, 
	 * and a multi-dimensional array are always handled as an 1D array, 
	 * so specify an 1D array for the argument "data". 
	 * How to store contents of data into the 1D array is the following.
	 * </span>
	 * <span class="lang-ja">
	 * このインターフェースでは, スカラ値や多次元配列も含めて, データは全て1次元の配列として入出力します. 
	 * そのため, 引数 data には常に 1 次元配列を渡してください. 1 次元配列への, データの格納の仕方は下記の通りです. 
	 * </span>
	 * </p>
	 *
	 * <p>
	 * <span class="lang-en">
	 * <b>When set a scalar value:</b>
	 * For the argument "data", specify an 1D array in which the scalar value is stored as an element, at any index.
	 * In addition, specify the array-index of the stored scalar value to the argument "offset".
	 * For the argument "lengths", specify an empty array of which length is 0
	 * (so we regard an scalar value as a "0-dimensional array" on this interface).
	 * </span>
	 * <span class="lang-ja">
	 * <b>スカラ値を設定する場合:</b>
	 * 引数 data には, スカラ値が要素として, 任意のインデックスの位置に格納されている配列を1次元指定します. 
	 * そして, そのインデックスを引数 offset に指定します. 
	 * 引数 lengths には, 長さ 0 のから配列を指定します
	 * （即ち, このインターフェースではスカラ値を「 0 次元の配列 」と見なして扱います）. 
	 * </span>
	 * </p>
	 *
	 * <p>
	 * <span class="lang-en">
	 * <b>When set 1-dimensional array data:</b>
	 * For the argument "data", specify the 1-D array data as it is. 
	 * For the argument "offset", always specify 0.
	 * For the argument "lengths", specify an array of which length is 1, 
	 * and stores the length of array data (specified to the argument "data") at [0].
	 * </span>
	 * <span class="lang-ja">
	 * <b>1次元配列データを設定する場合:</b>
	 * 引数 data には, 設定したい1次元配列データをそのまま指定します. 
	 * 引数 offset には, 常に 0 を指定します. 
	 * 引数 lengths には, 長さが 1 で, [0] の位置に1次元配列データ（引数 data に指定）の長さが格納された配列を指定します. 
	 * </span>
	 * </p>
	 * 
	 * <p>
	 * <span class="lang-en">
	 * <b>When set multi-dimensional array data:</b>
	 * For the argument "data", specify the 1-D array in which data of multi-dimensional array is aligned
	 * in the form of: 
	 * </span>
	 * 
	 * <div class="lang-en" style="border: 1px solid #000000; margin: 10px; padding:5px;">
	 *     (The case of 2D array, of which lengths are [N1][N2])
	 *     <br>
	 *     data[ index1D ] = multiDimArray[ multiIndex1 ][ multiIndex2 ]
	 *     <br>
	 *     index1D = N2*multiIndex1 + multiIndex3
	 * </div>
	 * 
	 * <div class="lang-en" style="border: 1px solid #000000; margin: 10px; padding:5px;">
	 *     (The case of 3D array, of which lengths are [N1][N2][N3])
	 *     <br>
	 *     data[ index1D ] = multiDimArray[ multiIndex1 ][ multiIndex2 ][ multiIndex3 ]
	 *     <br>
	 *     index1D = N3*N2*multiIndex1 + N3*multiIndex2 + multiIndex3
	 * </div>
	 * 
	 * <div class="lang-en" style="border: 1px solid #000000; margin: 10px; padding:5px;">
	 *     (The case of 4D array, of which lengths are [N1][N2][N3][N4])
	 *     <br>
	 *     data[ index1D ] = multiDimArray[ multiIndex1 ][ multiIndex2 ][ multiIndex3 ][ multiIndex4 ]
	 *     <br>
	 *     index1D = N4*N3*N2*multiIndex1 + N4*N3*multiIndex2 + N4*multiIndex3 + multiIndex4
	 * </div>
	 * 
	 * <span class="lang-en">
	 * ...
	 * <br>
	 * For the argument "offset", always specify 0.
	 * For the argument "lengths", specify an array of which length equals to the number of dimensions of the data array, 
	 * and stores lengths of each dimension, 
	 * where [0] represents the length of most-left dimension, and [max] represents the length of most-right dimension
	 * (so specify {N1, N2, N3} for 3D array data of which lengths are [N1][N2][N3]).
	 * </span>
	 * 
	 * <p>
	 * <span class="lang-ja">
	 * <b>多次元配列データを格納する場合:</b>
	 * 引数 data には, 多次元配列の各要素値を, 下記の通り規則的に並べた 1 次元配列を指定します:
	 * </span>
	 * 
	 * <div class="lang-ja" style="border: 1px solid #000000; margin: 5px;">
	 *     (長さが [N1][N2] の 2 次元配列の場合)
	 *     <br>
	 *     data[ index1D ] = multiDimArray[ multiIndex1 ][ multiIndex2 ]
	 *     <br>
	 *     index1D = N2*multiIndex1 + multiIndex3
	 * </div>
	 * 
	 * <div class="lang-ja" style="border: 1px solid #000000; margin: 5px;">
	 *     (長さが [N1][N2][N3] の 3 次元配列の場合)
	 *     <br>
	 *     data[ index1D ] = multiDimArray[ multiIndex1 ][ multiIndex2 ][ multiIndex3 ]
	 *     <br>
	 *     index1D = N3*N2*multiIndex1 + N3*multiIndex2 + multiIndex3
	 * </div>
	 * 
	 * <div class="lang-ja" style="border: 1px solid #000000; margin: 5px;">
	 *     (長さが [N1][N2][N3][N4] の 4 次元配列の場合)
	 *     (The case of 4D array, of which lengths are [N1][N2][N3][N4])
	 *     <br>
	 *     data[ index1D ] = multiDimArray[ multiIndex1 ][ multiIndex2 ][ multiIndex3 ][ multiIndex4 ]
	 *     <br>
	 *     index1D = N4*N3*N2*multiIndex1 + N4*N3*multiIndex2 + N4*multiIndex3 + multiIndex4
	 * </div>
	 * 
	 * <span class="lang-ja">
	 * ...
	 * <br>
	 * 引数 offset には, 常に 0 を指定します. 
	 * 引数 lengths には, 長さが多次元配列データ（引数 data に指定）の次元数に等しい配列を指定し, 
	 * その中に, 各次元の長さを格納します. その際, 要素 [0] が左端次元の長さを表し, 要素 [max] が右端次元の長さを表します
	 * （具体例としては, 長さ [N1][N2][N3] の 3 次元配列においては {N1, N2, N3} を指定します）. 
	 * </span>
	 * 
	 * <p>
	 * <span class="lang-en">
	 * By the way, methods for setting a value "data", "offset", or "lengths" independently are not provided,
	 * for preventing inconsistency between them (never allowed even for a moment).
	 * </span>
	 * <span class="lang-ja">
	 * なお, data や offset および lengths の値を個別に設定するメソッドは, 
	 * それらの関係が瞬間的にでも不整合な状態になる事を防ぐため, 提供されません. 
	 * </span>
	 * </p>
	 * 
	 * @param data
	 *     <span class="lang-en">The data to be set (see the above explanation)</span>
	 *     <span class="lang-ja">設定するデータ（上記参照）</span>
	 * @param offset
	 *     <span class="lang-en">The index of the scalar value (see the above explanation)</span>
	 *     <span class="lang-ja">スカラ値の格納位置（上記参照）</span>
	 * @param lengths
	 *     <span class="lang-en">An array storing lengths of each dimension (see the above explanation)</span>
	 *     <span class="lang-ja">次元ごとの長さを格納する配列</span>
	 */
	public abstract void setArrayData(T data, int offset, int[] lengths);


	/**
	 * <p>
	 * <span class="lang-en">Returns the array data</span>
	 * <span class="lang-ja">配列データを取得します</span>
	 * .
	 * </p>
	 * 
	 * <p>
	 * <span class="lang-en">
	 * This method always returns a 1-dimensional array.
	 * About how contents of data are stored in the 1-D array is, see the explanation of 
	 * {@link ArrayDataAccessorInterface1#setArrayData(Object, int, int[]) setArrayData} 
	 * method.
	 * </span>
	 * <span class="lang-ja">
	 * 戻り値は常に 1 次元配列です. 
	 * スカラ値や多次元配列データの格納のされ方については, 
	 * {@link ArrayDataAccessorInterface1#setArrayData(Object, int, int[]) setArrayData} 
	 * メソッドの説明を参照してください. 
	 * </span>
	 * </p>
	 * 
	 * @return
	 *     <span class="lang-en">The 1-D array storing contents of data</span>
	 *     <span class="lang-ja">データ内容を格納する 1 次元配列</span>
	 */
	public abstract T getArrayData();


	/**
	 * <p>
	 * <span class="lang-en">Returns whether any array data can be gotten or not</span>
	 * <span class="lang-ja">配列データを取得可能かどうかを判定します</span>
	 * .
	 * </p>
	 * 
	 * @return
	 *     <span class="lang-en">Returns "true" if any array data can be gotton</span>
	 *     <span class="lang-ja">配列データを取得可能であれば true が返されます</span>
	 */
	public abstract boolean hasArrayData();


	/**
	 * <p>
	 * <span class="lang-en">Returns the index of the scalar value in the array data</span>
	 * <span class="lang-ja">配列データの中で, スカラ値が格納されているインデックスを返します</span>
	 * .
	 * </p>
	 * 
	 * <p>
	 * <span class="lang-en">
	 * For details, see the explanation of "offset" argument of 
	 * {@link ArrayDataAccessorInterface1#setArrayData(Object, int, int[]) setArrayData} 
	 * method.
	 * </span>
	 * <span class="lang-ja">
	 * 詳細は, 
	 * {@link ArrayDataAccessorInterface1#setArrayData(Object, int, int[]) setArrayData} 
	 * メソッドの引数 offset に関する説明を参照してください. 
	 * </span>
	 * </p>
	 *
	 * @return
	 *     <span class="lang-en">The index of the scalar value in the array data</span>
	 *     <span class="lang-ja">配列データの中で, スカラ値が格納されているインデックス</span>
	 */
	public abstract int getArrayOffset();


	/**
	 * <p>
	 * <span class="lang-en">Returns the array storing lengths of dimensions of array data</span>
	 * <span class="lang-ja">配列データの各次元の長さを格納する配列を取得します</span>
	 * .
	 * </p>
	 *
	 * <p>
	 * <span class="lang-en">
	 * For details, see the explanation of "lengths" argument of 
	 * {@link ArrayDataAccessorInterface1#setArrayData(Object, int, int[]) setArrayData} 
	 * method.
	 * </span>
	 * <span class="lang-ja">
	 * 詳細は, 
	 * {@link ArrayDataAccessorInterface1#setArrayData(Object, int, int[]) setArrayData} 
	 * メソッドの引数 lengths に関する説明を参照してください. 
	 * </span>
	 * </p>
	 *
	 * @return
	 *     <span class="lang-en">The array storing lengths of dimensions of data</span>
	 *     <span class="lang-ja">データの各次元の長さを格納する配列</span>
	 */
	public abstract int[] getArrayLengths();


	/**
	 * <p>
	 * <span class="lang-en">Returns the size (lenth) of data as an 1-dimensional array</span>
	 * <span class="lang-ja">1 次元配列としての, データのサイズ（長さ）を返します</span>
	 * .
	 * </p>
	 *
	 * <p>
	 * <span class="lang-en">
	 * When data is a scalar value, the size always is 1.
	 * When data is an 1-dimensional array, the size is its length.
	 * When data is a multi-dimensional array of which lengths are [N1][N2][N3]...[NM], 
	 * the size is the value of the product of them: N1*n2*n3*...*NM.
	 * </span>
	 * <span class="lang-ja">
	 * データがスカラ値の場合, サイズは常に 1 となります. 
	 * データが 1 次元配列の場合, サイズはその長さそのものです. 
	 * データが長さ [N1][N2][N3]...[NM] の多次元配列の場合, サイズは N1*n2*n3*...*NM となります. 
	 * </span>
	 * </p>
	 * 
	 * @return
	 *     <span class="lang-en">The size (lenth) of data as an 1-dimensional array</span>
	 *     <span class="lang-ja">1 次元配列としてのデータのサイズ（長さ）</span>
	 */
	public abstract int getArraySize();


	/**
	 * <p>
	 * <span class="lang-en">Returns the number of dimensions (array-rank) of array data</span>
	 * <span class="lang-ja">配列データの次元数（配列ランク値）を返します</span>
	 * .
	 * </p>
	 *
	 * <p>
	 * <span class="lang-en">
	 * When data is a scalar value, the array-rank always is 0.
	 * When data is an 1-dimensional array, the array-rank always is 1.
	 * When data is an N-dimensional array, the array-rank is N.
	 * </span>
	 * <span class="lang-ja">
	 * データがスカラ値の場合, 配列ランク値は常に 0 となります. 
	 * データが 1 次元配列の場合, 配列ランク値は常に 1 となります. 
	 * データが N 次元配列の場合, 配列ランク値は N です. 
	 * </span>
	 * </p>
	 * 
	 * @return
	 *     <span class="lang-en">The number of dimensions (array-rank) of multi-dimensional array data</span>
	 *     <span class="lang-ja">多次元配列データの次元数（配列ランク値）</span>
	 */
	public abstract int getArrayRank();

}
