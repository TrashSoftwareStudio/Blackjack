package FightingTheLandlord;

import Deck.Card;
import Deck.Dealer;

import java.util.ArrayList;
import java.util.Objects;

import static Deck.Card.equalCards;
import static Deck.Rules.*;

public class FTLGame {
    private FTLPlayer[] ftlPlayers = new FTLPlayer[3];
    private Dealer deck = new Dealer(1, true);
    private int firstCall;
    private int landlord = -1;
    private ArrayList<Card> lastThreeCards = new ArrayList<>();
    private int currentPlayer = -1;
    private ArrayList<Card> currentTable = new ArrayList<>();
    private ArrayList<Card> chosenSuit = new ArrayList<>();
    private int lastPlayer = -1;

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
        for (FTLPlayer p : ftlPlayers) p.sortHands();
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

    public int getLastPlayer() {
        return lastPlayer;
    }

    public void setLandlord(int landlord) {
        this.landlord = landlord;
        currentPlayer = landlord;
        lastPlayer = landlord;
        ftlPlayers[landlord].getHands().addAll(lastThreeCards);
        ftlPlayers[landlord].sortHands();
    }

    public int winner() {
        for (FTLPlayer p : ftlPlayers) {
            if (p.getHands().isEmpty()) return p.getPlayerId();
        }
        return -1;
    }

    public static String suitKind(ArrayList<Card> suit) {
        if (suit.size() == 1) {
            return "单牌";
        } else if (suit.size() == 2) {
            if (suit.get(0).getNumber() == 16 || suit.get(1).getNumber() == 17) {
                return "炸弹";
            } else if (equalCards(suit)) {
                return "对子";
            }
        } else if (suit.size() == 3) {
            if (containsTriple(suit)) {
                return "飞机1";
            }
        } else if (suit.size() == 4) {
            if (equalCards(suit)) {
                return "炸弹";
            } else if (containsTriple(suit)) {
                return "飞机1带单";
            }
        } else if (suit.size() == 5) {
            if (containsTriple(suit)) {
                if (equalCards(suit, 0, 1) || equalCards(suit, 3, 4)) {
                    return "飞机1带对";
                }
            } else if (isStraight(suit)) {
                return "顺子5";
            }
        } else if (suit.size() == 6) {
            if (isFourWithTwo(suit)) {
                return "四带二";
            } else if (isTripleStraight(suit)) {
                return "飞机2";
            } else if (isDoubleStraight(suit)) {
                return "连对3";
            } else if (isStraight(suit)) {
                return "顺子6";
            }
        } else if (suit.size() == 7) {
            if (isStraight(suit)) {
                return "顺子7";
            }
        } else if (suit.size() == 8) {
            if (isFourWithTwo(suit)) {
                return "四带二对";
            } else if (isDoubleStraight(suit)) {
                return "连对4";
            } else if (isStraight(suit)) {
                return "顺子8";
            } else if (isTripleStraight(new ArrayList<>(suit.subList(0, 6)))
                    || isTripleStraight(new ArrayList<>(suit.subList(1, 7)))
                    || isTripleStraight(new ArrayList<>(suit.subList(2, 8)))) {
                return "飞机2带单";
            }
        } else if (suit.size() == 9) {
            if (isTripleStraight(suit)) {
                return "飞机3";
            } else if (isStraight(suit)) {
                return "顺子9";
            }
        } else if (suit.size() == 10) {
            if (isDoubleStraight(suit)) {
                return "连对5";
            } else if (isStraight(suit)) {
                return "顺子10";
            } else if (isTripleStraight(new ArrayList<>(suit.subList(0, 6)))
                    || isTripleStraight(new ArrayList<>(suit.subList(2, 8)))
                    || isTripleStraight(new ArrayList<>(suit.subList(4, 10)))) {
                return "飞机2带对";
            }
        } else if (suit.size() == 11) {
            if (isStraight(suit)) {
                return "顺子11";
            }
        } else if (suit.size() == 12) {
            if (isStraight(suit)) {
                return "顺子12";
            } else if (isTripleStraight(suit)) {
                return "飞机4";
            } else if (isDoubleStraight(suit)) {
                return "连对6";
            } else if (isTripleStraight(new ArrayList<>(suit.subList(0, 9)))
                    || isTripleStraight(new ArrayList<>(suit.subList(1, 10)))
                    || isTripleStraight(new ArrayList<>(suit.subList(2, 11)))
                    || isTripleStraight(new ArrayList<>(suit.subList(3, 12)))) {
                return "飞机3带单";
            }
        } else if (suit.size() == 14) {
            if (isDoubleStraight(suit)) {
                return "连对7";
            }
        } else if (suit.size() == 15) {
            if (isTripleStraight(suit)) {
                return "飞机5";
            } else if (isTripleStraight(new ArrayList<>(suit.subList(0, 9)))
                    || isTripleStraight(new ArrayList<>(suit.subList(2, 11)))
                    || isTripleStraight(new ArrayList<>(suit.subList(4, 13)))
                    || isTripleStraight(new ArrayList<>(suit.subList(6, 15)))) {
                return "飞机3带对";
            }
        } else if (suit.size() == 16) {
            if (isDoubleStraight(suit)) {
                return "连对8";
            } else if (isTripleStraight(new ArrayList<>(suit.subList(0, 12)))
                    || isTripleStraight(new ArrayList<>(suit.subList(1, 13)))
                    || isTripleStraight(new ArrayList<>(suit.subList(2, 14)))
                    || isTripleStraight(new ArrayList<>(suit.subList(3, 15)))
                    || isTripleStraight(new ArrayList<>(suit.subList(4, 16)))) {
                return "飞机4带单";
            }
        } else if (suit.size() == 18) {
            if (isDoubleStraight(suit)) {
                return "连对9";
            } else if (isTripleStraight(suit)) {
                return "飞机6";
            }
        } else if (suit.size() == 20) {
            if (isDoubleStraight(suit)) {
                return "连对10";
            } else if (isTripleStraight(new ArrayList<>(suit.subList(0, 12)))
                    || isTripleStraight(new ArrayList<>(suit.subList(2, 14)))
                    || isTripleStraight(new ArrayList<>(suit.subList(4, 16)))
                    || isTripleStraight(new ArrayList<>(suit.subList(6, 18)))
                    || isTripleStraight(new ArrayList<>(suit.subList(8, 20)))) {
                return "飞机4带对";
            }
        }
        return "";
    }

    public boolean isValidSuit(ArrayList<Card> suit) {
        String n = suitKind(suit);
        if (currentTable.size() == 0) {
            return !n.equals("");
        }
        if (n.equals("炸弹")) {
            if (suitKind(currentTable).equals("炸弹")) {
                return suit.size() == 2 || suit.get(0).getNumber() > currentTable.get(0).getNumber();
            }
            return true;
        } else if (suit.size() == currentTable.size() && n.equals(suitKind(currentTable))) {
            if (n.equals("单牌") || n.equals("对子") || n.contains("连对") || n.contains("顺子")) {
                return suit.get(0).getNumber() > currentTable.get(0).getNumber();
            } else if (n.contains("四")) {
                return Objects.requireNonNull(findKeyCard(suit, 4)).getNumber() > Objects.requireNonNull(findKeyCard(currentTable, 4)).getNumber();
            } else if (n.contains("飞机")) {
                return Objects.requireNonNull(findKeyCard(suit, 3)).getNumber() > Objects.requireNonNull(findKeyCard(currentTable, 3)).getNumber();
            }
        }
        return false;
    }

    public int round(String input) {
        int r = 0;
        if (currentPlayer == lastPlayer) {
            currentTable.clear();
        }
        if (input.equals("p")) {
            if (currentPlayer == lastPlayer) {
                return r;
            }
            currentPlayer = (currentPlayer + 1) % 3;
            r = 2;
            return r;
        }
        ArrayList<String> temp = new ArrayList<>();
        for (String s : input.split(",")) {
            s = s.trim();
            if (!temp.contains(s)) {
                temp.add(s);
            }
        }
        for (String s : temp) {
            chosenSuit.add(ftlPlayers[currentPlayer].getHands().get(Integer.parseInt(s) - 1));
        }
        if (isValidSuit(chosenSuit)) {
            ftlPlayers[currentPlayer].getHands().removeAll(chosenSuit);
            currentTable.clear();
            currentTable.addAll(chosenSuit);
            lastPlayer = currentPlayer;
            currentPlayer = (currentPlayer + 1) % 3;
            r = 1;
        }
        chosenSuit.clear();
        return r;
    }

    public boolean isGameOver() {
        return winner() >= 0;
    }

    public static void main(String[] args) {
        Card card1 = new Card(10, 0);
        Card card2 = new Card(11, 1);
        Card card3 = new Card(12, 2);
        Card card4 = new Card(13, 3);
        Card card5 = new Card(14, 0);
//        Card card6 = new Card(15, 1);
//        Card card7 = new Card(3, 0);
//        Card card8 = new Card(4, 1);
        ArrayList<Card> suit = new ArrayList<>();
        suit.add(card1);
        suit.add(card2);
        suit.add(card3);
        suit.add(card4);
//        System.out.println(equalCards(suit));
//        System.out.println(isStraight(suit));
        suit.add(card5);
//        suit.add(card6);
//        suit.add(card7);
//        suit.add(card8);
//        System.out.println(equalCards(suit));
//        System.out.println(equalCards(suit, 0, 1));
//        System.out.println(equalCards(suit, 4, 5));
//        System.out.println(equalCards(suit, 2, 5));
//        System.out.println(isStraight(suit));
        System.out.println(suit);
        System.out.println(suitKind(suit));
        System.out.println(suitKind(suit).contains("顺子"));
        System.out.println(suitKind(suit).charAt(2));
        System.out.println(((int) suitKind(suit).charAt(2) - '0') == 5);
    }
}
