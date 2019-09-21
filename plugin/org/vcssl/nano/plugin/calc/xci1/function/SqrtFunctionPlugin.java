package org.vcssl.nano.plugin.calc.xci1.function;

public class SqrtFunctionPlugin extends VectorizableFloat64OperationFunctionPlugin {

	@Override
	public final String getFunctionName() {
		return "sqrt";
	}

	@Override
	public final void operate(double[] outputData, double[] inputData, int dataLength) {
		for (int i=0; i<dataLength; i++) {
			outputData[i] = Math.sqrt(inputData[i]);
		}
	}
}
