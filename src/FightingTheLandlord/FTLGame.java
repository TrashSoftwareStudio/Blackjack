package FightingTheLandlord;


import Deck.Card;
import Deck.Dealer;

import java.util.ArrayList;

public class FTLGame {
    private FTLPlayer[] ftlPlayers = new FTLPlayer[3];
    private Dealer deck = new Dealer(1, true);
    private int firstCall;
    private int landlord = -1;
    private ArrayList<Card> lastThreeCards = new ArrayList<>();
    private int currentPlayer = -1;
    private ArrayList<Card> currentTable = new ArrayList<>();

    public FTLGame() {
        ftlPlayers[0] = new FTLPlayer(0);
        ftlPlayers[1] = new FTLPlayer(1);
        ftlPlayers[2] = new FTLPlayer(2);
    }

    public void deal() {
        double r = Math.random();
        int m = (int) Math.round(r * 50);
        firstCall = m % 3;
        deck.shuffle();
        for (int i = 0; i < 51; i++) {
            Card c = deck.deal();
            if (c.getNumber() < 3) {
                c.setNumber(c.getNumber() + 13);
            }
            ftlPlayers[i % 3].getHands().add(c);
        }
        for (FTLPlayer p: ftlPlayers) p.sortHands();
        for (int i = 0; i < 3; i++) {
            Card c = deck.deal();
            if (c.getNumber() < 3) {
                c.setNumber(c.getNumber() + 13);
            }
            lastThreeCards.add(c);
        }
    }

    public FTLPlayer[] getFtlPlayers() {
        return ftlPlayers;
    }

    public int getFirstCall() {
        return firstCall;
    }

    public int getLandlord() {
        return landlord;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public ArrayList<Card> getCurrentTable() {
        return currentTable;
    }

    public ArrayList<Card> getLastThreeCards() {
        return lastThreeCards;
    }

    public void setLandlord(int landlord) {
        this.landlord = landlord;
        currentPlayer = landlord;
        ftlPlayers[landlord].getHands().addAll(lastThreeCards);
        ftlPlayers[landlord].sortHands();
    }

    public int winner() {
        for (FTLPlayer p: ftlPlayers) {
            if (p.getHands().isEmpty()) return p.getPlayerId();
        }
        return -1;
    }

    public void round(String input) {
        ArrayList<Card> temp = new ArrayList<>();
        for (String s: input.split(",")) {
            temp.add(ftlPlayers[currentPlayer].getHands().get(Integer.parseInt(s) - 1));
        }
        ftlPlayers[currentPlayer].getHands().removeAll(temp);
        currentTable.addAll(temp);
        currentPlayer = (currentPlayer + 1) % 3;
    }

    public boolean isGameOver() {
        return winner() >= 0;
    }
}
