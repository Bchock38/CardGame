import java.util.ArrayList;
import java.util.Scanner;
public class Game {


private Player[] players;

private Scanner input;

private Deck r1;

private boolean gameStatus;

public Game() {
    input = new Scanner(System.in);
    String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    String[] suits = {"Hearts", "Clubs", "Diamonds", "Spades"};
    int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
    r1 = new Deck(ranks, suits, values);
    gameStatus = true;
    System.out.println("How many Players: ");
    int numPlayers = input.nextInt();
    input.nextLine();

    players = new Player[numPlayers];
    for (int i = 0; i < numPlayers; i++) {
        System.out.println("Player " + (i + 1) + " name:");
        String getName = input.nextLine();
        players[i] = new Player(getName);
    }

}
    public static void main(String[] args) {

        System.out.println("Instructions: Everyone is dealt 5 cards. The dealer/first player draws one a card from the top of the deck." +
                " The dealer then decides whether they want to keep \n the drawn card or pass it down to the next player." +
                " The goal is to have 4 of the same type of card (ex. 7777), but you can only hold 4 cards at a time. \n" +
                " The last player to receive a card puts there discarded card in the discard pile, and when the deck runs " +
                "out the discarded cards will be added to the deck.");
        Game game = new Game();
        game.dealCards();
        while (game.gameStatus) {
            game.playGame();
        }
        System.out.println("The End");
    }


        public void playGame(){
            Card m = null;
            for (int i = 0; i < players.length; i++){
                if (checkWin(i)){
                    gameStatus = false;
                    System.out.println(players[i].getName() + " Wins");
                    break;
                }
                if (r1.isEmpty()){
                    resetPile();
                }
                if (i == 0){
                    players[i].addCard(r1.deal());
                }
                else{
                    players[i].addCard(m);
                }
                int toRemove;
                do {
                    System.out.print("Player " + players[i].getName());
                    System.out.println(" Choose Which Number Card to Remove (ex. 1)");
                    System.out.println(players[i].getHand());
                    toRemove = input.nextInt();
                    input.nextLine();
                }
                while(toRemove > 5);
                players[i].getHand().get(toRemove-1).setInPlay(false);
                m = players[i].getHand().remove(toRemove-1);
                for (int j = 0; j < 10; j++){
                    System.out.println("");
                }

            }

        }

        public void resetPile(){
            System.out.println("Deck has been reset");
            r1.shuffle();
        }

        public boolean checkWin(int spot){
            ArrayList<Card> hand = players[spot].getHand();
            int tally = 0;
            int check = hand.get(0).getPoint();
            for(int i = 1; i < hand.size(); i++){
                if (hand.get(i).getPoint() != check){
                    return false;
                }
            }
            return true;
        }

        public void dealCards(){
            for (int i = 0; i < players.length; i++)
            {
                for (int j = 0; j < 4; j++){
                    players[i].addCard(r1.deal());
                }

            }
        }
}
