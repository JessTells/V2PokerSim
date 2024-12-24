import java.util.ArrayList;
import java.util.Scanner;

public class RoundHandler {
    private Scanner scnr;
    private ArrayList<Player> players;
    private Deck deck;
    private CommunityHand communityHand;
    private Player[] foldedPlayers;
    private Player currentPlayer;
    private int pot;
    private int previousBet;
    private Player previousPlayerWhoRaised;
    

    RoundHandler(Scanner scnr){
        this.scnr = scnr;
        players = new ArrayList<>();
        foldedPlayers = new Player[4];
        deck = new Deck();
        communityHand = new CommunityHand();
    }

    public void addPlayer(Player p){
        players.add(p);
    }

    public boolean blindBet(){
        if(previousBet == 0){
            previousBet = 3;
        }
        System.out.printf(" %s\n", currentPlayer.getPlayerName());
        System.out.printf("(1) Current Blind Bet: %d\n", previousBet);
        System.out.println("(2) Raise?");
        System.out.println("(3) Fold?");
        System.out.printf("Enter your choice: ");
        int currPlayerChoice = scnr.nextInt();
        switch(currPlayerChoice){
            case 1:
            if(previousBet > currentPlayer.getBalance()){
                currPlayerBet(currentPlayer.getBalance());
            }else{
                currPlayerBet(previousBet);
            }
            return false;
            
            case 2:
            playerRaise();
            return false;

            case 3:
            removePlayerFromRound();
            return true;

            default:
            System.out.println("\n* Enter a valid choice *\n");
            return blindBet();
        }
    }

    private void playerRaise(){
        System.out.print("Enter your bet amount: ");
        int betAmount = scnr.nextInt();
        if(betAmount > currentPlayer.getBalance()){
            betAmount = currentPlayer.getBalance();
        }else if(betAmount < previousBet){
            System.out.printf("\n* Value is less than previous bet of %d*\n\n", previousBet);
            playerRaise();
        }else{
            currPlayerBet(betAmount);
            previousPlayerWhoRaised = currentPlayer;
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

    private void removePlayerFromRound(){
        if(players.size() > foldedPlayers.length){
            Player[] foldHold = new Player[players.size()];
            System.arraycopy(foldedPlayers, 0, foldHold, 0, foldedPlayers.length);
            foldedPlayers = foldHold;
        }
        for(int i = 0; i < players.size(); ++i){
            if(currentPlayer == players.get(i)){
                foldedPlayers[i] = currentPlayer;
                players.remove(i);
            }
        }
    }

    public void resetFoldedPlayers(){
        for(int i = 0; i < foldedPlayers.length; ++i){
            if(foldedPlayers[i] != null){
                players.add(i, foldedPlayers[i]);
            }
            foldedPlayers[i] = null;
        }
    }

    public Player getCurrentPlayer(){
        return currentPlayer;
    }

    public Player getPreviousPlayerWhoRaise(){
        return previousPlayerWhoRaised;
    }

    public int getAmountOfPlayers(){
        return players.size();
    }

    public void currentPlayerWon(){
        currentPlayer.addToBalance(pot);
        System.out.printf("%s has won the pot of %d credits", currentPlayer.getPlayerName(), pot);
    }

    public void addThreeCardsToCommunity(){
        addSingleCardToCommunity();
        addSingleCardToCommunity();
        addSingleCardToCommunity();
    }

    public void addSingleCardToCommunity(){
        Card cardToAdd = deck.giveCard();
        
        communityHand.addCard(cardToAdd);
        
        for(int i = 0; i < players.size(); ++i){
            players.get(i).addCard(cardToAdd);
        }
        for(int i = 0; i < players.size(); ++i){
            players.get(i).sortCard();
        }
    }

    public void dealPlayerCards(){
        for(int i = 0; i < players.size(); ++i){
            players.get(i).addCard(deck.giveCard());
            players.get(i).addCard(deck.giveCard());
        }
        for(int i = 0; i < players.size(); ++i){
            players.get(i).sortCard();
        }
    }

    public void printPlayerStates(){
        for(int i = 0; i < players.size(); ++i){
            System.out.println(players.get(i).toString());
        }
    }

    public void printCommunityCards(){
        System.out.println(communityHand.toString());
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

        //TODO reorder the players ArrayList to have p1 be last
        After a round the blind bet moves on to p2
     */
}
