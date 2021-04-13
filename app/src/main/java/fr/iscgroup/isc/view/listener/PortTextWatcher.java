package fr.iscgroup.isc.view.listener;

import android.util.Log;

import fr.iscgroup.isc.view.data.ConnectionInfo;

/**
 * @author emoqu on 16/11/2020
 * @project ISC
 */
public class PortTextWatcher extends FieldTextWatcher {
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        int port = ConnectionInfo.EMPTY_PORT;
        if(s.length() > 0) port = Integer.parseInt(s.toString());

        setChanged();
        notifyObservers(new ConnectionInfo(port));
    }
}
