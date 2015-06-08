package ca.uwaterloo.Lab2_205_05;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.TextView;

class AccelerometerSensorEventListener implements SensorEventListener {
	float x,y,z;
	TextView output;
	float[] maxAcc = new float[3];
	float[] minAcc = new float[3];
	float[] outputA = new float[3];
	float previous;
	int C = 5;
	int state = 0;
	//int steps = 0;
	String[] num;
	String maxValues, minValues;
	
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
				if((outputA[0]) > maxAcc[0] ) {maxAcc[0] = (outputA[0]);}
				if((outputA[1]) > maxAcc[1] ) {maxAcc[1] = (outputA[1]);}
				if((outputA[2]) > maxAcc[2] ) {maxAcc[2] = (outputA[2]) ;}
				
				//MIN values
				if((outputA[0]) < minAcc[0] ) {minAcc[0] = (outputA[0]);}
				if((outputA[1]) < minAcc[1] ) {minAcc[1] = (outputA[1]);}
				if((outputA[2]) < minAcc[2] ) {minAcc[2] = (outputA[2]) ;}
				
				num = new String[] {(String.format("%.3f", x)), (String.format("%.3f", y)), (String.format("%.3f", z))};
				maxValues = String.format("(%.3f, %.3f, %.3f)", maxAcc[0], maxAcc[1], maxAcc[2]);
				minValues = String.format("(%.3f, %.3f, %.3f)", minAcc[0], minAcc[1], minAcc[2]);
												 			
				MainActivity.graph.addPoint(outputA);
			}
			
			if (state == 0 && outputA[2]>0){
				state = 1; 
			}
			else if (state == 0){
				state=0;
			}
			if (state ==1 && previous>outputA[2] && outputA[2] > 1){
				state=2; 
			}
			else if (state==2 && outputA[2]<0){
				state =3; 
			}
			else if (state==2 && previous<outputA[2]){
				state =0; 
			}
			if (state==3 && previous<outputA[2]){
				state =4; 
			}
			if (state==4 && outputA[2]>0){
				state =0;
				MainActivity.steps++; 
			}
			else if (state==4 && previous>outputA[2]){
				state =0;
				 
			}
			
			/*if( state == 0 && outputA[2] > 0) {
				state = 1;
			}
			else if (state == 0){
				state = 0;
			}
			else if(state == 1 && outputA[2] > 1.2){
				state = 2;
			}
			else if( state == 2 && outputA[2] > previous ) { //rising n rising
				state = 2;
			}
			else if(state == 2 && outputA[2] < previous){
				state = 0 ;
			}
			else if( state == 3 && outputA[2] > previous && outputA[2] < 6){
				state = 3;
			}
			else if( state == 3 && outputA[2] < previous && outputA[2] < 6){ //
				state = 4;
			}
			else if( state == 3 && outputA[2] > 6){
				state = 0;
			}
			else if( state == 4 && outputA[2] < 0 ){
				steps += 1;
			}
			else{
				state = 4;
			}
			*/
			previous = outputA[2];
			
				output.setTextSize(17);
				output.setText(" Max :" + maxValues + "\n" + " Min :" + minValues + "\nsteps: " + MainActivity.steps);
				
				
		}
	}

