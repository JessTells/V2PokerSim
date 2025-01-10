import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Player extends CardHolder{
    private int balance;
    private String playerName;
    private HashMap<Integer, ArrayList<Integer>> cardsValueMapSuitList; // for calculating hand ranks
    private int[] handRank;

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
        //Works Alone X 9 or 10: Straight Flush
        handRank = checkForStraightFlush();
        if(handRank[0] == 9){
            return;
        }
        
        //X Works Alone 8: Four of a kind
        handRank = checkForNthOfAKind();

        //X Works Alone 7: Full House
        if(handRank[0] > 6){
            return;
        }

        //X Works Alone 6: Flush
        handRank = checkFlush();
        if(handRank[0] == 6){
            return;
        }

        //X Works Alone 5: Straight
        if(handRank[0] == 5){
            return;
        }

        //X Works Alone 4: 3 of a kind

        //X Works Alone 3: two pair

        //X Works Alone 2: pair
        
        //X Works Alone 1: high card
    }

    public int[] getCardRank(){
        return handRank;
    }

    private int[] checkForStraightFlush(){ 
        int[] sequentialStraightList = new int[] {1,1,1,1,1};
        int highSequential = -1;
        int highSequentialAndFlush = -1;
        ArrayList<Integer> prevList = cardsValueMapSuitList.get(1);
        for(int i = 2; i <= 14; ++i){
            ArrayList<Integer> currList = cardsValueMapSuitList.get(i);
            if(prevList.size() == 0){
                prevList = currList;
                continue;
            }
            
            if(currList.size() > 0){
                ++sequentialStraightList[0];
                if(sequentialStraightList[0] >= 5){
                    highSequential = i;
                }
                for(int j = 0; j < currList.size(); ++j){
                    ++sequentialStraightList[currList.get(j)];
                    if(sequentialStraightList[currList.get(j)]>=5){
                        highSequentialAndFlush = i;
                    }
                }
            }else{
                if(sequentialStraightList[0] > 1){
                    for(int j = 0; j < sequentialStraightList.length; ++j){
                        sequentialStraightList[j] = 1;
                    }
                }
            }
        }

        if(highSequentialAndFlush > 0){
            return new int[] {9, highSequentialAndFlush};
        }
        if(highSequential > 0){
            return new int[] {5, highSequential};
        }
        return new int[] {0, 0};
    }

    private int[] checkForNthOfAKind(){
        int[] rank = new int[]{1,0};
        boolean foundPair = false;
        boolean foundTwoPair = false;
        boolean foundThreeKind = false;
        boolean foundFourKind = false;
        for(Map.Entry<Integer, ArrayList<Integer>> set : cardsValueMapSuitList.entrySet()) {
            if(set.getValue().size() == 0){
                continue;
            }
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
                    if(!foundPair && !foundThreeKind && !foundFourKind){
                        rank[1] = set.getKey();
                    }
                    break;
            
            }
        }

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

    private int[] checkFlush(){
        int[] rank = new int[] {0, 0};
        int[] suitCounts = new int[]{0,0,0,0};
        for(Map.Entry<Integer, ArrayList<Integer>> set : cardsValueMapSuitList.entrySet()) {
            ArrayList<Integer> currList = set.getValue();
            if(currList.size() > 0){
                for(int i = 0; i < currList.size(); ++i){
                    ++suitCounts[currList.get(i)-1];
                    if(suitCounts[currList.get(i)-1] >= 5){
                        rank[1] = set.getKey();
                    }
                }
            }
        }
        if(rank[1] > 0){
            rank[0] = 6;
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
