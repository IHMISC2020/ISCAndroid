package fr.iscgroup.isc.state.listeners.tasks;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.util.TimerTask;

import fr.iscgroup.isc.factory.Factory;
import fr.iscgroup.isc.model.cursor.CursorLocation;
import fr.iscgroup.isc.state.CaptorCursorState;
import fr.iscgroup.isc.state.listeners.CaptorCursorListener;
import fr.iscgroup.isc.utils.Configuration;
import fr.iscgroup.isc.utils.JsonUtils;

/**
 * @author emoqu on 25/01/2021
 * @project ISC
 */
public class GetLocationFromServerTask extends TimerTask {
    private CaptorCursorListener listener;
    private Context context;

    public GetLocationFromServerTask(CaptorCursorListener listener, Context context) {
        this.listener = listener;
        this.context = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void run() {
        Factory.getConnexionFactory(this.context).sendData(Configuration.POSITION_POINTEUR_MESSAGE);
        String json = Factory.getConnexionFactory(this.context).readData();
        this.listener.setCursorLocation(JsonUtils.getCursorLocation(json));
        Log.e("---------------", "On a " + json);
    }
}
