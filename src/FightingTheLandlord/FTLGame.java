package FightingTheLandlord;


import Deck.Card;
import Deck.Dealer;

public class FTLGame {
    private FTLPlayer[] ftlPlayers = new FTLPlayer[3];
    private Dealer deck = new Dealer(1, true);
    private int firstCall;
    private int landlord = -1;

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

    public void setLandlord(int landlord) {
        this.landlord = landlord;
    }
}
