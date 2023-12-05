import java.util.ArrayList;

public class Deck {

    private ArrayList<Card> deck;
    private int cardsLeft;
    public Deck(String[] ranks, String[] suit, int[] point){
        deck = new ArrayList<Card>();
        for (int i = 0; i < suit.length; i++){
            for (int j = 0; j < ranks.length; j++){
                  deck.add(new Card(ranks[j],suit[i],point[j]));
            }
        }
        cardsLeft = deck.size();
    }

    public boolean isEmpty(){
        if (cardsLeft == 0){
            return true;
        }
        return false;
    }

    public int getCardsLeft() {
        return cardsLeft;
    }

    public Card deal(){
        if (deck.isEmpty()){
            return null;
        }
        cardsLeft = cardsLeft -1;
        return deck.get(cardsLeft);
    }
    public void shuffle(){
        for (int i = cardsLeft; i > 0; i--){
            Card m = deck.get(i);
            int spot = (int) (Math.random() * cardsLeft) + 1;
            deck.set(i, deck.get(spot));
            deck.set(spot, m);
        }
    }
}
