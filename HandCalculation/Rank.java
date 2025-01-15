package HandCalculation;
import java.util.Arrays;

import DeckAndCard.Card;

public class Rank implements Comparable<Rank>{
    public int[] handRank;
    public Card[] cards;

    public Rank(){
        handRank = new int[]{0,0};
        cards = new Card[5];
    }

    public Rank(int[] handRank, Card[] cards){
        this.handRank = handRank;
        this.cards = cards;
    }

    @Override
    public String toString() {
        String s = "Cards: ";
        s += Arrays.toString(cards);
        s += "\nRank: ";
        s += Arrays.toString(handRank);
        return s;
    }

    @Override
    public int compareTo(Rank r) {
        int order = Integer.compare(handRank[0], r.handRank[0]);
        if(order == 0){
            order = Integer.compare(handRank[1], r.handRank[1]);
        }else{
            return order;
        }
        // possible tie ranks: X9, 6, 5, 3, 2, 1
        if(handRank[0] == 9){
            return 0;
        }
        if(handRank[0] == 6){

        }
        return order;
    }
}
