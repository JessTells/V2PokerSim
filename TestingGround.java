//import java.util.Scanner;

import java.util.Arrays;

public class TestingGround {
    public static void main(String[] args) {
        /*
        Scanner scnr = new Scanner(System.in);
        RoundHandler roundHandler = new RoundHandler(scnr);
        Player p1 = new Player(1000, "p1");
        Player p2 = new Player(1000, "p2");
        Player p3 = new Player(1000, "p3");
        Player p4 = new Player(1000, "p4");

        roundHandler.addPlayer(p1);
        roundHandler.addPlayer(p2);
        roundHandler.addPlayer(p3);
        roundHandler.addPlayer(p4);

        while(true){
            roundHandler.roundLoop();
            roundHandler.currentPlayerWon();
            roundHandler.resetEverythingForNewRound();
            
            System.out.println("\n\n  **New Round**\n");
        }
        */
        //Deck deck = new Deck();
        Player player = new Player(1000, "test");
        player.addCard(new Card(1, 2));
        player.addCard(new Card(1, 3));
        player.addCard(new Card(2, 3));
        player.addCard(new Card(2, 4));
        player.addCard(new Card(2, 5));
        player.addCard(new Card(1, 5));
        player.addCard(new Card(1, 6));
        player.rankCardHand();
        System.out.println(Arrays.toString(player.getCardRank()));
    }
}