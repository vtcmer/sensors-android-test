package vtcmer.sensors_test.ui.view;

import vtcmer.sensors_test.model.SensorAccelerometerData;

/**
 * Created by vtcmer on 13/03/16.
 */
public interface AccelerometerView {


    /**
     * Actualización de las coordenadas en pantalla
     * @param sensorData
     */
    void updateCoordinates(final SensorAccelerometerData sensorData);


}
