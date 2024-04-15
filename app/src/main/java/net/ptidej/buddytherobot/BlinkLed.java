package net.ptidej.buddytherobot;

import android.os.RemoteException;
import android.util.Log;

import com.bfr.buddy.usb.shared.IUsbCommadRsp;
import com.bfr.buddysdk.BuddySDK;

import java.util.concurrent.CompletableFuture;

public class BlinkLed {
    int blinkStatus;
    public CompletableFuture<Integer> blinkLed(String color, int period) {
        CompletableFuture<Integer> future=new CompletableFuture<>();
        BuddySDK.USB.blinkLed(2, color, period, new IUsbCommadRsp.Stub() {

            @Override
            public void onSuccess(String s) throws RemoteException {
                Log.i("coucou", "Message received : " + s);
                blinkStatus = 1;
                Log.i("BLINKING STATUS", "Status:" + blinkStatus);
                future.complete(blinkStatus);

            }

            @Override
            public void onFailed(String s) throws RemoteException {
                blinkStatus = 0;
                Log.i("BLINKING STATUS", "Status:" + blinkStatus);
                future.complete(blinkStatus);
            }
        });

     return future;
    }

    public CompletableFuture<Integer> blinkAllLed(String color, int period) {
        CompletableFuture<Integer> future=new CompletableFuture<>();
        BuddySDK.USB.blinkAllLed(color, period, new IUsbCommadRsp.Stub() {

            @Override
            public void onSuccess(String s) throws RemoteException {
                Log.i("coucou", "Message received : " + s);
                blinkStatus = 1;
                Log.i("BLINKING STATUS", "Status:" + blinkStatus);
                future.complete(blinkStatus);

            }

            @Override
            public void onFailed(String s) throws RemoteException {
                blinkStatus = 0;
                Log.i("BLINKING STATUS", "Status:" + blinkStatus);
                future.complete(blinkStatus);
            }
        });

        return future;
    }

    public CompletableFuture<Integer> fadeAllLed(String color, int period) {
        CompletableFuture<Integer> future=new CompletableFuture<>();
        BuddySDK.USB.fadeAllLed(color, period, new IUsbCommadRsp.Stub() {

            @Override
            public void onSuccess(String s) throws RemoteException {
                Log.i("coucou", "Message received : " + s);
                blinkStatus = 1;
                Log.i("BLINKING STATUS", "Status:" + blinkStatus);
                future.complete(blinkStatus);

            }

            @Override
            public void onFailed(String s) throws RemoteException {
                blinkStatus = 0;
                Log.i("BLINKING STATUS", "Status:" + blinkStatus);
                future.complete(blinkStatus);
            }
        });

        return future;
    }
    public CompletableFuture<Integer> updateLedColor(String color) {
        CompletableFuture<Integer> future=new CompletableFuture<>();
        BuddySDK.USB.updateLedColor(2,color, new IUsbCommadRsp.Stub() {

            @Override
            public void onSuccess(String s) throws RemoteException {
                Log.i("coucou", "Message received : " + s);
                blinkStatus = 1;
                Log.i("BLINKING STATUS", "Status:" + blinkStatus);
                future.complete(blinkStatus);

            }

            @Override
            public void onFailed(String s) throws RemoteException {
                blinkStatus = 0;
                Log.i("BLINKING STATUS", "Status:" + blinkStatus);
                future.complete(blinkStatus);
            }
        });

        return future;
    }

}