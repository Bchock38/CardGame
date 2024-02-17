import java.util.ArrayList;
import java.util.Scanner;
public class Game {


private Player[] players;

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
    players = new Player[numPlayers];
    for (int i = 0; i < numPlayers; i++) {
        System.out.println("Player " + (i + 1) + " name:");
        String getName = input.nextLine();
        players[i] = new Player(getName);
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
            Card m = null;
            for (currentPlayer = 0; currentPlayer < players.length; currentPlayer++){
                //check for winners
//                if (spoonsLeft == 0 && players.length > 1){
//                    gameStatus = false;
//                    for(int i = 0; i < players.length; i++){
//                        if (players[i].isHasSpoon() == false){
//
//                        }
//                    }
//                    break;
//                }
                //check for winners
                if (spoonsLeft == 0 && players.length > 1){
                    for(int i = 0; i < players.length; i++){
                        if (players[i].isHasSpoon()){
                            System.out.println(players[i].getName() + " Has Won");
                        }
                    }
                    gameStatus = false;
                    break;
                }
                //check for winner if only one player
                else if (players.length == 1){
                    if (checkMatch(0)){
                        System.out.println("You've Won");
                        gameStatus = false;
                        break;
                    }
                }
                //check if deck is empty
                if (r1.isEmpty()){
                    resetPile();
                }
                //deal card to first player
                if (currentPlayer == 0 && players[currentPlayer].getHand().size() < 5){
                    players[currentPlayer].addCard(r1.deal());
                }
                else{
                    if (m != null && players[currentPlayer].getHand().size() < 5) {
                        //pass previous discarded card to next player
                        players[currentPlayer].addCard(m);
                    }
                }
                int toRemove;
                //remove a card from player hand
                do {
                    System.out.print("Player " + players[currentPlayer].getName());
                    System.out.println(" Choose Which Position of Card to Remove (ex. 1)");
                    System.out.println(players[currentPlayer].getHand());
                    drawHand();
                    toRemove = input.nextInt();
                    input.nextLine();
                }
                while(toRemove > 6 || toRemove < 1);
                if (toRemove < 6) {
                    //set card status to no longer in game
                    players[currentPlayer].getHand().get(toRemove - 1).setInPlay(false);
                    m = players[currentPlayer].getHand().remove(toRemove - 1);
                }
                else {
                    if (checkMatch(currentPlayer)){
                        players[currentPlayer].setSpoonStatus(true);
                        spoonsLeft--;
                    }
                    else{
                        System.out.println("No Match Can't Take Spoon");
                        System.out.println("Turn Lost");
                        if (currentPlayer != (players.length-1)) {
                            players[currentPlayer + 1].addCard(r1.deal());
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
            ArrayList<Card> hand = players[spot].getHand();
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
            System.out.println("No Match");
            return false;
        }


        //deal cards to all players
        public void dealCards(){
            for (int i = 0; i < players.length; i++)
            {
                for (int j = 0; j < 4; j++){
                    players[i].addCard(r1.deal());
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

    public Player[] getPlayers() {
        return players;
    }

    public int getSpoonsLeft(){
        return spoonsLeft;
    }


}
