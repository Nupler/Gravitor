package be.kaasnapps.gravitor.model;

import be.kaasnapps.gravitor.model.util.Point;

/**
 * Created by jonas on 24/04/2015.
 */
public class Planet extends RoundPhysicalObject {
    //test

    public GravityField getGravityField() {
        return gravityField;
    }

    public void setGravityField(GravityField gravityField) {
        this.gravityField = gravityField;
    }

    private GravityField gravityField;

    public Planet(Point location, double radius) {
        super(location, radius);
        gravityField = new GravityField(location,radius*8);
    }
}
