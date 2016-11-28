package pizzagame.strategy;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import pizzagame.strategy.core.IPizzacore;
import pizzagame.strategy.core.Pizzacore;


public class PizzacoreTest {
    static private IPizzacore pizzacore = null;

    @BeforeClass()
    public static void init(){
        pizzacore = new Pizzacore();
    }

    @Test()
    public void checkMinimulPizzaToEat() throws Exception {
        try {
            pizzacore.setMaxPizzaToEat(5);
            Assert.fail("expected exception was not occured.");
        } catch(IllegalArgumentException e) {
            Assert.assertTrue("correct: no good number of pizza",true);
        }
    }
    @Test
    public void checkSameChoice() throws Exception {
        try {
            pizzacore.setMaxPizzaToEat(10);
            pizzacore.eatPizza("Player A", 1);
            pizzacore.eatPizza("Player B", 1);
            Assert.fail("expected exception was not occured.");
        }catch (IllegalArgumentException e){
            Assert.assertTrue("correct: impossibile to do the same choices",true);
        }
    }

    @Test
    public void checkChoiceNumberPizza() throws Exception {
        pizzacore.setMaxPizzaToEat(12);
        try {
            pizzacore.eatPizza("Player B", 4);
            Assert.fail("expected exception was not occured.");
        }catch (IllegalArgumentException e){
            Assert.assertTrue("correct: min 1 and max 3",true);
        }
    }

    @Test
    public void checkCountPizza() throws Exception {
        IPizzacore pizzacore_  = new Pizzacore(11);
        Assert.assertTrue(pizzacore_.eatPizza("Player A", 1) == 10 );
    }
}
