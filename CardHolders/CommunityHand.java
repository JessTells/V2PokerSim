package CardHolders;
public class CommunityHand extends CardHolder{
    
    public CommunityHand(){
        super();
    }

    @Override
    public String toString() {
        String s1 = "Community Cards ";
        s1 += super.toString();
        return s1;
    }
}
