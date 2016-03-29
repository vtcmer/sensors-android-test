package vtcmer.sensors_test.ui.activity;

import android.os.Bundle;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.InjectView;
import vtcmer.sensors_test.R;
import vtcmer.sensors_test.model.SensorAccelerometerData;
import vtcmer.sensors_test.ui.presenter.AccelerometerPresenter;
import vtcmer.sensors_test.ui.view.AccelerometerView;

public class AccelerometerActivity  extends SensorActivity implements AccelerometerView {


    @Inject
    AccelerometerPresenter presenter;


    @InjectView(R.id.maxX)TextView maxX;
    @InjectView(R.id.maxY)TextView maxY;
    @InjectView(R.id.maxZ)TextView maxz;
    @InjectView(R.id.movement)TextView movement;


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
    public void updateCoordinates(SensorAccelerometerData sensorData) {

        this.maxX.setText(String.valueOf(sensorData.getX()));
        this.maxY.setText(String.valueOf(sensorData.getY()));
        this.maxz.setText(String.valueOf(sensorData.getZ()));
        this.movement.setText(String.valueOf(sensorData.getMovement()));

    }
}
