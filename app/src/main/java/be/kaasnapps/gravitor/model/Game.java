package be.kaasnapps.gravitor.model;

import android.content.Context;
import android.media.MediaPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import be.kaasnapps.gravitor.R;
import be.kaasnapps.gravitor.model.asteroid.Asteroid;
import be.kaasnapps.gravitor.model.util.Point;
import be.kaasnapps.gravitor.model.util.Vector;

/**
 * Created by jonas on 6/04/2015.
 */
public class Game {

    private List<GravityField> gravityFields = new ArrayList<GravityField>();
    private Planet planet;
    private GravityField currentWell;
    private int width, height;
    private List<Asteroid> asteroids = new ArrayList<>();
    private int score;
    private MediaPlayer mediaPlayer;

    public Planet getPlanet() {
        return planet;
    }

    public void setPlanet(Planet planet) {
        this.planet = planet;
    }

    public Game(int width, int height, Context context) {
        Point planetLocation = new Point(width / 2, height / 2);
        planet = new Planet(planetLocation, 40);
        this.width = width;
        this.height = height;
        addAsteroid();
        score=0;
        mediaPlayer = MediaPlayer.create(context, R.raw.pang);
    }

    public void addGravityWell(GravityField gravityWell) {
        gravityFields.add(gravityWell);
        currentWell = gravityWell;
    }

    public void addAsteroid() {
        Vector direction = new Vector(0.5, 0.5);
        direction.normalize();
        Point asteroidLocation = new Point(0, 0);
        Asteroid asteroid = new Asteroid(3, asteroidLocation, 10, direction);
        asteroids.add(asteroid);
    }

    public void closeLastOpenedGravityWell() {
        currentWell = null;
    }

    public void tick() {
        updateGravityWells();

        for (Asteroid a : asteroids) {
            a.updateLocation();
            if (planet.getGravityField().intersects(a)) {
                Vector gravity = new Vector(a.getLocation(), planet.getLocation());
                gravity.normalize();
                gravity.setX(gravity.getX() / 60);
                gravity.setY(gravity.getY() / 60);
                a.changeDirection(gravity);
            }
        }

        for (GravityField gravityField : gravityFields) {
            for(Asteroid asteroid : asteroids){
                if(gravityField.intersects(asteroid)){
                    Vector gravity = new Vector(asteroid.getLocation(), gravityField.getLocation());
                    gravity.normalize();
                    gravity.setX(gravity.getX() / 20);
                    gravity.setY(gravity.getY() / 20);
                    asteroid.changeDirection(gravity);
                }
            }

        }
        //Collisions
            //for each asteroid check intersect
        List<Asteroid> toRemove = new ArrayList<Asteroid>();
        for (Asteroid a : asteroids){
            for (Asteroid b : asteroids){
                if(!a.equals(b)){
                    if (a.intersects(b)){
                        toRemove.add(a);
                        score+=1;
                        mediaPlayer.start();
                    }
                }
            }
        }
            //for each asteroid, check planet intersect
        for (Asteroid a : asteroids){
            if (a.intersects(planet)){
                toRemove.add(a);
                score-=1;
                mediaPlayer.start();
            }

        }
            //delete asteroids
        for(Asteroid toRem : toRemove){
            asteroids.remove(toRem);
        }

        Random r = new Random();
        int misschien = r.nextInt(30);
        if (misschien == 3) {
            addAsteroid();
        }
    }
        //Tick
    private void updateGravityWells() {
        List<GravityField> toRemoveWells = new ArrayList<>();

        for (GravityField w : gravityFields) {
            if (w == currentWell) {
                w.grow();
            } else {
                if (w.shrink()) {
                    toRemoveWells.add(w);
                }
            }
        }

        for (GravityField w : toRemoveWells) {
            gravityFields.remove(w);
        }
    }

    public List<GravityField> getGravityFields() {
        return gravityFields;
    }

    public List<Asteroid> getAsteroids() {
        return asteroids;
    }

    public int getScore(){
        return score;
        }
}
