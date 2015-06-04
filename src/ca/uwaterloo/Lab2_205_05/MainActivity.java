package ca.uwaterloo.Lab2_205_05;

import java.util.Arrays;

import android.app.Activity;
import android.app.Fragment;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends Activity {
	static LineGraphView graph;
	 static int steps = 0;
     //Button btnReset;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //btnReset = (Button)findViewById(R.id.btnReset);
       
        
        if (savedInstanceState == null) {
        	
        		getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();  
        		
        		graph = new LineGraphView(getApplicationContext(), 100, Arrays.asList("x", "y", "z"));
        		graph.setVisibility(View.VISIBLE);
        }
        
        
       /* btnReset.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			 ((AccelerometerSensorEventListener) a).setStep(0);
			}
		});*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
    	
        public PlaceholderFragment(){        	
        }
                
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            
            LinearLayout layout = (LinearLayout)rootView.findViewById(R.id.layout_Main);
            layout.setOrientation(LinearLayout.VERTICAL);
            TextView accelLabel = new TextView(rootView.getContext());
            TextView steps = new TextView(rootView.getContext());
            
            SensorManager sensorManager = (SensorManager) rootView.getContext().getSystemService(SENSOR_SERVICE);
         
        	Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        	SensorEventListener a = new AccelerometerSensorEventListener(accelLabel);
        	sensorManager.registerListener(a, accelerometer, SensorManager.SENSOR_DELAY_FASTEST);
        	
        	accelLabel.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        	layout.addView(accelLabel);
        	layout.addView(steps);
        	layout.addView(graph,0);
        	
        	return rootView; 
        }
    }
}
