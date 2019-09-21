package org.vcssl.nano.plugin.calc.xci1.function;

public class Log10FunctionPlugin extends VectorizableFloat64OperationFunctionPlugin {

	@Override
	public String getFunctionName() {
		return "log10";
	}

	@Override
	public void operate(double[] outputData, double[] inputData, int dataLength) {
		for (int i=0; i<dataLength; i++) {
			outputData[i] = Math.log10(inputData[i]);
		}
	}
}
