package CardHolders;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import DeckAndCard.Card;
import HandCalculation.Rank;

public class Player extends CardHolder implements Comparable<Player>{
    private int balance;
    private String playerName;
    private HashMap<Integer, ArrayList<Integer>> cardsValueMapSuitList; // for calculating hand ranks
    //private int[] handRank;
    private int cardsAdded;
    private Rank rank;

    public Player(int startingBalance, String playerName){
        super();
        balance = startingBalance;
        this.playerName = playerName;
        cardsValueMapSuitList = new HashMap<>();
        for(int i = 1; i <= 14; ++i){
            cardsValueMapSuitList.put(i, new ArrayList<Integer>());
        }
        cardsAdded = 0;
    }

    public int getBalance(){
        return balance;
    }

    public String getPlayerName(){
        return playerName;
    }

    public void bet(int betAmt){
        balance -= betAmt;
    }

    public void addToBalance(int add){
        balance += add;
    }

    public void subFromBalance(int sub){
        balance -= sub;
    }

    public void setHandRank(Rank rank){
        this.rank = rank;
    }

    public Rank getHandRank(){
        return rank;
    }

    public HashMap<Integer, ArrayList<Integer>> getCalcCards(){
        return cardsValueMapSuitList;
    }

    @Override
    public void clearCards() {
        for(Map.Entry<Integer, ArrayList<Integer>> set : cardsValueMapSuitList.entrySet()) {
            if(set.getValue().size() > 0){
                set.getValue().clear();
            }
        }
        super.clearCards();
    }

    @Override
    public void addCard(Card card) {
        // uses basic ordered insertion algorithm since the list gets AT MOST length of 4, so I feel it is a negligable time save
        ArrayList<Integer> currList = cardsValueMapSuitList.get(card.getValue());
        int compCardSuit = card.getSuit();
        int insertIndex = 0;
        for(int i = 0; i < currList.size() && compCardSuit > currList.get(i); ++i){
            ++insertIndex;
        }
        currList.add(insertIndex, compCardSuit);
        if(card.getValue() == 14){
            cardsValueMapSuitList.get(1).add(insertIndex, compCardSuit);;
        }
        

        /*FIXME
        if(cardsAdded < 2){
            super.addCard(card);
        } 
         */
        super.addCard(card);
        
        ++cardsAdded;
    }


    @Override
    public String toString() {
        /* FIXME
        String s1 = String.format("%s: %d", playerName, balance);
        
        LinkedList<Card> displayCards = getDisplayCards();
        if(!displayCards.isEmpty()){
            String s2 = String.format(" | Cards Held: [S%d, V%d] [S%d, V%d]", 
            displayCards.get(0).getSuit(), 
            displayCards.get(0).getValue(),
            displayCards.get(1).getSuit(), 
            displayCards.get(1).getValue());
            s1 += s2;
        }

        */
        
        return super.toString();
    }

    public String printCalculationCards(){
        String s1 = "\nCalculation Cards: ";
        for(Map.Entry<Integer, ArrayList<Integer>> set : cardsValueMapSuitList.entrySet()) {
            
            ArrayList<Integer> currList = set.getValue(); 
            if(!currList.isEmpty()){
                for(int i = 0; i < currList.size(); ++i){
                    s1 += String.format("[S:%d, V:%d] ",
                     currList.get(i), set.getKey());
                }   
            }
        }
        return s1;
    }

    public String printHandRank(){
        String s = rank.toString();
        return s;
    }

    @Override
    public int compareTo(Player p) {
        return rank.compareTo(p.rank);
    }

    
}
