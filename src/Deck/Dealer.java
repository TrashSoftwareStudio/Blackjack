package Deck;

import java.util.ArrayList;

public class Dealer {
    private int numOfDecks;
    private boolean includeJoker;
    private ArrayList<Card> deck;
    private int currentIndex;

    public Dealer(int numOfDecks, boolean includeJoker) {
        this.numOfDecks = numOfDecks;
        this.includeJoker = includeJoker;
        generateDeck();
    }

    private void generateDeck() {
        deck = new ArrayList<>();
        for (int i = 0; i < numOfDecks; i++) {
            for (int n = 0; n < 52; n++) {
                Card card = new Card(n % 13 + 1, n / 13);
                deck.add(card);
            }
            if (includeJoker) {
                deck.add(new Card(16, 4));
                deck.add(new Card(17, 4));
            }
        }
    }

    public void shuffle() {
        currentIndex = 0;
        for (int i = deck.size()-1; i > 0; i--) {
            double r = Math.random();
            int index = (int) Math.round(r * i);
            Card temp = deck.get(index);
            deck.set(index, deck.get(i));
            deck.set(i, temp);
        }
    }

    public Card deal() {
        if (currentIndex >= deck.size()) {
            throw new EmptyDeckException();
        }
        Card card = deck.get(currentIndex);
        currentIndex++;
        return card;
    }

    public static void main(String[] args) {
        Dealer d = new Dealer(1, true);
        d.shuffle();
        System.out.println(d.deck);
    }
}
