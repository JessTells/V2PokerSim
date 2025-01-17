package CardHolders;

public class BotPlayer extends Player{
    private static int botNum = 1;
    public BotPlayer(){
        super(1200, String.format("Bot %d", botNum));
        ++botNum;
    }

    @Override
    public int makeChoice() {

        return 0;
    }

    @Override
    public int makeBet(int prevBet) {
        
        return 0;
    }
}
