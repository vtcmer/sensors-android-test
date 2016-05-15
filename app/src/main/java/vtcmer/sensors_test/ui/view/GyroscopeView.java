package vtcmer.sensors_test.ui.view;

import vtcmer.sensors_test.model.commons.SensorCoordinates;

/**
 * Created by vtcmer on 15/05/16.
 */
public interface GyroscopeView {

    /**
     * Método que se encarga de actualizar las coordenadas en la vista
     * @param coordinates
     */
    void updateCoordiantes(final SensorCoordinates coordinates);
}
