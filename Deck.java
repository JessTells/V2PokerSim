
// the Deck will interact with the players by giving cards
public class Deck {
    private Card[] deck;
    private boolean[] usedCardIndex; // tells the giveCard method which card is being used. true means card is used. false means card is open

    Deck(){
        deck = new Card[52];
        usedCardIndex = new boolean[52];
        populateDeck();
        resetUsedCardIndex();
    }

    private void populateDeck(){
        int value = 1;
        int currSuit = 1;
        for(int i = 0; i < 52; ++i){
            if(value == 14){
                value = 1;
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


    // decides what card will be taken from the deck and given to the player or community hand
    // FIXME: this method needs testing
    public Card giveCard(){
        double cardIndexDouble = Math.random()*100;
        int cardIndex = (int)Math.floor(cardIndexDouble);
        if(cardIndex > 52){
            cardIndex -= 48;
        }

        /*
            if a card is used then it will go up and down (bouncing around) the usedCardIndex array to find the nearest unused card to give to the player
         */
        if(usedCardIndex[cardIndex] == true){
            int i = cardIndex+1;
            int j = cardIndex-1;
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
