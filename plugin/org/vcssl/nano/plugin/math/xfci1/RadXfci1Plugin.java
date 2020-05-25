package org.vcssl.nano.plugin.math.xfci1;

public class RadXfci1Plugin extends Float64VectorizableOperationXfci1Plugin {

	@Override
	public final String getFunctionName() {
		return "rad";
	}

	@Override
	public final void operate(double[] outputData, double[] inputData, int dataLength) {
		for (int i=0; i<dataLength; i++) {
			double deg = inputData[i];
			double rad = Math.PI * deg / 180.0;
			outputData[i] = rad;
		}
	}
}
