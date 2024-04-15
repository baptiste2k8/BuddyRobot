package net.ptidej.buddytherobot;
import android.os.RemoteException;
import com.bfr.buddy.speech.shared.ITTSCallback;
import com.bfr.buddy.ui.shared.LabialExpression;
import com.bfr.buddysdk.BuddySDK;
import android.content.Context;
import android.content.SharedPreferences;
public class Speak extends Action {
    protected String speech;
    protected String actionStatus;

    ExecutionStatus es;
    SharedPreferences sharedPreferences;
    public static final String mypreference = "mypref";
    public static final String BuddySpeak= "speakKey";
    Speak(final String aSpeech, final Action aNextAction) {
        this.speech = aSpeech;
    }

    @Override
    public void perform() {
        es=new ExecutionStatus();
        BuddySDK.Speech.startSpeaking(this.speech, LabialExpression.SPEAK_ANGRY, new ITTSCallback.Stub(){

            @Override
            public void onSuccess(String s) throws RemoteException {
                sharedPreferences = MainActivity.getContext().getSharedPreferences(mypreference, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(BuddySpeak, "SPEAKING_FINISHED");
                editor.commit();
            }

            @Override
            public void onPause() throws RemoteException {
                sharedPreferences = MainActivity.getContext().getSharedPreferences(mypreference, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(BuddySpeak, "SPEAKING_PAUSED");
                editor.commit();
            }

            @Override
            public void onResume() throws RemoteException {
                sharedPreferences = MainActivity.getContext().getSharedPreferences(mypreference, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(BuddySpeak, "SPEAKING_RESUMED");
                editor.commit();
            }

            @Override
            public void onError(String s) throws RemoteException {
                sharedPreferences = MainActivity.getContext().getSharedPreferences(mypreference, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(BuddySpeak, "SPEAKING_ERROR");
                editor.commit();
            }
        });

    }

}
