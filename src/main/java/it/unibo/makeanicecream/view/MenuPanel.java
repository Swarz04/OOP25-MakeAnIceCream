package it.unibo.makeanicecream.view;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;

import it.unibo.makeanicecream.api.EventType;
import it.unibo.makeanicecream.api.GameController;
import java.awt.Component;

/**
 * Panel for the main menu, allowing level selection.
 */
public final class MenuPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final int BORDER_SIZE = 50;
    private static final int TITLE_SPACING = 20;
    private static final int BUTTON_SPACING = 10;
    private static final int MAX_LEVELS = 5;

    private GameController controller;

    /**
     * Builds a new MenuPanel.
     */
    public MenuPanel() {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, BORDER_SIZE));

        final JLabel titleLabel = new JLabel("Choose a level", SwingConstants.CENTER);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(titleLabel);
        this.add(Box.createVerticalStrut(TITLE_SPACING));

        for (int i = 1; i <= MAX_LEVELS; i++) {
            final int levelNumber = i;
            final JButton levelButton = new JButton("Level " + i);
            levelButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            levelButton.addActionListener(e -> {
                if (this.controller != null) {
                    this.controller.handleInput(new EventImpl(EventType.START_LEVEL, String.valueOf(levelNumber)));
                }
            });
            this.add(levelButton);
            this.add(Box.createVerticalStrut(BUTTON_SPACING));
        }
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
