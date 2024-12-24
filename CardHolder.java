public abstract class CardHolder {

    protected CardLinkedList<Card> cardsHeld;

    CardHolder(){
        cardsHeld = new CardLinkedList<>();
    }

    public void clearCards(){
        cardsHeld.removeAll(cardsHeld);
    }

    public void addCard(Card card){
        cardsHeld.add(card);
    }

    public void sortCard(){
        cardsHeld.sort(cardsHeld);
    } 

    @Override
    public String toString() {
        String s1 = "";
        for(int i = 0; i < cardsHeld.size(); ++i){
            s1 += String.format("[%d, %d]", 
            cardsHeld.get(i).getSuit(), cardsHeld.get(i).getValue());
        }
        return s1;
    }
}
