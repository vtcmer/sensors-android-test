package vtcmer.sensors_test.model;

import vtcmer.sensors_test.model.commons.SensorCoordinates;

/**
 * Created by vtcmer on 13/03/16.
 */
public class SensorAccelerometerData extends SensorCoordinates {

    float movement = 0;


    public float getMovement() {
        return movement;
    }

    public void setMovement(float movement) {
        this.movement = movement;
    }
}
