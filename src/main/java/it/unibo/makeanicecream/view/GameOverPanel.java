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

public class GameOverPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private GameController controller;

    /**
     * Builds a new GameOverPanel.
     */
    public GameOverPanel() {
        setLayout(new GridBagLayout());
        setBackground(new Color(250, 218, 221)); // Consistent with MenuPanel

        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        final JLabel title = new JLabel("GAME OVER", JLabel.CENTER);
        title.setFont(title.getFont().deriveFont(24f));
        add(title, gbc);

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
