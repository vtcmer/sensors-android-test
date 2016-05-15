package vtcmer.sensors_test.ui.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.InjectView;
import vtcmer.sensors_test.R;
import vtcmer.sensors_test.model.commons.SensorCoordinates;
import vtcmer.sensors_test.ui.presenter.GyroscopePresenter;
import vtcmer.sensors_test.ui.view.GyroscopeView;

public class GyroscopeActivity extends SensorActivity implements GyroscopeView {


    @Inject
    GyroscopePresenter presenter;


    @InjectView(R.id.maxX)TextView maxX;
    @InjectView(R.id.maxY)TextView maxY;
    @InjectView(R.id.maxZ)TextView maxz;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyroscope);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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
    public void updateCoordiantes(SensorCoordinates coordinates) {

        this.maxX.setText(String.valueOf(coordinates.getX()));
        this.maxY.setText(String.valueOf(coordinates.getY()));
        this.maxz.setText(String.valueOf(coordinates.getZ()));
    }
}
