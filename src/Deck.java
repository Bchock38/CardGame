import javax.swing.*;
import java.util.ArrayList;
import java.awt.*;

public class Deck {

    private ArrayList<Card> deck;

    private CardViewer screen;
    private int cardsLeft;
    //make card deck/put all cards into an arraylist
    public Deck(String[] ranks, String[] suit, int[] point, CardViewer a){


        screen = a;
        deck = new ArrayList<Card>();
        int placement;
        for (int i = 0; i < suit.length; i++){
            placement = i+1;
            for (int j = 0; j < ranks.length; j++){
                String filename = "Resources/Cards/" + ((j*4)+placement) +".png";
                Image image = new ImageIcon(filename).getImage();
                deck.add(new Card(ranks[j],suit[i],point[j], image, screen));
            }
        }
        cardsLeft = deck.size();
        //shuffle deck
        shuffle();
    }

    //check if deck is empty
    public boolean isEmpty(){
        if (cardsLeft == 0){
            return true;
        }
        return false;
    }

    //return cards left in deck
    public int getCardsLeft() {
        return cardsLeft;
    }


    //deal card from deck
    public Card deal(){
        if (deck.isEmpty()){
            System.out.println("empty deck");
            return null;
        }
        cardsLeft = cardsLeft - 1;
        while (deck.get(cardsLeft).isInPlay()){
            cardsLeft = cardsLeft - 1;
        }
        //set card in game status tp true
        deck.get(cardsLeft).setInPlay(true);
        return deck.get(cardsLeft);
    }
    //shuffle deck
    public void shuffle(){
        cardsLeft = deck.size();
        for (int i = cardsLeft-1; i > 0; i--){
            Card m = deck.get(i);
            int spot = (int) (Math.random() * cardsLeft);
            deck.set(i, deck.get(spot));
            deck.set(spot, m);
        }

    }

    public ArrayList<Card> getDeck(){
        return deck;
    }
}
