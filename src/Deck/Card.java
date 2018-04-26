package Deck;

import java.util.ArrayList;

public class Card {
    private int number;
    private int colour;

    public Card(int number, int colour) {
        this.number = number;
        this.colour = colour;
    }

    public int getNumber() {
        return number;
    }

    public int getColour() {
        return colour;
    }

    private String getColourName() {
        switch (colour) {
            case 0:
                return "♠";
            case 1:
                return "♡";
            case 2:
                return "♣";
            case 3:
                return "♢";
            case 4:
                return "Joker";
            default:
                return "";
        }
    }

    private String getNumberName() {
        if (number == 1 || number == 14) {
            return "A";
        } else if (number == 2 || number == 15) {
            return "2";
        } else if (number == 11) {
            return "J";
        } else if (number == 12) {
            return "Q";
        } else if (number == 13) {
            return "K";
        } else if (number == 16) {
            return "●";
        } else if (number == 17) {
            return "○";
        } else {
            return String.valueOf(number);
        }
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "{" + getColourName() + getNumberName() + "}";
    }

    public static boolean equalCards(ArrayList<Card> suit, int start, int end) {
        for (int i = start; i < end; i ++) {
            if (suit.get(i).getNumber() != suit.get(i + 1).getNumber()) {
                return false;
            }
        }
        return true;
    }

    public static boolean equalCards(ArrayList<Card> suit) {
        return equalCards(suit, 0, suit.size());
    }
}
