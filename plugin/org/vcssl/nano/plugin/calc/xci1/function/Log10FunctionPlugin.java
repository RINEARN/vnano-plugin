package org.vcssl.nano.plugin.calc.xci1.function;

public class Log10FunctionPlugin extends Float64VectorizableOperationFunctionPlugin {

	@Override
	public final String getFunctionName() {
		return "log10";
	}

	@Override
	public final void operate(double[] outputData, double[] inputData, int dataLength) {
		for (int i=0; i<dataLength; i++) {
			outputData[i] = Math.log10(inputData[i]);
		}
	}
}
