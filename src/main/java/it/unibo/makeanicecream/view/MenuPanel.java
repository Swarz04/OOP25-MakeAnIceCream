package it.unibo.makeanicecream.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import javax.swing.BorderFactory;
import it.unibo.makeanicecream.api.GameController;

import it.unibo.makeanicecream.api.EventType;

/**
 * Panel for the main menu, allowing level selection.
 */
public final class MenuPanel extends JPanel {
    private static final Color MENU_BACKGROUND = new Color(250, 218, 221);
    private static final long serialVersionUID = 1L;
    private static final float TITLE_FONT_SIZE = 32f;
    private static final int BORDER_SIZE = 50;
    private static final int TITLE_SPACING = 20;
    private static final int BUTTON_SPACING = 10;
    private static final int MAX_LEVELS = 5;

    private transient GameController controller;

    /**
     * Builds a new MenuPanel.
     */
    public MenuPanel() {
        super();
        this.setLayout(new BorderLayout(0, TITLE_SPACING));
        this.setBorder(BorderFactory.createEmptyBorder(BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, BORDER_SIZE));

        this.setBackground(MENU_BACKGROUND);

        final JLabel titleLabel = new JLabel("Choose a level", SwingConstants.CENTER);
        titleLabel.setFont(titleLabel.getFont().deriveFont(TITLE_FONT_SIZE));
        this.add(titleLabel, BorderLayout.NORTH);

        final JPanel buttonsPanel = new JPanel(new GridLayout(MAX_LEVELS, 1, 0, BUTTON_SPACING));
        buttonsPanel.setOpaque(false);

        for (int i = 1; i <= MAX_LEVELS; i++) {
            final int levelNumber = i;
            final JButton levelButton = new JButton("Level " + i);
            levelButton.addActionListener(e -> {
                if (this.controller != null) {
                    this.controller.handleInput(new EventImpl(EventType.START_LEVEL, String.valueOf(levelNumber)));
                }
            });
            buttonsPanel.add(levelButton);
        }

        this.add(buttonsPanel, BorderLayout.CENTER);
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
}
