@echo off
setlocal

set maxOptLevel=3

echo.
echo - Vnano Standard Plug-ins Test -
echo.
echo --------------------------------------------------
echo.



echo [ SystemEnvironmentXnci1PluginTest.vnano ^(accelerator disabled^) ]
java -jar Vnano.jar --pluginList ./plugin/VnanoPluginList_AllStandards.txt --accelerator false ./plugin/org/vcssl/nano/plugin/system/xnci1/SystemEnvironmentXnci1PluginTest.vnano
if %errorlevel% neq 0 exit /b 1
echo.

for /l %%i in (1, 1, %maxOptLevel%) do (
echo [ SystemEnvironmentXnci1PluginTest.vnano ^(accelerator enabled, optimization leve %%i^) ]
java -jar Vnano.jar --pluginList ./plugin/VnanoPluginList_AllStandards.txt --accelerator true --optLevel %%i ./plugin/org/vcssl/nano/plugin/system/xnci1/SystemEnvironmentXnci1PluginTest.vnano
if %errorlevel% neq 0 exit /b 1
echo.
)



echo [ SystemDataTypeXnci1PluginTest.vnano ^(accelerator disabled^) ]
java -jar Vnano.jar --pluginList ./plugin/VnanoPluginList_AllStandards.txt --accelerator false ./plugin/org/vcssl/nano/plugin/system/xnci1/SystemDataTypeXnci1PluginTest.vnano
if %errorlevel% neq 0 exit /b 1
echo.

for /l %%i in (1, 1, %maxOptLevel%) do (
echo [ SystemDataTypeXnci1PluginTest.vnano ^(accelerator enabled, optimization level %%i^) ]
java -jar Vnano.jar --pluginList ./plugin/VnanoPluginList_AllStandards.txt --accelerator true --optLevel %%i ./plugin/org/vcssl/nano/plugin/system/xnci1/SystemDataTypeXnci1PluginTest.vnano
if %errorlevel% neq 0 exit /b 1
echo.
)



echo [ MathElementaryXnci1PluginTest.vnano ^(accelerator disabled^) ]
java -jar Vnano.jar --pluginList ./plugin/VnanoPluginList_AllStandards.txt --accelerator false ./plugin/org/vcssl/nano/plugin/math/xnci1/MathElementaryXnci1PluginTest.vnano
if %errorlevel% neq 0 exit /b 1
echo.

for /l %%i in (1, 1, %maxOptLevel%) do (
echo [ MathElementaryXnci1PluginTest.vnano ^(accelerator enabled, optimization level %%i^) ]
java -jar Vnano.jar --pluginList ./plugin/VnanoPluginList_AllStandards.txt --accelerator true --optLevel %%i ./plugin/org/vcssl/nano/plugin/math/xnci1/MathElementaryXnci1PluginTest.vnano
if %errorlevel% neq 0 exit /b 1
echo.
)



echo [ MathStatisticalXnci1PluginTest.vnano ^(accelerator disabled^) ]
java -jar Vnano.jar --pluginList ./plugin/VnanoPluginList_AllStandards.txt --accelerator false ./plugin/org/vcssl/nano/plugin/math/xnci1/MathStatisticalXnci1PluginTest.vnano 
if %errorlevel% neq 0 exit /b 1
echo.

for /l %%i in (1, 1, %maxOptLevel%) do (
echo [ MathStatisticalXnci1PluginTest.vnano ^(accelerator enabled, optimization level %%i^) ]
java -jar Vnano.jar --pluginList ./plugin/VnanoPluginList_AllStandards.txt --accelerator true --optLevel %%i ./plugin/org/vcssl/nano/plugin/math/xnci1/MathStatisticalXnci1PluginTest.vnano 
if %errorlevel% neq 0 exit /b 1
echo.
)



echo --------------------------------------------------
echo.
echo All tests have been completed successfully.
exit /b 0

