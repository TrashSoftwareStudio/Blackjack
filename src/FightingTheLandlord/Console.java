package FightingTheLandlord;

import java.util.Scanner;

public class Console {
    public static void main(String[] args) {
        System.out.println("Welcome to Fighting The Landlord!");
        while (true) {
            System.out.println("Start game? (y/n):");
            Scanner scanner = new Scanner(System.in);
            if (scanner.nextLine().equals("y")) {
                FTLGame g = new FTLGame();
                g.deal();
                for (FTLPlayer p : g.getFtlPlayers()) {
                    System.out.println(p.getHands());
                }
                for (int i = 0; i < 3; i++) {
                    System.out.println("Player" + ((g.getFirstCall() + i) % 3 + 1) + " wants to be the landlord? (y/n):");
                    if (scanner.nextLine().equals("y")) {
                        g.getFtlPlayers()[(g.getFirstCall() + i) % 3].setLandlord();
                        g.setLandlord((g.getFirstCall() + i) % 3);
                        break;
                    }
                }
                if (g.getLandlord() < 0) {
                    g.getFtlPlayers()[g.getFirstCall()].setLandlord();
                    g.setLandlord(g.getFirstCall());
                }
                System.out.println("Landlord: Player" + (g.getLandlord() + 1));
            } else {
                System.out.println("See you next time!");
                break;
            }
        }
    }
}
