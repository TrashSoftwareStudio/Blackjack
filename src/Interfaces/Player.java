package Interfaces;

import Deck.Card;

import java.util.ArrayList;

public interface Player {
    int getPlayerId();
    ArrayList<Card> getHands();
    void sortHands();
}
