package pizzagame;

import pizzagame.context.GoPlay;
import pizzagame.context.IGoPlay;
import pizzagame.strategy.Player.Player;
import pizzagame.strategy.randomturn.RandomTurns;
import pizzagame.strategy.core.Pizzacore;

/**
 *  Classe Startup
 */
public class PlayPizzaGame {
    /**
     *
     * @param args
     *          y,s,Y,S sarà necessaria l'interazione manuale
     */
    static public void main(String[] args){
        /**
         *  Definisco gli estremi del gioco:
         *  10: numero minimo di pizze sul tavolo
         *  30: numero massimo di pizze sul tavolo
         *  1 : minima sceta di pizze da mangiare
         *  2 : massima sceta di pizze da mangiare
         */

        boolean interactiveMode = (args.length > 0 && (args[0].equalsIgnoreCase("Y") || args[0].equalsIgnoreCase("S"))) ? true : false;

        IGoPlay goPlay = new GoPlay(10,30,1,3);
        goPlay.setPizzaCore(new Pizzacore());
        goPlay.setRandomChoice(new RandomTurns());
        goPlay.setPlayer(new Player("Player A","Player B", interactiveMode));

        goPlay.play();
    }
}
