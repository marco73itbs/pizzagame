package pizzagame.context;

import pizzagame.strategy.Player.IPlayer;
import pizzagame.strategy.randomturn.IRandomTurns;
import pizzagame.strategy.core.IPizzacore;

import java.lang.instrument.IllegalClassFormatException;
import java.util.Scanner;
import java.util.regex.Pattern;


public class GoPlay implements IGoPlay {
    private IRandomTurns randomChoice = null;
    private IPizzacore pizzacore = null;
    private IPlayer player = null;

    private int numberMinPizza = 10;
    private int numberMaxPizza = 30;
    private int minChoice = 1;
    private int maxChoice = 3;

    /**
     * Permette di personalizzare i valori di default.
     * @param numberMinPizza    : numero minimo di pizze sul tavolo
     * @param numberMaxPizza    : numero massimo di pizza sul tavolo
     * @param minChoice         : minima scelta possibile di pizze da "mangiare"
     * @param maxChoice         : massima scelta di pizze da "mangiare"
     */
    public GoPlay(int numberMinPizza, int numberMaxPizza, int minChoice, int maxChoice) {
        this.numberMinPizza = numberMinPizza <= 0 ? this.numberMinPizza : numberMinPizza;
        this.numberMaxPizza = numberMaxPizza <= 0 ? this.numberMaxPizza : numberMaxPizza;
        this.minChoice = minChoice <= 0 ? this.minChoice : minChoice;
        this.maxChoice = maxChoice <= 0 ? this.maxChoice : maxChoice;
    }

    public IRandomTurns getRandomChoice() {
        return randomChoice;
    }
    public void setRandomChoice(IRandomTurns randomChoice) {
        this.randomChoice = randomChoice;
    }

    public IPizzacore getPizzaCore() {
        return pizzacore;
    }
    public void setPizzaCore(IPizzacore pizzaCore) {
        this.pizzacore = pizzaCore;
    }

    public IPlayer getPlayer() {
        return player;
    }
    public void setPlayer(IPlayer player) {
        this.player = player;
    }

    public int getNumberMinPizza() {
        return numberMinPizza;
    }

    public int getNumberMaxPizza() {
        return numberMaxPizza;
    }

    public int getMinChoice() {
        return minChoice;
    }

    public int getMaxChoice() {
        return maxChoice;
    }

    /**
     * Verifica delle injection delle seguenti classi/interfacce : IRandomTurns,IPizzacore,IPlayer
     * calcolo in base agli estremi passati nelle relative property il numero di pizze da mettere sul tavolo
     * calcola in base al turno il giocatore da presentare
     * se interattiva attende l'input diversamente scegle tra 1 e 3
     * chiama la pizzacore per eseguire la mossa
     * finchè il numero di pizze ritornato è > 0
     *
     * @return zero : termine gioco
     */
    public boolean play(){
        boolean ret = false;
        int turno = 1;
        int numberPizzaToEat = 1;

        try {
            checkObjectInjection();
            //scelto tra l'estremo inferiore e superiore le pizze sul tavolo
            int maxPizzeToEat = randomChoice.getTurns(this.getNumberMinPizza(), this.getNumberMaxPizza());
            pizzacore.setMaxPizzaToEat(maxPizzeToEat);

            System.out.println("Numbert Pizze to eat : " + maxPizzeToEat);
            System.out.println("Play the game        : " + player.getPlayerA());

            do {
                String roundPlayerName = player.getTurnPlayer(turno);
                System.out.println(roundPlayerName + " |-> Choose how many pizza : ");
                numberPizzaToEat = player.getNumberPizzeToEat(this.minChoice,this.getMaxChoice(), (maxPizzeToEat == 1 && numberPizzaToEat == 1));
//                System.out.println(numberPizzaToEat);
                try {
                    maxPizzeToEat = pizzacore.eatPizza(roundPlayerName, numberPizzaToEat);
                    turno++;
                }catch (IllegalArgumentException i){
                    System.out.println(i.getMessage());
                }catch (Exception e){
                    throw e;
                }
            }while (maxPizzeToEat > 0);
            ret = true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return ret;
    }
    /**------------------------------------------------------------------------*/
    private void checkObjectInjection() throws IllegalClassFormatException {
        if (randomChoice == null){
            throw new IllegalClassFormatException("RandomTurns.class is null");
        }
        if (pizzacore == null){
            throw new IllegalClassFormatException("Pizzacore.class is null");
        }
        if (player == null){
            throw new IllegalClassFormatException("Player.class is null");
        }
    }
}
