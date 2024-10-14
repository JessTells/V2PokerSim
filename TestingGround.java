public class TestingGround {
    public static void main(String[] args) {
        Deck deck = new Deck();
        Player p1 = new Player(1000, "p1");
        
        for(int i = 0; i < 4; ++i){
            p1.addCard(deck.giveCard());
        }
        p1.printCardsHeld();
        p1.sortCard();
        p1.printCardsHeld();
    }
}