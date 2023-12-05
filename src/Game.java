public class Game {


    public static void main(String[] args) {
        System.out.println("Hello!");

        String[] ranks = {"A", "2", "3"};
        String[] suits = {"Hearts", "Clubs"};
        int[] values = {1,2,3};

        Deck r1 = new Deck(ranks, suits, values);

        int left = r1.getCardsLeft();

        System.out.println(left);

        Card m = new Card("A", "Clubs", 1);
        int c = m.getPoint();

        System.out.println(c);


    }
}
