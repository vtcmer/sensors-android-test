package vtcmer.sensors_test.modules.ui.activity;

import android.app.Activity;
import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import vtcmer.sensors_test.listener.SensorAccelerometerListener;
import vtcmer.sensors_test.listener.SensorProximityListener;
import vtcmer.sensors_test.ui.activity.AccelerometerActivity;
import vtcmer.sensors_test.ui.activity.ProximityActivity;
import vtcmer.sensors_test.ui.presenter.AccelerometerPresenter;
import vtcmer.sensors_test.ui.presenter.ProximityPresenter;
import vtcmer.sensors_test.ui.presenter.impl.AccelerometerPresenterImpl;
import vtcmer.sensors_test.ui.presenter.impl.ProximityPresenterImpl;


/**
 * Created by vtcmer on 06/03/2016.
 */
@Module(
        injects = {
                AccelerometerActivity.class,
                ProximityActivity.class
        },
        complete = false,library = true)
public class SensorActivityModule {


        @Provides
        AccelerometerPresenter AccelerometerPresenter(final @Named("ActivityContext") Activity context, final SensorAccelerometerListener sensorAccelerometerListener){
                return new AccelerometerPresenterImpl(context, sensorAccelerometerListener);
        }


        @Provides
        ProximityPresenter ProximityPresenter(final @Named("ActivityContext") Activity context, final SensorProximityListener sensorProximityListener){
                return new ProximityPresenterImpl(context, sensorProximityListener);
        }



}
