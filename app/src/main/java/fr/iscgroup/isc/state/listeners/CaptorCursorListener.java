package fr.iscgroup.isc.state.listeners;

import android.content.Context;
import android.os.Parcel;

import java.util.Timer;

import fr.iscgroup.isc.model.cursor.CursorLocation;
import fr.iscgroup.isc.state.CaptorCursorState;
import fr.iscgroup.isc.state.listeners.tasks.GetLocationFromServerTask;

/**
 * @author emoqu on 25/01/2021
 * @project ISC
 */
public class CaptorCursorListener extends CursorListener {
    private Timer timer;

    /**
     * Instantiates a new Cursor listener.
     *
     * @param context the context
     */
    public CaptorCursorListener(Context context) {
        super(new CursorLocation(0,0), context);

        // Create a timer that get and update cursor location from server every 10ms
        timer = new Timer();
    }

    public void initTask() {
        timer.schedule(new GetLocationFromServerTask(this, this.context), 30, 16);
    }

    public void stopTask() {
        this.timer.cancel();
    }
}
