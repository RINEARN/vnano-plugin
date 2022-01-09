/*
 * ==================================================
 * Method to XFCI Plug-in Adapter
 * --------------------------------------------------
 * This file is released under CC0.
 * Written in 2017-2022 by RINEARN (Fumihiro Matsui)
 * ==================================================
 */

package org.vcssl.connect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;


/**
 * <span class="lang-en">
 * An adapter class converting a host-language-side method to a 
 * {@link org.vcssl.connect.ExternalFunctionConnectorInterface1 XFCI 1} plug-in, 
 * to call it from scripts
 * </span>
 * <span class="lang-ja">
 * ホスト言語側のメソッドを, {@link org.vcssl.connect.ExternalFunctionConnectorInterface1 XFCI 1}
 * 形式の外部関数プラグイン仕様に変換し, スクリプト内から呼び出すためのアダプタです
 * </span>
 * .
 */
public class MethodToXfci1Adapter implements ExternalFunctionConnectorInterface1 {

	/**
	 * <span class="lang-en">The method to be called from scripts</span>
	 * <span class="lang-ja">スクリプト内から呼び出したいメソッドです</span>
	 * .
	 */
	private Method method = null;

	/**
	 * <span class="lang-en">The object instance to which the method belongs to</span>
	 * <span class="lang-ja">メソッドが属するオブジェクトのインスタンスです</span>
	 * .
	 */
	private Object objectInstance = null;


	/**
	 * <span class="lang-en">Creates a new adapter to access to a non-static method</span>
	 * <span class="lang-ja">非 static なメソッドにアクセスする, 新しいアダプタを生成します</span>
	 * .
	 * @param method
	 *     <span class="lang-en">The non-static method to be accessed in scripts</span>
	 *     <span class="lang-ja">スクリプト内でアクセスしたい非 static メソッド</span>
	 * 
	 * @param objectInstance
	 *     <span class="lang-en">The object instance to which the method belongs to</span>
	 *     <span class="lang-ja">メソッドが属するオブジェクトのインスタンス</span>
	 */
	public MethodToXfci1Adapter(Method method, Object objectInstance) {
		this.method = method;
		this.objectInstance = objectInstance;
	}


	/**
	 * <span class="lang-en">Creates a new adapter to access to a static method</span>
	 * <span class="lang-ja">static なメソッドにアクセスする, 新しいアダプタを生成します</span>
	 * .
	 * @param method
	 *     <span class="lang-en">The static method to be accessed in scripts</span>
	 *     <span class="lang-ja">スクリプト内でアクセスしたい static メソッド</span>
	 */
	public MethodToXfci1Adapter(Method method) {
		this.method = method;
		this.objectInstance = null;
	}


	@Override
	public String getFunctionName() {
		return this.method.getName();
	}

	@Override
	public boolean hasParameterNames() {
		return false;
	}

	@Override
	public String[] getParameterNames() {
		return null;
	}

	@Override
	public Class<?>[] getParameterClasses() {
		return this.method.getParameterTypes();
	}

	@Override
	public Class<?>[] getParameterUnconvertedClasses() {
		return null;
	}

	@Override
	public boolean[] getParameterDataTypeArbitrarinesses() {
		int numParameters = this.method.getParameterCount();
		boolean[] result = new boolean[numParameters];
		Arrays.fill(result, false);
		return result;
	}

	@Override
	public boolean[] getParameterArrayRankArbitrarinesses() {
		int numParameters = this.method.getParameterCount();
		boolean[] result = new boolean[numParameters];
		Arrays.fill(result, false);
		return result;
	}

	@Override
	public boolean[] getParameterReferencenesses() {
		int numParameters = this.method.getParameterCount();
		boolean[] result = new boolean[numParameters];
		Arrays.fill(result, false);
		return result;
	}

	@Override
	public boolean[] getParameterConstantnesses() {
		int numParameters = this.method.getParameterCount();
		boolean[] result = new boolean[numParameters];
		Arrays.fill(result, false);
		return result;
	}

	@Override
	public boolean isParameterCountArbitrary() {
		return false;
	}

	@Override
	public boolean hasVariadicParameters() {
		return this.method.isVarArgs();
	}

	@Override
	public Class<?> getReturnClass(Class<?>[] parameterClasses) {
		return this.method.getReturnType();
	}

	@Override
	public Class<?> getReturnUnconvertedClass(Class<?>[] parameterClasses) {
		return null;
	}

	@Override
	public boolean isReturnDataTypeArbitrary() {
		return false;
	}

	@Override
	public boolean isReturnArrayRankArbitrary() {
		return false;
	}

	@Override
	public boolean isDataConversionNecessary() {
		return true;
	}

	@Override
	public Object invoke(Object[] arguments) throws ConnectorException {
		try {
			return this.method.invoke(objectInstance, arguments);
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ConnectorException(
					objectInstance.getClass().getCanonicalName() + " class has no method named \"" + this.method.getName()
					+ "\" with expected parameters.",
					illegalArgumentException
			);
		} catch (IllegalAccessException illegalAccessException) {
			throw new ConnectorException(
					"The method \"" + this.method.getName() + "\" of " + objectInstance.getClass().getCanonicalName()
					+ " class is not accessable (probably it is private or protected).",
					illegalAccessException
			);
		} catch (InvocationTargetException invocationTargetException) {
			throw new ConnectorException(invocationTargetException);
		}
	}

	@Override
	public Class<?> getEngineConnectorClass() {
		return EngineConnectorInterface1.class;
	}

	@Override
	public void initializeForConnection(Object engineConnector) throws ConnectorException {
	}

	@Override
	public void finalizeForDisconnection(Object engineConnector) throws ConnectorException {
	}

	@Override
	public void initializeForExecution(Object engineConnector) throws ConnectorException {
	}

	@Override
	public void finalizeForTermination(Object engineConnector) throws ConnectorException {
	}
}
