package vtcmer.sensors_test.modules;

import android.content.Context;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import vtcmer.sensors_test.AppSensors;

/**
 * Created by vtcmer on 06/03/2016.
 */
@Module(
        injects = {
                AppSensors.class
        },
        library = true)
public class RootModule {

    private Context context = null;

    public RootModule(Context context){

        this.context = context;
    }

    @Provides
    @Named("ApplicationContext")
    Context provideApplicationContext(){

        return this.context;
    }
}
