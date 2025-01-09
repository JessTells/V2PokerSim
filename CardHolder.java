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

        displayCards.add(card);
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
        int sequentialStreak = 0;
        int sequentialAndFlushStreak = 0;

        ArrayList<Integer> prevList = cardsValueMapSuitList.get(1);

        for(int i = 2; i < 14; ++i){
            if(prevList.size() == 0){
                continue;
            }
            boolean hasSameSuit = false;
            ArrayList<Integer> currList = cardsValueMapSuitList.get(i);
            if(currList.size() > 0){
                ++sequentialStreak;
                int j = 0;
                int k = 0;
                while(j < prevList.size() && k < currList.size()){
                    if(prevList.get(j) == currList.get(k)){
                        hasSameSuit = true;
                    }
                    if(prevList.get(j) < currList.get(k)){
                        ++j;
                    }else{
                        ++k;
                    }
                }
                if(hasSameSuit){
                    ++sequentialAndFlushStreak;
                }else{
                    sequentialAndFlushStreak = 0;
                }
            }else{
                sequentialStreak = 0;
                sequentialAndFlushStreak = 0;
            }
            prevList = currList;
        }
        System.out.printf("Straight: %d, Straight-Flush: %d", sequentialStreak, sequentialAndFlushStreak);
        
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
