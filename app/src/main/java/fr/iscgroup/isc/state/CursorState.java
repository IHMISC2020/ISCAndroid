package fr.iscgroup.isc.state;

import fr.iscgroup.isc.model.cursor.CursorLocation;

/**
 * The type Cursor state.
 *
 * @author emoqu on 25/01/2021
 * @project ISC
 */
public abstract class CursorState {
    protected static CursorState instance;
    /**
     * The Next.
     */
    protected CursorState next;

    /**
     * Instantiates a new Cursor state.
     *
     * @param next the next
     */
    protected CursorState(CursorState next) {
        this.next = next;
    }

    /**
     * Gets cursor location.
     *
     * @return the cursor location
     */
    public abstract CursorLocation getCursorLocation();

    public abstract void stopUpdatingCursor();

    /**
     * Gets next.
     *
     * @return the next
     */
    public CursorState getNext() {
        return next;
    }

    /**
     * Sets next.
     *
     * @param next the next
     */
    public void setNext(CursorState next) {
        if(next != null) this.next = next;
    }
}
