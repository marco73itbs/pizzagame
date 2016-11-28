package pizzagame.context;

import pizzagame.strategy.Player.IPlayer;
import pizzagame.strategy.core.IPizzacore;
import pizzagame.strategy.randomturn.IRandomTurns;

/**
 * Created by cg07981 on 01/12/2016.
 */
public interface IGoPlay {
    IRandomTurns getRandomChoice();

    void setRandomChoice(IRandomTurns randomChoice);

    IPizzacore getPizzaCore();

    void setPizzaCore(IPizzacore pizzaCore);

    IPlayer getPlayer();

    void setPlayer(IPlayer player);

    int getNumberMinPizza();

    int getNumberMaxPizza();

    int getMinChoice();

    int getMaxChoice();

    boolean play();
}
