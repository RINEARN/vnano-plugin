# Vnano Standard Plug-ins

&raquo; [Japanese README](./README_JAPANESE.md)


## Abstract

This repository provides/manages plug-ins to extend build-in functions and variables available in script code written in the [Vnano](https://www.vcssl.org/en-us/vnano/). These plug-ins are available on applications equipped with the Script Engine of the Vnano.


## License

This package is released under [CC0](https://creativecommons.org/publicdomain/zero/1.0/deed).


## Specification Documents

For details and specifications of each plug-in in standard plug-ins, see "VnanoStandardPluginSpec_English.html" in this repository. 
You can refer same contents on the official website of the Vnano: 
[https://www.vcssl.org/en-us/vnano/plugin/](https://www.vcssl.org/en-us/vnano/plugin/).


<a id="how-to-compile-and-use"></a>
## How to Compile and Use

At first, clone this repository:

    cd <working-directory>
    git clone https://github.com/RINEARN/vnano-standard-plugin.git

Then next, compile souce files of plug-ins:

For Microsoft&reg; Windows&reg;:

    cd vnano-standard-plugin
    .\build.bat

For Linux&reg;, etc.:

    cd vnano-standard-plugin
    chmod +x ./build.sh          # if necessary: sudo ...
    ./build.sh

After the compilation will be completed successfully, class files of plug-ins will be generated in "plugin/..." directory. Copy them in the directory specified by the application you are using, 
and specify them in the setting file for loading plug-ins (e.g.: plugin/VnanoPluginList.txt ) of the application.
For details, see the document of the application you are using.


## How to Test

In this repository, Vnano scripts for testing plug-ins are bundled. (They are locating in same folders of source files as XNCI1 plug-ins, e.g.: [plugin/org/vcssl/nano/plugin/math/xnci1/MathElementaryXnci1PluginTest.vnano](./plugin/org/vcssl/nano/plugin/math/xnci1/MathElementaryXnci1PluginTest.vnano))

As a preparation for executing test-scripts and testing compiled plug-ins, build the script engine of the Vnano (see: [the repository of the Vnano](https://github.com/RINEARN/vnano)), and put the built JAR file "Vnano.jar" into the top-level directory of this repository.

Then, you can run test scripts for all compiled plug-ins (see: [How to Compile and Use](#how-to-compile-and-use) section) as follows:

For Microsoft&reg; Windows&reg;:

    cd <working-directory>\vnano-standard-plugin
    .\test.bat

For Linux&reg;, etc.:

    cd <working-directory>/vnano-standard-plugin
    chmod +x ./test.sh          # if necessary: sudo ...
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

## Credits

- Oracle and Java are registered trademarks of Oracle and/or its affiliates. 

- Microsoft Windows is either a registered trademarks or trademarks of Microsoft Corporation in the United States and/or other countries. 

- Linux is a trademark of linus torvalds in the United States and/or other countries. 

- Other names may be either a registered trademarks or trademarks of their respective owners. 


