package org.vcssl.nano.plugin.math.xfci1;

public class AtanXfci1Plugin extends Float64VectorizableOperationXfci1Plugin {

	@Override
	public final String getFunctionName() {
		return "atan";
	}

	@Override
	public final void operate(double[] outputData, double[] inputData, int dataLength) {
		for (int i=0; i<dataLength; i++) {
			outputData[i] = Math.atan(inputData[i]);
		}
	}
}
