package vtcmer.sensors_test.ui.activity;

import android.app.Activity;
import android.content.Intent;

import javax.inject.Inject;
import javax.inject.Named;

import vtcmer.sensors_test.model.commons.EnumSensorType;

/**
 * Created by vtcmer on 06/03/2016.
 */
public class Navigator {

    private final Activity context;

    @Inject
    public Navigator(@Named("ActivityContext") Activity context) {
        this.context = context;
    }

    public void redirect(EnumSensorType sensorType){

        if (sensorType.equals(EnumSensorType.ACCELERATION)){
            redirectToAccelerometerView();
        } else if (sensorType.equals(EnumSensorType.PROXIMITY)){
            redirectToProximityView();
        }


    }


    private void redirectToAccelerometerView(){
        Intent intent = new Intent(this.context, AccelerometerActivity.class);
        this.startActivity(intent);
    }

    private void redirectToProximityView(){
        Intent intent = new Intent(this.context, ProximityActivity.class);
        this.startActivity(intent);
    }


    private void startActivity(Intent intent) {
        context.startActivity(intent);
    }
}
