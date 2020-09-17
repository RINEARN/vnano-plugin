/*
 * Author:  RINEARN (Fumihiro Matsui), 2020
 * License: CC0
 */

package org.vcssl.nano.plugin.security.paci1;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JOptionPane;

import org.vcssl.connect.ConnectorException;
import org.vcssl.connect.ConnectorPermissionName;
import org.vcssl.connect.ConnectorPermissionValue;
import org.vcssl.connect.EngineConnectorInterface1;
import org.vcssl.connect.PermissionAuthorizerConnectorInterface1;

//Interface Specification: https://www.vcssl.org/en-us/dev/code/main-jimpl/api/org/vcssl/connect/PermissionAuthorizerConnectorInterface1.html
//インターフェース仕様書:  https://www.vcssl.org/ja-jp/dev/code/main-jimpl/api/org/vcssl/connect/PermissionAuthorizerConnectorInterface1.html

public class SecurityPermissionAuthorizerPaci1Plugin implements PermissionAuthorizerConnectorInterface1 {

	/**
	 * setPermissionMap で指定されたパーミッション内容を保持します。
	 */
	private Map<String, String> specifiedPermissionMap;

	/**
	 * スクリプト実行中に、ユーザーの返答などに応じて編集される、一時的なパーミッション内容を保持します。
	 * 例えば値が「ASK」のパーミッションは、ユーザーに許可/拒否の選択肢が提示されますが、
	 * ユーザーがそれを常に許可するように（再度尋ねないように）指示した場合、「ALLOW」に置き換わります。
	 * 編集された内容は、次回のスクリプトの実行時には引き継がれず、実行毎に specifiedPermissionMap と同じ内容で再初期化されます。
	 */
	private Map<String, String> temporaryPermissionMap;


	/**
	 * 標準入力からの入力内容を読むスキャナです。
	 */
	private Scanner standardInputScanner;


	/**
	 * スクリプトエンジンのUI_MODEオプション値が、"GUI" に指定されていた場合はtrue、それ以外("CUI")の場合は false を保持します。
	 */
	private boolean isGuiMode = true;


	/**
	 * スクリプトエンジンのロケール設定が日本語かどうかを保持します（エラーメッセージの表示言語切り替え用）。
	 */
	private boolean isJapanese;


	private static final Map<String, String> PERMISSION_JAJP_NAME_MAP;
	static {
		PERMISSION_JAJP_NAME_MAP = new HashMap<String, String>();
		PERMISSION_JAJP_NAME_MAP.put(ConnectorPermissionName.FILE_CREATE, "ファイルの新規作成");
		PERMISSION_JAJP_NAME_MAP.put(ConnectorPermissionName.FILE_WRITE, "ファイルの書き込み");
		PERMISSION_JAJP_NAME_MAP.put(ConnectorPermissionName.FILE_READ, "ファイルの読み込み");
		PERMISSION_JAJP_NAME_MAP.put(ConnectorPermissionName.FILE_OVERWRITE, "ファイルの上書き");
		PERMISSION_JAJP_NAME_MAP.put(ConnectorPermissionName.FILE_DELETE, "ファイルの削除");
		PERMISSION_JAJP_NAME_MAP.put(ConnectorPermissionName.FILE_INFORMATION_CHANGE, "ファイルの情報変更");
		PERMISSION_JAJP_NAME_MAP.put(ConnectorPermissionName.DIRECTORY_CREATE, "フォルダの新規作成");
		PERMISSION_JAJP_NAME_MAP.put(ConnectorPermissionName.DIRECTORY_LIST, "フォルダ内の一覧取得");
		PERMISSION_JAJP_NAME_MAP.put(ConnectorPermissionName.DIRECTORY_DELETE, "フォルダの削除");
	}

	private static final Map<String, String> PERMISSION_ENUS_NAME_MAP;
	static {
		PERMISSION_ENUS_NAME_MAP = new HashMap<String, String>();
		PERMISSION_ENUS_NAME_MAP.put(ConnectorPermissionName.FILE_CREATE, "Creating a File");
		PERMISSION_ENUS_NAME_MAP.put(ConnectorPermissionName.FILE_WRITE, "Writing to a File");
		PERMISSION_ENUS_NAME_MAP.put(ConnectorPermissionName.FILE_READ, "Reading from a File");
		PERMISSION_ENUS_NAME_MAP.put(ConnectorPermissionName.FILE_OVERWRITE, "Overwriting to a File");
		PERMISSION_ENUS_NAME_MAP.put(ConnectorPermissionName.FILE_DELETE, "Deleting a File");
		PERMISSION_ENUS_NAME_MAP.put(ConnectorPermissionName.FILE_INFORMATION_CHANGE, "Changing of Information of a File");
		PERMISSION_JAJP_NAME_MAP.put(ConnectorPermissionName.DIRECTORY_CREATE, "Creating a directory");
		PERMISSION_JAJP_NAME_MAP.put(ConnectorPermissionName.DIRECTORY_LIST, "Listing-up Files in a Directory");
		PERMISSION_JAJP_NAME_MAP.put(ConnectorPermissionName.DIRECTORY_DELETE, "Deliting a directory");
	}


	/**
	 * パーミッション項目の名前と値を格納するマップ（パーミッションマップ）によって, 各パーミッションの値を設定します。
	 *
	 * @param permissionMap パーミッション項目の名前と値を格納するマップ（パーミッションマップ）
	 */
	@Override
	public void setPermissionMap(Map<String, String> permissionMap) throws ConnectorException {
		this.specifiedPermissionMap = permissionMap;
	}


	/**
	 * パーミッション項目の名前と値を格納するマップ（パーミッションマップ）の形で, 各パーミッションの値を取得します。
	 *
	 * @return permissionMap パーミッション項目の名前と値を格納するマップ（パーミッションマップ）
	 */
	@Override
	public Map<String, String> getPermissionMap() throws ConnectorException {
		return this.specifiedPermissionMap;
	}


	/**
	 * 処理系への接続時に必要な初期化処理を行います。
	 *
	 * 引数には、スクリプトエンジンに依存するやり取りを行うためのオブジェクトが渡されます。
	 * このオブジェクトは、恐らく {@link EngineConnectorInterface1 EngineConnectorInterface1}
	 * もしくはその後継の、抽象化されたインターフェースでラップされた形で渡されます。
	 *
	 * @param engineConnector エンジンに依存するやり取りを行うためのオブジェクト
	 */
	@Override
	public void initializeForConnection(Object engineConnector) throws ConnectorException {

		// 処理系の情報を取得するコネクタ（処理系依存）の互換性を検査
		if (!(engineConnector instanceof EngineConnectorInterface1)) {
			throw new ConnectorException(
				"The type of the engine connector \"" +
				engineConnector.getClass().getCanonicalName() +
				"\" is not supported by this plug-in."
			);
		}
		EngineConnectorInterface1 eci1Connector = (EngineConnectorInterface1)engineConnector;

		// 処理系のUI設定がGUIかどうかを取得
		if (eci1Connector.hasOptionValue("UI_MODE")) {
			this.isGuiMode = ( (String)eci1Connector.getOptionValue("UI_MODE") ).equals("GUI");
		}

		// メッセージのロケール設定を取得し、日本語かどうかを調べる
		Locale locale = Locale.getDefault();
		if (eci1Connector.hasOptionValue("LOCALE")) {
			locale = (Locale)eci1Connector.getOptionValue("LOCALE");
		}
		this.isJapanese = ( locale.getLanguage()!=null && locale.getLanguage().equals("ja") )
				|| ( locale.getCountry()!=null && locale.getCountry().equals("JP") );
	}


	/**
	 * 処理系からの接続解除時に必要な終了時処理を行います。
	 *
	 * @param engineConnector エンジンに依存するやり取りを行うためのオブジェクト
	 */
	@Override
	public void finalizeForDisconnection(Object engineConnector) throws ConnectorException {
		this.specifiedPermissionMap = null;
		this.temporaryPermissionMap = null;

		// close すると標準入力も close してしまう（標準入力は再度開けない）
		//this.standardInputScanner.close();
		this.standardInputScanner = null;
	}


	/**
	 * スクリプト実行毎の初期化処理を行います。
	 *
	 * @param engineConnector エンジンに依存するやり取りを行うためのオブジェクト
	 */
	@Override
	public void initializeForExecution(Object engineConnector) throws ConnectorException {
		// temporaryPermissionMap を、specifiedPermissionMap と同内容で初期化/再初期化する
		this.temporaryPermissionMap = new HashMap<String, String>();
		if (this.specifiedPermissionMap != null) {
			Set<Map.Entry<String, String>> permissionEntrySet = this.specifiedPermissionMap.entrySet();
			for (Map.Entry<String, String> permissionEntry: permissionEntrySet) {
				this.temporaryPermissionMap.put(permissionEntry.getKey(), permissionEntry.getValue());
			}
		}
	}


	/**
	 * スクリプト実行毎の終了時処理を行います。
	 *
	 * @param engineConnector エンジンに依存するやり取りを行うためのオブジェクト
	 */
	@Override
	public void finalizeForTermination(Object engineConnector) throws ConnectorException {
		this.temporaryPermissionMap = null;
	}


	/**
	 * GUI上で、ユーザーにYES/NO（または はい/いいえ）の選択肢を提示します。
	 *
	 * @param confirmMessage ユーザーに提示するメッセージ
	 * @return YESが選択された場合に true
	 */
	private boolean confirmOnGui(String confirmMessage) {
		// ユーザーによる選択
		int userDecision = JOptionPane.showConfirmDialog(null, confirmMessage, "!", JOptionPane.YES_NO_OPTION);

		// YES が選択されていれば true を返す
		return userDecision == JOptionPane.YES_OPTION;
	}


	/**
	 * CUI上で、ユーザーにYES/NO（または はい/いいえ）の選択肢を提示します。
	 *
	 * @param confirmMessage ユーザーに提示するメッセージ
	 * @return YESが選択された場合に true
	 */
	private boolean confirmOnCui(String confirmMessage) {

		// メッセージを提示
		System.out.println(confirmMessage);
		System.out.print("yes(y)/no(n): ");

		// ユーザーによる入力
		if (this.standardInputScanner == null) {
			this.standardInputScanner = new Scanner(System.in);
		}
		String userDecision = this.standardInputScanner.nextLine().toLowerCase();

		// ユーザーによる入力結果が無効だった場合に、3回だけ再入力を促す（初回入力とあわせて合計4回の入力機会）
		int retryCount = 3;
		boolean inputIsValid = userDecision.equals("y") || userDecision.equals("n") || userDecision.equals("yes") || userDecision.equals("no");
		while (!inputIsValid && 0<=retryCount) {
			if (retryCount == 0) {
				if (this.isJapanese) {
					System.out.println("入力エラーです。パーミッション要求は拒否されます。");
				} else {
					System.out.println("Error. The permission request will be declined.");
				}
				return false;
			}
			if (this.isJapanese) {
				System.out.print("入力エラーです。許可する場合は「yes」か「y」、拒否する場合は「no」か「n」を入力してください: ");
			} else {
				System.out.print("Error. Input \"yes\" or \"y\" to allow, \"no\" or \"n\" to deny: ");
			}
			userDecision = this.standardInputScanner.nextLine().toLowerCase();
			inputIsValid = userDecision.equals("y") || userDecision.equals("n") || userDecision.equals("yes") || userDecision.equals("no");
			retryCount--;
		}

		// y か yes が選択されていれば true を返す
		return userDecision.equals("y") || userDecision.equals("yes");
	}


	/**
	 * パーミッション要求に対して、許可するかどうかをユーザーに尋ね、結果を返します。
	 * また、ユーザーが要求を許可した上で、次回以降の返答を省略するよう指示した場合には、
	 * それ以降はユーザーへの再問合せが発生しないように、
	 * temporaryPermissionMap フィールド内の該当パーミッションの値を「ALLOW」に書き変えます。
	 *
	 * @param permissionName パーミッションの名称
	 * @param requester パーミッションの要求元プラグイン
	 * @param metaInformation ユーザーに通知するメッセージ内等で用いられるメタ情報
	 * @return 要求が許可された場合に true
	 */
	private boolean askPermissionToUser(String permissionName, Object requester, Object metaInformation) {

		// パーミッション名を、メッセージ内でわかりやすい表記に変更（「FILE_WRITE」->「ファイルの書き込み」など）
		String permissionNameInMessage = PERMISSION_JAJP_NAME_MAP.containsKey(permissionName) ?
			PERMISSION_JAJP_NAME_MAP.get(permissionName) :
			PERMISSION_ENUS_NAME_MAP.get(permissionName);

		// パーミッション要求元プラグインをメッセージ内で通知するため、そのクラスパスを取得
		String requesterName = requester.getClass().getCanonicalName();

		// ユーザーに提示するメッセージを用意
		String confirmMessage = this.isJapanese ?
			"\n現在実行中の処理が、「 " + permissionNameInMessage + " 」の許可を求めています。許可しますか ?\n" + "( 要求元プラグイン： " + requesterName + " )\n" :
			"\nThe permission for \"" + permissionNameInMessage + "\" has been requested. Do you allow it?\n" + "( Requesting Plug-in: " + requesterName + " )\n" ;

		// メタ情報がString型の非配列の場合は、操作対象（読み書きするファイルのパスなど）が記載されたものなので、メッセージに追記
		if (metaInformation instanceof String) {
			confirmMessage += this.isJapanese ?
				"\n対象： " + (String)metaInformation + "\n":
				"\nTarget: " + (String)metaInformation + "\n";
		}

		// メッセージを提示して、許可するかどうかユーザーに尋ねる
		boolean allowed = this.isGuiMode ?
			this.confirmOnGui(confirmMessage) :
			this.confirmOnCui(confirmMessage) ;

		// 許可された場合は、同種の処理をスクリプト完了までの間自動的に許可したいか聞いて控えた上で return する
		if (allowed) {
			confirmMessage = this.isJapanese ?
				"\n現在実行中の処理の間、同じ種類の要求（ " + permissionNameInMessage + " ）を自動的に許可しますか ?\n" :
				"\nDo you want to allow the same type request ( " + permissionNameInMessage + " ) automatically during the current processing ?\n" ;

			boolean shouldAllowAutomatically = this.isGuiMode ?
				this.confirmOnGui(confirmMessage) :
				this.confirmOnCui(confirmMessage) ;

			// 自動許可がOKされた場合は、実行時用パーミッションマップの値を ALLOW に変更（実行毎にリセットされる）
			if (shouldAllowAutomatically) {
				this.temporaryPermissionMap.put(permissionName, ConnectorPermissionValue.ALLOW);
			}
		}
		return allowed;
	}


	/**
	 * 指定された名称のパーミッションを要求します。
	 *
	 * @param permissionName パーミッションの名称
	 * @param requester パーミッションの要求元プラグイン
	 * @param metaInformation ユーザーに通知するメッセージ内等で用いられるメタ情報
	 * @throws 要求したパーミッションが却下された場合にスローされます。
	 */
	@Override
	public void requestPermission(String permissionName, Object requester, Object metaInformation)
			throws ConnectorException {

		// 以下、指定されたパーミッション名に対応した設定値を取得し、この変数に控える
		String permissionValue = null;

		// 指定されたパーミッション名がマップに登録されている場合は、その設定値を用いる
		if (this.temporaryPermissionMap.containsKey(permissionName)) {
			permissionValue = this.temporaryPermissionMap.get(permissionName);

		// 上記以外で、メタパーミッション名 "ALL" がマップに登録されている場合は、その設定値を用いる
		// （分岐順序に注意: 上の分岐のように、指定パーミッション名が明示的に登録されている場合は、そちらを優先すべき）
		} else if (this.temporaryPermissionMap.containsKey(ConnectorPermissionName.ALL)) {
			permissionValue = this.temporaryPermissionMap.get(ConnectorPermissionName.ALL);

		// 指定パーミッション名も "ALL" もどちらも登録されていない場合はエラー
		} else {
			String errorMessage = this.isJapanese ?
				"パーミッション「 " + permissionName + " 」が要求されましたが、このパーミッションは現在の設定では使用できないか、このシステムではサポートされていません。" :
				"The permission for \"" + permissionName + "\" has been requested, but it is not available on the current settings, or it is unsupported on this script engine" ;
			throw new ConnectorException(errorMessage);
		}


		// 以下、上で取得したパーミッション設定値に応じて、許可するか拒否するかを判断する

		// "ALLOW" の場合は常に許可
		if (permissionValue.equals(ConnectorPermissionValue.ALLOW)) {
			return;

		// "DENY" の場合は常に拒否
		} else if (permissionValue.equals(ConnectorPermissionValue.DENY)) {

			String errorMessage = this.isJapanese ?
				"パーミッション「 " + permissionName + " 」が要求されましたが、設定またはユーザーの選択によって拒否されました。" :
				"The permission for \"" + permissionName + "\" has been requested, but it has been denied by settings or the user's decision" ;
			throw new ConnectorException(errorMessage);

		// "ASK" の場合はユーザーに尋ねる
		} else if (permissionValue.equals(ConnectorPermissionValue.ASK)) {

			// ユーザーに尋ねて結果を取得
			// (次回以降の返答を省略するか尋ねたり、その結果 temporaryPermissionMap を更新したり、その後の自動判断なども下記メソッド内に実装 )
			boolean allowedByUser = this.askPermissionToUser(permissionName, requester, metaInformation);

			// 許可された場合はそのまま終了
			if (allowedByUser) {
				return;

			// 拒否された場合はエラー
			} else {

				String errorMessage = this.isJapanese ?
					"パーミッション「 " + permissionName + " 」が要求されましたが、設定またはユーザーの選択によって拒否されました。" :
					"The permission for \"" + permissionName + "\" has been requested, but it has been denied by settings or the user's decision" ;
				throw new ConnectorException(errorMessage);
			}

		// それ以外はこのプラグインではサポートしていないパーミッション値
		} else {

			String errorMessage = this.isJapanese ?
				"パーミッション「 " + permissionName + " 」が要求されましたが、このパーミッションの現在の設定値「 " + permissionValue + " 」は、このシステムではサポートされていません。" :
				"The permission for \"" + permissionName + "\" has been requested, but its value \"" + permissionValue + "\" on the current settings is unsupported on this system" ;
			throw new ConnectorException(errorMessage);
		}
	}
}
