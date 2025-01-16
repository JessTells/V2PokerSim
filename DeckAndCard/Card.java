package DeckAndCard;
/*
 A single card that contains a Suit and Number
 Acts like a node
 */

public class Card implements Comparable<Card>{
    private int suit; // 1 diamonds, 2 hearts, 3 clubs, 4 spades
    private int value; // 1 Ace, 11 Jack, 12 Queen, 13 King, 14 Ace

    public Card(int suit, int value){ //TODO: make not public
        this.suit = suit;
        this.value = value;
    }

    public int getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString(){
        String cardStr = String.format("{S%d, V%d}", suit, value); 
        return cardStr;
    }

    @Override
    public int compareTo(Card c) {
        return Integer.compare(value, c.value);
    }
}
