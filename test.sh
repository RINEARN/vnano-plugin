#!/bin/sh
set -eu

maxOptLevel=3

echo ""
echo " - Vnano Standard Plug-ins Test -"
echo ""
echo "--------------------------------------------------"
echo ""



echo "[ SystemEnvironmentXnci1PluginTest.vnano (accelerator disabled) ]"
java -jar Vnano.jar --pluginList ./plugin/VnanoPluginList_AllStandards.txt --accelerator false ./plugin/org/vcssl/nano/plugin/system/xnci1/SystemEnvironmentXnci1PluginTest.vnano
echo ""

for i in `seq 0 1 $maxOptLevel`
do
echo "[ SystemEnvironmentXnci1PluginTest.vnano (accelerator enabled, optimization level $i) ]"
java -jar Vnano.jar --pluginList ./plugin/VnanoPluginList_AllStandards.txt --accelerator true --optLevel $i ./plugin/org/vcssl/nano/plugin/system/xnci1/SystemEnvironmentXnci1PluginTest.vnano
echo ""
done



echo "[ SystemDataTypeXnci1PluginTest.vnano (accelerator disabled) ]"
java -jar Vnano.jar --pluginList ./plugin/VnanoPluginList_AllStandards.txt --accelerator false ./plugin/org/vcssl/nano/plugin/system/xnci1/SystemDataTypeXnci1PluginTest.vnano
echo ""

for i in `seq 0 1 $maxOptLevel`
do
echo "[ SystemDataTypeXnci1PluginTest.vnano (accelerator enabled, optimization level $i) ]"
java -jar Vnano.jar --pluginList ./plugin/VnanoPluginList_AllStandards.txt --accelerator true --optLevel $i ./plugin/org/vcssl/nano/plugin/system/xnci1/SystemDataTypeXnci1PluginTest.vnano
echo ""
done



echo "[ MathElementaryXnci1PluginTest.vnano (accelerator disabled) ]"
java -jar Vnano.jar --pluginList ./plugin/VnanoPluginList_AllStandards.txt --accelerator false ./plugin/org/vcssl/nano/plugin/math/xnci1/MathElementaryXnci1PluginTest.vnano
echo ""

for i in `seq 0 1 $maxOptLevel`
do
echo "[ MathElementaryXnci1PluginTest.vnano (accelerator enabled, optimization level $i) ]"
java -jar Vnano.jar --pluginList ./plugin/VnanoPluginList_AllStandards.txt --accelerator true --optLevel $i ./plugin/org/vcssl/nano/plugin/math/xnci1/MathElementaryXnci1PluginTest.vnano
echo ""
done



echo "[ MathStatisticalXnci1PluginTest.vnano (accelerator disabled) ]"
java -jar Vnano.jar --pluginList ./plugin/VnanoPluginList_AllStandards.txt --accelerator false ./plugin/org/vcssl/nano/plugin/math/xnci1/MathStatisticalXnci1PluginTest.vnano
echo ""

for i in `seq 0 1 $maxOptLevel`
do
echo "[ MathStatisticalXnci1PluginTest.vnano (accelerator enabled, optimization level $i) ]"
java -jar Vnano.jar --pluginList ./plugin/VnanoPluginList_AllStandards.txt --accelerator true --optLevel $i ./plugin/org/vcssl/nano/plugin/math/xnci1/MathStatisticalXnci1PluginTest.vnano
echo ""
done



echo "--------------------------------------------------"
echo ""
echo "All tests have been completed successfully."

