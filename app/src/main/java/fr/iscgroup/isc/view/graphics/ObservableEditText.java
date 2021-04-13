package fr.iscgroup.isc.view.graphics;

import android.content.Context;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.Observer;

import fr.iscgroup.isc.view.listener.FieldTextWatcher;

/**
 * @author emoqu on 16/11/2020
 * @project ISC
 */
public class ObservableEditText {
    private EditText edt;
    private FieldTextWatcher watcher;

    public ObservableEditText(EditText edt, FieldTextWatcher watcher) {
        this.edt = edt;
        this.watcher = watcher;
        this.edt.addTextChangedListener(watcher);
    }

    public void addObserver(Observer observer) {
        this.watcher.addObserver(observer);
    }
}
