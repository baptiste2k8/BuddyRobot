package net.ptidej.buddytherobot;

import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.preference.PreferenceManager;

import com.bfr.buddy.ui.shared.FacialExpression;
import com.bfr.buddy.ui.shared.IUIFaceAnimationCallback;
import com.bfr.buddysdk.BuddySDK;

public class Mood extends Action {
    private final Action nextAction;
    private final int fe;
   // private SharedPreferences sharedPref;
    ExecutionStatus es;
    public Mood(int fe,Action nextAction) {
        this.nextAction = nextAction;
        this.fe = fe;
    }

    @Override
    public void perform() {
        FacialExpression facialExpression;
        es=new ExecutionStatus();
        if(fe==0){
            facialExpression=FacialExpression.ANGRY;
            Log.i("Facial Expression:",facialExpression.toString());
        }
        else if(fe==1){
            facialExpression=FacialExpression.HAPPY;
        }
        else if(fe==2){
            facialExpression=FacialExpression.GRUMPY;
        }
        else if(fe==3){
            facialExpression=FacialExpression.LISTENING;
        }
        else if(fe==4){
            facialExpression=FacialExpression.LOVE;
        }
        else if(fe==5){
            facialExpression=FacialExpression.SAD;
        }
        else if(fe==6){
            facialExpression=FacialExpression.SCARED;
        }
        else if(fe==7){
            facialExpression=FacialExpression.SICK;
        }
        else if(fe==8){
            facialExpression=FacialExpression.SURPRISED;
        }
        else{
            facialExpression=FacialExpression.NEUTRAL;
        }
        BuddySDK.UI.setMood(facialExpression, new IUIFaceAnimationCallback(){
            @Override
            public IBinder asBinder() {
               es.changeExecutionStatus("MOOD_STATUS",facialExpression.name());
                 return null;
            }

            @Override
            public void onAnimationEnd(String s, String s1) throws RemoteException {
                Log.i("Animation End","First Parameter: "+s+" "+"Second Parameter:"+s1);
            }
        });

    }
}
