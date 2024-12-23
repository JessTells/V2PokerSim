import java.util.Scanner;
public class TestingGround {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        RoundHandler roundHandler = new RoundHandler(scnr);
        Deck deck = new Deck();
        Player p1 = new Player(1000, "p1");
        Player p2 = new Player(1000, "p2");
        Player p3 = new Player(1000, "p3");
        Player p4 = new Player(1000, "p4");

        roundHandler.addPlayer(p1);
        roundHandler.addPlayer(p2);
        roundHandler.addPlayer(p3);
        roundHandler.addPlayer(p4);

        roundHandler.setCurrentPlayer(0);

        // blind bet phase
        int betCounter = 0;
        roundHandler.setCurrentPlayer(betCounter);
        while(roundHandler.getCurrentPlayer() != roundHandler.getPreviousPlayerWhoRaise() && roundHandler.getAmountOfPlayers() > 1){
            boolean currentPlayerFolded = roundHandler.blindBet();
            if(!currentPlayerFolded){
                ++betCounter;
            }
            roundHandler.setCurrentPlayer(betCounter);
        }

        if(roundHandler.getAmountOfPlayers() < 1){
            System.out.println("Winner");
        }

        // flop

        // non-blind bet phase


        
    }
}