import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
public class Player extends CardHolder{
    private int balance;
    private String playerName;
    private HashMap<Integer, ArrayList<Integer>> cardsValueMapSuitList; // for calculating hand ranks
    private int[] cardRanking;

    Player(int startingBalance, String playerName){
        super();
        balance = startingBalance;
        this.playerName = playerName;
        cardsValueMapSuitList = new HashMap<>();
        for(int i = 1; i <= 14; ++i){
            cardsValueMapSuitList.put(i, new ArrayList<Integer>());
        }
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

    public void rankCardHand(){
        //X 9 or 10: Straight Flush
        cardRanking = checkForStraightFlush();
        if(cardRanking[0] == 9){
            return;
        }
        
        //X 8: Four of a kind
        cardRanking = checkForNthOfAKind();
        if(cardRanking[0] == 8){
            return;
        }

        //X 7: Full House
        if(cardRanking[0] == 7){
            return;
        }

        //6: Flush
        if(cardRanking[0] == 6){
            return;
        }

        //X 5: Straight
        if(cardRanking[0] == 5){
            return;
        }

        //X 4: 3 of a kind
        if(cardRanking[0] == 4){
            return;
        }

        //X 3: two pair
        if(cardRanking[0] == 3){
            return;
        }

        //X 2: pair
        if(cardRanking[0] == 2){
            return;
        }
        
        //1: high card
    }

    public int[] checkForStraightFlush(){
        int sequentialStreak = 0;
        int sequentialAndFlushStreak = 0;
        int highCardSeq = -1;
        int highCardSeqAndFlush = -1;

        ArrayList<Integer> prevList = cardsValueMapSuitList.get(1);

        for(int i = 2; i <= 14; ++i){
            ArrayList<Integer> currList = cardsValueMapSuitList.get(i);
            if(prevList.size() == 0){
                prevList = currList;
                continue;
            }
            boolean hasSameSuit = false;
            if(currList.size() > 0){
                ++sequentialStreak;
                int j = 0;
                int k = 0;
                while(j < prevList.size() && k < currList.size()){
                    if(prevList.get(j) == currList.get(k)){
                        hasSameSuit = true;
                    }
                    if(prevList.get(j) < currList.get(k)){
                        ++j;
                    }else{
                        ++k;
                    }
                }
                if(hasSameSuit){
                    ++sequentialAndFlushStreak;
                }else{
                    sequentialAndFlushStreak = 0;
                }
            }else{
                sequentialStreak = 0;
                sequentialAndFlushStreak = 0;
            }
            prevList = currList;
            if(sequentialStreak == 4){
                highCardSeq = i;
                if(sequentialAndFlushStreak == 4){
                    highCardSeqAndFlush = i;
                }
            }
        }
        if(highCardSeqAndFlush > 0){
            return new int[] {9, highCardSeqAndFlush};
        }
        if(highCardSeq > 0){
            return new int[] {5, highCardSeq};
        }
        return new int[] {0, 0};
    }

    public int[] checkForNthOfAKind(){
        int[] rank = new int[]{0,0};
        boolean foundPair = false;
        boolean foundTwoPair = false;
        boolean foundThreeKind = false;
        boolean foundFourKind = false;
        for(Map.Entry<Integer, ArrayList<Integer>> set : cardsValueMapSuitList.entrySet()) {
            switch (set.getValue().size()) {
                case 2:
                    if(foundPair){
                            foundTwoPair = true;
                    }else{
                        foundPair = true;
                    }
                    
                    if(!foundTwoPair && !foundThreeKind){
                        rank[1] = set.getKey();
                    }
                    break;
            
                case 3:
                    foundThreeKind = true;
                    if(!foundFourKind){
                        rank[1] = set.getKey();
                    }
                    break;
                
                case 4:
                    if(set.getKey() == 1){
                        rank[1] = 14;
                    }else{
                        rank[1] = set.getKey();
                    }
                    foundFourKind = true;
                    break;

                default:
                    break;
            
            }
        }
        // TODO: Test two pair
        // TODO: Test 3 of a kind
        // TODO: Test full house
        // TODO: Test 4 kind;
        if(foundPair){
            rank[0] = 2;
        }
        if(foundTwoPair){
            rank[0] = 3;
        }
        if(foundThreeKind){
            rank[0] = 4;
        }
        if(foundThreeKind && foundPair){
            rank[0] = 7;
        }
        if(foundFourKind){
            rank[0] = 8;
        }
        return rank;
    }

    @Override
    public void clearCards() {
        for(Map.Entry<Integer, ArrayList<Integer>> set : cardsValueMapSuitList.entrySet()) {
            if(set.getValue().size() > 0){
                set.getValue().clear();
            }
        }
        super.clearCards();
    }

    @Override
    public void addCard(Card card) {
        // uses basic ordered insertion algorithm since the list gets AT MOST length of 4, so I feel it is a negligable time save
        ArrayList<Integer> currList = cardsValueMapSuitList.get(card.getValue());
        int compCardSuit = card.getSuit();
        int insertIndex = 0;
        for(int i = 0; i < currList.size() && compCardSuit > currList.get(i); ++i){
            ++insertIndex;
        }
        currList.add(insertIndex, compCardSuit);
        if(card.getValue() == 14){
            cardsValueMapSuitList.get(1).add(insertIndex, compCardSuit);;
        }
        super.addCard(card);
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

        String s3 = "\nCalculation Cards: ";
        for(Map.Entry<Integer, ArrayList<Integer>> set : cardsValueMapSuitList.entrySet()) {
            
            ArrayList<Integer> currList = set.getValue(); 
            if(currList.size() > 0){
                for(int i = 0; i < currList.size(); ++i){
                    s3 += String.format("[S:%d, V:%d] ",
                     currList.get(i), set.getKey());
                }   
            }
        }
        s1 += s3;
        
        return s1;
    }
}
