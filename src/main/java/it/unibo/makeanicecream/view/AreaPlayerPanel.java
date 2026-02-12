package it.unibo.makeanicecream.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.CardLayout;


import javax.swing.BorderFactory;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import it.unibo.makeanicecream.api.Conetype;
import it.unibo.makeanicecream.api.GameController;
import it.unibo.makeanicecream.api.EventType;

public class AreaPlayerPanel extends JPanel {
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
        
        add(conePanel, "cone");
        add(builderPanel, "builder");

        showConePanel();
    }

    public void setController(final GameController controller) {
        this.controller = controller;
    }

    public void showConePanel() {
        cardLayout.show(this, "cone");
    }

    public void showBuilderPanel() {
        cardLayout.show(this, "builder");
    }

    public void updateIceCreamView(final String status) {
        builderStatus.setText(status);
    }

    private void buildConePanel() {
        conePanel.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        conePanel.add(new JLabel("Choose your cone"), BorderLayout.NORTH);
        
        for(final Conetype cone : Conetype.values()) {
            final JButton button = new JButton(cone.name());
            button.addActionListener(e -> {
                sendEvent(EventType.CHOOSE_CONE, cone.name());
                showBuilderPanel();
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
