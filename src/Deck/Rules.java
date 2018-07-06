package Deck;

import java.util.ArrayList;

import static Deck.Card.equalCards;

public class Rules {
    public static boolean isStraight(ArrayList<Card> suit) {
        if (suit.get(suit.size() - 1).getNumber() >= 15) {
            return false;
        }
        for (int i = 0 ; i < suit.size() - 1; i++) {
            if (suit.get(i + 1).getNumber() != suit.get(i).getNumber() + 1) {
                return false;
            }
        }
        return true;
    }

    public static boolean containsTriple(ArrayList<Card> suit) {
        for (int i  = 0; i < suit.size() - 2; i++) {
            if (equalCards(suit, i, i + 2)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isDoubleStraight(ArrayList<Card> suit) {
        ArrayList<Card> test = new ArrayList<>();
        for (int i = 0; i < suit.size(); i += 2) {
            if (equalCards(suit, i, i + 1)) {
                test.add(suit.get(i));
            } else {
                return false;
            }
        }
        return isStraight(test);
    }

    public static boolean isTripleStraight(ArrayList<Card> suit) {
        ArrayList<Card> test = new ArrayList<>();
        for (int i = 0; i < suit.size(); i += 3) {
            if (equalCards(suit, i, i + 2)) {
                test.add(suit.get(i));
            } else {
                return false;
            }
        }
        return isStraight(test);
    }

    public static boolean isFourWithTwo(ArrayList<Card> suit) {
        int s = 0;
        int e = 0;
        int r = 0;
        for (int i = 0; i < suit.size() - 3; i++) {
            if (equalCards(suit, i, i + 3)) {
                s = i;
                e = i + 3;
            }
        }
        if (s == 0 && e == 0) {
            return false;
        } else {
            if (suit.size() == 6) {
                return true;
            } else {
                if (s > 0) {
                    for (int k = 0; k < s; k += 2) {
                        if (equalCards(suit, k, k + 1)) {
                            r++;
                        }
                    }
                }
                if (e < suit.size() - 1) {
                    for (int m = e + 1; m < suit.size() - 1; m += 2) {
                        if (equalCards(suit, m, m + 1)) {
                            r++;
                        }
                    }
                }
                return r == 2;
            }
        }
    }

    /**
     * Find the key card in a suit used for comparison.
     * @param baseNum Number of cards in a base suit. BaseNum could only be 3 or 4.
     *                For example, baseNum = 3 in "飞机4" and baseNum = 4 in "四带二".
     * @return the key card in a suit used for comparison.
     */
    public static Card findKeyCard(ArrayList<Card> suit, int baseNum) {
        if (baseNum == 4) {
            for (int i = 0; i < suit.size() - 3; i++) {
                if (equalCards(suit, i, i + 3)) {
                    return suit.get(i);
                }
            }
        } else {
            for (int i = 0; i < suit.size() - 2; i++) {
                if (equalCards(suit, i, i + 2)) {
                    return suit.get(i);
                }
            }
        }
        return null;
    }
}
