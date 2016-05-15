package vtcmer.sensors_test.ui.presenter.impl;

import javax.inject.Inject;

import vtcmer.sensors_test.model.commons.EnumSensorType;
import vtcmer.sensors_test.ui.activity.Navigator;
import vtcmer.sensors_test.ui.presenter.MainPresenter;
import vtcmer.sensors_test.ui.view.MainView;

/**
 * Created by vtcmer on 06/03/2016.
 */
public class MainPresenterImpl implements MainPresenter{

    /**
     * Referencia a la vista activa
     */
    private MainView mainView = null;

    private Navigator navigator = null;

    @Inject
    public MainPresenterImpl(final Navigator navigator){
        this.navigator = navigator;
    }

    @Override
    public void setView(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void renderSensor(EnumSensorType sensorType) {
        this.navigator.redirect(sensorType);
    }



}
