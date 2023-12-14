import java.util.ArrayList;

public class Player {
    private ArrayList<Card> hand;
    private int points;

    private String name;

    public Player(String name){
        points = 0;
        this.name = name;
        hand = new ArrayList<Card>();
    }
    public Player(String name, ArrayList<Card> hand){
        this.name = name;
        this.hand = hand;
    }

    //return player's total points
    public int getPoints(){
        return points;
    }

    //return name of player
    public String getName(){
        return name;
    }

    //return player's hand of cards
    public ArrayList<Card> getHand() {
        return hand;
    }

    //add points to player
    public void addPoints(int point){
        points += point;
    }

    //add card to player's hand
    public void addCard(Card newCard){
        hand.add(newCard);
    }

    @Override
    //print out players name and points
    public String toString() {
        return name + " has " + points + " points\n" + name + "'s cards: " + hand;
    }
}
