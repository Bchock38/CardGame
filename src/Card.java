import java.awt.*;

public class Card {
    private String rank;
    private String suit;
    private int point;
    private Image cardImage;

    private CardViewer screen;

    private boolean inPlay;

    public Card(String rank, String suit, int point, Image card, CardViewer a) {
        this.screen = a;
        this.rank = rank;
        this.suit = suit;
        this.point = point;
        cardImage = card;
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


    public void draw(Graphics g, int position){
        g.drawImage(cardImage, position, 600, 160,200,screen);
    }

    //pint out rank and suit of card
    public String toString() {
        return rank + " of " + suit;
    }
}
