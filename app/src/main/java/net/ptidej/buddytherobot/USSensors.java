package net.ptidej.buddytherobot;

import android.util.Log;

import com.bfr.buddysdk.BuddySDK;

public class USSensors implements ISensor {
    String TAG = "USSensor: " ;
    public int rightSensorDist=0;//distance USSensors
    public int leftSensorDist=0;//distance USSensors
    public int rightSensorAmpl=0;//amplitude USSensors
    public int leftSensorAmpl=0;
    @Override
    public void launchSensors() {
        BuddySDK.Sensors.USSensors().RightUS().getDistance();//launching the right sensor distance measurement
        BuddySDK.Sensors.USSensors().LeftUS().getDistance();//launching the left sensor distance measurement
        Log.i(TAG,"USSensors launched....");
    }
    @Override
    public int returnSensorValue() {
        int USSensorStatus;
        rightSensorDist=BuddySDK.Sensors.USSensors().RightUS().getDistance();
        Log.i(TAG,"Right Sensor Distance = " + rightSensorDist);
        leftSensorDist=BuddySDK.Sensors.USSensors().LeftUS().getDistance();
        Log.i(TAG,"Left Sensor Distance = " + leftSensorDist);
        rightSensorAmpl=BuddySDK.Sensors.USSensors().RightUS().getAmplitude();
        Log.i(TAG,"Right Sensor Amplitude = " + rightSensorAmpl);
        leftSensorAmpl=BuddySDK.Sensors.USSensors().LeftUS().getAmplitude();
        Log.i(TAG,"Left Sensor Amplitude = " + leftSensorAmpl);
        if(rightSensorDist!=0 || leftSensorDist!=0){
            USSensorStatus=(rightSensorDist!=0?rightSensorDist:leftSensorDist);
        }
        else if(rightSensorAmpl!=0||leftSensorAmpl!=0){
            USSensorStatus=(rightSensorAmpl!=0?rightSensorAmpl:leftSensorAmpl);
        }
        else{
            USSensorStatus=0;
        }
        return USSensorStatus;
    }


}
