package fr.iscgroup.isc.state;

import android.content.Context;
import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;

import fr.iscgroup.isc.model.cursor.CursorLocation;
import fr.iscgroup.isc.state.listeners.BasicCursorListener;

/**
 * The type Basic cursor listener.
 *
 * @author emoqu on 02/02/2021
 * @project ISC
 */
public class BasicCursorState extends CursorState {
    /**
     * The Listener.
     */
    protected BasicCursorListener listener;


    /**
     * Instantiates a new Basic cursor state.
     *
     * @param context    the context
     * @param next       the next
     * @param background the background
     */
    protected BasicCursorState(Context context, CursorState next, View background) {
        super(next);

        this.listener = new BasicCursorListener(context);
    }

    @Override
    public CursorLocation getCursorLocation() {
        return this.listener.getCursorLocation();
    }

    @Override
    public void stopUpdatingCursor() {

    }

    /**
     * Gets instance.
     *
     * @param context    the context
     * @param next       the next
     * @param background the background
     * @return the instance
     */
    public static BasicCursorState getInstance(Context context, CursorState next, View background) {
        if(instance == null)
            instance = new BasicCursorState(context, next, background);

        return (BasicCursorState) instance;
    }
}