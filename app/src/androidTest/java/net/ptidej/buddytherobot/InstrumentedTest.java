package net.ptidej.buddytherobot;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.MutableLiveData;
import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
/**
 * Instrumented test, which will execute on an Buddy Robot.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class InstrumentedTest {
    private MainActivity5 mainActivity5;
    @Rule
    public ActivityScenarioRule<MainActivity5> rule = new  ActivityScenarioRule<>(MainActivity5.class);
    ActivityScenario<MainActivity5> scenario;
    ExecutionStatus es;
    Context appContext;
    private ServiceConnection mServiceConnection=new ServiceConnection(){
        @Override
        public void onServiceConnected(ComponentName iName, IBinder iBinder){
         Log.i("COMPANION:","Connection succeeded");
        }
        @Override
        public void onServiceDisconnected(ComponentName iName){
         Log.i("COMPANION:","Disconnected");
        }
    };


    @Before
    public void lunchActivity(){
       scenario= ActivityScenario.launch((MainActivity5.class));
       scenario.moveToState(Lifecycle.State.CREATED);
       appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
       es=new ExecutionStatus();
    }
    @After
    public void tearDown(){
        scenario.close();
    }
    @Test
    public void useAppContext() {
       assertEquals("net.ptidej.buddytherobot", appContext.getPackageName());
    }
    @Test
    public void moveTest() {
        final Move mv=new Move(0.1f, 0.1f, null);
        mv.perform();
        String status=es.getExecutionStatus("MOVE_STATUS");
        es.showAllExecutionStatus();
        assertTrue(status.contains("_FINISHED"));
    }
    @Test
    public void speakMoveTest(){
        final Speak sp=new Speak("Testing",null);
        final Move mv=new Move(0.1f, 0.1f, sp);
        mv.perform();
        String status=es.getExecutionStatus("MOVE_STATUS");
        assertTrue(status.contains("_FINISHED"));
    }

    @Test
    public void enableWheelsTest() {
        EnableWheels ew=new EnableWheels(true,null);
        ew.perform();
        String status=es.getExecutionStatus("ENABLE_WHEEL_STATUS");
        es.showAllExecutionStatus();
        assertTrue(status.equalsIgnoreCase("OK"));
    }
    @Test
    public void rotateTest() {
        Rotate rotate=new Rotate(12f,12f,null);
        rotate.perform();
        String status=es.getExecutionStatus("ROTATE_STATUS");
        es.showAllExecutionStatus();
        assertTrue(status.contains("_FINISHED"));
    }
    @Test
    public void speakTest() {
        Speak speak=new Speak("GOOD",null);
        speak.perform();
        String status=es.getExecutionStatus("SPEAK_STATUS");
        es.showAllExecutionStatus();
        assertTrue(status.equalsIgnoreCase("GOOD"));
    }
    @Test
    public void moodTest() {
        Mood mood=new Mood(7,null);
        mood.perform();
        String status=es.getExecutionStatus("MOOD_STATUS");
        es.showAllExecutionStatus();
        assertTrue(status.equalsIgnoreCase("SICK"));
    }
    /*@Test
    public void sensorEnableTest() throws InterruptedException, ExecutionException {
        int status;
        EnableSensors es=new EnableSensors();
        CompletableFuture<Integer> sensors=es.enableSensors();
        status=Integer.parseInt(String.valueOf(sensors.get()));
        assertEquals(1,status);
    }

    @Test
    public void blinkLedTest() throws InterruptedException, ExecutionException {
        int status;
        BlinkLed bl=new BlinkLed();
        CompletableFuture<Integer> led=bl.blinkLed("#C3C435",1);
        status=Integer.parseInt(String.valueOf(led.get()));
        assertEquals(1,status);
    }
    @Test
    public void blinkAllLedTest() throws InterruptedException, ExecutionException {
        int status;
        BlinkLed bl=new BlinkLed();
        CompletableFuture<Integer> led=bl.blinkAllLed("#C3C435",1);
        status=Integer.parseInt(String.valueOf(led.get()));
        assertEquals(1,status);
    }
    @Test
    public void fadeAllLedTest() throws InterruptedException, ExecutionException { //passed
        int status;
        BlinkLed bl=new BlinkLed();
        CompletableFuture<Integer> led=bl.fadeAllLed("#C3C435",1);
        status=Integer.parseInt(String.valueOf(led.get()));
        assertEquals(1,status);
    }
    @Test
    public void updateLedColorTest() throws InterruptedException, ExecutionException {
        int status;
        BlinkLed bl=new BlinkLed();
        CompletableFuture<Integer> led=bl.updateLedColor("#FF0000");
        status=Integer.parseInt(String.valueOf(led.get()));
        assertEquals(1,status);
    }*/
}