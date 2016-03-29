package vtcmer.sensors_test.modules.ui.activity;

import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import vtcmer.sensors_test.MainActivity;
import vtcmer.sensors_test.ui.activity.Navigator;
import vtcmer.sensors_test.ui.presenter.MainPresenter;
import vtcmer.sensors_test.ui.presenter.impl.MainPresenterImpl;

/**
 * Created by vtcmer on 06/03/2016.
 */
@Module(
        injects = {
                MainActivity.class
        },
        complete = false)
public class MainActivityModule {


        @Provides
        @Singleton
        MainPresenter provideMainPresenter(Navigator navigator){
                return new MainPresenterImpl(navigator);
        }
}
