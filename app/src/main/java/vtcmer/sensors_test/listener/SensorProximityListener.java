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
 * Created by vtcmer on 13/03/16.
 */
public class SensorProximityListener extends SensorAbstractListener implements SensorEventListener {


    private Observer<? super SensorCoordinates> sensorObserver;


    @Inject
    public SensorProximityListener(){}


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

    @Override
    public void onSensorChanged(SensorEvent event) {

        System.out.println();
        SensorCoordinates data = new SensorCoordinates();
        data.setX(event.values[0]);
        sensorObserver.onNext(data);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void completed() {
        this.sensorObserver.onCompleted();
    }
}
