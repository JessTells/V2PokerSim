public class Player extends CardHolder{
    private CardLinkedList<Card> displayCards;
    private int balance;
    private String playerName;

    Player(int startingBalance, String playerName){
        super();
        balance = startingBalance;
        this.playerName = playerName;
        displayCards = new CardLinkedList<>();
    }

    public int getBalance(){
        return balance;
    }

    public String getPlayerName(){
        return playerName;
    }

    public void bet(int betAmt){
        balance -= betAmt;
    }

    public void addToBalance(int add){
        balance += add;
    }

    public void subFromBalance(int sub){
        balance -= sub;
    }

    @Override
    public void addCard(Card card) {
        if(super.cardsHeld.size() < 2){
            displayCards.add(card);
        }
        super.addCard(card);
        if(card.getValue() == 14){
            Card ace = new Card(card.getSuit(), 1);
            super.addCard(ace);
        }
    }

    @Override
    public String toString() {
        String s1 = String.format("%s: %d", playerName, balance);
        if(super.cardsHeld.size() != 0){
            String s2 = String.format(" | Cards Held: [%d, %d] [%d, %d]", 
            displayCards.get(0).getSuit(), 
            displayCards.get(0).getValue(),
            displayCards.get(1).getSuit(), 
            displayCards.get(1).getValue());
            s1 += s2;
        }
        return s1;
    }
}
