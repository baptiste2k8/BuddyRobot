package net.ptidej.buddytherobot;

import android.util.Log;

import com.bfr.buddysdk.BuddySDK;

public class HeadSensor implements ISensor{
    String TAG = "Head Sensor : " ;
    //head sensors
    boolean touch_left_head = false;//Touching Head left
    boolean touch_top_head = false;//Touching Head top
    boolean touch_right_head = false;//Touching Head right
    @Override
    public int returnSensorValue() {
        int headTouched;
        touch_top_head= BuddySDK.Sensors.HeadTouchSensors().Top().isTouched();//boolean registered
        Log.i(TAG,"Head Top : "+touch_top_head);//layout of right sensor
        touch_left_head = BuddySDK.Sensors.HeadTouchSensors().Left().isTouched();//boolean registered
        Log.i(TAG,"Head Left : "+touch_left_head);//layout of left sensor
        touch_right_head = BuddySDK.Sensors.HeadTouchSensors().Right().isTouched();//boolean registered
        Log.i(TAG,"Head Right : " +touch_right_head);//layout of right sensor
        if(touch_top_head!=false && touch_left_head!=false & touch_right_head!=false){
            headTouched=1;
        }
        else{
            headTouched=0;
        }
        return headTouched;
    }

    @Override
    public void launchSensors() {
        BuddySDK.Sensors.HeadTouchSensors().Top().isTouched();//Top head sensor
        BuddySDK.Sensors.HeadTouchSensors().Left().isTouched();//Left sensor touched
        BuddySDK.Sensors.HeadTouchSensors().Right().isTouched();//Right sensor touched
    }
}
