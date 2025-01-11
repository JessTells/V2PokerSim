import java.util.Scanner;

import CardHolders.Player;

public class TestingGround {
    public static void main(String[] args) {
        
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

        roundHandler.dealPlayerCards();
        roundHandler.addThreeCardsToCommunity();
        roundHandler.addSingleCardToCommunity();
        roundHandler.addSingleCardToCommunity();

        roundHandler.printPlayerStates();
        roundHandler.printCommunityCards();

        roundHandler.calculateAndAwardWinner();
        roundHandler.printPlayerHandRank();;
        
    }
    /* FIXME:
p1: 1000 | Cards Held: [S4, V10] [S3, V14]
p2: 1000 | Cards Held: [S1, V11] [S1, V12]
p3: 1000 | Cards Held: [S2, V8] [S1, V10]
p4: 1000 | Cards Held: [S3, V3] [S1, V14]
Community Cards [S:1, V:4] [S:2, V:6] [S:2, V:7] [S:3, V:13] [S:1, V:13]
p1, p2p1, p2, p3p1, p2p1, p2, p3, p4p1, p2p1, p2, p3p1, p2p1, p2, p3, p4, and  won a pot of 0 credits! With each player getting 0 credits each!
p1: 1000 | Cards Held: [S4, V10] [S3, V14] [2, 13]
p2: 1000 | Cards Held: [S1, V11] [S1, V12] [2, 13]
p3: 1000 | Cards Held: [S2, V8] [S1, V10] [2, 13]
p4: 1000 | Cards Held: [S3, V3] [S1, V14] [2, 13]
     */

}