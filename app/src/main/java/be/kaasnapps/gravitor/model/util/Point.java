package be.kaasnapps.gravitor.model.util;

/**
 * Created by jonas on 27/04/2015.
 */
public class Point {
    public double x;
    public double y;

    public Point(double x, double y){
       setX(x);
        setY(y);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double distanceTo(Point p){
        return Math.sqrt((p.getX() - x) * (p.getX() - x) + (p.getY() - y) * (p.getY() - y));
    }

    public void add(Vector vector){
        this.setX(this.getX() + vector.getX());
        this.setY(this.getY() + vector.getY());
    }
    public void add(Point point){
        this.setX(this.getX() + point.getX());
        this.setY(this.getY() + point.getY());
    }
}
