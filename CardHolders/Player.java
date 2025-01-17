package CardHolders;

import java.util.Scanner;
import java.util.LinkedList;

import DeckAndCard.Card;
import DeckAndCard.CardSuitArray;
import HandCalculation.Rank;

public class Player extends CardHolder implements Comparable<Player>{
    private int balance;
    private String playerName;
    private LinkedList<CardSuitArray> calculationCards;
    private Rank rank;
    private Scanner input;

    protected Player(int startingBalance, String playerName){
        super();
        balance = startingBalance;
        this.playerName = playerName;
        calculationCards = new LinkedList<>();
        input = null;
    }

    public Player(int startingBalance, String playerName, Scanner input){
        super();
        balance = startingBalance;
        this.playerName = playerName;
        calculationCards = new LinkedList<>();
        this.input = input;
    }

    public int makeChoice(){
        int choice = input.nextInt();
        return choice;
    }

    public int makeBet(int prevBet){
        int amount = input.nextInt();

        if(amount != balance && amount < prevBet){
            System.out.printf("\n* Value is less than previous bet of %d*\n\n", prevBet);
            System.out.print("Enter amount: ");
            makeBet(prevBet);
        }
        amount = subFromBalance(amount);
        return amount;
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

    public int subFromBalance(int amount){
        if(amount > balance){
            amount = balance;
        }
        balance -= amount;
        return amount;
    }

    public void setHandRank(Rank rank){
        this.rank = rank;
    }

    public Rank getHandRank(){
        return rank;
    }

    public LinkedList<CardSuitArray> getCalcCards(){
        return calculationCards;
    }

    @Override
    public void clearCards() {
        calculationCards.clear();
        super.clearCards();
    }

    @Override
    public void addCard(Card card) {
        if(calculationCards.size() == 0){
            calculationCards.add(new CardSuitArray(card));
        }else{
            int insertIndex = 0;
            for(int i = 0; i < calculationCards.size(); ++i){
                CardSuitArray cardSuitList = calculationCards.get(i);
                if(cardSuitList.getCardValue() == card.getValue()){
                    cardSuitList.addCardSuit(card);
                    insertIndex = -1;
                    break;
                }else if(card.getValue() > cardSuitList.getCardValue()){
                    ++insertIndex;
                }else{
                    break;
                }
                
            }
            if(insertIndex >= 0){
                calculationCards.add(insertIndex, new CardSuitArray(card));        
            }
            
        }
        super.addCard(card);
    }


    public String toStringDisplayCards(){
        return super.toString();
    }

    @Override
    public String toString() {
        String s1 = String.format("%s: %d", playerName, balance);
        
        LinkedList<Card> displayCards = getDisplayCards();
        if(!displayCards.isEmpty()){
            String s2 = String.format(" | Cards Held: [S%d, V%d] [S%d, V%d]", 
            displayCards.get(0).getSuit(), 
            displayCards.get(0).getValue(),
            displayCards.get(1).getSuit(), 
            displayCards.get(1).getValue());
            s1 += s2;
        }

        return s1;
    }

    public String toStringCalculationCards(){
        String s1 = "\nCalculation Cards: ";
        for (CardSuitArray suitList : calculationCards) {
            Card[] cardList = suitList.cardSuits;
            for(int i = 0; i < cardList.length; ++i){
                if(cardList[i] == null){
                    continue;
                }
                s1 += cardList[i].toString();
            }
        }
        return s1;
    }

    public String toStringHandRank(){
        String s = rank.toString();
        return s;
    }

    @Override
    public int compareTo(Player p) {
        return rank.compareTo(p.rank);
    }

    
}
