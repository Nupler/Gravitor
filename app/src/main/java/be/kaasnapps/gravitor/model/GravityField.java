package be.kaasnapps.gravitor.model;

import be.kaasnapps.gravitor.model.util.Point;
import be.kaasnapps.gravitor.model.util.RoundPhysicalObject;

/**
 * test
 * Created by jonas on 6/04/2015.
 */
public class GravityField extends RoundPhysicalObject {
    public static final double GROW_SPEED = 6;
    public static final double SHRINK_SPEED = 2;

    public GravityField(Point location, double radius) {
        super(location, radius);
    }

    public void grow() {
        setRadius(getRadius() + GROW_SPEED);
    }

    /**
     * shrinks the size of this gravitywell with decrementor SHRINK_SPEED, when size < 0 returns true
     */
    public boolean shrink() {
        boolean result = false;
        setRadius(getRadius() - SHRINK_SPEED);
        if (getRadius() < 0) {
            result = true;
        }
        return result;
    }



}
