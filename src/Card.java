public class Card {
    private String rank;
    private String suit;
    private int point;

    private boolean inPlay;

    //could add boolean to check if in play

    public Card(String rank, String suit, int point){
        this.rank = rank;
        this.suit = suit;
        this.point = point;
        inPlay = false;
    }

    public boolean isInPlay() {
        return inPlay;
    }

    public void setInPlay(boolean status){
        inPlay = status;
    }

    public String getRank(){
        return rank;
    }

    public String getSuit(){
        return suit;
    }

    public int getPoint(){
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public String toString() {
        return rank + " of " + suit;
    }
}
