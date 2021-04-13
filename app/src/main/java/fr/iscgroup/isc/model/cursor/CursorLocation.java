package fr.iscgroup.isc.model.cursor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * The Location of the cursor.
 *
 * @author emoqu on 25/01/2021
 * @project ISC
 */
public class CursorLocation {
    private double x, y;

    /**
     * Instantiates a new Cursor location.
     *
     * @param x the x
     * @param y the y
     */
    public CursorLocation(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * Sets x.
     *
     * @param x the x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public double getY() {
        return y;
    }

    /**
     * Sets y.
     *
     * @param y the y
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Sets location.
     *
     * @param x the x
     * @param y the y
     */
    public void setLocation(double x, double y) {
        this.setX(x);
        this.setY(y);
    }

    @NonNull
    @Override
    public String toString() {
        return "{ X:" + this.getX() + ", Y:" + this.getY() + " }";
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        try {
            CursorLocation loc = (CursorLocation) obj;
            return loc.getX() == this.getX() && loc.getY() == this.getY();
        } catch (Exception e) {
            return false;
        }
    }
}
