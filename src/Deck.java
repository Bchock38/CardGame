import java.util.ArrayList;

public class Deck {

    private ArrayList<Card> deck;
    public Deck(String[] ranks, String[] suit, int[] point){
        deck = new ArrayList<Card>();
        for (int i = 0; i < suit.length; i++){
            for (int j = 0; j < ranks.length; j++){
                  deck.add(new Card(ranks[j],suit[i],point[j]));
            }

        }
    }
}
