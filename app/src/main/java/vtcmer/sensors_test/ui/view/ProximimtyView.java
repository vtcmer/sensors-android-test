package vtcmer.sensors_test.ui.view;

import vtcmer.sensors_test.model.SensorAccelerometerData;
import vtcmer.sensors_test.model.commons.SensorCoordinates;

/**
 * Created by vtcmer on 13/03/16.
 */
public interface ProximimtyView {


    /**
     * Actualización de las coordenadas en pantalla
     * @param sensorData
     */
    void updateCoordinates(final SensorCoordinates sensorData);


}
