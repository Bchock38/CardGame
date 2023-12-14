public class Card {
    private String rank;
    private String suit;
    private int point;

    private boolean inPlay;

    public Card(String rank, String suit, int point){
        this.rank = rank;
        this.suit = suit;
        this.point = point;
        inPlay = false;
    }

    //return cards in play status
    public boolean isInPlay() {
        return inPlay;
    }

    //set cards in play status
    public void setInPlay(boolean status){
        inPlay = status;
    }

    //return rank of card
    public String getRank(){
        return rank;
    }

    //return suit of card
    public String getSuit(){
        return suit;
    }

    //return point value of card
    public int getPoint(){
        return point;
    }

    //set point value of card
    public void setPoint(int point) {
        this.point = point;
    }

    //set rank of card
    public void setRank(String rank) {
        this.rank = rank;
    }

    //set suit of card
    public void setSuit(String suit) {
        this.suit = suit;
    }

    //pint out rank and suit of card
    public String toString() {
        return rank + " of " + suit;
    }
}
