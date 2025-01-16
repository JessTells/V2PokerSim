package DeckAndCard;

public class CardSuitArray {
    private int cardValue;
    private int size;
    public Card[] cardSuits; //TODO this could be a LinkedList
    public Card firstCardAdded;

    public CardSuitArray(Card card){
        cardSuits = new Card[4];
        cardSuits[card.getSuit()-1] = card;
        size = 1;
        cardValue = card.getValue();
    }

    public void addCardSuit(Card card){
        if(firstCardAdded == null){
            firstCardAdded = card;
        }
        cardSuits[card.getSuit()-1] = card;
        ++size;
    }

    public int size(){
        return size;
    } 

    public int getCardValue(){
        return cardValue;
    }
}
