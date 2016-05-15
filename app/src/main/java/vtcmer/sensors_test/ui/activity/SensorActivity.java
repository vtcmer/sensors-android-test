package vtcmer.sensors_test.ui.activity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import vtcmer.sensors_test.modules.ui.activity.SensorActivityModule;

/**
 * Created by vtcmer on 06/03/2016.
 */
public abstract class SensorActivity extends ApplicationActivity{


    //protected SensorManager mSensorManager;

    //protected EnumSensorType sensorType;

    /**
     * Inicialización
     */
    protected void initialize(){
        ButterKnife.inject(this);
        //this.setSensorType();
        //mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    }


    /**
     * Recuperación del tipo de sensor concreto
     * @return

    protected Sensor getSensor(){
        Sensor sensor = this.mSensorManager.getDefaultSensor(this.sensorType.getSensorType());
        return sensor;
    }
     */

    /**
     * Establece el tipo de sensor
     */
    //protected abstract void setSensorType();

    @Override
    protected List<Object> getModules() {
        List<Object> modules = new ArrayList<Object>();
        modules.add(new SensorActivityModule());
        return modules;
    }
}
