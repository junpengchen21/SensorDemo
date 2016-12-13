package cn.edu.gdmec.s07150705.sensordemo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private Sensor mOrientation;
    private Sensor mLight;
    private TextView tAccelerometer;
    private TextView tOrientation;
    private TextView tLight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tOrientation = (TextView) this.findViewById(R.id.accelerometer);
        tOrientation = (TextView) this.findViewById(R.id.orientation);
        tLight = (TextView) this.findViewById(R.id.light);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mOrientation = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this,mAccelerometer,SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this,mOrientation,SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this,mLight,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float x =sensorEvent.values[SensorManager.DATA_X];
        float y =sensorEvent.values[SensorManager.DATA_Y];
        float z =sensorEvent.values[SensorManager.DATA_Z];
        if(sensorEvent.sensor.getType() == Sensor.TYPE_ORIENTATION){
            tOrientation.setText("方位:"+x+","+y+","+z);
        }else if(sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            tAccelerometer.setText("加速度:"+x+","+y+","+z);
        }else if(sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT){
            tLight.setText("光线:"+sensorEvent.values[0]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
