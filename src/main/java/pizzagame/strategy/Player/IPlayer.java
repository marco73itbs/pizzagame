package pizzagame.strategy.Player;


public interface IPlayer {
    String getPlayerA();
    String getPlayerB();

    String getTurnPlayer(int turn);
    int getNumberPizzeToEat(int minChoice, int maxChoice, boolean skipTheTurn);
}
