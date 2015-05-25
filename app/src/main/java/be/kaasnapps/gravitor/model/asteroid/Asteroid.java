package be.kaasnapps.gravitor.model.asteroid;


import be.kaasnapps.gravitor.model.RoundPhysicalObject;
import be.kaasnapps.gravitor.model.util.Point;
import be.kaasnapps.gravitor.model.util.Vector;

/**
 * Created by jonas on 20/04/2015.
 */
public class Asteroid extends RoundPhysicalObject {

    private double speed;
    private Vector direction;


    public double getSpeed() {
        return speed;
    }

    public Asteroid(double speed, Point location,double radius, Vector direction){
        super(location,radius);
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
