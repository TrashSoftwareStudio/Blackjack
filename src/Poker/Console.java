package Poker;


import java.util.Scanner;

public class Console {
    public static void main(String[] args) {
        System.out.println("Welcome to Poker!");
        while (true) {
            System.out.println("Start game? (y/n):");
            Scanner scanner = new Scanner(System.in);
            if (scanner.nextLine().equals("y")) {
                System.out.println("How many players?:");
                int numOfPlayers = Integer.parseInt(scanner.nextLine());
                PokerGame g = new PokerGame(numOfPlayers);
                g.deal();
                for (PokerPlayer p : g.getPokerPlayers()) {
                    System.out.println(p.getHands());
                }
//                while (!g.isGameOver()) {
//                    int current = g.getCurrentPlayer() + 1;
//                    for (FTLPlayer p : g.getFtlPlayers()) {
//                        System.out.println(p.getHands());
//                    }
//                    System.out.println("Player" + current + " please choose cards:");
//                    g.round(scanner.nextLine());
//                    System.out.println("Player" + current + " chose:" + g.getCurrentTable());
//                }
            } else {
                System.out.println("See you next time!");
                break;
            }
        }
    }
}
