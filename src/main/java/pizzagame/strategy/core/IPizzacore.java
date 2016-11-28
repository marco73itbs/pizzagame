package pizzagame.strategy.core;

public interface IPizzacore {
    public void setMaxPizzaToEat(int maxPizzaToEat);
    public int eatPizza(String player, int numeroPizzeDaMnangiare) throws Exception;
}
