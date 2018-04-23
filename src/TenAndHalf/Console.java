package TenAndHalf;

import java.util.Scanner;

public class Console {

    public static void main(String[] args) {
        System.out.println("Welcome to Ten And A Half!!!");
        while (true) {
            System.out.println("Start game? (y/n):");
            Scanner scanner = new Scanner(System.in);
            if (scanner.nextLine().equals("y")) {
                TAHGame game;
                while (true) {
                    System.out.println("How many players? (2 to 4):");
                    try {
                        int playerNumber = Integer.valueOf(scanner.nextLine());
                        if (playerNumber < 2 || playerNumber > 4) {
                            System.out.println("Too many or too few players");
                            continue;
                        }
                        game = new TAHGame(playerNumber);
                        break;
                    } catch (Exception e) {
                        System.out.println("Please input a number, you idiot.");
                    }
                }
                game.startGame();

                LOOP:
                while (true) {
                    System.out.println("Player " + game.getCurrentPlayer().getPlayerId() + "'s turn");
                    while (true) {
                        System.out.println(game.getCurrentPlayer().getHands());
                        System.out.println("Require for next card? (y/n):");
                        if (scanner.nextLine().equals("y")) {
                            if (!game.requireCard(true)) {
                                System.out.println("You cannot get more cards.");
                                if (!game.skipPlayer()) break LOOP;
                                else break;
                            }
                        } else {
                            if (!game.skipPlayer()) break LOOP;
                            else break;
                        }
                    }
                }

                for (TAHPlayer player : game.getPlayers()) System.out.println(player.getHands());
                System.out.println("Player " + game.compare() + " wins!");
            } else {
                System.out.println("See you next time!");
                break;
            }
        }
    }
}
