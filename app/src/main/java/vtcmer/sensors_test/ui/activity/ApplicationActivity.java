package vtcmer.sensors_test.ui.activity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import dagger.ObjectGraph;
import vtcmer.sensors_test.AppSensors;
import vtcmer.sensors_test.modules.ui.activity.ActivityModule;

/**
 * Created by vtcmer on 06/03/2016.
 */
public abstract class  ApplicationActivity extends ActivityBase {

    private ObjectGraph objectGraph = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
    }


    /**
     * Inyección  de dependencias
     */
    private void injectDependencies() {
        AppSensors application = (AppSensors) getApplication();
        List<Object> modules = getModules();
        if (modules == null){
            modules = new ArrayList<Object>();
        }
        modules.add(new ActivityModule(this));
        this.objectGraph = application.plus(modules);
        this.objectGraph.inject(this);


    }

    /**
     * Devuelve la lista de módulos a cargar desde la Actividad específica
     * @return
     */
    protected abstract List<Object> getModules();
}
