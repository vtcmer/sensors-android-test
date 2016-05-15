package vtcmer.sensors_test.ui.presenter;


import vtcmer.sensors_test.ui.view.GyroscopeView;

/**
 * Created by vtcmer on 15/05/16.
 */
public interface GyroscopePresenter {

    /**
     * Se establece la vista
     * @param view
     */
    void setView(final GyroscopeView view);

    /**
     * Registro del servicio
     */
    void register();

    /**
     * unregister del servicio
     */
    void unregister();
}
