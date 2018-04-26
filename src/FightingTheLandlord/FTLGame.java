package FightingTheLandlord;


import Deck.Card;
import Deck.Dealer;

import java.util.ArrayList;

import static Deck.Card.equalCards;

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

    public static boolean isStraight(ArrayList<Card> suit) {
        for (int i = 0 ; i < suit.size() - 1; i++) {
            if (suit.get(i + 1).getNumber() != suit.get(i).getNumber() + 1) {
                return false;
            }
        }
        return true;
    }

    public static String suitKind(ArrayList<Card> suit) {
        if (suit.size() == 1) {
            return "单牌";
        } else if (suit.size() == 2) {
            if (suit.get(0).getNumber() == 16 || suit.get(1).getNumber() == 17) {
                return "炸弹";
            } else {
                return "对子";
            }
        } else if (suit.size() == 3) {
            return "三张";
        } else if (suit.size() == 4) {
            if (equalCards(suit)) {
                return "炸弹";
            } else {
                return "三带一";
            }
        } else if (suit.size() == 5) {
            if (equalCards(suit, 0, 1)) {
                return "三带二";
            } else if (isStraight(suit)) {
                return "顺子5";
            }
        } else if (suit.size() == 6) {
            if (equalCards(suit, 0, 3) || equalCards(suit, 2, 5)) {
                return "四带二";
            } else if (equalCards(suit, 0, 2) && equalCards(suit, 3, 5) && suit.get(3).getNumber() == suit.get(2).getNumber() + 1) {
                return "飞机";
            } else if (equalCards(suit, 0, 1) && equalCards(suit, 2, 3) && equalCards(suit, 4, 5) &&
                    suit.get(2).getNumber() == suit.get(1).getNumber() + 1 && suit.get(4).getNumber() == suit.get(3).getNumber() + 1) {
                return "亲三对";
            } else if (isStraight(suit)) {
                return "顺子6";
            }
        } else if (suit.size() == 7){
            if (isStraight(suit)) {
                return "顺子7";
            }
        }
        return "";

    }

//    public boolean isValidSuit(ArrayList<Card> suit) {
//        if (suit.size() == currentTable.size()) {
//
//        }
//    }

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

    public static void main(String[] args) {
        Card card1 = new Card(1,0);
        Card card2 = new Card(1,1);
        Card card3 = new Card(2,2);
        Card card4 = new Card(2,3);
        Card card5 = new Card(3,0);
        Card card6 = new Card(3,1);
//        Card card7 = new Card(2,2);
        ArrayList<Card> suit = new ArrayList<>();
        suit.add(card1);
        suit.add(card2);
        suit.add(card3);
        suit.add(card4);
        System.out.println(equalCards(suit));
        System.out.println(isStraight(suit));
        suit.add(card5);
        suit.add(card6);
//        suit.add(card7);
        System.out.println(equalCards(suit));
        System.out.println(equalCards(suit, 0, 1));
        System.out.println(equalCards(suit, 4, 5));
        System.out.println(equalCards(suit, 2, 5));
        System.out.println(isStraight(suit));
        System.out.println(suit);
        System.out.println(suitKind(suit));
    }
}
