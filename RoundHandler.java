import java.util.ArrayList;
import java.util.Scanner;
import java.lang.StringBuilder;

import CardHolders.*;
import DeckAndCard.*;
import HandCalculation.CalculateHands;

import java.util.HashSet;
import java.util.LinkedList;

public class RoundHandler {
    private Scanner scnr;
    private ArrayList<Player> players;
    private Deck deck;
    private CommunityHand communityHand;
    private HashSet<Player> foldedPlayers;
    private Player currentPlayerTurn;
    private int pot;
    private int previousBet;
    private Player previousPlayerWhoRaised;
    

    public RoundHandler(Scanner scnr){
        players = new ArrayList<>();
        foldedPlayers = new HashSet<>();
        deck = new Deck();
        communityHand = new CommunityHand();
        previousBet = 3;
        this.scnr = scnr;
    }

    // TODO: createPlayer() method and createBot()

    public void addPlayer(Player p){
        players.add(p);
    }

    private void playerBet(){
        System.out.printf(" %s\n", currentPlayerTurn.getPlayerName());
        System.out.printf("(1) Current Bet: %d\n", previousBet);
        System.out.println("(2) Raise?");
        System.out.println("(3) Fold?");
        System.out.printf("Enter your choice: ");

        int currPlayerChoice;
        
        currPlayerChoice = currentPlayerTurn.makeChoice();
        
        playerBetSelection(currPlayerChoice);
    }

    private void playerBetSelection(int currPlayerChoice){
        switch(currPlayerChoice){
            case 1:
            int betAmount = currentPlayerTurn.subFromBalance(previousBet);
            pot += betAmount;
            if(previousPlayerWhoRaised == null){
                previousPlayerWhoRaised = currentPlayerTurn;
            }
            break;
            
            case 2:
            playerRaise();
            break;

            case 3:
            removePlayerFromRound();
            break;

            default:
            System.out.println("\n* Enter a valid choice *\n");
            break;
        }
    }

    private void playerRaise(){
        System.out.print("Enter your bet amount: ");
        int betAmount = currentPlayerTurn.makeBet(previousBet);
        pot += betAmount;
        previousBet = betAmount;
        previousPlayerWhoRaised = currentPlayerTurn;
    }

    private void removePlayerFromRound(){
        foldedPlayers.add(currentPlayerTurn);
    }

    public void resetFoldedPlayers(){
        foldedPlayers.clear();
    }

    public void currentPlayerWon(){
        currentPlayerTurn.addToBalance(pot);
        System.out.printf("%s has won the pot of %d credits", currentPlayerTurn.getPlayerName(), pot);
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
    }

    public void dealPlayerCards(){
        for(int i = 0; i < players.size(); ++i){
            players.get(i).addCard(deck.giveCard());
            players.get(i).addCard(deck.giveCard());
        }
    }

    public void printPlayerStates(){
        for(int i = 0; i < players.size(); ++i){
            if(!foldedPlayers.contains(players.get(i))){
                System.out.println(players.get(i).toString());    
            }
        }
    }

    public void printPlayerHandRank(){
        for(int i = 0; i < players.size(); ++i){
            if(!foldedPlayers.contains(players.get(i))){
                System.out.println(players.get(i).toString() + " " + players.get(i).toStringHandRank());    
            }
        }
    }

    public void printCommunityCards(){
        System.out.println(communityHand.toString());
    }

    public void printPot(){
        System.out.printf("Pot: %d credits\n", pot);
    }

    public void betLoop(){
        int currPlayerIndex = 0;
        currentPlayerTurn = players.get(currPlayerIndex);
        while(currentPlayerTurn != previousPlayerWhoRaised && players.size() - foldedPlayers.size() > 1){
            if(!foldedPlayers.contains(currentPlayerTurn)){
                playerBet();
            }
            ++currPlayerIndex;
            currentPlayerTurn = players.get(currPlayerIndex % players.size());
            
        }
        previousBet = 3;
        previousPlayerWhoRaised = null;
    }

    public boolean isThereWinner(){
        if(players.size() - foldedPlayers.size() <= 1){
            return true;
        }
        return false;
    }

    public void roundLoop(){
        betLoop();
        printPot();
        if(isThereWinner()){
            return;
        }
        dealPlayerCards();
        printPlayerStates();
        betLoop();
        if(isThereWinner()){
            return;
        }
        addThreeCardsToCommunity();
        printPot();
        printCommunityCards();
        betLoop();
        if(isThereWinner()){
            return;
        }
        printPot();
        addSingleCardToCommunity();
        printCommunityCards();
        betLoop();
        if(isThereWinner()){
            return;
        }
        printPot();
        addSingleCardToCommunity();
        printCommunityCards();
        betLoop();
        printPot();
        calculateAndAwardWinner();
    }

    public void calculateAndAwardWinner(){
        LinkedList<Player> winnerList = new LinkedList<>();
        for(int i = 0; i < players.size(); ++i){
            Player p = players.get(i);
            if(!foldedPlayers.contains(p)){
                CalculateHands.rankCardHand(p);
                if(winnerList.isEmpty()){
                    winnerList.add(p);
                }else{
                    int comparePlayerRank = p.compareTo(winnerList.get(0)); 
                    if(comparePlayerRank > 0){
                        winnerList.clear();
                        winnerList.add(p);
                    }else if(comparePlayerRank == 0){
                        winnerList.add(p);
                    }
                    
                }
                
            }
        }
        
        StringBuilder sb = new StringBuilder();
        if(winnerList.size() > 1){
            if(winnerList.size() == 2){
                sb.append(winnerList.get(0).getPlayerName());
                sb.append(" and ");
                sb.append(winnerList.get(1).getPlayerName());
            }else{
                sb.append(winnerList.get(0).getPlayerName());
                sb.append(", ");
                for(int i = 1; i < winnerList.size(); ++i){
                    sb.append(winnerList.get(i).getPlayerName());
                    if(i < winnerList.size() - 1){
                        sb.append(", ");
                    }else if(i == winnerList.size() - 1){
                        sb.append(", and ");
                    }
                }
            }
            
        }else{
            sb.append(winnerList.get(0).getPlayerName());
        }

        sb.append(String.format(" won a pot of %d credits!", pot));
        int amountToAward = Math.floorDiv(pot, winnerList.size());
        
        if(winnerList.size() > 1){
            sb.append(String.format(" With each player getting %d credits each!", amountToAward));
        }
        for(int i = 0; i < winnerList.size(); ++i){
            winnerList.get(i).addToBalance(amountToAward);
        }
        System.out.println(sb.toString());
    }

    public void resetEverythingForNewRound(){
        resetFoldedPlayers();
        communityHand.clearCards();
        for(int i = 0; i < players.size(); ++i){
            players.get(i).clearCards();
        }
        deck.populateDeck();
        deck.resetUsedCardIndex();
        setNextBlindBetStarterPlayer();
        currentPlayerTurn = null;
    }

    public void setNextBlindBetStarterPlayer(){
        Player p = players.get(0);
        players.remove(0);
        players.add(p);
    }

    // TODO: Remove below method
    public void forceCommunityCard(int suit, int value){
        Card cardToAdd = new Card(suit, value);
        communityHand.addCard(cardToAdd);
        for(int i = 0; i < players.size(); ++i){
            players.get(i).addCard(cardToAdd);
        }
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
