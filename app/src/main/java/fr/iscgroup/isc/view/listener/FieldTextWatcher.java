package fr.iscgroup.isc.view.listener;

import android.text.Editable;
import android.text.TextWatcher;

import java.util.Observable;

import fr.iscgroup.isc.view.data.ConnectionInfo;

/**
 * @author emoqu on 16/11/2020
 * @project ISC
 */
public class FieldTextWatcher extends Observable implements TextWatcher {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(count > 0) {
            setChanged();
            notifyObservers(s.toString());
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
