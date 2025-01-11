package CardHolders;
import java.util.LinkedList;

import DeckAndCard.Card;


public abstract class CardHolder {
    
    private LinkedList<Card> displayCards;

    CardHolder(){
        displayCards = new LinkedList<>();
    }

    public LinkedList<Card> getDisplayCards(){
        return displayCards;
    }

    public void clearCards(){
        
        displayCards.clear();
    }

    public void addCard(Card card){
        displayCards.add(card);
    }


    @Override
    public String toString() {
        String s1 = "";
        if(displayCards.size() > 0){
            for(int i = 0; i < displayCards.size(); ++i){
                s1 += String.format("[S:%d, V:%d] ", 
                displayCards.get(i).getSuit(), 
                displayCards.get(i).getValue());
            }
        }
        return s1;
    }

    
}
