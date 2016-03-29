package vtcmer.sensors_test.modules.ui.activity;

import android.app.Activity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by vtcmer on 06/03/2016.
 */
@Module(library = true, complete = false)
public class ActivityModule {

    private final Activity activity;

    public ActivityModule(Activity activity){
        this.activity = activity;
    }


    @Provides
    @Named("ActivityContext")
    Activity provideActivityContext(){
        return this.activity;
    }
}
