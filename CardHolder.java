public abstract class CardHolder {

    private CardLinkedList<Card> cardsHeld;

    CardHolder(){
        cardsHeld = new CardLinkedList<>();
    }

    public void clearHand(){
        cardsHeld.removeAll(cardsHeld);
    }

    public void addCard(Card card){
        cardsHeld.add(card);
    }

    public void printCardsHeld(){
        for(int i = 0; i < cardsHeld.size(); ++i){
            System.out.println(cardsHeld.get(i));
        }
    }
}
