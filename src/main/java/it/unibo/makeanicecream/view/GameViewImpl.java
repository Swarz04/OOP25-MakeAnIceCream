package it.unibo.makeanicecream.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import it.unibo.makeanicecream.api.GameController;
import it.unibo.makeanicecream.api.GameView;

/**
 * A {@link GameView} implementation .
 */
public class GameViewImpl implements GameView {

    private static final String FRAME_NAME = "Make an Ice Cream";
    private static final String LEVEL_PHRASE = "Choose a level";

    private GameController controller;

    private final JFrame frame = new JFrame(FRAME_NAME);

    public GameViewImpl(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JLabel titleLabel = new JLabel(LEVEL_PHRASE, SwingConstants.CENTER);
        final JPanel mainPanel = new JPanel(new BorderLayout());
        final JPanel levelPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        for (int i = 1; i <= 5; i++) {
            JButton levelButton = new JButton("Level " + i);
            levelPanel.add(levelButton);
        }
        levelPanel.setBorder(BorderFactory.createEmptyBorder(50, 200, 50, 200));
        mainPanel.add(levelPanel, BorderLayout.CENTER);
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        frame.getContentPane().add(mainPanel);

        frame.pack();
        frame.setLocationByPlatform(true);
    }

    @Override
    public void setController(GameController controller) {
        this.controller = controller;
    }

    @Override
    public void showCustomer() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'showCustomer'");
    }

    @Override
    public void showOrder() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'showOrder'");
    }

    @Override
    public void showTimer() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'showTimer'");
    }

    @Override
    public void showLives() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'showLives'");
    }

    @Override
    public void showIngredients() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'showIngredients'");
    }

    @Override
    public void showIceCream() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'showIceCream'");
    }

    @Override
    public void start() {
        this.frame.setVisible(true);
    }

    @Override
    public void stop() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'stop'");
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

}