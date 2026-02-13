package it.unibo.makeanicecream.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.CardLayout;

import javax.swing.BorderFactory;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.ImageIcon;

import it.unibo.makeanicecream.api.Conetype;
import it.unibo.makeanicecream.api.GameController;
import it.unibo.makeanicecream.api.EventType;

/**
 * Panel representing the player area in the game.
 * It contains a cone selection panel and an ice cream builder panel.
 * Players can choose a cone type and then add ingredients to it.
 */
public class AreaPlayerPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final String CONE_PANEL = "cone";
    private static final String BUILDER_PANEL = "builder";
    private GameController controller;
    private final CardLayout cardLayout = new CardLayout();

    private final JPanel conePanel = new JPanel(new BorderLayout(0, 10));
    private final JPanel coneButtons = new JPanel(new GridLayout(1, 3, 12, 0));
    private final JPanel builderPanel = new JPanel(new BorderLayout());
    private final JLabel builderStatus = new JLabel("Builder Status", JLabel.CENTER);

    /**
     * Constructs a new AreaPlayerPanel.
     * Initializes the cone selection and ice cream builder panels.
     */
    public AreaPlayerPanel() {
        setLayout(cardLayout);
        setBorder(BorderFactory.createTitledBorder("Player Area"));

        buildConePanel();
        buildBuilderPanel();

        add(conePanel, CONE_PANEL);
        add(builderPanel, BUILDER_PANEL);

        showConePanel();
    }

    /**
     * Sets the controller to which events will be sent.
     * 
     * @param controller the game controller
     */
    public void setController(final GameController controller) {
        this.controller = controller;
    }

    /**
     * Shows the cone selection panel, allowing the player to choose a cone type.
     */
    public void showConePanel() {
        cardLayout.show(this, CONE_PANEL);
        revalidate();
        repaint();
    }

    /**
     * Shows the ice cream builder panel, allowing the player to see and update the current ice cream.
     */
    public void showBuilderPanel() {
        cardLayout.show(this, BUILDER_PANEL);
        revalidate();
        repaint();
    }

    /**
     * Updates the ice cream view with a textual status.
     * 
     * @param status a textual representation of the current ice cream
     */
    public void updateIceCreamView(final String status) {
        builderStatus.setText(status);
    }

    /**
     * Builds the cone selection panel with buttons for each cone type.
     */
    private void buildConePanel() {
        conePanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        conePanel.add(new JLabel("Choose your cone"), BorderLayout.NORTH);

        for (final Conetype cone : Conetype.values()) {
            final JButton button = new JButton(cone.name());
            final java.net.URL resource = getClass().getResource("/" + cone.name().toLowerCase() + ".png");
            if (resource != null) {
                final ImageIcon icon = new ImageIcon(resource);
                final Image scaledImage = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                button.setIcon(new ImageIcon(scaledImage));
                button.setToolTipText(cone.name());
            } else {
                button.setText(cone.name());
            }

            button.addActionListener(e -> {
                sendEvent(EventType.CHOOSE_CONE, cone.name());
                showBuilderPanel();
                updateIceCreamView("Cone selected: " + cone.name() + " | Ingredients: [] | Open");
            });
            coneButtons.add(button);
        }
        conePanel.add(coneButtons, BorderLayout.CENTER);
    }

    /**
     * Builds the ice cream builder panel where the current ice cream status is displayed.
     */
    private void buildBuilderPanel() {
        builderPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        builderPanel.add(new JLabel("Builder Panel"), BorderLayout.NORTH);
        builderPanel.add(builderStatus, BorderLayout.CENTER);
    }

    /**
     * Sends an event to the game controller.
     * 
     * @param type the type of event
     * @param data associated data, for example cone name
     */
    private void sendEvent(final EventType type, final String data) {
        if (controller == null) {
            return;
        }
        controller.handleInput(new EventImpl(type, data));
    }
}
