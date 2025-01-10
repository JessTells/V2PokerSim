package DeckAndCard;

public class Deck {
    private Card[] deck;
    private boolean[] usedCardIndex; // tells the giveCard method which card is being used. true means card is used. false means card is open

    public Deck(){
        deck = new Card[52];
        usedCardIndex = new boolean[52];
        populateDeck();
        resetUsedCardIndex();
    }
    /*
     * Diamond: 1
     * Heart: 2
     * Spade: 3
     * Clover 4
     * Jack: 11
     * Queen: 12
     * King: 13
     * Ace: 1 and 14
     */
    public void populateDeck(){
        int value = 2;
        int currSuit = 1;
        for(int i = 0; i < 52; ++i){
            if(value == 15){
                value = 2;
                ++currSuit;
            }
            deck[i] = new Card(currSuit, value);
            ++value;   
        }
    }

    // resets the used card index (occurs at the end of the round)
    public void resetUsedCardIndex(){
        for(int i = 0; i < 52; ++i){
            usedCardIndex[i] = false;
        }
    }

    // TODO: Test to see if this is evenly distributed
    // decides what card will be taken from the deck and given to the player or community hand
    public Card giveCard(){
        double cardIndexDouble = Math.random()*100;
        int cardIndex = (int)Math.floor(cardIndexDouble);
        if(cardIndex >= 52){
            cardIndex -= 52;
        }

        /*
            if a card is used then it will go up and down (bouncing around) the usedCardIndex array to find the nearest unused card to give to the player
         */
        if(usedCardIndex[cardIndex] == true){
            int i = cardIndex;
            int j = cardIndex;
            int totalIndeciesChecked = 0;

            while(totalIndeciesChecked < 52){
                if(i < 51){
                    ++i;
                    ++totalIndeciesChecked;
                }
                if(j > 0){
                    --j;
                    ++totalIndeciesChecked;
                }

                if(usedCardIndex[i] == false){
                    usedCardIndex[i] = true;

                    return deck[i];
                }
                if(usedCardIndex[j] == false){
                    usedCardIndex[j] = true;

                    return deck[j];
                }
            }

        }else{
            usedCardIndex[cardIndex] = true;
        }
        return deck[cardIndex];
    }

    
}
