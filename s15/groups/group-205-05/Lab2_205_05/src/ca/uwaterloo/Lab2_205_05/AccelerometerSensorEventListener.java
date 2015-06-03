package ca.uwaterloo.Lab2_205_05;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;

class AccelerometerSensorEventListener implements SensorEventListener {
	float x,y,z;
	TextView output;
	float maxAccx;
	float maxAccy;
	float maxAccz;
	float minAccx, minAccy, minAccz;
	float[] outputA = new float[3];
	int C = 20;
	
	public AccelerometerSensorEventListener(TextView outputText){
		output = outputText;
		
	}		
		public void onAccuracyChanged(Sensor s, int i){}
		
		public void onSensorChanged(SensorEvent se){
			
			outputA[0] += (se.values[0] - outputA[0]) / C;
			outputA[1] += (se.values[1] - outputA[1]) / C;
			outputA[2] += (se.values[2] - outputA[2]) / C;
			
				
			if (se.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION){
				
				x = se.values[0];
				y = se.values[1];
				z = se.values[2];
				
				//MAX values
				if((outputA[0]) > maxAccx ) {maxAccx = (outputA[0]);}
				if((outputA[1]) > maxAccy ) {maxAccy = (outputA[1]);}
				if((outputA[2]) > maxAccz ) {maxAccz = (outputA[2]) ;}
				
				//MIN values
				if((outputA[0]) < minAccx ) {minAccx = (outputA[0]);}
				if((outputA[1]) < minAccy ) {minAccy = (outputA[1]);}
				if((outputA[2]) < minAccz ) {minAccz = (outputA[2]) ;}
				
				String[] num = new String[] {(String.format("%.3f", x)), (String.format("%.3f", y)), (String.format("%.3f", z))};
				String maxValues = String.format("(%.3f, %.3f, %.3f)", maxAccx, maxAccy, maxAccz);
				String minValues = String.format("(%.3f, %.3f, %.3f)", minAccx, minAccy, minAccz);
								
				output.setText("\n\n\n--LINEAR ACCELERATION (m/s^2)-- \n    X: " + num[0] + "\n"
						+ "    Y: " + num[1] + "\n    Z: " + num[2] +"\n"
						+ " Max :" + maxValues + "\n" + " Min :" + minValues); 
				
				
				MainActivity.graph.addPoint(outputA);
				}
			}
	}

