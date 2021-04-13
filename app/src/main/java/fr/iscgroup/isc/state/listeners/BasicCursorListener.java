package fr.iscgroup.isc.state.listeners;

import android.content.Context;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.RequiresApi;

import fr.iscgroup.isc.model.cursor.CursorLocation;

/**
 * @author emoqu on 25/01/2021
 * @project ISC
 */
public class BasicCursorListener extends CursorListener {
    /**
     * Instantiates a new Cursor listener.
     *
     * @param context the context
     */
    public BasicCursorListener(Context context) {
        super(new CursorLocation(-1,-1), context);

    }

    /**
     * TODO trouver le moyen de recuperer la position du curseur
     */
}
