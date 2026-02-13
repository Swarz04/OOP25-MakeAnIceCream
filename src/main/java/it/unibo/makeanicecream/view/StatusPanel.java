package it.unibo.makeanicecream.view;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Panel responsible for displaying game status like lives and time.
 */
public class StatusPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private final JLabel livesLabel;
    private final JLabel timerLabel;

    /**
     * Builds a new StatusPanel.
     */
    public StatusPanel() {
        super(new BorderLayout());
        
        this.livesLabel = new JLabel();
        this.timerLabel = new JLabel();

        add(livesLabel, BorderLayout.WEST);
        add(timerLabel, BorderLayout.EAST);
    }

    /**
     * Updates the status display.
     *
     * @param lives the current number of lives
     * @param timeLeft the remaining time
     */
    public void update(final int lives, final double timeLeft) {
        livesLabel.setText("Lives: " + lives);
        timerLabel.setText(String.format("%.0fs", timeLeft));
    }
}
