import java.util.Scanner;

import CardHolders.Player;
import DeckAndCard.Card;

public class TestingGround {
    public static void main(String[] args) {
        
        Scanner scnr = new Scanner(System.in);
        RoundHandler roundHandler = new RoundHandler(scnr);
        Player p1 = new Player(1000, "p1");
        Player p2 = new Player(1000, "p2");
        Player p3 = new Player(1000, "p3");
        Player p4 = new Player(1000, "p4");

        p1.addCard(new Card(3, 13));
        p1.addCard(new Card(2, 8));

        p2.addCard(new Card(2, 11));
        p2.addCard(new Card(2, 14));

        p3.addCard(new Card(3, 8));
        p3.addCard(new Card(3, 2));

        p4.addCard(new Card(4, 14));
        p4.addCard(new Card(2, 9));



        roundHandler.addPlayer(p1);
        roundHandler.addPlayer(p2);
        roundHandler.addPlayer(p3);
        roundHandler.addPlayer(p4);

        roundHandler.forceCommunityCard(1, 8);
        roundHandler.forceCommunityCard(3, 12);
        roundHandler.forceCommunityCard(4, 2);
        roundHandler.forceCommunityCard(2, 12);
        roundHandler.forceCommunityCard(3, 9);


        roundHandler.printPlayerStates();
        roundHandler.printCommunityCards();

        roundHandler.calculateAndAwardWinner();
        roundHandler.printPlayerHandRank();
        
    }
    /* FIXME:
p1: 1000 | Cards Held: [S3, V13] [S2, V8]
p2: 1000 | Cards Held: [S2, V11] [S2, V14]
p3: 1000 | Cards Held: [S3, V8] [S3, V2]
p4: 1000 | Cards Held: [S4, V14] [S2, V9]
Community Cards [S:1, V:8] [S:3, V:12] [S:4, V:2] [S:2, V:12] [S:3, V:9]
p1, p3, p4, and  won a pot of 0 credits! With each player getting 0 credits each!
p1: 1000 | Cards Held: [S3, V13] [S2, V8] [3, 8]
p2: 1000 | Cards Held: [S2, V11] [S2, V14] [2, 12]
p3: 1000 | Cards Held: [S3, V8] [S3, V2] [3, 2]
p4: 1000 | Cards Held: [S4, V14] [S2, V9] [3, 9]
     */

}