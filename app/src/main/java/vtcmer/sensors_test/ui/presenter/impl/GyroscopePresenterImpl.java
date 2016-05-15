package vtcmer.sensors_test.ui.presenter.impl;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorManager;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import vtcmer.sensors_test.listener.SensorGyroscopeListener;
import vtcmer.sensors_test.model.commons.EnumSensorType;
import vtcmer.sensors_test.model.commons.SensorCoordinates;
import vtcmer.sensors_test.ui.presenter.GyroscopePresenter;
import vtcmer.sensors_test.ui.view.GyroscopeView;

/**
 * Created by vtcmer on 15/05/16.
 */
public class GyroscopePresenterImpl extends AbstractSensorPresenter implements GyroscopePresenter {

    /**
     * Vista para actualizar y recoger datos
     */
    private GyroscopeView view;


    /**
     * Listener para los eventos del sensor
     */
    private SensorGyroscopeListener sensorGyroscopeListener;





    @Inject
    public GyroscopePresenterImpl(final @Named("ActivityContext") Activity context, final SensorGyroscopeListener sensorGyroscopeListener){
        super(context);
        this.sensorGyroscopeListener = sensorGyroscopeListener;
    }

    @Override
    public void setView(GyroscopeView view) {
        this.view = view;
    }

    @Override
    public void register() {


        Sensor sensor = this.getSensor(EnumSensorType.GYROSCOPE);
        if (sensor != null) {

            this.subscriptionSensor = this.sensorGyroscopeListener.register()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observerResultSensor);

            this.mSensorManager.registerListener(this.sensorGyroscopeListener,sensor, SensorManager.SENSOR_DELAY_GAME);
        } else{
            throw new RuntimeException("Not found "+EnumSensorType.GYROSCOPE.getName());
        }

    }

    @Override
    public void unregister() {

        if (this.sensorGyroscopeListener != null){
            this.sensorGyroscopeListener.completed();
            this.subscriptionSensor.unsubscribe();
            this.mSensorManager.unregisterListener(this.sensorGyroscopeListener);
        }

    }


    /**
     * Callback para actualizar las coordenadas
     */
    private Observer<SensorCoordinates> observerResultSensor=
            new Observer<SensorCoordinates>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    //TODO MANAGE ERRORS
                }

                @Override
                public void onNext(SensorCoordinates sensor) {
                    view.updateCoordiantes(sensor);
                }
            };

}
