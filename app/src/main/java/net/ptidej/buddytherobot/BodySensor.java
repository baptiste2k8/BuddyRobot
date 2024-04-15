package net.ptidej.buddytherobot;

import android.util.Log;

import com.bfr.buddysdk.BuddySDK;

public class BodySensor implements ISensor{
    String TAG = "BoddySensor : " ;
    boolean touch_left_body = false;//Touching Head left
    boolean touch_center_body = false;//Touching Head top
    boolean touch_right_body = false;//Touching Head right
    @Override
    public int returnSensorValue() {
        int returnedValue;
        touch_center_body = BuddySDK.Sensors.BodyTouchSensors().Torso().isTouched();//center body sensor
        Log.i(TAG,"Body Center : "+touch_center_body);//layout of the sensor's state
        touch_left_body = BuddySDK.Sensors.BodyTouchSensors().LeftShoulder().isTouched();//left body sensor
        Log.i(TAG,"Body left : "+touch_left_body);//layout of the sensor's state
        touch_right_body = BuddySDK.Sensors.BodyTouchSensors().RightShoulder().isTouched();//tight body sensor
        Log.i(TAG,"Body right : "+touch_right_body);//layout of the sensor's state
        if(touch_center_body!=false||touch_left_body!=false||touch_right_body!=false){
            returnedValue=touch_center_body!=false?1:(touch_left_body!=false?1:(touch_right_body!=false?1:0));
        }
        else{
            returnedValue=0;
        }
        //return list of logs as well.
        return returnedValue;
    }

    @Override
    public void launchSensors() {
        BuddySDK.Sensors.BodyTouchSensors().Torso().isTouched();//center body sensor
        BuddySDK.Sensors.BodyTouchSensors().LeftShoulder().isTouched();//left body sensor
        BuddySDK.Sensors.BodyTouchSensors().RightShoulder().isTouched();//right body sensor
    }
}
