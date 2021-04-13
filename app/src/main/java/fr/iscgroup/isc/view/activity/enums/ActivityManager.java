package fr.iscgroup.isc.view.activity.enums;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;

import androidx.core.util.Pair;

import java.util.ArrayList;

import fr.iscgroup.isc.view.activity.Calibration;
import fr.iscgroup.isc.view.activity.IsoActivity;
import fr.iscgroup.isc.view.activity.MainActivity;
import fr.iscgroup.isc.view.activity.ResultActivity;

/**
 * @author emoqu on 02/02/2021
 * @project ISC
 */
public enum ActivityManager {
    CALIBRATION(Calibration.class),
    ISO(IsoActivity.class),
    MAIN(MainActivity.class),
    RESULT(ResultActivity.class);

    private Class<?> v_class;

    ActivityManager(Class<?> v_class) {
        this.v_class = v_class;
    }

    /**
     * Start the activity
     *
     * @param context
     * @param extra
     */
    public void startActivity(Context context, ArrayList<Pair<String, Parcelable>> extra) {
        Intent intent = new Intent(context, this.v_class);

        if(extra != null)
            for(Pair<String, Parcelable> obj : extra)
                intent.putExtra(obj.first, obj.second);

        context.startActivity(intent);
    }

    /**
     * Start the activity
     *
     * @param context
     */
    public void startActivity(Context context) {
        this.startActivity(context, null);
    }
}
