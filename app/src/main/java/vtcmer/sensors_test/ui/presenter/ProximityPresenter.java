package vtcmer.sensors_test.ui.presenter;

import vtcmer.sensors_test.ui.view.ProximimtyView;

/**
 * Created by vtcmer on 13/03/16.
 */
public interface ProximityPresenter {


    /**
     * Se establece la vista
     * @param view
     */
    void setView(final ProximimtyView view);

    /**
     * Registro del servicio
     */
    void register();

    /**
     * unregister del servicio
     */
    void unregister();
}
