public class Player extends CardHolder{
    private int balance;
    private String playerName;

    Player(int startingBalance, String playerName){
        super();
        balance = startingBalance;
        this.playerName = playerName;
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
        String s = String.format("%s: %.2d", playerName, balance);
        return s;
    }
}
