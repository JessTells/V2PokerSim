
/*
 A single card that contains a Suit and Number
 Acts like a node
 */

public class Card {
    private int suit; // 1 diamonds, 2 hearts, 3 clubs, 4 spades
    private int value; // 1 Ace, 11 Jack, 12 Queen, 13 King

    Card(int suit, int value){
        this.suit = suit;
        this.value = value;
    }

    public int getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }

    public String printCard(){
        String cardStr = suit + " " + value;
        return cardStr;
    }
}
