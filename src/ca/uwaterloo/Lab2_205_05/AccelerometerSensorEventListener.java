package ca.uwaterloo.Lab2_205_05;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;

class AccelerometerSensorEventListener implements SensorEventListener {
	float x,y,z;
	TextView output;
	float maxAccx, maxAccy, maxAccz;
	float minAccx, minAccy, minAccz;
	float[] outputA = new float[3];
	int C = 4;
	float previous;
	int state = 1;
	int steps = 0;
	String[] num;
	//Button reset;
	String maxValues, minValues;
	
	public AccelerometerSensorEventListener(TextView outputText){
		output = outputText;
		//this.reset = reset;
		
		}
	
		public void onAccuracyChanged(Sensor s, int i){}
		
		public void onSensorChanged(SensorEvent se){
			
			outputA[0] = outputA[0] + (se.values[0] - outputA[0]) / C;
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
				
				num = new String[] {(String.format("%.3f", x)), (String.format("%.3f", y)), (String.format("%.3f", z))};
				maxValues = String.format("(%.3f, %.3f, %.3f)", maxAccx, maxAccy, maxAccz);
				minValues = String.format("(%.3f, %.3f, %.3f)", minAccx, minAccy, minAccz);
												 			
				MainActivity.graph.addPoint(outputA);
				
				}
			
		
				/*if( state == 0 && outputA[2] > 0 ) {
					state = 1;
				}
				else if( state == 1 && outputA[2] < previous ) {
					state = 2;
				}
				
				if( state == 2 && outputA[2] < 0 && outputA[2] > previous ){
					state = 3;
				}
				
				if( state == 3 && outputA[2] < 0 && outputA[2] < previous ){
					state = 4;
				}
				
				if( state == 4 && outputA[2] > 0 ){
					steps += steps;
				}*/
			
			if (state == 0 && outputA[2]>0)
			{
				state = 1; 
			}
			else if (state == 0)
			{
				state=0;
			}
			if (state ==1 && previous>outputA[2] && outputA[2] > 1.3)
			{
				state=2; 
			}
			else 
			if (state==2 && outputA[2]<0)
			{
				state =3; 
			}
			else if (state==2 && previous<outputA[2])
			{
				state =0; 
			}
			if (state==3 && previous<outputA[2])
			{
				state =4; 
			}
			if (state==4 && outputA[2]>0)
			{
				state =0;
				steps++; 
			}
			else if (state==4 && previous>outputA[2])
			{
				state =0;
				 
			}
			
				output.setTextSize(15);
				output.setText("\n\n\n--LINEAR ACCELERATION (m/s^2)-- \n    X: " + num[0] + "\n"
						+ "    Y: " + num[1] + "\n    Z: " + num[2] +"\n"
						+ " Max :" + maxValues + "\n" + " Min :" + minValues + "\n" + steps);
				
				previous = outputA[2];
		}
		public void setStep(int value)
		{
			this.steps = value;
		}
					
	}

