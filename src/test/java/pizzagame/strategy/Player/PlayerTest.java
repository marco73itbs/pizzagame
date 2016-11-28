package pizzagame.strategy.Player;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by cg07981 on 01/12/2016.
 */
public class PlayerTest {

    @Test
    public void TurnPlayerTest() throws Exception {
        Player player = new Player("Player A","Player B", false);
        Assert.assertTrue("Player B".equals(player.getTurnPlayer(2)));
        Assert.assertTrue("Player A".equals(player.getTurnPlayer(1)));
    }

    @Test
    public void TurnPlayerPlayerABTest() throws Exception {
        Player player = new Player("Player A","Player B", false);
        Assert.assertTrue(player.getPlayerB().equals(player.getTurnPlayer(2)));
        Assert.assertTrue(player.getPlayerA().equals(player.getTurnPlayer(1)));
    }

    @Test
    public void NumberPizzeToEatTest() throws Exception {
        Player player = new Player("Player A","Player B", false);
        int n = player.getNumberPizzeToEat(1,3,false);
        Assert.assertTrue("",n > 0 && n < 4);
    }

    @Test
    public void NumberPizzeToEatManualTest() throws Exception {
        Player player = new Player("Player A","Player B", false);
        Assert.assertTrue("",player.getNumberPizzeToEat(1,3,false) > 0);
    }
}