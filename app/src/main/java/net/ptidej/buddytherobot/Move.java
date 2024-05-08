package net.ptidej.buddytherobot;

import android.os.RemoteException;
import android.util.Log;

import com.bfr.buddy.usb.shared.IUsbCommadRsp;
import com.bfr.buddysdk.BuddySDK;
import android.content.Context;
import android.content.SharedPreferences;
public class Move extends Action{
    protected float speed;
    protected float distance;
    private final Action nextAction;
    ExecutionStatus es;
    SharedPreferences sharedPreferences;
    public static final String mypreference = "mypref";
    public static final String BuddyMove = "moveKey";
    Move(final float aSpeed, final float aDistance, final Action aNextAction) {
        this.speed = aSpeed;
        this.distance = aDistance;
        this.nextAction = aNextAction;
    }
    @Override
    public void perform() {
        es=new ExecutionStatus();

        EnableWheels ew=new EnableWheels(true,null);
        ew.perform();
        BuddySDK.USB.moveBuddy(this.speed, this.distance, new IUsbCommadRsp.Stub() {
            @Override
            public void onSuccess(final String s) throws RemoteException {
                Log.i("MOVE_STATUS",s);
                if (s.toUpperCase().contains("WHEEL_MOVE_FINISHED")) {
                    sharedPreferences = MainActivity.getContext().getSharedPreferences(mypreference, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(BuddyMove, "MOVE_FINISHED");
                    editor.commit();
                    Log.i("MOVE_STATUS",s);
                   // es.changeExecutionStatus("MOVE_STATUS",s);

                    Log.i("NEXT_ACTION",nextAction.toString());
                    if (nextAction != null) {
                        nextAction.perform();
                    }
                }

            }

            @Override
            public void onFailed(final String s) throws RemoteException {
                sharedPreferences = MainActivity.getContext().getSharedPreferences(mypreference, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(BuddyMove, "MOVE_FAILED");
                editor.commit();
            }
        });

    }
}
