package fr.iscgroup.isc.view.listener;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.View.OnClickListener;

import androidx.annotation.RequiresApi;

import fr.iscgroup.isc.state.factory.CursorStateFactory;
import fr.iscgroup.isc.state.factory.StateType;
import fr.iscgroup.isc.view.activity.enums.ActivityManager;

/**
 * @author emoqu on 11/01/2021
 * @project ISC
 */
public class CursorClickListener implements OnClickListener {
    private final Context context;
    private View background;

    public CursorClickListener(Context context, View background) {
        this.context = context;
        this.background = background;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View v) {
        // Setup the state
        CursorStateFactory.getInstance(this.context, background).setState(StateType.CAPTOR);

        // Start the iso activity
        ActivityManager.CALIBRATION.startActivity(this.context);
    }
}
