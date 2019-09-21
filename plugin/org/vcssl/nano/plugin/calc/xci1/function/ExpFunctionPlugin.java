package org.vcssl.nano.plugin.calc.xci1.function;

public class ExpFunctionPlugin extends VectorizableFloat64OperationFunctionPlugin {

	@Override
	public final String getFunctionName() {
		return "exp";
	}

	@Override
	public final void operate(double[] outputData, double[] inputData, int dataLength) {
		for (int i=0; i<dataLength; i++) {
			outputData[i] = Math.exp(inputData[i]);
		}
	}
}
