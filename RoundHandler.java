import java.util.ArrayList;
import java.util.Scanner;

import CardHolders.*;
import DeckAndCard.*;

import java.util.HashSet;
import java.util.LinkedList;

public class RoundHandler {
    private Scanner scnr;
    private ArrayList<Player> players;
    private Deck deck;
    private CommunityHand communityHand;
    private HashSet<Player> foldedPlayers;
    private Player currentPlayer;
    private int pot;
    private int previousBet;
    private Player previousPlayerWhoRaised;
    

    RoundHandler(Scanner scnr){
        this.scnr = scnr;
        players = new ArrayList<>();
        foldedPlayers = new HashSet<>();
        deck = new Deck();
        communityHand = new CommunityHand();
        previousBet = 3;
    }

    public void addPlayer(Player p){
        players.add(p);
    }

    //TODO: make the interface for playerBet() better, make playerSelection so that in the future it interacts with bots smoothly
    //TODO: all things that require input are going to need to be changed so that it interacts with bots
    private void playerBet(){
        System.out.printf(" %s\n", currentPlayer.getPlayerName());
        System.out.printf("(1) Current Bet: %d\n", previousBet);
        System.out.println("(2) Raise?");
        System.out.println("(3) Fold?");
        System.out.printf("Enter your choice: ");
        int currPlayerChoice = scnr.nextInt();
        playerSelection(currPlayerChoice);
    }

    private void playerSelection(int currPlayerChoice){
        switch(currPlayerChoice){
            case 1:
            if(previousBet > currentPlayer.getBalance()){
                currPlayerBet(currentPlayer.getBalance());
            }else{
                currPlayerBet(previousBet);
            }
            if(previousPlayerWhoRaised == null){
                previousPlayerWhoRaised = currentPlayer;
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
        int betAmount = scnr.nextInt();
        if(betAmount > currentPlayer.getBalance()){
            betAmount = currentPlayer.getBalance();
            currPlayerBet(betAmount);
        }else if(betAmount < previousBet){
            System.out.printf("\n* Value is less than previous bet of %d*\n\n", previousBet);
            playerRaise();
        }else{
            currPlayerBet(betAmount);
            previousPlayerWhoRaised = currentPlayer;
        }
    }

    private void currPlayerBet(int betAmount){
        currentPlayer.subFromBalance(betAmount);
        pot += betAmount;
        previousBet = betAmount;
    }


    private void removePlayerFromRound(){
        foldedPlayers.add(currentPlayer);
    }

    public void resetFoldedPlayers(){
        foldedPlayers.clear();
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
    }

    public void dealPlayerCards(){
        for(int i = 0; i < players.size(); ++i){
            players.get(i).addCard(deck.giveCard());
            players.get(i).addCard(deck.giveCard());
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

    public void printPot(){
        System.out.printf("Pot: %d credits\n", pot);
    }

    public void betLoop(){
        int currPlayerIndex = 0;
        currentPlayer = players.get(currPlayerIndex);
        while(currentPlayer != previousPlayerWhoRaised && players.size() - foldedPlayers.size() > 1){
            if(!foldedPlayers.contains(currentPlayer)){
                playerBet();
            }
            ++currPlayerIndex;
            currentPlayer = players.get(currPlayerIndex % players.size());
            
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
        calculateWinner();
    }

    public void calculateWinner(){
        System.out.println("Fix RoundHandler: calculateWinner()");
        //FIXME Calculate player hands
        
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
        currentPlayer = null;
    }

    public void setNextBlindBetStarterPlayer(){
        Player p = players.get(0);
        players.remove(0);
        players.add(p);
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
