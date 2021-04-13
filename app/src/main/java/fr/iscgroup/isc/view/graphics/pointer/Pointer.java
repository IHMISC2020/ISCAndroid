package fr.iscgroup.isc.view.graphics.pointer;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.util.Log;
import android.view.Display;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;

import java.util.Timer;

import fr.iscgroup.isc.model.cursor.CursorLocation;
import fr.iscgroup.isc.state.factory.CursorStateFactory;
import fr.iscgroup.isc.view.activity.IsoActivity;
import fr.iscgroup.isc.view.graphics.tasks.PointerUpdateTask;

/**
 * The type Pointer.
 *
 * @author emoqu on 02/02/2021
 * @project ISC
 */
public class Pointer implements IPointer {
    /**
     * The constant instance.
     */
    public static Pointer instance;

    private Context context;
    private ImageView pointer;
    private Point size;
    private Timer timer;

    /**
     * Pointer contructor
     * @param context
     * @param pointer
     * @param display
     */
    private Pointer(Context context, ImageView pointer, Display display) {
        this.context = context;
        this.pointer = pointer;
        this.size = new Point(0,0);
        display.getSize(this.size);

        timer = new Timer();
        timer.schedule(new PointerUpdateTask(this::updatePointerLocationByState), 10, 16);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void updatePointerLocationByState() {
        // Get the pointer location based on the state of the application
        CursorLocation location =
                CursorStateFactory.getInstance(this.context).getState().getCursorLocation();

        this.updatePointerLocation(size.x * location.getX() + ((float)this.pointer.getWidth() / 2), size.y * location.getY() + ((float)this.pointer.getHeight() / 2));
    }

    @Override
    public void updatePointerLocation(double x, double y) {
        Log.e("POINTERRRRRRRRR", "ICI: " + x + " et " + y);
        // Update cursor location
        IsoActivity.HANDLER.post(() -> {
            this.pointer.setX((float) x);
            this.pointer.setY((float) y);
            this.pointer.bringToFront();
        });
        this.pointer.refreshDrawableState();
    }

    /**
     * Initialize the pointer.
     *
     * @param context the context
     * @param pointer the pointer
     * @param display the display
     * @return the pointer
     */
    public static Pointer initPointer(Context context, ImageView pointer, Display display) {
       instance = new Pointer(context, pointer, display);
        return instance;
    }

    public void removePointer() {
        this.timer.cancel();
    }

    /**
     * Gets pointer.
     *
     * @return the pointer
     */
    public static Pointer getPointer() {
        if(instance == null)
            throw new NullPointerException("The pointer need to be initialize by using initPointer");
        return instance;
    }
}
