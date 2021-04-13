package fr.iscgroup.isc.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import fr.iscgroup.isc.R;
import fr.iscgroup.isc.view.activity.enums.ActivityManager;
import fr.iscgroup.isc.view.listener.CalibrationListener;

/**
 * The type Calibration.
 */
public class Calibration extends AppCompatActivity {
    /**
     * The Instruction.
     */
    TextView instruction;
    /**
     * The Listener.
     */
    CalibrationListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calibration);

        // get view elements
        this.instruction = findViewById(R.id.instruction);

        // start the calibration by the listener
        this.listener = new CalibrationListener(this, setupCalibrationDisplayAction());
        ((ConstraintLayout) this.findViewById(R.id.background)).setOnTouchListener(this.listener);
    }

    /**
     * Setup an arraylist with all the graphics actions to do during the calibration
     *
     * @return ArrayList<Runnable> all the graphics actions
     */
    private ArrayList<Runnable> setupCalibrationDisplayAction() {
        ArrayList<Runnable> actions = new ArrayList<>();
        actions.add(() -> this.instruction.setText(R.string.calibration_point_one));               // Ask to setup first point
        actions.add(() -> this.instruction.setText(R.string.calibration_point_two));                // Ask to setup second point
        actions.add(() -> ActivityManager.ISO.startActivity(this));                                                      // Start the iso activity, calibration is done

        return actions;
    }
}