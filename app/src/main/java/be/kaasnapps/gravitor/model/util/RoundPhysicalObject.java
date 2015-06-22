package be.kaasnapps.gravitor.model.util;

/**
 * Created by jonas on 24/04/2015.
 */
public class RoundPhysicalObject {

    private Point location;
    private double radius;

    public RoundPhysicalObject(Point location, double radius) {
        setLocation(location);
        setRadius(radius);
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public boolean intersects(RoundPhysicalObject other) {
        double distance = location.distanceTo(other.getLocation());
         boolean result = false;
        if (distance < this.getRadius() + other.getRadius()){
            result = true;
        }
        return result;
    }




}
