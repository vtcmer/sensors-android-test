package vtcmer.sensors_test.ui.presenter.impl;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;

import rx.Subscription;
import vtcmer.sensors_test.model.commons.EnumSensorType;

/**
 * Created by vtcmer on 13/03/16.
 */
public abstract class AbstractSensorPresenter {


    /**
     * Contexto de la actividad
     */
    protected Context context;

    protected Subscription subscriptionSensor;


    /**
     * Gestor de sensores
     */
    protected SensorManager mSensorManager;

    public AbstractSensorPresenter(final Activity context){
        this.context = context;
        mSensorManager = (SensorManager) this.context.getSystemService(this.context.SENSOR_SERVICE);

    }


    /**
     * Devuelve el sensor específico en función del tipo de sensor específicado
     * @return
     */
    protected Sensor getSensor(final EnumSensorType sensorType){
        Sensor sensor = this.mSensorManager.getDefaultSensor(sensorType.getSensorType());
        return sensor;
    }






}
