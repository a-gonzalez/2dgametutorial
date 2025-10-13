package unus.main;

import javax.swing.JFrame;

public class Main
{
    public static void main(String[] arguments) throws Exception
    {
        Game game = new Game();

        JFrame frame = new JFrame("Treasure Hunter");
        frame.setSize(game.SCREEN_WIDTH, game.SCREEN_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(game); // Game is a panel
        frame.pack();

        game.requestFocus();

        frame.setVisible(true);
    }
}