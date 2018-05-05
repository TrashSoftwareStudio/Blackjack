package Deck;

import java.util.ArrayList;

public class Rules {
    public static boolean isStraight(ArrayList<Card> suit) {
        for (int i = 0 ; i < suit.size() - 1; i++) {
            if (suit.get(i + 1).getNumber() != suit.get(i).getNumber() + 1) {
                return false;
            }
        }
        return true;
    }
}
