#!/bin/sh
set -eu
echo ""
echo " - Vnano Standard Plug-ins Test -"
echo ""
echo "--------------------------------------------------"
echo ""


echo "[ MathElementaryXnci1PluginTest.vnano (accelerator disabled) ]"
java -jar Vnano.jar --pluginList ./plugin/VnanoPluginList_AllStandards.txt --accelerator false ./plugin/org/vcssl/nano/plugin/math/xnci1/MathElementaryXnci1PluginTest.vnano

echo ""

echo "[ MathElementaryXnci1PluginTest.vnano (accelerator enabled) ]"
java -jar Vnano.jar --pluginList ./plugin/VnanoPluginList_AllStandards.txt --accelerator true ./plugin/org/vcssl/nano/plugin/math/xnci1/MathElementaryXnci1PluginTest.vnano

echo ""

echo "[ MathStatisticalXnci1PluginTest.vnano (accelerator disabled) ]"
java -jar Vnano.jar --pluginList ./plugin/VnanoPluginList_AllStandards.txt --accelerator false ./plugin/org/vcssl/nano/plugin/math/xnci1/MathStatisticalXnci1PluginTest.vnano

echo ""

echo "[ MathStatisticalXnci1PluginTest.vnano (accelerator enabled) ]"
java -jar Vnano.jar --pluginList ./plugin/VnanoPluginList_AllStandards.txt --accelerator true ./plugin/org/vcssl/nano/plugin/math/xnci1/MathStatisticalXnci1PluginTest.vnano


echo ""
echo "--------------------------------------------------"
echo ""
echo "All tests have been completed successfully."

