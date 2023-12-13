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

    public int getPoints(){
        return points;
    }

    public String getName(){
        return name;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void addPoints(int point){
        points += point;
    }

    public void addCard(Card newCard){
        hand.add(newCard);
    }

    @Override
    public String toString() {
        return name + " has " + points + " points\n" + name + "'s cards: " + hand;
    }
}
