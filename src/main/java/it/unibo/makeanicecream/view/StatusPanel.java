package it.unibo.makeanicecream.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import it.unibo.makeanicecream.api.EventType;
import it.unibo.makeanicecream.api.GameController;

/**
 * Panel responsible for displaying game status like lives and time.
 */
public class StatusPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private final JLabel livesLabel;
    private final JLabel timerLabel;
    private final JButton pauseButton;
    private GameController controller;

    /**
     * Builds a new StatusPanel.
     */
    public StatusPanel() {
        super(new BorderLayout());

        this.livesLabel = new JLabel();
        this.timerLabel = new JLabel();
        this.pauseButton = new JButton();

        final java.net.URL pauseIcon = getClass().getResource("/pause.png");
        if (pauseIcon != null) {
            final ImageIcon pauseImageIcon = new ImageIcon(pauseIcon);
            final Image pauseImage = pauseImageIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            pauseButton.setIcon(new ImageIcon(pauseImage));
        } else {
            pauseButton.setText("Pause");
        }
        pauseButton.addActionListener(e -> {
            if (controller != null) {
                controller.handleInput(new EventImpl(EventType.PAUSE, null));
            }
        });

        final JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.add(timerLabel);
        rightPanel.add(pauseButton);

        add(livesLabel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);
    }

    /**
     * Updates the lives display.
     *
     * @param lives the current number of lives
     */
    public void updateLives(final int lives) {
        livesLabel.setText("Lives: " + lives);
    }

    /**
     * Updates the timer display.
     *
     * @param timeLeft the remaining time
     */
    public void updateTimer(final double timeLeft) {
        timerLabel.setText(String.format("%.0fs", timeLeft));
    }

    /**
     * Sets the controller for this panel.
     *
     * @param controller the game controller
     */
    public void setController(final GameController controller) {
        this.controller = controller;
    }
}
