package pizzagame.strategy.randomturn;

import java.util.Random;

/**
 *
 numero casuale tra l'intervalli min e max passati in input [estremi compresi]
 *
 * Marco A.
 */
public class RandomTurns implements IRandomTurns  {
    /**
     *
     * @param min   estremo inferiore
     * @param max   estremo superiore
     * @return      numero casuale
     * @throws IllegalArgumentException
     *
     * getTruns(10,30) --> 15
     */
    public int getTurns(int min, int max) throws IllegalArgumentException {
        if (min < max) {
            return new Random().nextInt((max - min) + 1) + min;
        }else if (min == max){
            return min;
        }else{
            throw new IllegalArgumentException("max must be greater than min");
        }

    }
}
