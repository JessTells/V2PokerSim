package HandCalculation;
import java.util.Arrays;

import DeckAndCard.Card;

public class Rank implements Comparable<Rank>{
    public int handRank;
    public Card[] cards;

    public Rank(){
        handRank = -1;
        cards = new Card[5];
    }

    public Rank(int handRank, Card[] cards){
        this.handRank = handRank;
        this.cards = cards;
    }

    @Override
    public String toString() {
        String s = "Cards: ";
        s += Arrays.toString(cards);
        s += " Rank: ";
        s += Integer.toString(handRank);
        return s;
    }

    @Override
    public int compareTo(Rank r) {
        int order = Integer.compare(handRank, r.handRank);
        if(order == 0){
            for(int i = 0; i < cards.length; ++i){
                order = cards[i].compareTo(r.cards[i]); 
                if(order != 0){
                    return order; 
                }
            }
        }else{
            return order;
        }
        // possible tie ranks: 9, 6, 5, 3, 2, 1
        return order;
    }
}
