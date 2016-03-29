package vtcmer.sensors_test.listener;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

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





            //sensorObserver.onCompleted();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

        System.out.println();

    }
}
