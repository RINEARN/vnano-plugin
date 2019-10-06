package org.vcssl.nano.plugin.calc.xci1.function;

public class AcosFunctionPlugin extends Float64VectorizableOperationFunctionPlugin {

	@Override
	public final String getFunctionName() {
		return "acos";
	}

	@Override
	public final void operate(double[] outputData, double[] inputData, int dataLength) {
		for (int i=0; i<dataLength; i++) {
			outputData[i] = Math.acos(inputData[i]);
		}
	}
}
