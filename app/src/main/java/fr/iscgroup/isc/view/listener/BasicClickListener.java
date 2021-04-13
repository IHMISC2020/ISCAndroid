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
public class BasicClickListener implements OnClickListener {
    private final Context context;
    private View background;

    public BasicClickListener(Context context, View background) {
        this.context = context;
        this.background = background;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View v) {
        // Setup the state
        CursorStateFactory.getInstance(this.context, this.background).setState(StateType.BASIC);

        // Start the iso activity
        ActivityManager.ISO.startActivity(this.context);
    }
}
