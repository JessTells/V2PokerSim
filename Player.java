import java.util.LinkedList;
public class Player extends CardHolder{
    private int balance;
    private String playerName;

    Player(int startingBalance, String playerName){
        super();
        balance = startingBalance;
        this.playerName = playerName;
    }

    public String getCalcCards(){
        return super.toString();
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
    public String toString() {
        String s1 = String.format("%s: %d", playerName, balance);
        LinkedList<Card> displayCards = getDisplayCards();
        if(displayCards.size() > 0){
            String s2 = String.format(" | Cards Held: [S%d, V%d] [S%d, V%d]", 
            displayCards.get(0).getSuit(), 
            displayCards.get(0).getValue(),
            displayCards.get(1).getSuit(), 
            displayCards.get(1).getValue());
            s1 += s2;
        }
        return s1;
    }
}
