package vtcmer.sensors_test.listener;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.subscriptions.Subscriptions;
import vtcmer.sensors_test.model.SensorAccelerometerData;

/**
 * Created by vtcmer on 06/03/2016.
 */
public class SensorAccelerometerListener extends SensorAbstractListener implements SensorEventListener {

    @Inject
    public SensorAccelerometerListener(){}

    private long last_update = 0;
    private long last_movement = 0;

    private float prevX = 0;
    private float prevY = 0;
    private float prevZ = 0;

    private float curX = 0;
    private float curY = 0;
    private float curZ = 0;



    private static final float SHAKE_THRESHOLD = 1.1f;
    private static final int SHAKE_WAIT_TIME_MS = 250;

    private long mShakeTime = 0;

    private Observer<? super SensorAccelerometerData> sensorObserver;


    /**
     * Registro del observador
     * @return
     */
    public Observable<SensorAccelerometerData> register() {

        return Observable.create(new Observable.OnSubscribeFunc<SensorAccelerometerData>() {

            @Override
            public Subscription onSubscribe(Observer<? super SensorAccelerometerData> t1) {

                sensorObserver = t1;
                return Subscriptions.empty();
            }
        });
    }

    /**
     * Finaliza el proceso
     */
    public void completed(){
        this.sensorObserver.onCompleted();
    }



    @Override
    public void onSensorChanged(SensorEvent event) {

        synchronized (this) {



            long now = System.currentTimeMillis();

            if ((now - mShakeTime) > SHAKE_WAIT_TIME_MS) {
                mShakeTime = now;

                float gX = event.values[0] / SensorManager.GRAVITY_EARTH;
                float gY = event.values[1] / SensorManager.GRAVITY_EARTH;
                float gZ = event.values[2] / SensorManager.GRAVITY_EARTH;

                // gForce will be close to 1 when there is no movement
                double gForce = Math.sqrt(gX * gX + gY * gY + gZ * gZ);

                // Change background color if gForce exceeds threshold;
                // otherwise, reset the color
                if (gForce > SHAKE_THRESHOLD) {
                    //soundAcc.start();
                    SensorAccelerometerData d = new SensorAccelerometerData();
                    d.setX(gX);
                    d.setY(gY);
                    d.setZ(gZ);
                    //d.setMovement(movement);
                    sensorObserver.onNext(d);

                }
            }

            /*

            long current_time = event.timestamp;

            curX = event.values[0];
            curY = event.values[1];
            curZ = event.values[2];

            if (prevX == 0 && prevY == 0 && prevZ == 0) {
                last_update = current_time;
                last_movement = current_time;
                prevX = curX;
                prevY = curY;
                prevZ = curZ;
            }

            long time_difference = current_time - last_update;
            if (time_difference > 0) {
                float movement = Math.abs((curX + curY + curZ) - (prevX - prevY - prevZ)) / time_difference;
                int limit = 1500;
                float min_movement = 1E-6f;
                if (movement > min_movement) {
                    if (current_time - last_movement >= limit) {
                        System.out.println("Hay movimiento de " + movement);

                        SensorAccelerometerData d = new SensorAccelerometerData();
                        d.setX(curX);
                        d.setY(curY);
                        d.setZ(curZ);
                        d.setMovement(movement);
                        sensorObserver.onNext(d);

                    }
                    last_movement = current_time;
                }
                prevX = curX;
                prevY = curY;
                prevZ = curZ;
                last_update = current_time;
            }

            */



            //sensorObserver.onCompleted();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

        System.out.println();

    }
}
