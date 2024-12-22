import java.util.ArrayList;
import java.util.Scanner;

public class RoundHandler {
    private Scanner scnr;
    private ArrayList<Player> players;
    private Player currentPlayer;
    private int pot;
    private int previousBet;
    

    RoundHandler(Scanner scnr){
        this.scnr = scnr;
        players = new ArrayList<>();
    }

    public void addPlayer(Player p){
        players.add(p);
    }

    public void blindBet(){
        System.out.println("(1) Blind bet: 3");
        System.out.println("(2) Raise?");
        int currPlayerChoice = scnr.nextInt();
        switch(currPlayerChoice){
            case 1:
            
            if(3 > currentPlayer.getBalance()){
                currPlayerBet(currentPlayer.getBalance());
            }else{
                currPlayerBet(3);
            }
            break;
            
            case 2:
            int betAmount = scnr.nextInt();
            if(betAmount > currentPlayer.getBalance()){
                betAmount = currentPlayer.getBalance();
            }
            currPlayerBet(betAmount);
            break;

            default:
            System.out.println("\n* Enter a valid choice* \n");
            blindBet();
            break;
        }
    }

    public void currPlayerBet(int betAmount){
        currentPlayer.subFromBalance(betAmount);
        pot += betAmount;
        previousBet = betAmount;
    }

    public void setCurrentPlayer(int playerIndex){
        currentPlayer = players.get(playerIndex % players.size());
    }

    /*
        A simple round in Texas Holdem Poker:
        Blind Bet of p1
        deal 2 cards to each player
        p2 raises or checks or folds
        p3 raises or checks or folds
        ...
        pn raises or checks or folds
        
        ^ This loops until all players have checked or all but 1 folded

        If all but 1 player folds then that players gets the pot

        If there are still players then the first card is revealed, process begins again

        After a round the blind bet moves on to p2
     */
}
