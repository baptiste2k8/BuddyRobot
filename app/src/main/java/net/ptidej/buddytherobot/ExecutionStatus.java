package net.ptidej.buddytherobot;

import android.content.SharedPreferences;
import android.util.Log;

import androidx.preference.PreferenceManager;

import java.util.Map;

public class ExecutionStatus {
    SharedPreferences sh;
    public void changeExecutionStatus(String action, String status){
    sh = PreferenceManager.getDefaultSharedPreferences(MainActivity.getContext());
    SharedPreferences.Editor myEdit = sh.edit();
    myEdit.putString(action,status);
    myEdit.apply();
}
public String getExecutionStatus(String action){
    sh = PreferenceManager.getDefaultSharedPreferences(MainActivity.getContext());
    String status = sh.getString(action, "");
    return status;
}
public void showAllExecutionStatus(){
    sh = PreferenceManager.getDefaultSharedPreferences(MainActivity.getContext());
    Map<String,?> keys=sh.getAll();
    for(Map.Entry<String,?> entry : keys.entrySet()){
        Log.i("KEY::"+entry.getKey(),"VALUE::"+entry.getValue());
    }
}

}
