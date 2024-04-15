package net.ptidej.buddytherobot;

import android.os.RemoteException;
import android.util.Log;

import com.bfr.buddy.usb.shared.IUsbCommadRsp;
import com.bfr.buddysdk.BuddySDK;

public class EnableNoMotor extends Action{

    //Enable Yes Motor
    //State : (0) disable, (1) enable
    private final int state;
    private final Action nextAction;

    public EnableNoMotor(int state, Action nextAction) {
        this.nextAction = nextAction;
        this.state=state;
    }

    @Override
    public void perform() {
        Log.i("Motor State: ","State: "+state);
        BuddySDK.USB.enableNoMove(state, new IUsbCommadRsp.Stub() {

            @Override
            public void onSuccess(String s) throws RemoteException {
                Log.i("TAG", "Motor Enabled");
            }

            @Override
            public void onFailed(String s) throws RemoteException {
                Log.i("Motor No", "No motor Enabled Failed");
            }
        });

    }

}
