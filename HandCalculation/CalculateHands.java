package HandCalculation;
import java.util.LinkedList;

import DeckAndCard.Card;
import DeckAndCard.CardSuitArray;

import CardHolders.Player;

public final class CalculateHands {
    public static void rankCardHand(Player p){
        LinkedList<CardSuitArray> calculationCards = p.getCalcCards();
        Rank hold;
        Rank rank;

        
        rank = checkForStraightFlush(calculationCards); // Evaluates rank 9, 5
        
        hold = checkForNthOfAKind(calculationCards); // Evaluates rank 8, 7, 3, 2, 1 
        if(rank == null){
            rank = hold;
        }else if(hold.compareTo(rank) > 0){
            rank = hold;
        }

        hold = checkFlush(calculationCards);  // Evaluates rank 6
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
    private static Rank checkForStraightFlush(LinkedList<CardSuitArray> calculationCards){ 
        LinkedList<Card>[] sequentialAndFlushList = new LinkedList[]{
            new LinkedList<Card>(), // non-flush consecutive
            new LinkedList<Card>(), // 1 diamonds
            new LinkedList<Card>(), // 2 hearts  
            new LinkedList<Card>(), // 3 clubs 
            new LinkedList<Card>()};// 4 spades
        LinkedList<Card> seqOrFlushList = null;
        int handRank = -1;
        CardSuitArray prevArr = calculationCards.get(0);
        int seqIndex = -1;

        for(int i = 1; i < calculationCards.size(); ++i){
            CardSuitArray currArr = calculationCards.get(i);
            
            sequentialAndFlushList[0].add(prevArr.firstCardAdded);
            for(int j = 0; j < prevArr.cardSuits.length; ++j){
                if(prevArr.cardSuits[j] == null){
                    continue;
                }
                sequentialAndFlushList[prevArr.cardSuits[j].getSuit()].add(prevArr.cardSuits[j]);
            }
            

            if(prevArr.getCardValue() == currArr.getCardValue()-1){

                sequentialAndFlushList[0].add(currArr.firstCardAdded);
                if(sequentialAndFlushList[0].size() >= 5){
                    seqIndex = 0;
                    if(sequentialAndFlushList[0].size() > 5){
                        sequentialAndFlushList[0].removeFirst();
                    }
                }

                for(int j = 0; j < currArr.cardSuits.length; ++j){
                    if(currArr.cardSuits[j] == null){
                        continue;
                    }
                    int suitIndex = currArr.cardSuits[j].getSuit();
                    sequentialAndFlushList[suitIndex].add(currArr.cardSuits[j]);
                    if(sequentialAndFlushList[suitIndex].size() >= 5){
                        seqIndex = suitIndex;
                        if(sequentialAndFlushList[suitIndex].size() > 5){
                            sequentialAndFlushList[suitIndex].removeFirst();
                        }
                    }
                }
                
            }else{
                if(seqIndex >= 0){
                    handRank = (seqIndex == 0) ? 5 : 9; 
                    
                    seqOrFlushList = sequentialAndFlushList[seqIndex];
                    sequentialAndFlushList[seqIndex] = new LinkedList<>();
                }
                seqIndex = -1;
                
                for(int j = 0; j < sequentialAndFlushList.length; ++j){
                    sequentialAndFlushList[j].clear();
                }
            }
            prevArr = currArr;
        }
        
        Card[] cards = new Card[5];

        if(seqIndex >= 0){
            int tempRank = (seqIndex == 0) ? 5 : 9; 
            if(tempRank >= handRank){
                handRank = tempRank;
                seqOrFlushList = sequentialAndFlushList[seqIndex];
            }
        }

        if(seqOrFlushList != null){
            seqOrFlushList.toArray(cards);
            return new Rank(handRank, cards);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private static Rank checkForNthOfAKind(LinkedList<CardSuitArray> calculationCards){
        LinkedList<Card>[] cardLinkedLists = new LinkedList[] {
            new LinkedList<Card>(), // four of a kind
            new LinkedList<Card>(), // three of a kind
            new LinkedList<Card>(), // two of a kind
            new LinkedList<Card>()};// singles

        for(int i = (calculationCards.get(0).getCardValue() == 1) ? 1 : 0; i < calculationCards.size(); ++i) {
            CardSuitArray suitList = calculationCards.get(i);
            int insertIndex = 4 - suitList.size();
            for(int j = 0; j < suitList.cardSuits.length; ++j){
                if(suitList.cardSuits[j] == null){
                    continue;
                }
                cardLinkedLists[insertIndex].add(suitList.cardSuits[j]);
            }
        }

        int handRank = 1;
        Card[] cards = new Card[5];
        
        if(cardLinkedLists[0].size() > 0){
            cards[0] = cardLinkedLists[0].removeLast();    
            cards[1] = cardLinkedLists[0].removeLast();
            cards[2] = cardLinkedLists[0].removeLast();
            cards[3] = cardLinkedLists[0].removeLast();
            cards[4] = cardLinkedLists[3].removeLast();
            handRank = 8;
            return new Rank(handRank, cards);
        }
        
        if(cardLinkedLists[1].size() > 0){
            LinkedList<Card> threeOfKind = cardLinkedLists[1];

            cards[0] = threeOfKind.removeLast();
            cards[1] = threeOfKind.removeLast();
            cards[2] = threeOfKind.removeLast();

            LinkedList<Card> lessOfKind = cardLinkedLists[2]; 
            if(!threeOfKind.isEmpty() || !lessOfKind.isEmpty()){
                handRank = 7;
                if(!threeOfKind.isEmpty()){
                    lessOfKind = (threeOfKind.getLast().compareTo(lessOfKind.getLast()) > 0) ? threeOfKind: lessOfKind;
                }
            }else{
                lessOfKind = cardLinkedLists[3];  
                handRank = 4;  
            }
            cards[3] = lessOfKind.removeLast();
            cards[4] = lessOfKind.removeLast();
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
    private static Rank checkFlush(LinkedList<CardSuitArray> calculationCards){
        int handRank = -1;
        int hasFlushIndex = -1;
        LinkedList<Card>[] suitCounts = new LinkedList[] {
            new LinkedList<Card>(), // 1 diamonds
            new LinkedList<Card>(), // 2 hearts  
            new LinkedList<Card>(), // 3 clubs 
            new LinkedList<Card>()};// 4 spades

        for(int i = 0; i < calculationCards.size(); ++i) {
            CardSuitArray suitArr = calculationCards.get(i);
            
            for(int j = 0; j < suitArr.cardSuits.length; ++j){
                if(suitArr.cardSuits[j] == null){
                    continue;
                }
                suitCounts[j].add(suitArr.cardSuits[j]);
                if(suitCounts[j].size() >= 5){
                    hasFlushIndex = j;
                    if(suitCounts[j].size() > 5){
                        suitCounts[j].removeFirst();
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
