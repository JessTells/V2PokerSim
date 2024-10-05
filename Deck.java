
// the Deck will interact with the players by giving cards
public class Deck {
    private Card[] deckInit;
    private boolean[] usedCardIndex;

    Deck(){
        deckInit = new Card[52];
        usedCardIndex = new boolean[52];
        populateDeck();
    }

    public void populateDeck(){
        int value = 1;
        int currSuit = 1;
        for(int i = 0; i < 52; ++i){
            if(value == 14){
                value = 1;
                ++currSuit;
            }
            deckInit[i] = new Card(currSuit, value);
            ++value;

            usedCardIndex[i] = false;
        }
        
    }

    public Card giveCard(){
        double cardIndexDouble = Math.random()*100;
        int cardIndex = (int)Math.floor(cardIndexDouble);
        if(cardIndex > 52){
            cardIndex -= 48;
        }
        if(usedCardIndex[cardIndex] == true){
            // FIXME figure out how to fairly give a card when a card is already taken
        }
        return null; //FIXME
    }
}
