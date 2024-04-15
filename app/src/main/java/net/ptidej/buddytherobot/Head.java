package net.ptidej.buddytherobot;

import android.os.RemoteException;
import android.util.Log;

import com.bfr.buddy.usb.shared.IUsbCommadRsp;
import com.bfr.buddysdk.BuddySDK;

public class Head extends Action {
    protected float angle;
    protected String sayYesNo;
    protected float speed;
    private final Action nextAction;

    public Head(String sayYesNo, float angle, float speed, Action nextAction) {
        this.sayYesNo = sayYesNo;
        this.angle = angle;
        this.speed = speed;
        this.nextAction = nextAction;
    }

    public Head(String sayYesNo, float speed, Action nextAction) {
        this.sayYesNo = sayYesNo;
        this.speed = speed;
        this.nextAction = nextAction;
    }

    public Head(String sayYesNo, Action nextAction) {
        this.sayYesNo = sayYesNo;
        this.nextAction = nextAction;
    }

    @Override
    public void perform() {
        int option;
        if (sayYesNo.equalsIgnoreCase("Yes") && angle > 0 && speed > 0) {
            option = 1;
        } else if (sayYesNo.equalsIgnoreCase("No") && angle > 0 && speed > 0) {
            option = 2;
        } else if (sayYesNo.equalsIgnoreCase("Yes") && angle == 0 && speed > 0) {
            option = 3;
        } else if (sayYesNo.equalsIgnoreCase("No") && angle == 0 && speed > 0) {
            option = 4;
        } else if (sayYesNo.equalsIgnoreCase("Yes") && angle == 0 && speed == 0) {
            option = 5;
        } else if (sayYesNo.equalsIgnoreCase("No") && angle == 0 && speed == 0) {
            option = 6;
        } else {
            option = 7;
        }
        switch (option) {
            case 1:
                BuddySDK.USB.buddySayYes(speed, angle, new IUsbCommadRsp.Stub() {
                    @Override
                    public void onSuccess(String s) throws RemoteException {
                        if (s.equals("YES_MOVE_FINISHED")) {
                            Log.i("SayYes Success:", "" + s);
                        }
                    }

                    @Override
                    public void onFailed(String s) throws RemoteException {
                        Log.i("SayYes Failure:", "" + s);
                    }
                });
                break;
            case 2:
                BuddySDK.USB.buddySayNo(speed, angle, new IUsbCommadRsp.Stub() {
                    @Override
                    public void onSuccess(String s) throws RemoteException {
                        if (s.equals("NO_MOVE_FINISHED")) {
                            Log.i("SayNo Success:", "" + s);
                        }
                    }

                    @Override
                    public void onFailed(String s) throws RemoteException {
                        Log.i("SayNo Failure:", "" + s);
                    }
                });
                break;
            case 3:
                BuddySDK.USB.buddySayYesStraight(speed, new IUsbCommadRsp.Stub() {
                    @Override
                    public void onSuccess(String s) throws RemoteException {
                        if (s.equals("YES_MOVE_FINISHED")) {
                            Log.i("SayYes Success:", "" + s);
                        }
                    }

                    @Override
                    public void onFailed(String s) throws RemoteException {
                        Log.i("SayYes Failure:", "" + s);
                    }
                });
                break;
            case 4:
                BuddySDK.USB.buddySayNoStraight(speed, new IUsbCommadRsp.Stub() { //function with speed, angle and stub callback
                    @Override
                    public void onSuccess(String s) throws RemoteException {
                        if (s.equals("NO_MOVE_FINISHED")) {
                            Log.i("SayNo Success:", "" + s);

                        }
                    }

                    @Override
                    public void onFailed(String s) throws RemoteException {
                        Log.i("SayNo Failure:", "" + s);
                    }
                });
                break;
            case 5:
                BuddySDK.USB.buddyStopYesMove(new IUsbCommadRsp.Stub() {
                    @Override
                    public void onSuccess(String s) throws RemoteException {
                        if (s.equals("YES_MOVE_FINISHED")) {
                            Log.i("SayYes Success:", "" + s);
                        }
                    }

                    @Override
                    public void onFailed(String s) throws RemoteException {
                        Log.i("SayYes Failure:", "" + s);
                    }
                });
                break;
            case 6:
                BuddySDK.USB.buddyStopNoMove(new IUsbCommadRsp.Stub() {
                    @Override
                    public void onSuccess(String s) throws RemoteException {
                        // if (s.equals("NO_MOVE_FINISHED")) {
                        Log.i("No Motor Stop Success:", "" + s);
                        // }
                    }

                    @Override
                    public void onFailed(String s) throws RemoteException {
                        Log.i("No Motor Stop Failure:", "" + s);
                    }
                });
        }

    }
}