package vtcmer.sensors_test;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import vtcmer.sensors_test.listener.SensorAccelerometerListener;
import vtcmer.sensors_test.model.commons.EnumSensorType;
import vtcmer.sensors_test.modules.ui.activity.MainActivityModule;
import vtcmer.sensors_test.ui.activity.ApplicationActivity;
import vtcmer.sensors_test.ui.adapter.SensorListAdapter;
import vtcmer.sensors_test.ui.presenter.MainPresenter;
import vtcmer.sensors_test.ui.view.MainView;

public class MainActivity extends ApplicationActivity implements MainView {


    private SensorListAdapter sensorListAdapter;

    private SensorManager mSensorManager;

    @Inject
    MainPresenter presenter;


    @InjectView(R.id.sensorList)ListView sensorListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.initialize();

    }

    @Override
    protected List<Object> getModules() {
        List<Object> modules = new ArrayList<Object>();
        modules.add(new MainActivityModule());
        return modules;
    }


    @Override
    public void onResume() {
        super.onResume();
        this.refreshSensors();
    }


    /**
     * Inicialización de elementos de la actividad
     */
    private void initialize(){

        ButterKnife.inject(this);


        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        this.presenter.setView(this);

        sensorListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Sensor sensor = (Sensor) parent.getItemAtPosition(position);

                EnumSensorType sensorType = EnumSensorType.findByName(sensor.getName());
                presenter.renderSensor(sensorType);



            }
        });

    }

    private void refreshSensors(){

        if (this.sensorListAdapter == null){
            List<Sensor> sensors= mSensorManager.getSensorList(Sensor.TYPE_ALL);
            this.sensorListAdapter = new SensorListAdapter(this,sensors);
            this.sensorListView.setAdapter(this.sensorListAdapter);
        }else{
            this.sensorListAdapter.notifyDataSetChanged();
        }


    }



}
