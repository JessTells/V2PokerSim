package HandCalculation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.LinkedList;

import DeckAndCard.Card;

import CardHolders.Player;

public final class CalculateHands {
    public static void rankCardHand(Player p){
        HashMap<Integer, ArrayList<Integer>> cardsValueMapSuitList = p.getCalcCards();
        Rank hold;
        Rank rank; // TODO have class Rank be used to hold hand rank and cards that create that rank

        
        rank = checkForStraightFlush(cardsValueMapSuitList); // Evaluates rank 9, 5
        
        hold = checkForNthOfAKind(cardsValueMapSuitList); // Evaluates rank 8, 7, 3, 2, 1 
        if(rank == null){
            rank = hold;
        }else if(hold.compareTo(rank) > 0){
            rank = hold;
        }

        hold = checkFlush(cardsValueMapSuitList);  // Evaluates rank 6
        if(hold != null && hold.compareTo(rank) > 0){
            rank = hold;
        }
        p.setHandRank(rank);

        //9: Straight Flush
        //8: Four of a kind
        //7: Full House
        //6: Flush
        //5: Straight
        //4: 3 of a kind
        //3: two pair
        //2: pair
        //1: high card
    }

    

    @SuppressWarnings("unchecked")
    private static Rank checkForStraightFlush(HashMap<Integer, ArrayList<Integer>> cardsValueMapSuitList){ 
        LinkedList<Card>[] sequentialStraightList = new LinkedList[]{
            new LinkedList<Card>(), // non-flush consecutive
            new LinkedList<Card>(), // 1 diamonds
            new LinkedList<Card>(), // 2 hearts  
            new LinkedList<Card>(), // 3 clubs 
            new LinkedList<Card>()};// 4 spades
        int highSequentialIndex = -1;
        int highSequentialAndFlushIndex = -1;
        ArrayList<Integer> prevList = cardsValueMapSuitList.get(1);
        
        for(int i = 2; i <= 14; ++i){
            ArrayList<Integer> currList = cardsValueMapSuitList.get(i);
            if(prevList.isEmpty()){
                prevList = currList;
                continue;
            }
            
            if(sequentialStraightList[0].isEmpty()){
                sequentialStraightList[0].add(new Card(prevList.get(0), i-1));
                for(int j = 0; j < prevList.size(); ++j){
                    int suitIndex = prevList.get(j);
                    sequentialStraightList[suitIndex].add(new Card(suitIndex, i-1));
                }
                
            }
            

            if(!currList.isEmpty()){
                //FIXME
                sequentialStraightList[0].add(new Card(currList.get(0), i));
                if(sequentialStraightList[0].size() >= 5){
                    highSequentialIndex = 0;
                }
                for(int j = 0; j < currList.size(); ++j){
                    int suitIndex = currList.get(j);
                    sequentialStraightList[suitIndex].add(new Card(suitIndex, i));
                    if(sequentialStraightList[suitIndex].size() >= 5){
                        if(sequentialStraightList[suitIndex].size() > 5){
                            sequentialStraightList[suitIndex].removeFirst();
                        }
                        highSequentialAndFlushIndex = suitIndex;
                    }
                }
                if(sequentialStraightList[0].size() > 5){
                    sequentialStraightList[0].removeFirst();
                }
            }else{
                if(!sequentialStraightList[0].isEmpty()){
                    for(int j = 0; j < sequentialStraightList.length; ++j){
                        sequentialStraightList[j].clear();
                    }
                }
            }
            prevList = currList;
        }
        
        int handRank;
        Card[] cards = new Card[5];
        if(highSequentialAndFlushIndex > 0){
            handRank = 9;
            sequentialStraightList[highSequentialAndFlushIndex].toArray(cards);
            return new Rank(handRank, cards);
        }
        if(highSequentialIndex == 0){
            handRank = 5;
            sequentialStraightList[0].toArray(cards);
            return new Rank(handRank, cards);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private static Rank checkForNthOfAKind(HashMap<Integer, ArrayList<Integer>> cardsValueMapSuitList){//FIXME
        LinkedList<Card>[] cardLinkedLists = new LinkedList[] {
            new LinkedList<Card>(), // four of a kind
            new LinkedList<Card>(), // three of a kind
            new LinkedList<Card>(), // two of a kind
            new LinkedList<Card>()};// singles

        for(Map.Entry<Integer, ArrayList<Integer>> set : cardsValueMapSuitList.entrySet()) {
            if(set.getValue().isEmpty()){
                continue;
            }
            ArrayList<Integer> suitList = set.getValue();
            switch (set.getValue().size()) {
                case 2:
                    cardLinkedLists[2].add(new Card(suitList.get(0), set.getKey()));
                    cardLinkedLists[2].add(new Card(suitList.get(1), set.getKey()));
                    break;
            
                case 3:
                    cardLinkedLists[1].add(new Card(suitList.get(0), set.getKey()));
                    cardLinkedLists[1].add(new Card(suitList.get(1), set.getKey()));
                    cardLinkedLists[1].add(new Card(suitList.get(1), set.getKey()));    
                    break;
                
                case 4:
                    cardLinkedLists[0].add(new Card(suitList.get(0), set.getKey()));
                    cardLinkedLists[0].add(new Card(suitList.get(1), set.getKey()));
                    cardLinkedLists[0].add(new Card(suitList.get(2), set.getKey()));
                    cardLinkedLists[0].add(new Card(suitList.get(3), set.getKey()));
                break;

                default:
                    cardLinkedLists[3].add(new Card(suitList.get(0), set.getKey()));
                break;
            
            }
        }

        int handRank = 1;
        Card[] cards = new Card[5];
        if(cardLinkedLists[0].size() > 0){
            cardLinkedLists[0].toArray(cards);
            for(int i = 14; i <= 2; --i){
                ArrayList<Integer> currArrList = cardsValueMapSuitList.get(i);
                if(currArrList.isEmpty() || currArrList.size() == 4){
                    continue;
                }else{
                    cards[4] = new Card(currArrList.get(0), i);
                } 
            }
            handRank = 8;
            return new Rank(handRank, cards);
        }
        
        if(cardLinkedLists[1].size() > 0){
            LinkedList<Card> currList = cardLinkedLists[1];
            cards[0] = currList.removeLast();
            cards[1] = currList.removeLast();
            cards[2] = currList.removeLast();
            currList = cardLinkedLists[2]; 
            if(currList.size() > 0){
                handRank = 7;
            }else{
                currList = cardLinkedLists[3];  
                handRank = 4;  
            }
            cards[3] = currList.removeLast();
            cards[4] = currList.removeLast();
            return new Rank(handRank, cards);
        }
        if(cardLinkedLists[2].size() > 0){
            LinkedList<Card> currList = cardLinkedLists[2];
            cards[0] = currList.removeLast(); 
            cards[1] = currList.removeLast();
            handRank = 3;
            if(currList.isEmpty()){
                currList = cardLinkedLists[3];
                handRank = 2;
            }
            cards[2] = currList.removeLast(); 
            cards[3] = currList.removeLast();
        }

        for(int i = 0; i < cards.length; ++i){
            if(cards[i] != null){
                continue;
            }
            cards[i] = cardLinkedLists[3].removeLast();
        }

        return new Rank(handRank, cards);
    }

    @SuppressWarnings("unchecked")
    private static Rank checkFlush(HashMap<Integer, ArrayList<Integer>> cardsValueMapSuitList){
        int handRank = -1; // FIXME
        int hasFlushIndex = -1;
        LinkedList<Card>[] suitCounts = new LinkedList[] {
            new LinkedList<Card>(), // 1 diamonds
            new LinkedList<Card>(), // 2 hearts  
            new LinkedList<Card>(), // 3 clubs 
            new LinkedList<Card>()};// 4 spades

        for(int i = 2; i <= 14; ++i) {
            ArrayList<Integer> currList = cardsValueMapSuitList.get(i);
            if(!currList.isEmpty()){
                for(int j = 0; j < currList.size(); ++j){
                    int suitIndex = currList.get(j)-1; 
                    suitCounts[suitIndex].add(new Card(suitIndex+1, i));
                    if(suitCounts[suitIndex].size() >= 5){
                        hasFlushIndex = suitIndex;
                        if(suitCounts[suitIndex].size() > 5){
                            suitCounts[suitIndex].removeFirst();
                        }
                    }
                }
            }
        }
        if(hasFlushIndex >= 0){
            Card[] cards = new Card[5];
            handRank = 6;
            suitCounts[hasFlushIndex].toArray(cards);
            return new Rank(handRank, cards);
        }else{
            return null;
        }
    }
}
