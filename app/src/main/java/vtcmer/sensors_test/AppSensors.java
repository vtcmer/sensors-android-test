package vtcmer.sensors_test;

import android.app.Application;

import java.util.List;

import dagger.ObjectGraph;
import vtcmer.sensors_test.modules.RootModule;

/**
 * Created by vtcmer on 06/03/2016.
 */
public class AppSensors  extends Application {

    /**Grafo de objetos**/
    private ObjectGraph objectGraph;


    @Override
    public void onCreate() {
        super.onCreate();
        initInjection();

    }

    /**
     * Inyección del objeto
     * @param object
     */
    public void inject(Object object) {
        objectGraph.inject(object);
    }

    /**
     * Añade más módulos al grafo
     * @param modules
     * @return
     */
    public ObjectGraph plus(List<Object> modules){
        if (modules == null) {
            throw new IllegalArgumentException(
                    "You can't plus a null module, review your getModules() implementation");
        }
        return this.objectGraph.plus(modules.toArray());
    }




    /**
     * Función que se encarga de la inicialización del grafo
     */
    private void initInjection() {
        objectGraph = ObjectGraph.create(getModules());
        objectGraph.inject(this);
        objectGraph.injectStatics();
    }



    private Object[] getModules(){
        return new Object[]{new RootModule(this)};
    }
}
