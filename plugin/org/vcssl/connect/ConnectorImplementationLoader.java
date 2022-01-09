/*
 * ==================================================
 * Connector Implementation Loader
 * --------------------------------------------------
 * This file is released under CC0.
 * Written in 2019-2022 by RINEARN (Fumihiro Matsui)
 * ==================================================
 */

package org.vcssl.connect;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * <span class="lang-en">
 * A class for loading plug-in objects, implementing plug-in connector interfaces provided in this package
 * </span>
 * <span class="lang-ja">
 * このパッケージで提供される各種のプラグイン接続インターフェースを実装した, プラグインオブジェクトをロードするためのクラスです
 * </span>
 * .
 */
public class ConnectorImplementationLoader {

	/**
	 * <span class="lang-en">The default path from which plug-ins will be loaded</span>
	 * <span class="lang-ja">プラグインが読み込まれる場所の, デフォルトのパスです</span>
	 * .
	 */
	private static final String[] DEFAULT_LOADING_PATHS = { "." };

	/**
	 * <span class="lang-en">The name of the field declaring the type ID of the interface</span>
	 * <span class="lang-ja">インターフェースの形式IDが宣言されているフィールドの名称です</span>
	 * .
	 */
	private static final String INTERFACE_TYPE_ID_FIELD_NAME = "INTERFACE_TYPE_ID";

	/**
	 * <span class="lang-en">The name of the field declaring the generation of the interface</span>
	 * <span class="lang-ja">インターフェースの世代が宣言されているフィールドの名称です</span>
	 * .
	 */
	private static final String INTERFACE_GENERATION_FIELD_NAME = "INTERFACE_GENERATION";

	/**
	 * <span class="lang-en">The ClassLoader for loading classes of plug-ins</span>
	 * <span class="lang-ja">プラグインのクラスをロードする際に使用するクラスローダです</span>
	 * .
	 */
	private ClassLoader classLoader = null;

	/**
	 * <span class="lang-en">Stores whether filter out loaded plug-ins in which no interface type name is declared</span>
	 * <span class="lang-ja">読み込まれたプラグインから、形式名が宣言されていないものを除外するかどうかを格納します</span>
	 * .
	 */
	private boolean interfaceFilterEnabled = false;


	/**
	 * <span class="lang-en">Creates an new loader</span>
	 * <span class="lang-ja">新しいローダーを生成します</span>
	 * .
	 */
	public ConnectorImplementationLoader() {
		this.classLoader = null;
	}


	/**
	 * <span class="lang-en">Creates an new loader using the specified ClassLoader</span>
	 * <span class="lang-ja">指定されたクラスローダーを用いる, 新しいローダーを生成します</span>
	 * .
	 * @param classLoader
	 *     <span class="lang-en">The ClassLoader for loading classes of plug-ins</span>
	 *     <span class="lang-ja">プラグインのクラスの読み込みに用いるクラスローダ</span>
	 */
	public ConnectorImplementationLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}


	/**
	 * <span class="lang-en">Initializes the ClassLoader by default procedures</span>
	 * <span class="lang-ja">デフォルトの手続きでクラスローダを初期化します</span>
	 * .
	 * @param directoryPaths
	 *     <span class="lang-en">An array storing path of directories from which plug-in will be loaded</span>
	 *     <span class="lang-ja">プラグインを読み込むディレクトリのパスを格納した配列</span>
	 * 
	 * @throws MalformedURLException
	 *     <span class="lang-en">Thrown when paths in "directoryPaths" are invalid</span>
	 *     <span class="lang-ja">引数 directoryPaths に指定したパスが無効であった場合にスローされます</span>
	 */
	private void initializeDefaultClassLoader(String[] directoryPaths) throws MalformedURLException {
		int directoryLength = directoryPaths.length;
		URL directoryURLs[] = new URL[directoryLength];
		for (int directoryIndex=0; directoryIndex<directoryLength; directoryIndex++) {
			File directoryFile = new File(directoryPaths[directoryIndex]);
			URL directoryURL = directoryFile.toURI().toURL();
			directoryURLs[directoryIndex] = directoryURL;
		}
		this.classLoader = new URLClassLoader(directoryURLs);
	}


	/**
	 * <span class="lang-en">
	 * Sets whether filter out plug-ins in which no interface type name is declared
	 * </span>
	 * <span class="lang-ja">
	 * インターフェース形式名が宣言されていないプラグインを, 除外するかどうかを指定します
	 * </span>
	 * .
	 * @param enabled
	 *     <span class="lang-en">Specify "true" for filtering out</span>
	 *     <span class="lang-ja">除外したい場合に true を指定します</span>
	 */
	public void setInterfaceFilterEnabled(boolean enabled) {
		this.interfaceFilterEnabled = enabled;
	}


	/**
	 * <span class="lang-en">
	 * Returns whether filter out plug-ins in which no interface type name is declared
	 * </span>
	 * <span class="lang-ja">
	 * インターフェース形式名が宣言されていないプラグインを, 除外するかどうかを返します
	 * </span>
	 * .
	 * @return
	 *     <span class="lang-en">Returns "true" it it will be filtered out</span>
	 *     <span class="lang-ja">除外される場合に true が返されます</span>
	 */
	public boolean isInterfaceFilterEnabled() {
		return this.interfaceFilterEnabled;
	}

	/**
	 * <span class="lang-en">Loads the plug-in class and instantiate it</span>
	 * <span class="lang-ja">指定された名称のプラグインオブジェクトを読み込み, インスタンス化します</span>
	 * .
	 * @param connectorImplementationName
	 *     <span class="lang-en">
	 *     The name of the plug-in class to be loaded, which plug-in connector interfaces provided in this package
	 *     </span>
	 *     <span class="lang-ja">
	 *     このパッケージで提供されるプラグイン接続インターフェースを実装した, 読み込み対称のプラグインクラスの名称
	 *     </span>
	 * 
	 * @return
	 *     <span class="lang-en">An object storing the loaded plug-in and information of implemented the interface</span>
	 *     <span class="lang-ja">読み込まれたプラグインとインターフェース情報を格納するオブジェクト</span>
	 * 
	 * @throws ConnectorException
	 *     <span class="lang-en">Thrown when the loading has failed, or the loaded plug-in has been filtered out</span>
	 *     <span class="lang-ja">読み込みに失敗したか, 読み込まれたプラグインがフィルタによって除外された場合にスローされます</span>
	 */
	public ConnectorImplementationContainer load(String connectorImplementationName)
			throws ConnectorException {

		if (this.classLoader == null) {
			try {
				this.initializeDefaultClassLoader(DEFAULT_LOADING_PATHS);
			} catch (MalformedURLException e) {
				throw new ConnectorException(
					"ClassLoader initialization failed.", e
				);
			}
		}

		Class<?> connectorImplClass = null;
		try {
			connectorImplClass = this.classLoader.loadClass(connectorImplementationName);
		} catch (ClassNotFoundException e) {
			throw new ConnectorException(
				"Loading failed: " + connectorImplementationName, e
			);
		}

		Object connectorImplInstance = null;
		try {
			connectorImplInstance = connectorImplClass.getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {

			throw new ConnectorException(
				"Instantiation failed: " + connectorImplementationName, e
			);
		}

		String interfaceTypeId = null;
		String interfaceGeneration = null;
		try {
			Field interfaceTypeIdField = connectorImplClass.getField(INTERFACE_TYPE_ID_FIELD_NAME);
			Field interfaceVersionField = connectorImplClass.getField(INTERFACE_GENERATION_FIELD_NAME);
			interfaceTypeId = interfaceTypeIdField.get(null).toString();
			interfaceGeneration = interfaceVersionField.get(null).toString();

		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {

			if (connectorImplInstance instanceof GeneralProcessConnectorInterface2) {
				interfaceTypeId = "GPCI";
				interfaceGeneration = "2";
			} else if (connectorImplInstance instanceof GeneralProcessConnectorInterface1) {
				interfaceTypeId = "GPCI";
				interfaceGeneration = "1";
			} else {
				if (this.interfaceFilterEnabled) {
					throw new ConnectorException(
						"Invalid implementation (unknown interface): "
						+ connectorImplementationName
						+ " (interface-type-id: " + interfaceTypeId + ", "
						+ "interface-generation: " + interfaceGeneration + ")", e
					);
				}
			}
		}

		ConnectorImplementationContainer container = new ConnectorImplementationContainer(
			connectorImplInstance, interfaceTypeId, interfaceGeneration
		);

		this.checkImplementation(container, connectorImplementationName);

		return container;
	}


	/**
	 * <span class="lang-en">Checks whether the loaded result is valid</span>
	 * <span class="lang-ja">読み込み結果が有効かどうかを検証します</span>
	 * .
	 * @param container
	 *     <span class="lang-en">An object storing the loaded plug-in and information of implemented the interface</span>
	 *     <span class="lang-ja">読み込まれたプラグインとインターフェース情報を格納するオブジェクト</span>
	 * 
	 * @param connectorImplementationName
	 *     <span class="lang-en">The name of the loaded plug-in class</span>
	 *     <span class="lang-ja">読み込まれたプラグインのクラスの名称</span>
	 * 
	 * @throws ConnectorException
	 *     <span class="lang-en">Thrown when the loaded result is invalid</span>
	 *     <span class="lang-ja">読み込み結果が無効であった場合にスローされます</span>
	 */
	private void checkImplementation(
			ConnectorImplementationContainer container, String connectorImplementationName)
					throws ConnectorException {

		String interfaceTypeId = container.getInterfaceTypeId();
		String interfaceGeneration = container.getInterfaceGeneration();
		if (interfaceTypeId == null) {
			if (!this.interfaceFilterEnabled) {
				return;
			}
			throw new ConnectorException(
				"Invalid implementation (null interface-type-id): " + connectorImplementationName
			);
		}
		if (interfaceGeneration == null) {
			throw new ConnectorException(
				"Invalid implementation (null interface-generation): " + connectorImplementationName
			);
		}

		Object implementation = container.getConnectorImplementation();
		String interfaceCode = container.getInterfaceTypeId() + container.getInterfaceGeneration();

		switch (interfaceCode) {

			case "XFCI1" : {
				if ( !(implementation instanceof ExternalFunctionConnectorInterface1) ) {
					throw new ConnectorException(
						"Invalid implementation"
						+ " (should implement org.vcssl.connect.ExternalFunctionConnectorInterface1): "
						+ connectorImplementationName
					);
				}
				break;
			}

			case "XVCI1" : {
				if ( !(implementation instanceof ExternalVariableConnectorInterface1) ) {
					throw new ConnectorException(
						"Invalid implementation"
						+ " (should implement org.vcssl.connect.ExternalVariableConnectorInterface1): "
						+ connectorImplementationName
					);
				}
				break;
			}

			case "GPCI1" : {
				if ( !(implementation instanceof GeneralProcessConnectorInterface1) ) {
					throw new ConnectorException(
						"Invalid implementation"
						+ " (should implement org.vcssl.connect.GeneralProcessConnectorInterface1): "
						+ connectorImplementationName
					);
				}
				break;
			}

			case "GPCI2" : {
				if ( !(implementation instanceof GeneralProcessConnectorInterface2) ) {
					throw new ConnectorException(
						"Invalid implementation"
						+ " (should implement org.vcssl.connect.GeneralProcessConnectorInterface2): "
						+ connectorImplementationName
					);
				}
				break;
			}

			case "GPCI3" : {
				if ( !(implementation instanceof GeneralProcessConnectorInterface3) ) {
					throw new ConnectorException(
						"Invalid implementation"
						+ " (should implement org.vcssl.connect.GeneralProcessConnectorInterface3): "
						+ connectorImplementationName
					);
				}
				break;
			}

			default : {
				if (this.interfaceFilterEnabled) {
					throw new ConnectorException(
						"Invalid implementation (unsupported interface): "
						+ connectorImplementationName
						+ " (interface-type-id: " + interfaceTypeId + ", "
						+ "interface-generation: " + interfaceGeneration + ")"
					);
				}
			}

		} // end of switch

	} // end of method

}
