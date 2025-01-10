package CardHolders;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import DeckAndCard.Card;

public class Player extends CardHolder implements Comparable<Player>{
    private int balance;
    private String playerName;
    private HashMap<Integer, ArrayList<Integer>> cardsValueMapSuitList; // for calculating hand ranks
    private int[] handRank;

    public Player(int startingBalance, String playerName){
        super();
        balance = startingBalance;
        this.playerName = playerName;
        cardsValueMapSuitList = new HashMap<>();
        for(int i = 1; i <= 14; ++i){
            cardsValueMapSuitList.put(i, new ArrayList<Integer>());
        }
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

    public void setHandRank(int[] handRank){
        this.handRank = handRank;
    }

    public int[] getHandRank(){
        return handRank;
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
        super.addCard(card);
    }


    @Override
    public String toString() {
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

        String s3 = "\nCalculation Cards: ";
        for(Map.Entry<Integer, ArrayList<Integer>> set : cardsValueMapSuitList.entrySet()) {
            
            ArrayList<Integer> currList = set.getValue(); 
            if(!currList.isEmpty()){
                for(int i = 0; i < currList.size(); ++i){
                    s3 += String.format("[S:%d, V:%d] ",
                     currList.get(i), set.getKey());
                }   
            }
        }
        s1 += s3;
        
        return s1;
    }

    @Override
    public int compareTo(Player p) {
        int order = Integer.compare(handRank[0], p.handRank[0]);
        if(order == 0){
            order = Integer.compare(handRank[1], p.handRank[0]);
        }else{
            return order;
        }
        if(order == 0){
            for(int  i = 14; i >= 2; --i) {
                boolean xListEmpty = cardsValueMapSuitList.get(i).isEmpty();
                boolean yListEmpty = p.cardsValueMapSuitList.get(i).isEmpty();
                if(xListEmpty && !yListEmpty){
                    return -1;
                }else if(!xListEmpty && yListEmpty){
                    return 1;
                }
            }
        }
        return 0;
    }

    
}
