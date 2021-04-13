package fr.iscgroup.isc.view.listener;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

import fr.iscgroup.isc.factory.Factory;
import fr.iscgroup.isc.utils.Configuration;

/**
 * The type Calibration listener.
 *
 * @author emoqu on 02/02/2021
 * @project ISC
 */
public class CalibrationListener implements View.OnTouchListener {
    private Context context;
    private ArrayList<Runnable> actions;
    private int state;

    /**
     * Instantiates a new Calibration listener.
     *
     * @param context the context
     * @param actions the actions
     */
    public CalibrationListener(Context context, ArrayList<Runnable> actions) {
        this.context = context;
        this.actions = actions;
        this.state = 0;

        this.executeDisplayAction();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Thread warnServer = new Thread(() -> {
            // Warn the server that the point need to be set
            Factory.getConnexionFactory(context).sendData(Configuration.CALIBRATION_POINT_ADD_MESSAGE);
        });

        // Update display when server is warn
        try {
            warnServer.start();
            warnServer.join();
            this.executeDisplayAction();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void executeDisplayAction() {
        this.actions.get(this.state).run();
        this.state++;
    }
}
