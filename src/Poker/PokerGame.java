package Poker;

import Deck.Card;
import Deck.Dealer;

import java.util.ArrayList;

public class PokerGame {
    private Dealer deck = new Dealer(1, false);
    private PokerPlayer[] pokerPlayers;
    private int numOfPlayers;

    public PokerGame(int numOfPlayers) {
        this.numOfPlayers = numOfPlayers;
        pokerPlayers = new PokerPlayer[numOfPlayers];
        for (int i = 0; i < numOfPlayers; i++) pokerPlayers[i] = new PokerPlayer(i);
    }

    public void deal() {
        deck.shuffle();
        for (int i = 0; i < numOfPlayers * 5; i++) {
            Card c = deck.deal();
            if (c.getNumber() == 1) {
                c.setNumber(c.getNumber() + 13);
            }
            pokerPlayers[i % numOfPlayers].getHands().add(c);
        }
        for (PokerPlayer p : pokerPlayers) p.sortHands();
    }

    public PokerPlayer[] getPokerPlayers() {
        return pokerPlayers;
    }

    public int winner() {
        for (PokerPlayer p : pokerPlayers) {
            if (p.getHands().isEmpty()) return p.getPlayerId();
        }
        return -1;
    }

    public boolean isGameOver() {
        return winner() >= 0;
    }

//    public static String suitKind(ArrayList<Card> suit) {
//
//    }
}
