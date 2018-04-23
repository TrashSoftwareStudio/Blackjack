package TenAndHalf;

import Deck.Card;
import Interfaces.Player;

import java.util.ArrayList;

public class TAHPlayer implements Player {

    private int id;
    private ArrayList<Card> hands = new ArrayList<>();
    private boolean isBank;
    private int currentSum;

    public TAHPlayer(int id) {
        this.id = id;
        isBank = id == 0;
    }

    void addCard(Card c) {
        int number = c.getNumber();
        if (number <= 10) currentSum += number * 2;
        else currentSum += 1;
        hands.add(c);
    }

    boolean isExploded() {
        return currentSum > 21;
    }

    public int getCurrentSum() {
        return currentSum;
    }

    @Override
    public void sortHands() {
    }

    @Override
    public int getPlayerId() {
        return id;
    }

    @Override
    public ArrayList<Card> getHands() {
        return hands;
    }
}
