package vtcmer.sensors_test.ui.presenter;

import android.hardware.Sensor;

import vtcmer.sensors_test.model.commons.EnumSensorType;
import vtcmer.sensors_test.ui.view.MainView;

/**
 * Created by vtcmer on 06/03/2016.
 */
public interface MainPresenter {

    /**
     * Se establece la vista
     * @param mainView
     */
    void setView(final MainView mainView);

    /**
     * Renderización del sensor correspondiente
     * @param sensorType
     */
    void renderSensor(final EnumSensorType sensorType);
}
