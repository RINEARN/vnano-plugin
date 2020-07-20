# Vnano Standard Plug-ins


## Abstract - 概要

This repository provides/manages plug-ins to extend build-in functions and variables available in script code written in the [Vnano](https://www.vcssl.org/en-us/vnano/). These plug-ins are available on applications equipped with the Script Engine of the Vnano.

このリポジトリは、[Vnano](https://www.vcssl.org/en-us/vnano/) で記述されたスクリプトコード内から利用可能な組み込み関数/変数を拡張するための、プラグインのセットを提供/管理します。
各プラグインは、Vnano のスクリプトエンジンを搭載したアプリケーションで利用できます。


#### Current Build/Test Results - 現在のビルド/テスト検証結果

&nbsp;&nbsp;- master branch -
<br />
&nbsp;&nbsp;&nbsp;&nbsp;<img src="https://github.com/RINEARN/vnano-standard-plugin/workflows/Standard%20Build%2FTest%20CI/badge.svg?branch=master" alt="Standard CI Result" /> on JDK 14
&nbsp;&nbsp;&nbsp;&nbsp;<img src="https://github.com/RINEARN/vnano-standard-plugin/workflows/Old-Env%20Build%2FTest%20CI/badge.svg?branch=master" alt="Old-Env CI Result" /> on JDK 8
&nbsp;&nbsp;
<br />
&nbsp;&nbsp;- develop branch -
<br />
&nbsp;&nbsp;&nbsp;&nbsp;<img src="https://github.com/RINEARN/vnano-standard-plugin/workflows/Standard%20Build%2FTest%20CI/badge.svg?branch=develop" alt="Standard CI Result" /> on JDK 14
&nbsp;&nbsp;&nbsp;&nbsp;<img src="https://github.com/RINEARN/vnano-standard-plugin/workflows/Old-Env%20Build%2FTest%20CI/badge.svg?branch=develop" alt="Old-Env CI Result" /> on JDK 8
&nbsp;&nbsp;
<br />
&nbsp;&nbsp;&nbsp;&nbsp;( Green: OK / Red: NG )

<hr />

## License - ライセンス

This package is released under [CC0](https://creativecommons.org/publicdomain/zero/1.0/deed).

このパッケージは [CC0](https://creativecommons.org/publicdomain/zero/1.0/deed.ja) で公開されています。


## Specification Documents - 仕様書

For details and specifications of each plug-in in standard plug-ins, see "VnanoStandardPluginSpec_English.html" in this repository. 
You can refer same contents on the official website of the Vnano: 
[https://www.vcssl.org/en-us/vnano/plugin/](https://www.vcssl.org/en-us/vnano/plugin/).

各プラグインの詳細や仕様については、リポジトリ内の「 VnanoStandardPluginSpec_Japanese.html 」をご参照ください。同仕様書は、Vnanoの公式サイト上でも掲載されています： 
[https://www.vcssl.org/ja-jp/vnano/plugin/](https://www.vcssl.org/ja-jp/vnano/plugin/).


<a id="how-to-compile-and-use"></a>
## How to Compile and Use - コンパイル及び使用方法

At first, clone this repository:

まず、このリポジトリをクローンします：

    cd <working-directory>
    git clone https://github.com/RINEARN/vnano-standard-plugin.git

Then next, compile souce files of plug-ins:

続いて、プラグインのソースコードをコンパイルします：

    cd vnano-standard-plugin
    cd plugin
    javac -encoding UTF-8 @org/vcssl/connect/sourcelist.txt
    javac -encoding UTF-8 @org/vcssl/nano/plugin/sourcelist.txt

After the compilation will be completed successfully, class files of plug-ins will be generated in "plugin/..." directory. Copy them in the directory specified by the application you are using, 
and specify them in the setting file for loading plug-ins (e.g.: plugin/VnanoPluginList.txt ) of the application.
For details, see the document of the application you are using.

コンパイルが正常に完了すると、プラグインのクラスファイルが「 plugin/... 」フォルダ以下に生成されます。それらを、ご使用のアプリケーションで指定されているフォルダ内にコピーしてください。また、ご使用のアプリケーションの設定ファイル（ 例：plugin/VnanoPluginList.txt ）などから、それらを読み込むように指定してください。詳細については、ご使用のアプリケーションの説明書をご参照ください。


## How to Test - テスト方法

As a preparation, build the script engine of the Vnano (see: <a href="https://github.com/RINEARN/vnano">the repository of the Vnano</a>), and put the built JAR file "Vnano.jar" into the top-level directory of this repository.
Then, you can run test scripts for all compiled plug-ins (see: <a href="#how-to-compile-and-use">How to Compile and Use</a> section) as follows:

まず準備として、Vnanoのスクリプトエンジンをビルドし、生成されたJARファイル「 Vnano.jar 」を、このリポジトリ直下のフォルダ内に配置してください。その後、以下のようにして、全てのコンパイル済みプラグイン（「<a href="#how-to-compile-and-use">コンパイル及び使用方法</a>」の項目参照）のテストスクリプトを実行できます：

For Microsoft&reg; Windows&reg;:

    cd <working-directory>\vnano-standard-plugin
    .\test.bat

For Linux&reg;, etc.:

    cd <working-directory>/vnano-standard-plugin
    ./test.sh

Result:

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

## Credits - 本文中の商標など

- Oracle and Java are registered trademarks of Oracle and/or its affiliates. 

- Microsoft Windows is either a registered trademarks or trademarks of Microsoft Corporation in the United States and/or other countries. 

- Linux is a trademark of linus torvalds in the United States and/or other countries. 

- Other names may be either a registered trademarks or trademarks of their respective owners. 

- OracleとJavaは、Oracle Corporation 及びその子会社、関連会社の米国及びその他の国における登録商標です。文中の社名、商品名等は各社の商標または登録商標である場合があります。

- Windows は、米国 Microsoft Corporation の米国およびその他の国における登録商標です。

- Linux は、Linus Torvalds 氏の米国およびその他の国における商標または登録商標です。 

- その他、文中に使用されている商標は、その商標を保持する各社の各国における商標または登録商標です。

