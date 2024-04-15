package net.ptidej.buddytherobot;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import com.bfr.buddy.usb.shared.IUsbCommadRsp;
import com.bfr.buddysdk.BuddyActivity;
import com.bfr.buddysdk.BuddySDK;
import android.content.Context;
import android.content.SharedPreferences;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

public class MainActivity extends BuddyActivity {
    SharedPreferences sharedpreferences;
    public static final String BuddyMove = "moveKey";
    public static final String BuddyRotate = "rotateKey";
    public static final String BuddyEnableWheel = "enableWheelKey";
    public static final String BuddySpeak= "speakKey";
    String TAG = "Move Tuto" ;
    private SharedPreferences sharedPref;
    private static Context context;
    public static String SERVER_IP = "";
    public static final int SERVER_PORT = 8080;
    private WebSocketClient mWebSocketClient;
    public static final String mypreference = "mypref";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        Log.i(TAG,"onCreate finished");
        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        if (sharedpreferences.contains(BuddyMove)) {
           Log.i(TAG,sharedpreferences.getString(BuddyMove, ""));
        }
        if (sharedpreferences.contains(BuddyRotate)) {
            Log.i(TAG,sharedpreferences.getString(BuddyRotate, ""));

        }
        if (sharedpreferences.contains(BuddyEnableWheel)) {
            Log.i(TAG,sharedpreferences.getString(BuddyEnableWheel, ""));
        }
        if (sharedpreferences.contains(BuddySpeak)) {
            Log.i(TAG,sharedpreferences.getString(BuddySpeak, ""));

        }
    }
    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
    @Override
    public void onSDKReady() {
        Log.i(TAG,"onSDKReady finished");
        connect2WIMPt();

    }
    public void buddyAction(){
        Speak s=new Speak("Finish",null);
        Move m2=new Move(0.1f,0.3f,s);
        Rotate r=new Rotate(10.0f,10.0f,m2);
        Move m=new Move(0.1f,0.1f,r);
        EnableWheels a=new EnableWheels(true,m);
        a.perform();
        Log.d(TAG, "COMPLETED BUDDY ACTION");
        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        if (sharedpreferences.contains(BuddyMove)) {
            Log.i(TAG,sharedpreferences.getString(BuddyMove, ""));
        }
        if (sharedpreferences.contains(BuddyRotate)) {
            Log.i(TAG,sharedpreferences.getString(BuddyRotate, ""));

        }
        if (sharedpreferences.contains(BuddyEnableWheel)) {
            Log.i(TAG,sharedpreferences.getString(BuddyEnableWheel, ""));
        }
        if (sharedpreferences.contains(BuddySpeak)) {
            Log.i(TAG,sharedpreferences.getString(BuddySpeak, ""));

        }
    }
    public void connect2WIMPt(){
        URI uri;
        String TAG="WimpApp";
        String websocketEndPointUrl;
        try {
           // websocketEndPointUrl="ws://192.168.2.39:8181"; // SocketServer's IP and port [home]
            websocketEndPointUrl="ws://192.168.191.114:8181"; // SocketServer's IP and port  [lab]
            uri = new URI(websocketEndPointUrl);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }
        mWebSocketClient = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake serverHandshake) {
                Log.i(TAG, "WIMP connection is opened");
                mWebSocketClient.send("Client:" + Build.MANUFACTURER + " " + Build.MODEL);
                Log.i(TAG,"Client:  " + Build.MANUFACTURER + " " + Build.MODEL);
            }

            @Override
            public void onMessage(String message) {
                sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.commit();
                buddyAction();
            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
                Log.i(TAG, "Closed, code= " + code+", reason="+reason+", remote="+remote);
            }

            @Override
            public void onError(Exception e) {

                Log.i(TAG, "Error " + e.getMessage());
            }
        };
        mWebSocketClient.connect();

    }

    public static Context getContext() {
        return context;
    }

}