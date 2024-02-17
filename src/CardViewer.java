import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CardViewer extends JFrame {

    private Game a;

    private final int WINDOW_WIDTH = 1500;
    private final int WINDOW_HEIGHT = 1000;
    private final int TITLE_BAR_HEIGHT = 23;

    private Image spoon;

    public CardViewer(Game a){
        this.a = a;
        spoon = new ImageIcon( "Resources/Spoon.jpg").getImage();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Spoons");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);

    }


    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0,0,WINDOW_HEIGHT,WINDOW_WIDTH);
        g.setColor(Color.black);
        Player[] players = a.getPlayers();
        for (int i = 0; i< a.getSpoonsLeft(); i++) {
            g.drawImage(spoon, (i + 4) * 100, 200,100,140, this);
        }
        if (players != null) {
            int currentPlayer = a.getCurrentPlayer();
            ArrayList<Card> hand = players[currentPlayer].getHand();
            for (int i = 0; i < hand.size(); i++) {
                hand.get(i).draw(g, (i + 4) * 100);
            }
        }

    }

}
