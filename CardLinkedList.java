import java.util.LinkedList;
import java.util.Comparator;


// FIXME: make separate comparator classes for various sortings to calculate CardHand rankings
public class CardLinkedList<E> extends LinkedList<E> implements Comparator<Card>{

    CardLinkedList(){
        super();
    }
    @Override
    public int compare(Card card1, Card card2) {
        int cardSuitCompare = Integer.compare(card1.getSuit(), card2.getSuit());
        if(cardSuitCompare != 0){
            return cardSuitCompare;
        }
        
        return Integer.compare(card1.getValue(), card2.getValue());
    }
}
