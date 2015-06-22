package be.kaasnapps.gravitor.model.ship;

import be.kaasnapps.gravitor.model.util.Point;
import be.kaasnapps.gravitor.model.util.RoundPhysicalObject;
import be.kaasnapps.gravitor.model.util.Vector;

/**
 * Created by Jan Vandendriessche on 22/06/2015.
 */
public class Ship extends RoundPhysicalObject {

    private double speed;
    private Vector direction;

    //public double getSpeed() {
    //    return speed;
    //}
//
    //public void setSpeed(double speed) {
    //    this.speed = speed;
    //}
//
    //public Vector getDirection() {
    //    return direction;
    //}
//
    //public void setDirection(Vector direction) {
    //    this.direction = direction;
    //}

    public Ship(double speed, Point location,double radius, Vector direction) {
        super(location, radius);
        this.direction = direction;
        this.speed = speed;
    }

    public void changeDirection(Vector changer){
        direction.addVector(changer);
        direction.normalize();
    }

    public void updateLocation(){
        this.getLocation().setX(this.getLocation().getX() + speed * direction.getX());
        this.getLocation().setY(this.getLocation().getY() + speed * direction.getY());
    }


}
