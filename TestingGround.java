import java.util.Scanner;

import CardHolders.Player;
import DeckAndCard.*;
import HandCalculation.*;


//9: Straight Flush
//8: Four of a kind
//7: Full House
//6: Flush
//5: Straight
//4: 3 of a kind
//3: two pair
//2: pair
//1: high card

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
        roundHandler.printPlayerHandRank();

        p1.addCard(new Card(3, 13));
        p1.addCard(new Card(2, 8));

        p2.addCard(new Card(2, 11));
        p2.addCard(new Card(2, 14));

        p3.addCard(new Card(3, 8));
        p3.addCard(new Card(3, 2));

        p4.addCard(new Card(4, 14));
        p4.addCard(new Card(2, 9));
        

        CalculateHands.rankCardHand(p1);
        scnr.close();
        
    }
    /* FIXME:
        p1.addCard(new Card(1, 14));
        p1.addCard(new Card(1, 13));
        p1.addCard(new Card(1, 12));
        p1.addCard(new Card(1, 11));
        p1.addCard(new Card(1, 10));
        p1.addCard(new Card(1, 9));
        p1.addCard(new Card(4, 2));

p1: 1000 | Cards Held: [S1, V6] [S1, V4]
p2: 1000 | Cards Held: [S3, V10] [S2, V14]
p3: 1000 | Cards Held: [S3, V14] [S1, V9]
p4: 1000 | Cards Held: [S2, V7] [S4, V7]
Community Cards [S:1, V:14] [S:1, V:11] [S:3, V:9] [S:2, V:13] [S:3, V:8]
p3 won a pot of 0 credits!
p1: 1000 | Cards Held: [S1, V6] [S1, V4] Cards: [1 14, 2 13, 1 11, 3 9, 3 8] Rank: -1
p2: 1000 | Cards Held: [S3, V10] [S2, V14] Cards: [2 14, 1 14, 2 1, 1 1, 2 13] Rank: 3
p3: 1000 | Cards Held: [S3, V14] [S1, V9] Cards: [3 14, 1 14, 3 9, 1 9, 2 13] Rank: 3
p4: 1000 | Cards Held: [S2, V7] [S4, V7] Cards: [4 7, 2 7, 1 14, 2 13, 1 11] Rank: 2

        
     */
    

}