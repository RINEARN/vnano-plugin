/*
 * Author:  RINEARN (Fumihiro Matsui), 2020
 * License: CC0
 */

package org.vcssl.nano.plugin.system.xfci1;

import org.vcssl.connect.ConnectorException;
import org.vcssl.connect.ExternalFunctionConnectorInterface1;

// Interface Specification: https://www.vcssl.org/en-us/dev/code/main-jimpl/api/org/vcssl/connect/ExternalFunctionConnectorInterface1.html
// インターフェース仕様書:  https://www.vcssl.org/ja-jp/dev/code/main-jimpl/api/org/vcssl/connect/ExternalFunctionConnectorInterface1.html

public class ExitVoidXfci1Plugin implements ExternalFunctionConnectorInterface1 {

	// 接続時の初期化
	@Override
	public void initializeForConnection(Object engineConnector) throws ConnectorException { }

	// スクリプト実行前の初期化
	@Override
	public void initializeForExecution(Object engineConnector) throws ConnectorException { }

	// スクリプト実行後の終了時処理
	@Override
	public void finalizeForDisconnection(Object engineConnector) throws ConnectorException { }

	// 接続解除時の終了時処理
	@Override
	public void finalizeForTermination(Object engineConnector) throws ConnectorException { }


	// 関数名を返す
	@Override
	public String getFunctionName() {
		return "exit";
	}

	// 引数は取らないので空配列を返す
	@Override
	public Class<?>[] getParameterClasses() {
		return new Class<?>[0];
	}

	// 引数名は定義されていないので false を返す
	@Override
	public boolean hasParameterNames() {
		return false;
	}

	// 引数名を返すが、引数が無いので空配列を返す
	@Override
	public String[] getParameterNames() {
		return new String[0];
	}

	// 任意型の引数を取る場合に true を返すが、引数が無いので空配列を返す
	@Override
	public boolean[] getParameterClassArbitrarinesses() {
		return new boolean[0];
	}

	// 任意次元の引数を取る場合に true を返すが、引数が無いので空配列を返す
	@Override
	public boolean[] getParameterRankArbitrarinesses() {
		return new boolean[0];
	}

	// 参照渡しする必要がある場合に true を返すが、引数が無いので空配列を返す
	@Override
	public boolean[] getParameterReferencenesses() {
		return new boolean[0];
	}

	// 引数の中身を書き変えない場合に true を返すが、引数が無いので空配列を返す
	@Override
	public boolean[] getParameterConstantnesses() {
		return new boolean[0];
	}

	// 任意個の引数は取らないので false を返す
	@Override
	public boolean isParameterCountArbitrary() {
		return false;
	}

	// 可変長引数は取らないので false を返す
	@Override
	public boolean hasVariadicParameters() {
		return false;
	}

	// 戻り値は無いので、void 型を返す
	@Override
	public Class<?> getReturnClass(Class<?>[] parameterClasses) {
		return void.class;
	}

	// データ型の自動変換機能は利用するので true を返す
	@Override
	public boolean isDataConversionNecessary() {
		return true;
	}

	// スクリプトから呼ばれた際に実行する処理
	@Override
	public Object invoke(Object[] arguments) throws ConnectorException {
		throw new ConnectorException("___EXIT:0");
	}
}
