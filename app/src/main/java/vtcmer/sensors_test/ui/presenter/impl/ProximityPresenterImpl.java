package vtcmer.sensors_test.ui.presenter.impl;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorManager;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vtcmer.sensors_test.listener.SensorProximityListener;
import vtcmer.sensors_test.model.commons.EnumSensorType;
import vtcmer.sensors_test.model.commons.SensorCoordinates;
import vtcmer.sensors_test.ui.presenter.ProximityPresenter;
import vtcmer.sensors_test.ui.view.ProximimtyView;

/**
 * Created by vtcmer on 13/03/16.
 */
public class ProximityPresenterImpl extends AbstractSensorPresenter implements ProximityPresenter {


    /**
     * Vista asociada
     */
    private ProximimtyView view;


    private SensorProximityListener sensorProximityListener;


    @Inject
    public ProximityPresenterImpl(final @Named("ActivityContext") Activity context, final SensorProximityListener sensorProximityListener){
        super(context);
        this.sensorProximityListener = sensorProximityListener;
    }




    @Override
    public void setView(ProximimtyView view) {
        this.view = view;
    }

    @Override
    public void register() {


        Sensor sensor = this.getSensor(EnumSensorType.PROXIMITY);
        if (sensor != null) {

            this.subscriptionSensor = this.sensorProximityListener.register()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observerResultSensor);;

            this.mSensorManager.registerListener(this.sensorProximityListener,sensor, SensorManager.SENSOR_DELAY_GAME);
        } else{
            throw new RuntimeException("Not found "+EnumSensorType.ACCELERATION.getName());
        }

    }

    @Override
    public void unregister() {

        if (this.sensorProximityListener != null){
            this.sensorProximityListener.completed();
            this.subscriptionSensor.unsubscribe();
            this.mSensorManager.unregisterListener(this.sensorProximityListener);
        }

    }






    private Observer<SensorCoordinates> observerResultSensor=
            new Observer<SensorCoordinates>() {
                @Override
                public void onCompleted() {
                    System.out.println("");
                }

                @Override
                public void onError(Throwable e) {
                    //TODO MANAGE ERRORS
                }

                @Override
                public void onNext(SensorCoordinates sensor) {

                    System.out.println("Acelerómetro X: " + sensor.getX());
                    System.out.println("Acelerómetro Y: " + sensor.getY());
                    System.out.println("Acelerómetro Z: " + sensor.getZ());
                    //refreshData();
                    view.updateCoordinates(sensor);
                }
            };



}
