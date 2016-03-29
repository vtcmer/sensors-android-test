package vtcmer.sensors_test.ui.activity;

import android.os.Bundle;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.InjectView;
import vtcmer.sensors_test.R;
import vtcmer.sensors_test.model.SensorAccelerometerData;
import vtcmer.sensors_test.model.commons.SensorCoordinates;
import vtcmer.sensors_test.ui.presenter.AccelerometerPresenter;
import vtcmer.sensors_test.ui.presenter.ProximityPresenter;
import vtcmer.sensors_test.ui.view.AccelerometerView;
import vtcmer.sensors_test.ui.view.ProximimtyView;

public class ProximityActivity extends SensorActivity implements ProximimtyView {


    @Inject
    ProximityPresenter presenter;


    @InjectView(R.id.maxX)TextView maxX;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);
        this.initialize();
    }


    /**
     * Inicialización de elementos de la actividad
     */
    @Override
    protected void initialize(){
        super.initialize();
        this.presenter.setView(this);


    }

    @Override
    protected void onResume() {
        super.onResume();
        this.presenter.register();
    }

    @Override
    protected void onPause() {
        this.presenter.unregister();
        super.onPause();
    }

    @Override
    protected void onStop() {

        this.presenter.unregister();
        super.onStop();
    }


    @Override
    public void updateCoordinates(SensorCoordinates sensorData) {

        this.maxX.setText(String.valueOf(sensorData.getX()));


    }
}
