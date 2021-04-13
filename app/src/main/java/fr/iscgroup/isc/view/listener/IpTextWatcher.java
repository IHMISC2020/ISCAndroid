package fr.iscgroup.isc.view.listener;

import fr.iscgroup.isc.view.data.ConnectionInfo;

/**
 * @author emoqu on 16/11/2020
 * @project ISC
 */
public class IpTextWatcher extends FieldTextWatcher{

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String res = ConnectionInfo.EMPTY_IP;
        if(s.length() > 0) res = s.toString();

        setChanged();
        notifyObservers(new ConnectionInfo(res));
    }
}
