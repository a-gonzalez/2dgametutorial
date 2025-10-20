package unus.main;

import javax.swing.JFrame;
//import java.awt.GraphicsEnvironment;

public class Main
{
    public static void main(String[] arguments) throws Exception
    {
        Game game = new Game();

        JFrame frame = new JFrame("Drifter");
        frame.setSize(game.SCREEN_WIDTH, game.SCREEN_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(game); // Game is a panel
        frame.pack();

        /*String fonts[]
        = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

        for (int i = 0; i < fonts.length; i++) {
            System.out.println(fonts[i]);
        }*/

        game.requestFocus();
        game.setup();
        game.start();

        frame.setVisible(true);
    }
}