package be.kaasnapps.gravitor.model.util;

/**
 * Created by jonas on 20/04/2015.
 */
public class Vector {

    private double x;
    private double y;

    public Vector(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Vector (Point from, Point to){
        this.x = to.x - from.x;
        this.y = to.y - from.y;
    }

    public double getY() {
        return y;
    }

    public void setY(double yDir) {
        this.y = yDir;
    }

    public double getX() {
        return x;
    }

    public void setX(double xDir) {
        this.x = xDir;
    }

    public void normalize(){
        setX((float) (x/getLength()));
        setY((float) (y/getLength()));
    }

    public double getLength(){
        return Math.sqrt(x*x + y*y);
    }

    public void addVector(Vector vector){
        this.setX(this.x+vector.getX());
        this.setY(this.y+vector.getY());
    }







}
