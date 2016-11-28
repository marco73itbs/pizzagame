package pizzagame.context;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import pizzagame.strategy.Player.Player;
import pizzagame.strategy.randomturn.RandomTurns;
import pizzagame.strategy.core.Pizzacore;


public class GoPlayTest {
    IGoPlay goPlay;
    @Before
    public void init(){
        goPlay = new GoPlay(10,30,1,3);
    }
    @Test
    public void play() throws Exception {
        Player player = Mockito.mock(Player.class);
        RandomTurns randomTurns = Mockito.mock(RandomTurns.class);
        Pizzacore pizzacore = Mockito.mock(Pizzacore.class);

        Mockito.when(player.getPlayerA()).thenReturn("A");
        Mockito.when(player.getPlayerB()).thenReturn("B");
        Mockito.when(player.getTurnPlayer(Matchers.anyInt())).thenReturn("A","B","A","B","A","B");
        Mockito.when(player.getNumberPizzeToEat(Matchers.anyInt(),Matchers.anyInt(), Matchers.anyBoolean())).thenReturn(1,3,2,1,3,2);

        Mockito.when(randomTurns.getTurns(Matchers.anyInt(),Matchers.anyInt())).thenReturn(12);
        Mockito.when(pizzacore.eatPizza(Matchers.anyString(),Matchers.anyInt())).thenReturn(11,8,6,5,2,0);

        goPlay.setPlayer(player);
        goPlay.setRandomChoice(randomTurns);
        goPlay.setPizzaCore(pizzacore);
        goPlay.play();
    }
}
