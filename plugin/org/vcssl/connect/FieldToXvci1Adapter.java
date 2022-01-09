/*
 * ==================================================
 * Field to XVCI Plug-in Adapter
 * --------------------------------------------------
 * This file is released under CC0.
 * Written in 2017-2022 by RINEARN (Fumihiro Matsui)
 * ==================================================
 */

package org.vcssl.connect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;


/**
 * <span class="lang-en">
 * An adapter class converting a host-language-side field to a 
 * {@link org.vcssl.connect.ExternalVariableConnectorInterface1 XVCI 1} plug-in, 
 * to access to it in scripts
 * </span>
 * <span class="lang-ja">
 * ホスト言語側のフィールドを, {@link org.vcssl.connect.ExternalVariableConnectorInterface1 XVCI 1}
 * 形式の外部変数プラグイン仕様に変換し, スクリプト内でアクセスするためのアダプタです
 * </span>
 * .
 */
public class FieldToXvci1Adapter implements ExternalVariableConnectorInterface1 {

	/**
	 * <span class="lang-en">The field to be accessed in scripts</span>
	 * <span class="lang-ja">スクリプト内でアクセスしたいフィールドです</span>
	 * .
	 */
	private Field field = null;

	/**
	 * <span class="lang-en">The object instance to which the field belongs to</span>
	 * <span class="lang-ja">フィールドが属するオブジェクトのインスタンスです</span>
	 * .
	 */
	private Object objectInstance = null;


	/**
	 * <span class="lang-en">Creates a new adapter to access to a non-static field</span>
	 * <span class="lang-ja">非 static なフィールドにアクセスする, 新しいアダプタを生成します</span>
	 * .
	 * @param field
	 *     <span class="lang-en">The non-static field to be accessed in scripts</span>
	 *     <span class="lang-ja">スクリプト内でアクセスしたい非 static フィールド</span>
	 * 
	 * @param objectInstance
	 *     <span class="lang-en">The object instance to which the field belongs to</span>
	 *     <span class="lang-ja">フィールドが属するオブジェクトのインスタンス</span>
	 */
	public FieldToXvci1Adapter (Field field, Object objectInstance) {
		this.field = field;
		this.objectInstance = objectInstance;
	}


	/**
	 * <span class="lang-en">Creates a new adapter to access to a static field</span>
	 * <span class="lang-ja">static なフィールドにアクセスする, 新しいアダプタを生成します</span>
	 * .
	 * @param field
	 *     <span class="lang-en">The static field to be accessed in scripts</span>
	 *     <span class="lang-ja">スクリプト内でアクセスしたい static フィールド</span>
	 */
	public FieldToXvci1Adapter (Field field) {
		this.field = field;
		this.objectInstance = null;
	}


	@Override
	public String getVariableName() {
		return this.field.getName();
	}

	@Override
	public Class<?> getDataClass() {
		return this.field.getType();
	}

	@Override
	public Class<?> getDataUnconvertedClass() {
		return null;
	}

	@Override
	public boolean isConstant() {
		return Modifier.isFinal(field.getModifiers());
	}

	@Override
	public boolean isReference() {
		return false;
	}

	@Override
	public boolean isDataTypeArbitrary() {
		return false;
	}

	@Override
	public boolean isArrayRankArbitrary() {
		return false;
	}

	@Override
	public boolean isDataConversionNecessary() {
		return true;
	}

	@Override
	public Object getData() throws ConnectorException {
		try {
			return this.field.get(this.objectInstance);
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ConnectorException(
					objectInstance.getClass().getCanonicalName() + " class has no field named \"" + this.field.getName() + "\"",
					illegalArgumentException
			);
		} catch (IllegalAccessException illegalAccessException) {
			throw new ConnectorException(
					"The field \"" + this.field.getName() + "\" of " + objectInstance.getClass().getCanonicalName()
					+ " class is not accessable (probably it is private or protected).",
					illegalAccessException
			);
		}
	}

	@Override
	public void getData(Object dataContainer) throws ConnectorException {
	}

	@Override
	public void setData(Object data) throws ConnectorException {
		try {
			this.field.set(this.objectInstance, data);
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new ConnectorException(
					objectInstance.getClass().getCanonicalName() + " class has no field named \"" + this.field.getName() + "\"",
					illegalArgumentException
			);
		} catch (IllegalAccessException illegalAccessException) {
			throw new ConnectorException(
					"The field \"" + this.field.getName() + "\" of " + objectInstance.getClass().getCanonicalName()
					+ " class is not accessable (probably it is private or protected).",
					illegalAccessException
			);
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
