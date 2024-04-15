package net.ptidej.buddytherobot;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.RemoteException;

import com.bfr.buddy.usb.shared.IUsbCommadRsp;
import com.bfr.buddysdk.BuddySDK;

public class Rotate extends Action{
    protected float speed;
    protected float angle;
    protected String actionStatus;
    private final Action nextAction;
    ExecutionStatus es;
    SharedPreferences sharedPreferences;
    public static final String mypreference = "mypref";
    public static final String BuddyRotate = "rotateKey";
    Rotate(final float aSpeed, final float anAngle, final Action aNextAction) {
        this.speed = aSpeed;
        this.angle = anAngle;
        this.nextAction = aNextAction;
    }

    @Override
    public void perform() {
        es=new ExecutionStatus();
        BuddySDK.USB.rotateBuddy(this.speed, this.angle, new IUsbCommadRsp.Stub() {
            @Override
            public void onSuccess(final String s) throws RemoteException {
                if (s.toUpperCase().contains("WHEEL_MOVE_FINISHED")) {
                    sharedPreferences = MainActivity.getContext().getSharedPreferences(mypreference, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(BuddyRotate, "ROTATE_FINISHED");
                    editor.commit();
                   // es.changeExecutionStatus("ROTATE_STATUS",s);
                    if (nextAction != null) {
                        nextAction.perform();
                    }
                }

            }

            @Override
            public void onFailed(final String s) throws RemoteException {
                sharedPreferences = MainActivity.getContext().getSharedPreferences(mypreference, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(BuddyRotate, "ROTATE_FAILED");
                editor.commit();
            }
        });


    }
}

