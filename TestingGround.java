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

        //FIXME if there is a straight that does not end at 14 then it will not be added to Card[] cards in CalculateHands
        p1.addCard(new Card(4, 5));
        p1.addCard(new Card(4, 3));
        p1.addCard(new Card(1, 3));
        p1.addCard(new Card(1, 11));
        p1.addCard(new Card(4, 4));
        p1.addCard(new Card(4, 7));
        p1.addCard(new Card(2, 6));
        CalculateHands.rankCardHand(p1);
        System.out.println(p1.toString());
        System.out.println(p1.toStringHandRank());

        
        
        scnr.close();
    }
    /* FIXME:
        
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

p1: 1000 | Cards Held: [S3, V13] [S1, V5]
p2: 1000 | Cards Held: [S3, V10] [S4, V10]
p3: 1000 | Cards Held: [S3, V14] [S2, V3]
p4: 1000 | Cards Held: [S3, V8] [S4, V2]
Community Cards [S:2, V:2] [S:2, V:10] [S:4, V:9] [S:3, V:12] [S:1, V:3]
p2 won a pot of 0 credits!
p1: 1000 | Cards Held: [S3, V13] [S1, V5] Cards: [3 13, 3 12, 2 10, 4 9, 1 5] Rank: -1
p2: 1000 | Cards Held: [S3, V10] [S4, V10] Cards: [3 10, 3 10, 2 10, 3 12, 4 9] Rank: 4
p3: 1000 | Cards Held: [S3, V14] [S2, V3] Cards: [2 3, 1 3, 3 14, 3 12, 2 10] Rank: 2
p4: 1000 | Cards Held: [S3, V8] [S4, V2] Cards: [4 2, 2 2, 3 12, 2 10, 4 9] Rank: 2        
     */
    

}