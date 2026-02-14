package it.unibo.makeanicecream.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.makeanicecream.api.EventType;
import it.unibo.makeanicecream.api.GameController;

/**
 * Panel displayed when the a level is completed.
 */
public final class LevelCompletedPanel extends JPanel {
    private static final Color LEVEL_COMPLETED_BACKGROUND = new Color(255, 239, 153);
    private static final long serialVersionUID = 1L;
    private static final float TITLE_FONT_SIZE = 32f;
    private transient GameController controller;

    /**
     * Builds a new LevelCompletedPanel.
     */
    public LevelCompletedPanel() {
        setLayout(new GridBagLayout());
        setBackground(LEVEL_COMPLETED_BACKGROUND);

        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        final JLabel title = new JLabel("LEVEL COMPLETED!", JLabel.CENTER);
        title.setFont(title.getFont().deriveFont(TITLE_FONT_SIZE));

        final java.net.URL completedIcon = getClass().getResource("/completed.png");
        if(completedIcon != null) {
            final ImageIcon completedImageIcon = new ImageIcon(completedIcon);
            final Image completedImage = completedImageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);

            title.setIcon(new ImageIcon(completedImage));
            title.setHorizontalTextPosition(JLabel.LEFT);
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
