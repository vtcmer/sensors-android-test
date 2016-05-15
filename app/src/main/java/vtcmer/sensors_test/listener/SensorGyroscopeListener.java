package vtcmer.sensors_test.listener;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.subscriptions.Subscriptions;
import vtcmer.sensors_test.model.commons.SensorCoordinates;

/**
 * Created by vtcmer on 15/05/16.
 */
public class SensorGyroscopeListener extends SensorAbstractListener implements SensorEventListener {


    private Observer<? super SensorCoordinates> sensorObserver;

    private static final float ROTATION_THRESHOLD = 2.0f;
    private static final int ROTATION_WAIT_TIME_MS = 100;


    private long mRotationTime = 0;


    /**
     * Registro del observador
     * @return
     */
    public Observable<SensorCoordinates> register() {

        return Observable.create(new Observable.OnSubscribeFunc<SensorCoordinates>() {

            @Override
            public Subscription onSubscribe(Observer<? super SensorCoordinates> t1) {

                sensorObserver = t1;
                return Subscriptions.empty();
            }
        });
    }

    @Inject
    public SensorGyroscopeListener(){}


    @Override
    public void completed() {
        this.sensorObserver.onCompleted();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        synchronized (this) {


            long now = System.currentTimeMillis();

            if ((now - mRotationTime) > ROTATION_WAIT_TIME_MS) {
                mRotationTime = now;



                // Change background color if rate of rotation around any
                // axis and in any direction exceeds threshold;
                // otherwise, reset the color
                if (Math.abs(event.values[0]) > ROTATION_THRESHOLD ||
                        Math.abs(event.values[1]) > ROTATION_THRESHOLD ||
                        Math.abs(event.values[2]) > ROTATION_THRESHOLD) {

                    SensorCoordinates d = new SensorCoordinates();
                    d.setX(event.values[0]);
                    d.setY(event.values[1]);
                    d.setZ(event.values[2]);
                    //d.setMovement(movement);
                    sensorObserver.onNext(d);

                }
            }

        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
