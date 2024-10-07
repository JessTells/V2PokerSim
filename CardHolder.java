public abstract class CardHolder {
    CardLinkedList cardsHeld;

    CardHolder(){
        cardsHeld = new CardLinkedList();
    }

    public void clearHand(){

    }

    public void addCard(Card card){
        cardsHeld.addCard(card);
        // interacts with the linkedlist
    }
}
