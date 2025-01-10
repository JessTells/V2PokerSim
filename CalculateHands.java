import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class CalculateHands {
    public static int[] rankCardHand(HashMap<Integer, ArrayList<Integer>> cardsValueMapSuitList){
        int[] handRank = {0,0};

        //Works Alone X 9 or 10: Straight Flush
        handRank = checkForStraightFlush(cardsValueMapSuitList);
        if(handRank[0] == 9){
            return handRank;
        }
        
        //X Works Alone 8: Four of a kind
        handRank = checkForNthOfAKind(cardsValueMapSuitList);

        //X Works Alone 7: Full House
        if(handRank[0] > 6){
            return handRank;
        }

        //X Works Alone 6: Flush
        handRank = checkFlush(cardsValueMapSuitList);
        
        return handRank;

        //X Works Alone 5: Straight

        //X Works Alone 4: 3 of a kind

        //X Works Alone 3: two pair

        //X Works Alone 2: pair
        
        //X Works Alone 1: high card
    }

    

    private static int[] checkForStraightFlush(HashMap<Integer, ArrayList<Integer>> cardsValueMapSuitList){ 
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

    private static int[] checkForNthOfAKind(HashMap<Integer, ArrayList<Integer>> cardsValueMapSuitList){
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

    private static int[] checkFlush(HashMap<Integer, ArrayList<Integer>> cardsValueMapSuitList){
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
}
