import CardHolders.Player;
import DeckAndCard.*;
import HandCalculation.*;

import java.util.Scanner;


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

        Player p1 = new Player(1000, "p1", scnr);
        Player p2 = new Player(1000, "p2", scnr);
        Player p3 = new Player(1000, "p3", scnr);
        Player p4 = new Player(1000, "p4", scnr);

        roundHandler.addPlayer(p1);
        roundHandler.addPlayer(p2);
        roundHandler.addPlayer(p3);
        roundHandler.addPlayer(p4);

        roundHandler.betLoop();

        scnr.close();
    }
    /*
        
        roundHandler.dealPlayerCards();
        roundHandler.addThreeCardsToCommunity();
        roundHandler.addSingleCardToCommunity();
        roundHandler.addSingleCardToCommunity();

        roundHandler.printPlayerStates();
        roundHandler.printCommunityCards();

        roundHandler.calculateAndAwardWinner();
        roundHandler.printPlayerHandRank();
     */
    

}