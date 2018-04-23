package TenAndHalf;

import Deck.Card;
import Deck.Dealer;

import java.util.Collection;

public class TAHGame {

    private int playerNumber;
    private TAHPlayer[] players;
    private Dealer dealer = new Dealer(1, true);
    private int currentPlayer;

    public TAHGame(int playerNumber) {
        this.playerNumber = playerNumber;
        this.players = new TAHPlayer[playerNumber];
        for (int i = 0; i < playerNumber; i++) players[i] = new TAHPlayer(i);  // Initialize players.
    }

    public void startGame() {
        dealer.shuffle();
        for (TAHPlayer player : players) player.addCard(dealer.deal());  // Base card.
    }

    /**
     * Turns to the next players round if exists..
     *
     * @return whether the current player is not the last player.
     */
    public boolean skipPlayer() {
        currentPlayer++;
        return currentPlayer != playerNumber;
    }

    /**
     * To give the current player a new card.
     *
     * @param print whether to print the new card.
     * @return whether the request is successful.
     */
    public boolean requireCard(boolean print) {
        if (players[currentPlayer].getHands().size() >= 5 || players[currentPlayer].isExploded())
            return false;
        else {
            Card newCard = dealer.deal();
            if (print) System.out.println(newCard);
            players[currentPlayer].addCard(newCard);
            return true;
        }
    }

    /**
     * Returns the index (id) of player who wins.
     *
     * @return the index of player who wins.
     */
    public int compare() {
        int win = 0;  // Banker wins if there is no winner.
        int max = 0;
        for (int i = 0; i < playerNumber; i++) {
            TAHPlayer player = players[i];
            int number = player.getCurrentSum();
            if (number <= 21) {
                if (number == max) {
                    if (player.getHands().size() > players[win].getHands().size())
                        win = i;
                } else if (number > max) {
                    max = number;
                    win = i;
                }
            }
        }
        return win;
    }

    public TAHPlayer getCurrentPlayer() {
        return players[currentPlayer];
    }

    public TAHPlayer[] getPlayers() {
        return players;
    }
}
