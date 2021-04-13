package fr.iscgroup.isc.state.listeners;

import android.content.Context;
import android.os.Parcelable;

import fr.iscgroup.isc.model.cursor.CursorLocation;

/**
 * The type Cursor listener.
 *
 * @author emoqu on 25/01/2021
 * @project ISC
 */
public abstract class CursorListener {
    /**
     * The Cursor location.
     */
    protected CursorLocation cursorLocation;
    /**
     * The Context.
     */
    protected Context context;

    /**
     * Instantiates a new Cursor listener.
     *
     * @param cursorLocation the cursor location
     * @param context        the context
     */
    public CursorListener(CursorLocation cursorLocation, Context context) {
        this.cursorLocation = cursorLocation;
        this.context = context;
    }

    /**
     * Gets cursor location.
     *
     * @return the cursor location
     */
    public CursorLocation getCursorLocation() {
        return cursorLocation;
    }

    /**
     * Sets cursor location.
     *
     * @param cursorLocation the cursor location
     */
    public void setCursorLocation(CursorLocation cursorLocation) {
        this.cursorLocation = cursorLocation;
    }
}
