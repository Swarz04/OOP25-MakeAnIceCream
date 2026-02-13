package it.unibo.makeanicecream.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.CardLayout;
import java.awt.Image;


import javax.swing.BorderFactory;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.ImageIcon;

import it.unibo.makeanicecream.api.Conetype;
import it.unibo.makeanicecream.api.GameController;
import it.unibo.makeanicecream.api.EventType;

public class AreaPlayerPanel extends JPanel {
    private static final String CONE_PANEL = "cone";
    private static final String BUILDER_PANEL = "builder";
    private GameController controller;
    private final CardLayout cardLayout = new CardLayout();

    private final JPanel conePanel = new JPanel(new BorderLayout(0,10));
    private final JPanel coneButtons = new JPanel(new GridLayout(1,3,12,0));
    private final JPanel builderPanel = new JPanel(new BorderLayout());
    private final JLabel builderStatus = new JLabel("Builder Status", JLabel.CENTER);

    public AreaPlayerPanel() {
        setLayout(cardLayout);
        setBorder(BorderFactory.createTitledBorder("Player Area"));

        buildConePanel();
        buildBuilderPanel();
        
        add(conePanel, CONE_PANEL);
        add(builderPanel, BUILDER_PANEL);

        showConePanel();
    }

    public void setController(final GameController controller) {
        this.controller = controller;
    }

    public void showConePanel() {
        cardLayout.show(this, CONE_PANEL);
        revalidate();
        repaint();
    }

    public void showBuilderPanel() {
        cardLayout.show(this, BUILDER_PANEL);
        revalidate();
        repaint();
    }

    public void updateIceCreamView(final String status) {
        builderStatus.setText(status);
    }

    private void buildConePanel() {
        conePanel.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        conePanel.add(new JLabel("Choose your cone"), BorderLayout.NORTH);

        for(final Conetype cone : Conetype.values()) {
            final JButton button = new JButton();
            final java.net.URL resource = getClass().getResource("/" + cone.name().toLowerCase() + ".png");
            if (resource != null) {
                final ImageIcon originalIcon = new ImageIcon(resource);
                final Image scaledImage = originalIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
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

    private void buildBuilderPanel() {
        builderPanel.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        builderPanel.add(new JLabel("Builder Panel"), BorderLayout.NORTH);
        builderPanel.add(builderStatus, BorderLayout.CENTER);
    }

    private void sendEvent(final EventType type, final String data) {
        if(controller == null) {
            return;
        }
        controller.handleInput(new EventImpl(type, data));
    }
}
