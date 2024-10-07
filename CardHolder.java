import java.util.LinkedList;
public abstract class CardHolder {

    private LinkedList<Card> cardsHeld;

    CardHolder(){
        cardsHeld = new LinkedList<Card>();
    }

    public void clearHand(){

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
