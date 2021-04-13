package fr.iscgroup.isc.state.factory;

import android.content.Context;
import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;

import fr.iscgroup.isc.state.BasicCursorState;
import fr.iscgroup.isc.state.CaptorCursorState;
import fr.iscgroup.isc.state.CursorState;

/**
 * The type Cursor state factory.
 *
 * @author emoqu on 02/02/2021
 * @project ISC
 */
public class CursorStateFactory {
    /**
     * The constant instance.
     */
    private static CursorStateFactory instance;

    /**
     * The current state.
     */
    private CursorState state, basicState, captorState;

    private View background;

    private CursorStateFactory(Context context, View background) {
        this.background = background;

        // Setup Basic state with
        this.basicState = BasicCursorState.getInstance(
                context, null, this.background);

        this.captorState = CaptorCursorState.getInstance(
                context, this.basicState);

        this.basicState.setNext(this.basicState);
        this.state = this.basicState;
    }

    /**
     * Sets state.
     *
     * @param type the type
     * @return the state
     */
    public void setState(StateType type) {
        // Setup the state to return
        switch (type) {
            case BASIC:
                this.state = basicState;
                break;
            case CAPTOR:
                this.state = captorState;
                break;
        }
    }

    public CursorState getState() {
        return this.state;
    }

    public void setBackground(View background) {
        this.background = background;
    }

    /**
     * Gets instance.
     *
     * @param context the context
     * @return the instance
     */
    public static CursorStateFactory getInstance(Context context, View background) {
        if(instance == null)
            instance = new CursorStateFactory(context, background);

        return instance;
    }

    /**
     * Gets instance without background
     * /!\ background need to be set after by using setBackground
     *
     * @param context the context
     * @return the instance
     */
    public static CursorStateFactory getInstance(Context context) {
        if(instance == null)
            instance = new CursorStateFactory(context, null);

        return instance;
    }
}
