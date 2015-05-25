package be.kaasnapps.gravitor.model.asteroid;

import java.util.Random;

/**
 * Created by jonas on 7/05/2015.
 */
public class RandomAsteroidFactory {



    public static Asteroid getRandomAsteroid(){
        Random generator = new Random();
        int random = generator.nextInt(3);
        switch (random){
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
        }


        return null;
    }


}
