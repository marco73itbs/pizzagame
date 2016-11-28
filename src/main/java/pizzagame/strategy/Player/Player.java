package pizzagame.strategy.Player;

import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 Gestisce l'interazione con il core del game: rappresenta i due giocatori
 Di  default è atttiva la modalità automatica per la scelta dei nomi ed il nome in base al turno
 *
 * Marco A.
 */
public class Player implements IPlayer {
    private String playerA;
    private String playerB;
    private boolean interactiveMode = true;
    /**
     * @param playerA           Nome giocare A
     * @param playerB           Nome giocate B
     *
     * */
    public Player(String playerA, String playerB, boolean interactiveMode) {
        this.playerA = playerA;
        this.playerB = playerB;
        this.interactiveMode = interactiveMode;
    }

    public String getPlayerA() {
        return playerA;
    }
    public String getPlayerB() {
        return playerB;
    }

    /**
     * @param turn
     * @return il nome del giocatore in base al turno
     */
    public String getTurnPlayer(int turn) {
        if ((turn % 2) == 0) {
            return playerB;
        } else {
            return playerA;
        }
    }

    /**
     *
     * @param minChoice minimo numero di pizze da mangiare
     * @param maxChoice massimo numero di pizze da mangiare
     * @return pizze da mangiare
     */
    public int getNumberPizzeToEat(int minChoice, int maxChoice, boolean skipTheTurn) {
        int numberPizzaToEat = 0;

        if (!skipTheTurn) {
            if (interactiveMode) {
                String choice;
                boolean checkNumber;
                do {
                    Scanner scanner = new Scanner(System.in);
                    choice = scanner.nextLine();
                    checkNumber = Pattern.matches("[0-9]{1}", choice);
                } while (!checkNumber);
                numberPizzaToEat = Integer.parseInt(choice);
            } else {
                //nel caso di scelte automatiche scelto tra il numero di pizze da mangiare tra min e max
                numberPizzaToEat = new Random().nextInt((maxChoice - minChoice) + 1) + minChoice;
            }
        }
        return numberPizzaToEat;
    }
}
