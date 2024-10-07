public class CardLinkedList {
    CardNode head;
    CardNode tail;

    CardLinkedList(){
        head = null;
        tail = null;
    }

    public void addCard(Card card){ // FIXME: Figure out how Deck.java interacts with CardLinkedList
        if(head == null){
            head = new CardNode(card);
            tail = head;
        }else{ // FIXME:
            // tail.nextCard = new CardNode(card);
            // tail = tail.nextCard;
        }

        
    }


    class CardNode {
        public Card currentCard;
        public Card nextCard;
        CardNode(){

        }

        CardNode(Card card){
            this.currentCard = card;
        }
    }
    
}
