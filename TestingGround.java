public class TestingGround {
    public static void main(String[] args) {
        Deck deck = new Deck();
        Player p1 = new Player();
        
        for(int i = 0; i < 52; ++i){
            p1.addCard(deck.giveCard());
        }
        p1.printCardsHeld();

    }
}