package net.ptidej.buddytherobot;

import android.os.RemoteException;
import android.util.Log;

import com.bfr.buddy.usb.shared.IUsbCommadRsp;
import com.bfr.buddysdk.BuddySDK;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class EnableSensors {
    int sensorsStatus;
    public CompletableFuture<Integer> enableSensors() {
        CompletableFuture<Integer> future=new CompletableFuture<>();
        //  after sdk launched we can set sensors
        BuddySDK.USB.enableSensorModule(true, new IUsbCommadRsp.Stub() {//called to enable sensors
            @Override
            public void onSuccess(String s) throws RemoteException {//in case of success
                Log.i("ENABLING SENSORS SUCCESS", "Enabled Sensors");//show if sensors are enabled
                sensorsStatus = 1;
                future.complete(sensorsStatus);
            }

            @Override
            public void onFailed(String s) throws RemoteException {//in case of failure
                Log.i("ENABLING SENSORS FAIL", "Fail to Enable sensors :" + s);//if fail show why
                sensorsStatus = 1;
                future.complete(sensorsStatus);
            }
        });
        return future;
    }
    public void sense() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> eS = enableSensors();
        int status;
        status = Integer.parseInt(String.valueOf(eS.get()));
        if (status == 1){
            Thread SensorsTh = new Thread() {
                public void run() {//function of the thread
                    while (true) {//do indefinitly
                        if (BuddySDK.Sensors.BodyTouchSensors().Torso().isTouched() || BuddySDK.Sensors.BodyTouchSensors().RightShoulder().isTouched() ||
                                    BuddySDK.Sensors.BodyTouchSensors().LeftShoulder().isTouched()) {
                            Log.i("TOUCHED_TORSO", "Body status: " + BuddySDK.Sensors.BodyTouchSensors().Torso().isTouched());
                            Log.i("TOUCHED_LEFT", "Body left: " + BuddySDK.Sensors.BodyTouchSensors().LeftShoulder().isTouched());
                            Log.i("TOUCHED_RIGHT", "Body right: " + BuddySDK.Sensors.BodyTouchSensors().RightShoulder().isTouched());
                        }
                        if (BuddySDK.Sensors.HeadTouchSensors().Top().isTouched() || BuddySDK.Sensors.HeadTouchSensors().Left().isTouched() ||
                                    BuddySDK.Sensors.HeadTouchSensors().Right().isTouched()) {
                            Log.i("TOUCHED_HEAD_TOP", "Head top touched status: " + BuddySDK.Sensors.HeadTouchSensors().Top().isTouched());
                            Log.i("TOUCHED_HEAD_LEFT", "Head left touched status: " + BuddySDK.Sensors.HeadTouchSensors().Left().isTouched());
                            Log.i("TOUCHED_HEAD_RIGHT", "Head right touched status: " + BuddySDK.Sensors.HeadTouchSensors().Right().isTouched());
                        }
                    }
                }
            };
        SensorsTh.start();//start the thread
    }
    }
}
