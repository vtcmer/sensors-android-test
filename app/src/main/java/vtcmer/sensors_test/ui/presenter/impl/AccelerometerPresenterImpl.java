package vtcmer.sensors_test.ui.presenter.impl;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vtcmer.sensors_test.listener.SensorAccelerometerListener;
import vtcmer.sensors_test.model.SensorAccelerometerData;
import vtcmer.sensors_test.model.commons.EnumSensorType;
import vtcmer.sensors_test.ui.presenter.AccelerometerPresenter;
import vtcmer.sensors_test.ui.view.AccelerometerView;

/**
 * Created by vtcmer on 13/03/16.
 */
public class AccelerometerPresenterImpl extends AbstractSensorPresenter implements AccelerometerPresenter {


    /**
     * Vista asociada
     */
    private AccelerometerView view;

    private SensorAccelerometerListener sensorAccelerometerListener;


    @Inject
    public AccelerometerPresenterImpl(final @Named("ActivityContext") Activity context, final SensorAccelerometerListener sensorAccelerometerListener){
        super(context);
        this.sensorAccelerometerListener = sensorAccelerometerListener;
    }


    @Override
    public void setView(AccelerometerView view) {
        this.view = view;
    }

    @Override
    public void register() {


        Sensor sensor = this.getSensor(EnumSensorType.ACCELERATION);
        if (sensor != null) {

            this.subscriptionSensor = this.sensorAccelerometerListener.register()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observerResultSensor);;

            this.mSensorManager.registerListener(this.sensorAccelerometerListener,sensor, SensorManager.SENSOR_DELAY_GAME);
        } else{
            throw new RuntimeException("Not found "+EnumSensorType.ACCELERATION.getName());
        }

    }

    @Override
    public void unregister() {

        if (this.sensorAccelerometerListener != null){
            this.sensorAccelerometerListener.completed();
            this.subscriptionSensor.unsubscribe();
            this.mSensorManager.unregisterListener(this.sensorAccelerometerListener);
        }

    }




    private Observer<SensorAccelerometerData> observerResultSensor=
            new Observer<SensorAccelerometerData>() {
                @Override
                public void onCompleted() {
                    System.out.println("");
                }

                @Override
                public void onError(Throwable e) {
                    //TODO MANAGE ERRORS
                }

                @Override
                public void onNext(SensorAccelerometerData sensor) {

                    System.out.println("Acelerómetro X: " + sensor.getX());
                    System.out.println("Acelerómetro Y: " + sensor.getY());
                    System.out.println("Acelerómetro Z: " + sensor.getZ());
                    //refreshData();
                    view.updateCoordinates(sensor);
                }
            };



}
