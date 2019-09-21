package org.vcssl.nano.plugin.calc.xci1.function;

public class CosFunctionPlugin extends VectorizableFloat64OperationFunctionPlugin {

	@Override
	public final String getFunctionName() {
		return "cos";
	}

	@Override
	public final void operate(double[] outputData, double[] inputData, int dataLength) {
		for (int i=0; i<dataLength; i++) {
			outputData[i] = Math.cos(inputData[i]);
		}
	}
}
