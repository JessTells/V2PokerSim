import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.ArrayList;

public abstract class CardHolder {
    private HashMap<Integer, ArrayList<Integer>> cardsValueMapSuitList; // for calculating hand ranks
    private LinkedList<Card> displayCards;

    CardHolder(){
        displayCards = new LinkedList<>();
        cardsValueMapSuitList = new HashMap<>();
        for(int i = 1; i <= 14; ++i){
            cardsValueMapSuitList.put(i, new ArrayList<Integer>());
        }
    }

    public LinkedList<Card> getDisplayCards(){
        return displayCards;
    }

    public void clearCards(){
        for(Map.Entry<Integer, ArrayList<Integer>> set : cardsValueMapSuitList.entrySet()) {
            if(set.getValue().size() > 0){
                set.getValue().clear();
            }
        }
        displayCards.clear();
    }

    public void addCard(Card card){
        cardsValueMapSuitList.get(card.getValue()).add(card.getSuit());
        if(card.getValue() == 14){
            cardsValueMapSuitList.get(1).add(card.getSuit());
        }
        displayCards.add(card);
    }

    public void sortByValues(){
        
    }

    public void sortBySuit(){

    }

    public void rankCardHand(){
        //Straight Flush (can also be royal flush with high of 14)
        //  All same suit with sequential values
        //Four of a kind
        //  Same value for 4 cards
        //Full House
        //  3 of a kind with a pair
        //Flush
        //  5 cards of same suit    
        //Straight
        //  Sequential 5 cards
        //3 of a kind
        //  3 of same value
        //two pair
        //  two pairs
        //high card
        //  get highet card
    
    }

    public void checkForStraightFlush(){
        // Probably need to go back to the drawing board with how cards are stored
        // Mainly change the storage in cardsHeld
        // thinking of a HashMap with the key as the Card Value and a LinkedList for the stored object
        // Reasoning: For ranking the hand of the cards, I would need to quickly know sequential card values and all of the current card value suits that the player has and the compared card suits
        // With just a LinkedList that the cards are stored in, it would be difficult to know if a player with (1, 4) (2, 4) (1, 5) (2, 5) has a Straight Flush, even though it is possible that they do


        // cardsHeld.sortByValue();
        // int consequtiveStreak = 0;
        // int flushStreak = 0;
        // int straightFlushStreak = 0;
        // ArrayList<Integer>dupeValSuits = new ArrayList<>();
        // int currVal = cardsHeld.get(0).getValue();
        // int currSuit = cardsHeld.get(0).getSuit();
        // for(int i = 1; i < cardsHeld.size(); ++i){
        //     int compVal = cardsHeld.get(i).getValue();
        //     int compSuit = cardsHeld.get(0).getSuit();
        //     if(currVal == compVal){
        //         dupeValSuits.add(currSuit);
        //     }else if(currVal == compVal - 1){
        //         dupeValSuits.add(currSuit);
        //         for(int j = 0; j < dupeValSuits.size(); ++i){
                    
        //         }
        //     }
        // }
        
    }



    @Override
    public String toString() {
        String s1 = "";

        for(Map.Entry<Integer, ArrayList<Integer>> set : cardsValueMapSuitList.entrySet()) {
            ArrayList<Integer> currList = set.getValue(); 
            if(currList.size() > 0){
                for(int i = 0; i < currList.size(); ++i){
                    s1 += String.format("[S:%d, V:%d]",
                     currList.get(i), set.getKey());
                }
            }
        }
        return s1;
    }

    
}
