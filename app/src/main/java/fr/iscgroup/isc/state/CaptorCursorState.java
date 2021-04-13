package fr.iscgroup.isc.state;

import android.content.Context;
import android.util.Log;

import fr.iscgroup.isc.model.cursor.CursorLocation;
import fr.iscgroup.isc.state.listeners.CaptorCursorListener;

/**
 * The type Captor cursor state.
 *
 * @author emoqu on 02/02/2021
 * @project ISC
 */
public class CaptorCursorState extends CursorState {

    /**
     * The Listener.
     */
    protected CaptorCursorListener listener;
    protected boolean isInit;


    /**
     * Instantiates a new Captor cursor state.
     *
     * @param context the context
     * @param next    the next
     */
    protected CaptorCursorState(Context context, CursorState next) {
        super(next);
        this.listener = new CaptorCursorListener(context);
        this.isInit = false;
    }

    @Override
    public CursorLocation getCursorLocation() {
        if(!this.isInit){
            this.listener.initTask();
            this.isInit = true;
        }
        return this.listener.getCursorLocation();
    }

    @Override
    public void stopUpdatingCursor() {
        this.listener.stopTask();
    }

    /**
     * Gets instance.
     *
     * @param context the context
     * @param next    the next
     * @return the instance
     */
    public static CaptorCursorState getInstance(Context context, CursorState next) {
        if(instance == null || !(instance instanceof CaptorCursorState))
            instance = new CaptorCursorState(context, next);

        return (CaptorCursorState) instance;
    }
}
