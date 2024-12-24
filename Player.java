public class Player extends CardHolder{
    private CardLinkedList<Card> cardsHeldMinusCommunity;
    private int balance;
    private String playerName;

    Player(int startingBalance, String playerName){
        super();
        balance = startingBalance;
        this.playerName = playerName;
        cardsHeldMinusCommunity = new CardLinkedList<>();
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
            cardsHeldMinusCommunity.add(card);
        }
        super.addCard(card);
    }

    @Override
    public String toString() {
        String s1 = String.format("%s: %d", playerName, balance);
        if(super.cardsHeld.get(0) != null){
            String s2 = String.format(" | Cards Held: [%d, %d] [%d, %d]", 
            cardsHeldMinusCommunity.get(0).getSuit(), 
            cardsHeldMinusCommunity.get(0).getValue(),
            cardsHeldMinusCommunity.get(1).getSuit(), 
            cardsHeldMinusCommunity.get(1).getValue());
            s1 += s2;
        }
        return s1;
    }
}
