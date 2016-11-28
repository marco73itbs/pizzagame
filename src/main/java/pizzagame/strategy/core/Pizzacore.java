package pizzagame.strategy.core;

/**
 *
 Core del gioco

 I giocatori in gioco devono essere 2
 Il numero di pizze viene determinato randomicamente all'inizio del gioco e deve essere sempre maggiore di 10
 Ogni giocatore, durante il proprio turno, deve mangiare 1, 2 o 3 pizze
 Ogni giocatore non puó saltare il proprio turno
 Ogni giocatore non puó ripetere la scelta fatta in precedenza dall'avversario
 Se un giocatore durante il proprio turno non ha mosse valide
    allora viene obbligato a saltare il proprio turno. A questo punto l'altro giocatore sarà obbligato a mangiare la pizza avvelenata e,
    di conseguenza, a perdere. Questo è l'unico caso nel quale è consentito il salto del turno
 *
 * Marco A.
 */
public class Pizzacore implements IPizzacore {
    private final int MAXPIZZA = 10;
    private int lastChoice = 0;
    private int maxPizzaToEat = MAXPIZZA;
    private int minimumPizzaEat = 1;
    private int borderline = 2;
    private int maximunPizzaEat = 3;
    private final String PIZZASINGOLARE = "pizza";
    private final String PIZZAPLURALE = "pizze";

    /**
     * Costruttuore base
     */
    public Pizzacore() {
    }
    /**
     *
     * @param maxPizzaToEat : numero massimo di pizze presenti sul tavolo
     */
    public Pizzacore(int maxPizzaToEat) {
        checkMaxPizzaToEat(maxPizzaToEat);
        this.maxPizzaToEat = maxPizzaToEat;
    }
    public int getMaxPizzaToEat() {
        return maxPizzaToEat;
    }
    public void setMaxPizzaToEat(int maxPizzaToEat) throws IllegalArgumentException {
        checkMaxPizzaToEat(maxPizzaToEat);
        this.maxPizzaToEat = maxPizzaToEat;
    }

    /**
     *
     * @param player                :nome del giocatore
     * @param actualPizzaChoice     :scelta di quante pizze mangiare (1,2,3)
     * @return                      :pizze rimanenti sul tavolo
     * @throws IllegalArgumentException
     *
     * La logica consiste in questo:
     * verifico che l'input delle scelte delle pizze sia
     *      1) un numero <= al numero delle pizze sul tavolo
     *      2) verifico che sul tavolo ci sia una pizza e che la scelta precedente sia sempre 1: questo permette di saltare il turno
     *      3) un numero compreso tra 1 e 3
     *      4) non sia uguale a quella precedente
     * calcolo le pizze rimanenti e se :
     *      1) uguale a 0 il giocatore ha perso
     */
    public int eatPizza(String player, int actualPizzaChoice) throws IllegalArgumentException {
        //Controllo che la scelta di pizza da mangiare sia <= a quelle sul tavolo
        if (!checkNumberOfChoicePizzaMinorOfMaxPizzeToEat(actualPizzaChoice, maxPizzaToEat))
            throw new IllegalArgumentException("minimum possible value is equal or less " + maxPizzaToEat);

        //devo verificare che non si presenti il salto turno
        if (maxPizzaToEat == 1 && lastChoice == 1){
            lastChoice = 0;
            System.out.println(player + " skip his turn (will remain " + maxPizzaToEat + ")");
        }else {
            //Ogni giocatore, durante il proprio turno, deve mangiare 1, 2 o 3 pizze
            if (!checkNumberOfChoicePizza(actualPizzaChoice)) {
                throw new IllegalArgumentException("minimum possible value is 1 and maximun is 3");
            }
            //Ogni giocatore non puó ripetere la scelta fatta in precedenza dall'avversario
            if (lastChoice == actualPizzaChoice) {
                throw new IllegalArgumentException(player + ": choice (" + actualPizzaChoice + ") already made by the opponent");
            }
            maxPizzaToEat -= actualPizzaChoice;
            System.out.println(player + " eats " + actualPizzaChoice + " " + (actualPizzaChoice > 1 ? PIZZAPLURALE : PIZZASINGOLARE) + " (will remain " + maxPizzaToEat + ")");
            if (maxPizzaToEat == 0){
                System.out.println(player + " LOSE");
            }
            lastChoice = actualPizzaChoice;
        }
        return maxPizzaToEat;
    }
    /**
     *
     * @param choicePizza
     * @param maxPizzaToEat
     * @return boolean se la scelta delle pizze da mangiare è corretta
     */
    private boolean checkNumberOfChoicePizzaMinorOfMaxPizzeToEat(int choicePizza, int maxPizzaToEat){
        return (choicePizza <= maxPizzaToEat) ? true : false;
    }
    /**
     *
     * @param choicePizza
     * @return boolean se il numero di pizze da mangiare è tra minimumPizzaEat e maximunPizzaEat
     */
    private boolean checkNumberOfChoicePizza(int choicePizza){
        return (choicePizza >=minimumPizzaEat && choicePizza <=maximunPizzaEat) ? true : false;
    }
    /**
     * Controllo che il numero delle pizze sul tavolo non sia inferiore a MAXPIZZA
     * @param maxPizzaToEat_
     */
    private void checkMaxPizzaToEat(int maxPizzaToEat_){
        if(maxPizzaToEat_ < MAXPIZZA){
            throw new IllegalArgumentException("minimum value is " + MAXPIZZA);
        }
    }
}
