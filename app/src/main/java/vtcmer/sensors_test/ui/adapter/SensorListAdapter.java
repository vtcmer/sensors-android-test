package vtcmer.sensors_test.ui.adapter;

import android.content.Context;
import android.hardware.Sensor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import vtcmer.sensors_test.R;

/**
 * Created by vtcmer on 21/02/2016.
 */
public class SensorListAdapter extends BaseAdapter {

    private Context context;

    /**
     * Listado de sensores disponibles en el dispositivo
     */
    private List<Sensor> sensors;

    public SensorListAdapter(final Context context, final List<Sensor> sensors){
        this.context = context;
        this.sensors = sensors;
    }

    @Override
    public int getCount() {
        return sensors.size();
    }

    @Override
    public Object getItem(int position) {
        return sensors.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.sensor_item, parent, false);
        }

        Sensor sensor = this.sensors.get(position);

        TextView sensorName = (TextView) convertView.findViewById(R.id.idSensorName);
        sensorName.setText(sensor.getName());

        TextView sensorVendor = (TextView) convertView.findViewById(R.id.idSensorVendor);
        sensorVendor.setText(sensor.getVendor());

        TextView sensorVersion = (TextView) convertView.findViewById(R.id.idSensorVersion);
        sensorVersion.setText(String.valueOf(sensor.getVersion()));

        return convertView;
    }
}
