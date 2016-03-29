package vtcmer.sensors_test.ui.presenter;

import vtcmer.sensors_test.ui.view.AccelerometerView;

/**
 * Created by vtcmer on 13/03/16.
 */
public interface AccelerometerPresenter {


    /**
     * Se establece la vista
     * @param view
     */
    void setView(final AccelerometerView view);

    /**
     * Registro del servicio
     */
    void register();

    /**
     * unregister del servicio
     */
    void unregister();
}
