package org.vcssl.nano.plugin.calc.xci1.function;

public class DegFunctionPlugin extends Float64VectorizableOperationFunctionPlugin {

	@Override
	public final String getFunctionName() {
		return "deg";
	}

	@Override
	public final void operate(double[] outputData, double[] inputData, int dataLength) {
		for (int i=0; i<dataLength; i++) {
			double rad = inputData[i];
			double deg = 180.0 * rad / Math.PI;
			outputData[i] = deg;
		}
	}
}
