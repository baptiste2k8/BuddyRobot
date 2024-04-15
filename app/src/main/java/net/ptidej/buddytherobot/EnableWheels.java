package net.ptidej.buddytherobot;

import android.content.SharedPreferences;
import android.os.RemoteException;
import android.util.Log;

import com.bfr.buddy.usb.shared.IUsbCommadRsp;
import com.bfr.buddysdk.BuddySDK;
import android.content.Context;
import android.content.SharedPreferences;
public class EnableWheels extends Action{
    protected boolean shouldEnable;

    public String actionStatus;
    int i=0;
    private final Action nextAction;
    ExecutionStatus es;
    SharedPreferences sharedPreferences;
    public static final String mypreference = "mypref";
    public static final String BuddyEnableWheel = "enableWheelKey";
    EnableWheels(final boolean aShouldEnable, final Action aNextAction) {
        this.shouldEnable = aShouldEnable;
        this.nextAction = aNextAction;
    }

    @Override
    public void perform() {
        es=new ExecutionStatus();
        final int leftWheelStatus, rightWheelStatus;
        if (shouldEnable) {
            leftWheelStatus = 1;
            rightWheelStatus = 1;
        } else {
            leftWheelStatus = 0;
            rightWheelStatus = 0;
        }
        BuddySDK.USB.enableWheels(leftWheelStatus, rightWheelStatus, new IUsbCommadRsp.Stub() {
            @Override
            public void onSuccess(String s) throws RemoteException {
                Log.i("ENABLE_WHEEL_STATUS",s);
                if (s.equalsIgnoreCase("OK")) {
                    sharedPreferences = MainActivity.getContext().getSharedPreferences(mypreference, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(BuddyEnableWheel, "WHEELS_ENABLE_FINISHED");
                    editor.commit();
                  // es.changeExecutionStatus("ENABLE_WHEEL_STATUS",s);
                    if (nextAction != null) {
                        nextAction.perform();
                    }

                }
            }

            @Override
            public void onFailed(String s) throws RemoteException {
                sharedPreferences = MainActivity.getContext().getSharedPreferences(mypreference, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(BuddyEnableWheel, "WHEELS_ENABLE_FAILED");
                editor.commit();
            }
        });

    }


}
