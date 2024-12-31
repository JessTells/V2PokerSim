import java.util.Scanner;
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

        while(!roundHandler.isThereWinner()){
            // blind bet phase
            roundHandler.betLoop();
            if(roundHandler.isThereWinner()){
                break;
            }

            // deal cards
            roundHandler.dealPlayerCards();
            roundHandler.printPlayerStates();

            // pre-flop bet phase
            roundHandler.betLoop();
            if(roundHandler.isThereWinner()){
                break;
            }

            // flop
            roundHandler.addThreeCardsToCommunity();
            roundHandler.printCommunityCards();

            roundHandler.printPot();
            roundHandler.betLoop();

            roundHandler.resetFoldedPlayers();
            roundHandler.printPlayerStates();
            
            break;
        }
        roundHandler.currentPlayerWon();
        
    }
}