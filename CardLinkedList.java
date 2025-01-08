import java.util.Comparator;
import java.util.LinkedList;

public class CardLinkedList extends LinkedList<Card>{
    private CardCompareValues compVals;
    private CardCompareSuits compSuits;

    CardLinkedList(){
        super();
        compVals = new CardCompareValues();
        compSuits = new CardCompareSuits();
    }

    public void sortByValue(){
        sort(compVals);
    }

    public void sortBySuit(){
        sort(compSuits);
    }

    class CardCompareValues implements Comparator<Card>{
        @Override
        public int compare(Card card1, Card card2) {
            int cardSuitCompare = Integer.compare(card1.getValue(), card2.getValue()); 
            
            if(cardSuitCompare != 0){
                return cardSuitCompare;
            }
            
            return Integer.compare(card1.getSuit(), card2.getSuit());
        }
    }

    class CardCompareSuits implements Comparator<Card>{
        @Override
        public int compare(Card card1, Card card2) {
            int cardSuitCompare = Integer.compare(card1.getSuit(), card2.getSuit());
            
            
            if(cardSuitCompare != 0){
                return cardSuitCompare;
            }
            
            return Integer.compare(card1.getValue(), card2.getValue());
        }
    }
}


