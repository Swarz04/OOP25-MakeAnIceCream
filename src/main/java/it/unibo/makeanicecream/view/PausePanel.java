package it.unibo.makeanicecream.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.makeanicecream.api.EventType;
import it.unibo.makeanicecream.api.GameController;

/**
 * Panel representing the pause menu.
 */
public final class PausePanel extends JPanel {
    private static final Color PAUSE_BACKGROUND = new Color(250, 218, 221);
    private static final float TITLE_FONT_SIZE = 24f;
    private static final long serialVersionUID = 1L;
    private GameController controller;

    /**
     * Builds a new PausePanel.
     */
    public PausePanel() {
        setLayout(new GridBagLayout());
        setBackground(PAUSE_BACKGROUND); // Consistent with MenuPanel

        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        final JLabel title = new JLabel("GAME PAUSED", JLabel.CENTER);
        title.setFont(title.getFont().deriveFont(TITLE_FONT_SIZE));
        add(title, gbc);

        gbc.gridy++;
        final JButton resumeButton = new JButton("Resume");
        resumeButton.addActionListener(e -> {
            if (controller != null) {
                controller.handleInput(new EventImpl(EventType.RESUME, null));
            }
        });
        add(resumeButton, gbc);

        gbc.gridy++;
        final JButton menuButton = new JButton("Return to Menu");
        menuButton.addActionListener(e -> {
            if (controller != null) {
                controller.handleInput(new EventImpl(EventType.GO_TO_MENU, null));
            }
        });
        add(menuButton, gbc);
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
