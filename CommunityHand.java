import java.util.LinkedList;
public class CommunityHand extends CardHolder{
    
    CommunityHand(){
        super();
    }

    @Override
    public String toString() {
        LinkedList<Card> displayCards = getDisplayCards();
        String s1 = "Community Cards: ";
        if(displayCards.size() > 0){
            for(int i = 0; i < displayCards.size(); ++i){
                s1 += String.format("[S:%d, V:%d] ", 
                displayCards.get(i).getSuit(), 
                displayCards.get(i).getValue());
            }
        }
        return s1;
    }
}
