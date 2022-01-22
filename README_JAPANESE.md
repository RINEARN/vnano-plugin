# Vnano 標準プラグイン

&raquo; [English README](./README.md)


## 概要

このリポジトリは、[Vnano](https://www.vcssl.org/en-us/vnano/) で記述されたスクリプトコード内から利用可能な組み込み関数/変数を拡張するための、プラグインのセットを提供/管理します。
各プラグインは、Vnano のスクリプトエンジンを搭載したアプリケーションで利用できます。


## ライセンス

このパッケージは [CC0](https://creativecommons.org/publicdomain/zero/1.0/deed.ja) で公開されています。


## 仕様書

各プラグインの詳細や仕様については、リポジトリ内の「 VnanoStandardPluginSpec_Japanese.html 」をご参照ください。同仕様書は、Vnanoの公式サイト上でも掲載されています： 
[https://www.vcssl.org/ja-jp/vnano/plugin/](https://www.vcssl.org/ja-jp/vnano/plugin/).


<a id="how-to-compile-and-use"></a>
## コンパイル及び使用方法

まず、このリポジトリをクローンします：

    cd <working-directory>
    git clone https://github.com/RINEARN/vnano-standard-plugin.git

続いて、プラグインのソースコードをコンパイルします：

Microsoft&reg; Windows&reg; を使用している場合は:

    cd vnano-standard-plugin
    .\build.bat

Linux&reg; などの他のOSを使用している場合は:

    cd vnano-standard-plugin
    chmod +x ./build.sh          # 必要に応じて: sudo ...
    ./build.sh

コンパイルが正常に完了すると、プラグインのクラスファイルが「 plugin/... 」フォルダ以下に生成されます。それらを、ご使用のアプリケーションで指定されているフォルダ内にコピーしてください。また、ご使用のアプリケーションの設定ファイル（ 例：plugin/VnanoPluginList.txt ）などから、それらを読み込むように指定してください。詳細については、ご使用のアプリケーションの説明書をご参照ください。


## テスト方法

各プラグインには、テスト用のVnanoスクリプトが付属しています。（XNCI1プラグインのソースコードと同じフォルダ内にあります。例: [plugin/org/vcssl/nano/plugin/math/xnci1/MathElementaryXnci1PluginTest.vnano](./plugin/org/vcssl/nano/plugin/math/xnci1/MathElementaryXnci1PluginTest.vnano)）

プラグインのテストを実行するには、まず準備として、Vnanoのスクリプトエンジンをビルドし（[Vnano のリポジトリ](https://github.com/RINEARN/vnano) 参照）、生成されたJARファイル「 Vnano.jar 」を、このリポジトリ直下のフォルダ内に配置してください。

その後、以下のようにして、全てのコンパイル済みプラグイン（「[コンパイル及び使用方法](#how-to-compile-and-use)」の項目参照）のテストスクリプトを実行できます：

Microsoft&reg; Windows&reg; を使用している場合は:

    cd <working-directory>\vnano-standard-plugin
    .\test.bat

Linux&reg; などの他のOSを使用している場合は:

    cd <working-directory>/vnano-standard-plugin
    chmod +x ./test.sh          # 必要に応じて: sudo ...
    ./test.sh

実行結果:

    ...

    [ MathElementaryXnci1PluginTest.vnano ]
    PI: OK.
    rad(degree): OK.
    deg(radian): OK.
    sin(x): OK.
    cos(x): OK.
    tan(x): OK.
    ...

    [ MathStatisticalXnci1PluginTest.vnano ]
    sum(...): OK.
    mean(...): OK.
    van(...): OK.
    van1(...): OK.
    sdn(...): OK.
    sdn1(...): OK.
    ...

    --------------------------------------------------
    All tests have been completed successfully.
  


---

## 本文中の商標など

- OracleとJavaは、Oracle Corporation 及びその子会社、関連会社の米国及びその他の国における登録商標です。文中の社名、商品名等は各社の商標または登録商標である場合があります。

- Windows は、米国 Microsoft Corporation の米国およびその他の国における登録商標です。

- Linux は、Linus Torvalds 氏の米国およびその他の国における商標または登録商標です。 

- その他、文中に使用されている商標は、その商標を保持する各社の各国における商標または登録商標です。

