import java.util.ArrayList;
import java.util.Scanner;
public class Game {


private ArrayList<Player> players;

private int currentPlayer;

private Scanner input;

private Deck r1;

private CardViewer window;

private boolean gameStatus;

private int spoonsLeft;

public Game() {
    input = new Scanner(System.in);
    this.window = new CardViewer(this);
    //Make the deck of cards
    String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
    int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
    r1 = new Deck(ranks, suits, values, window);
    gameStatus = true;
    //get the amount of players
    System.out.println("How many Players: ");
    int numPlayers = input.nextInt();
    input.nextLine();
    //make an array to hold all the players
    players = new ArrayList<Player>();
    for (int i = 0; i < numPlayers; i++) {
        System.out.println("Player " + (i + 1) + " name:");
        String getName = input.nextLine();
        players.add(new Player(getName));
    }
    spoonsLeft = (numPlayers-1);

}
    public static void main(String[] args) {
        // print instructions
        System.out.println("Instructions: Everyone is dealt 5 cards. The dealer/first player draws one a card from the top of the deck." +
                " The dealer then decides whether they want to keep \n the drawn card or pass it down to the next player." +
                " The goal is to have 4 of the same type of card (ex. 7777), but you can only hold 4 cards at a time. \n" +
                " The last player to receive a card puts there discarded card in the discard pile, and when the deck runs " +
                "out the discarded cards will be added to the deck.");
        //make game
        Game game = new Game();
        game.dealCards();
        //while no winner decided continue game
        while (game.gameStatus) {
            game.playGame();
        }
        //Declare game has ended
        System.out.println("The End");
    }


        public void playGame(){
            window.setText("Instructions: Everyone is dealt 5 cards. The dealer/first player draws one a card from the top of the deck." +
                    " The dealer then decides whether they want to keep \n the drawn card or pass it down to the next player." +
                    " The goal is to have 4 of the same type of card (ex. 7777), but you can only hold 4 cards at a time. \n" +
                    " The last player to receive a card puts there discarded card in the discard pile, and when the deck runs " +
                    "out the discarded cards will be added to the deck. \n press 1 to remove 1st card 1, 2 to remove 2nd card, etc. 6 to take a spoon ");
            Card m = null;
            //check for winners
            if (spoonsLeft == 0 && players.size() > 1){
                for(int i = 0; i < players.size(); i++){
                    if (!players.get(i).isHasSpoon()){
                        System.out.println(players.get(i).getName() + " has been eliminated");
                        window.setText(players.get(i).getName() + " has been eliminated");
                        window.repaint();
                        players.remove(i);
                        for(int j = 0; j < players.size(); j++){
                            for (int k = 0; k < players.get(j).getHand().size(); k++){
                                players.get(j).getHand().get(k).setInPlay(false);
                                players.get(j).getHand().remove(k);
                            }
                        }
                    }
                }
            }
            //check for winner if only one player
            else if (players.size() == 1){
                if (checkMatch(0)){
                    window.setText("You've Won");
                    System.out.println("You've Won");
                    gameStatus = false;
                }
            }
            for (currentPlayer = 0; currentPlayer < players.size(); currentPlayer++){
                //check for winners
//                if (spoonsLeft == 0 && players.size() > 1){
//                    for(int i = 0; i < players.size(); i++){
//                        if (players.get(i).isHasSpoon()){
//                            System.out.println(players.get(i).getName() + " Has Won");
//                        }
//                    }
//                    gameStatus = false;
//                    break;
//                }

                //check if deck is empty
                if (r1.isEmpty()){
                    resetPile();
                }
                //deal card to first player
                if (currentPlayer == 0 && players.get(currentPlayer).getHand().size() < 5){
                    players.get(currentPlayer).addCard(r1.deal());
                }
                else{
                    if (m != null && players.get(currentPlayer).getHand().size() < 5) {
                        //pass previous discarded card to next player
                        players.get(currentPlayer).addCard(m);
                    }
                }
                int toRemove;
                //remove a card from player hand
                do {
                    System.out.print("Player " + players.get(currentPlayer).getName());
                    window.setText("Player " + players.get(currentPlayer).getName());
                    window.repaint();
                    System.out.println(" Choose Which Position of Card to Remove (ex. 1)");
                    window.setText(" Choose Which Position of Card to Remove (ex. 1)");
                    window.repaint();
                    System.out.println(players.get(currentPlayer).getHand());
                    drawHand();
                    toRemove = input.nextInt();
                    input.nextLine();
                }
                while(toRemove > 6 || toRemove < 1);
                if (toRemove < 6) {
                    //set card status to no longer in game
                    players.get(currentPlayer).getHand().get(toRemove - 1).setInPlay(false);
                    m = players.get(currentPlayer).getHand().remove(toRemove - 1);
                }
                else {
                    if (checkMatch(currentPlayer)){
                        players.get(currentPlayer).setSpoonStatus(true);
                        spoonsLeft--;
                    }
                    else{
                        System.out.println("No Match Can't Take Spoon");
                        window.setText("No Match Can't Take Spoon");
                        window.repaint();
                        System.out.println("Turn Lost");
                        if (currentPlayer != (players.size()-1)) {
                            players.get(currentPlayer+1).addCard(r1.deal());
                        }
                    }
                }
                //clear screen
                for (int j = 0; j < 10; j++){
                    System.out.println();
                }

            }

        }

        public void takeTurn(){

        }
        //reset pile
        public void resetPile(){
            System.out.println("Deck has been reset");
            r1.shuffle();
        }

        //check for winner
//        public boolean checkWin(int spot){
//
//        }

        public boolean checkMatch(int spot){
            ArrayList<Card> hand = players.get(spot).getHand();
            int check2 = 0;
            for(int i = 0; i < hand.size(); i++) {
                for (int j = 0; j < hand.size(); j++) {
                    if (hand.get(i).getPoint() == hand.get(j).getPoint()) {
                        check2++;
                    }
                }
                if (check2 == 4) {
                    System.out.println("Match");
                    return true;
                }
                else{
                    check2 = 0;
                }
            }
            return false;
        }


        //deal cards to all players
        public void dealCards(){
            for (int i = 0; i < players.size(); i++)
            {
                for (int j = 0; j < 4; j++){
                    players.get(i).addCard(r1.deal());
                }

            }
        }


        public void resetGame(){

            resetPile();
            gameStatus = true;
        }

        public void drawHand(){
            window.repaint();
        }

        public int getCurrentPlayer(){
            return currentPlayer;
        }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public int getSpoonsLeft(){
        return spoonsLeft;
    }


}
