package it.unibo.makeanicecream.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.makeanicecream.api.EventType;
import it.unibo.makeanicecream.api.GameController;

/**
 * Panel displayed when the game is over.
 */
public final class GameOverPanel extends JPanel {
    private static final Color GAME_OVER_BACKGROUND = new Color(173, 216, 230);
    private static final long serialVersionUID = 1L;
    private static final float TITLE_FONT_SIZE = 32f;
    private transient GameController controller;

    /**
     * Builds a new GameOverPanel.
     */
    public GameOverPanel() {
        setLayout(new GridBagLayout());
        setBackground(GAME_OVER_BACKGROUND);

        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        final JLabel title = new JLabel("GAME OVER", JLabel.CENTER);
        title.setFont(title.getFont().deriveFont(TITLE_FONT_SIZE));

        final java.net.URL smashedIcon = getClass().getResource("/smashed_icecream.png");
        if(smashedIcon != null) {
            final ImageIcon smashedImageIcon = new ImageIcon(smashedIcon);
            final Image smashedImage = smashedImageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);

            title.setIcon(new ImageIcon(smashedImage));
            title.setHorizontalTextPosition(JLabel.RIGHT);
            title.setIconTextGap(12);
        }else{
            System.err.println("Not found image");
        }

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
     * This reference is intentionally stored to allow the panel to send events to the controller.
     *
     * @param controller the game controller
     */
    @SuppressFBWarnings(value = "EI2", justification = "Controller intentionally referenced.")
    public void setController(final GameController controller) {
        this.controller = controller;
    }
}
