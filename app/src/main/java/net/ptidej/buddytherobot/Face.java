package net.ptidej.buddytherobot;

import android.os.RemoteException;
import android.util.Log;

import com.bfr.buddy.ui.shared.FaceTouchData;
import com.bfr.buddy.ui.shared.IUIFaceTouchCallback;
import com.bfr.buddysdk.BuddySDK;

public class Face extends Action{
    private final Action nextAction;

    public Face(Action nextAction) {
        this.nextAction = nextAction;
    }

    @Override
    public void perform() {
        Log.i("Face::","Inside face");
        BuddySDK.UI.addFaceTouchListener(new IUIFaceTouchCallback.Stub() {
            @Override
            public void onTouch(FaceTouchData faceTouchData) throws RemoteException {
                switch(faceTouchData.getArea())
                {
                    case FACE:
                        Log.v("FACE TOUCHED:","Face touched");
                        break;
                    case MOUTH:
                        Log.v("MOUTH TOUCHED:","Mouth touched");
                        break;
                    case RIGHT_EYE:
                        Log.v("RIGHT_EYE TOUCHED:","Right eye touched");
                        break;
                    case LEFT_EYE:
                        Log.v("LEFT_EYE TOUCHED:","Left eye touched");
                        break;
                    default:
                        Log.v("NOTHING TOUCHED:","Nothing touched");
                        break;
                }
            }

            @Override
            public void onRelease(FaceTouchData faceTouchData) throws RemoteException {

            }
        });

    }
}
