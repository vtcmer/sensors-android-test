package vtcmer.sensors_test.model.commons;

import android.hardware.Sensor;

/**
 * Created by vtcmer on 06/03/2016.
 */
public enum EnumSensorType {

    ACCELERATION("K3DH Acceleration Sensor", Sensor.TYPE_ACCELEROMETER),
    MAGNETIC("AK8975 Magnetic field Sensor",Sensor.TYPE_MAGNETIC_FIELD),
    LISHT("CM3663 Light Sensor",Sensor.TYPE_LIGHT),
    PROXIMITY("CM3663 Proximity Sensor", Sensor.TYPE_PROXIMITY),
    GYROSCOPE("K3G Gyroscope Sensor", Sensor.TYPE_GYROSCOPE),
    ROTATION("Rotation Vector Sensor", Sensor.TYPE_ROTATION_VECTOR),
    GRAVITY("Gravity Sensor", Sensor.TYPE_GRAVITY),
    LINEAR_ACCELERATION ("Linear Acceleration Sensor", Sensor.TYPE_LINEAR_ACCELERATION),
    ORIENTATION("Orientation Sensor",Sensor.TYPE_ORIENTATION),
    CORRECTED_GYROSCOPE("Corrected Gyroscope Sensor", Sensor.TYPE_GYROSCOPE_UNCALIBRATED);




    private String name;

    private int sensorType;

    EnumSensorType(String name, int sensorType) {
        this.name = name;
        this.sensorType = sensorType;
    }

    public String getName() {
        return name;
    }

    public int getSensorType() {
        return sensorType;
    }

    /**
     * Búsqueda del tipo enumerado por el nombre
     * @param name
     * @return
     */
    public static EnumSensorType findByName(final String name){
        EnumSensorType sensor = null;

        for (EnumSensorType val : EnumSensorType.values()) {
            if (val.name.equals(name)){
                sensor = val;
                break;
            }
        }

        return sensor;
    }



}
