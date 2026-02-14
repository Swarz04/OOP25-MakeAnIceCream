package it.unibo.makeanicecream.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.makeanicecream.api.EventType;
import it.unibo.makeanicecream.api.GameController;

/**
 * Panel responsible for displaying game status like lives and time.
 */
public final class StatusPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final int MAX_LIVES = 3;

    private final JLabel[] heartLabels = new JLabel[MAX_LIVES];
    private ImageIcon heartFull;
    private ImageIcon heartEmpty;
    private final JLabel timerLabel;
    private final JButton pauseButton;
    private transient GameController controller;

    /**
     * Builds a new StatusPanel.
     */
    public StatusPanel() {
        super(new BorderLayout());

        heartFull = loadAndScale("/heart_full.png", 28);
        heartEmpty = loadAndScale("/heart_empty.png", 28);

        final JPanel heartPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,6,0));
        heartPanel.setOpaque(false);
        for(int i = 0; i < MAX_LIVES; i++) {
            heartLabels[i] = new JLabel(heartFull);
            heartPanel.add(heartLabels[i]);
        }

        this.timerLabel = new JLabel();
        this.pauseButton = new JButton();

        timerLabel.setFont(timerLabel.getFont().deriveFont(Font.BOLD, 32f));
        timerLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

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

        add(heartPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);
    }

    /**
     * Updates the lives display.
     *
     * @param lives the current number of lives
     */
    public void updateLives(final int lives) {
        for (int i = 0; i < MAX_LIVES; i++) {
            heartLabels[i].setIcon(i < lives ? heartFull : heartEmpty);
        }
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
     * This reference is intentionally stored to allow the panel to send events to the controller.
     *
     * @param controller the game controller
     */
    @SuppressFBWarnings(value = "EI2", justification = "Controller intentionally referenced.")
    public void setController(final GameController controller) {
        this.controller = controller;
    }

    private ImageIcon loadAndScale(final String path, final int size) {
        final java.net.URL resource = getClass().getResource(path);
        if (resource == null) {
            System.err.println("Not found :" +path);
            return null;
        }
        final ImageIcon icon = new ImageIcon(resource);
        final Image scaled = icon.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }
}
