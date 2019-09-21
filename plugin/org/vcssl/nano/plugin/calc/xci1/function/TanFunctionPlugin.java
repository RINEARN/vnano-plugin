package org.vcssl.nano.plugin.calc.xci1.function;

public class TanFunctionPlugin extends VectorizableFloat64OperationFunctionPlugin {

	@Override
	public String getFunctionName() {
		return "tan";
	}

	@Override
	public void operate(double[] outputData, double[] inputData, int dataLength) {
		for (int i=0; i<dataLength; i++) {
			outputData[i] = Math.tan(inputData[i]);
		}
	}
}
