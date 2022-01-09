/*
 * ==================================================
 * Connector Implementation Container
 * --------------------------------------------------
 * This file is released under CC0.
 * Written in 2019-2022 by RINEARN (Fumihiro Matsui)
 * ==================================================
 */

package org.vcssl.connect;

/**
 * <span class="lang-en">An object for storing the loaded result of {@link ConnectorImplementationLoader}</span>
 * <span class="lang-ja">{@link ConnectorImplementationLoader} によるロード結果を格納するためのオブジェクトです</span>
 * .
 */
public class ConnectorImplementationContainer {

	/**
	 * <span class="lang-en">Stores the loaded object implementing plug-in connector interfaces</span>
	 * <span class="lang-ja">読み込まれたプラグイン接続インターフェース実装オブジェクトを保持します</span>
	 * .
	 */
	private Object connectorImplementation = null;

	/**
	 * <span class="lang-en">Stores the type ID (abbreviated name) of the interface, implemented by the loaded plug-in</span>
	 * <span class="lang-ja">読み込まれたプラグインが実装している, インターフェースの形式ID（略称）を保持します</span>
	 * .
	 */
	private String interfaceTypeId = null;

	/**
	 * <span class="lang-en">Stores the generation of the interface, implemented by the loaded plug-in</span>
	 * <span class="lang-ja">読み込まれたプラグインが実装している, インターフェースの世代を保持します</span>
	 * .
	 */
	private String interfacaGeneration = null;


	/**
	 * <span class="lang-en">Creates an instance storing specified loaded results</span>
	 * <span class="lang-ja">指定されたロード結果を格納するインスタンスを生成します</span>
	 * .
	 * @param connectorImplementation
	 *     <span class="lang-en">The loaded object implementing plug-in connector interfaces</span>
	 *     <span class="lang-ja">読み込まれたプラグイン接続インターフェース実装オブジェクト</span>
	 * 
	 * @param interfaceTypeId
	 *     <span class="lang-en">The type ID (abbreviated name) of the interface</span>
	 *     <span class="lang-ja">インターフェースの形式ID（略称）</span>
	 * 
	 * @param interfaceGeneration
	 *     <span class="lang-en">The generation of the interface</span>
	 *     <span class="lang-ja">インターフェースの世代</span>
	 */
	public ConnectorImplementationContainer(
			Object connectorImplementation, String interfaceTypeId, String interfaceGeneration) {

		this.connectorImplementation = connectorImplementation;
		this.interfaceTypeId = interfaceTypeId;
		this.interfacaGeneration = interfaceGeneration;
	}


	/**
	 * <span class="lang-en">Returns the loaded object implementing plug-in connector interfaces</span>
	 * <span class="lang-ja">読み込まれたプラグイン接続インターフェース実装オブジェクトを返します</span>
	 * .
	 * @return
	 *     <span class="lang-en">The loaded object implementing plug-in connector interfaces</span>
	 *     <span class="lang-ja">読み込まれたプラグイン接続インターフェース実装オブジェクト</span>
	 */
	public Object getConnectorImplementation() {
		return this.connectorImplementation;
	}


	/**
	 * <span class="lang-en">Returns the type ID (abbreviated name) of the interface, implemented by the loaded plug-in</span>
	 * <span class="lang-ja">読み込まれたプラグインが実装している, インターフェースの形式ID（略称）を返します</span>
	 * .
	 * @return
	 *     <span class="lang-en">The type ID (abbreviated name) of the interface</span>
	 *     <span class="lang-ja">インターフェースの形式ID（略称）</span>
	 */
	public String getInterfaceTypeId() {
		return this.interfaceTypeId;
	}


	/**
	 * <span class="lang-en">Returns the generation of the interface, implemented by the loaded plug-in</span>
	 * <span class="lang-ja">読み込まれたプラグインが実装している, インターフェースの世代を返します</span>
	 * .
	 * @return
	 *     <span class="lang-en">The generation of the interface</span>
	 *     <span class="lang-ja">インターフェースの世代</span>
	 */
	public String getInterfaceGeneration() {
		return this.interfacaGeneration;
	}
}
