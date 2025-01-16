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
        
        scnr.close();
    }
    /* FIXME:

        p1.addCard(new Card(4, 14));
        p1.addCard(new Card(4, 13));
        p1.addCard(new Card(4, 12));
        p1.addCard(new Card(4, 11));
        p1.addCard(new Card(4, 10));
        p1.addCard(new Card(2, 11));
        p1.addCard(new Card(3, 3));
        CalculateHands.rankCardHand(p1);
        System.out.println(p1.toString());
        System.out.println(p1.toStringCalculationCards());
        System.out.println(p1.toStringHandRank());
     */
    

}