package it.unibo.makeanicecream.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;


import javax.swing.BorderFactory;
import javax.swing.JButton;

import javax.swing.JPanel;

import it.unibo.makeanicecream.api.GameController;
import it.unibo.makeanicecream.api.LiquidToppingType;
import it.unibo.makeanicecream.api.SolidToppingType;
import it.unibo.makeanicecream.api.EventType;
import it.unibo.makeanicecream.api.FlavorType;

/**
 * Panel responsible for displaying available ingredients as buttons.
 * Flavors are placed in the center, while toppings are placed on the right side, divided into liquids and solids.
 * Topping button are enabled only after level >=4.
 */
public class IngredientsPanel extends JPanel {

    private GameController controller;
    private final JPanel flavorPanel = new JPanel(new GridLayout(2,3,6,6));
    private final JPanel liquidPanel = new JPanel(new GridLayout(0,1,8,8));
    private final JPanel solidPanel = new JPanel(new GridLayout(0,1,8,8));

    /**
     * Builds a new IngredientsPanel.
     * Flavors are always enabled, while toppings are disabled by default.
     */
    public IngredientsPanel() {
        setLayout(new BorderLayout(12,0));
        setBorder(BorderFactory.createTitledBorder("Ingredients"));

        JPanel flavorsBox = new JPanel(new BorderLayout());
        flavorsBox.setBorder(BorderFactory.createTitledBorder("Flavors"));
        flavorsBox.add(flavorPanel, BorderLayout.CENTER);

        JPanel toppingBox = new JPanel(new GridLayout(1,2,12,0));
        toppingBox.setBorder(BorderFactory.createTitledBorder("Toppings"));

        JPanel liquidsBox = new JPanel(new BorderLayout());
        liquidsBox.setBorder(BorderFactory.createTitledBorder("Liquids"));
        liquidsBox.add(liquidPanel, BorderLayout.CENTER);

        JPanel solidsBox = new JPanel(new BorderLayout());
        solidsBox.setBorder(BorderFactory.createTitledBorder("Solids"));
        solidsBox.add(solidPanel, BorderLayout.CENTER);

        toppingBox.add(liquidsBox);
        toppingBox.add(solidsBox);

        add(flavorsBox, BorderLayout.CENTER);
        add(toppingBox, BorderLayout.EAST);

        buildFlavorButtons();
        buildToppingButtons();

        setToppingButtonsEnabled(false);
    }

    /**
     * Sets the controller to which this panel will send events when buttons are pressed.
     *
     * @param controller the controller to set
     */
    public void setController(final GameController controller) {
        this.controller = controller;
    }

    /**
     * Enables or disables all topping buttons.
     *
     * @param enabled true to enable the buttons, false to disable them
     */
    public void setToppingButtonsEnabled(final boolean enabled) {
        setPanelEnabled(liquidPanel, enabled);
        setPanelEnabled(solidPanel, enabled);
    }

    /**
     * Builds the flavor buttons and adds action listeners to send events when they are pressed.
     */
    private void buildFlavorButtons() {
        for (final FlavorType f : FlavorType.values()) {
            final JButton button = new JButton(f.name());
            button.addActionListener(e -> sendEvent(EventType.ADD_INGREDIENT, f.name()));
            flavorPanel.add(button);
        }
    }

    /**
     * Builds the topping buttons and adds action listeners to send events when they are pressed.
     */
    private void buildToppingButtons() {
        for (final LiquidToppingType liquid : LiquidToppingType.values()) {
            final JButton button = new JButton(liquid.name());
            button.addActionListener(e -> sendEvent(EventType.ADD_INGREDIENT, liquid.name()));
            liquidPanel.add(button);
        }

        for (final SolidToppingType solid : SolidToppingType.values()) {
            final JButton button = new JButton(solid.name());
            button.addActionListener(e -> sendEvent(EventType.ADD_INGREDIENT, solid.name()));
            solidPanel.add(button);
        }
    }

    /**
     * Sends an event to the controller with the specified type and data.
     *
     * @param type the type of the event to send
     * @param data the data associated with the event
     */
    private void sendEvent(final EventType type, final String data) {
        if(controller == null) {
           return;
        }

        controller.handleInput(new EventImpl(type, data));
    }

    /**
     * Enables or disables all buttons in the specified panel.
     *
     * @param panel the panel whose buttons to enable or disable
     * @param enabled true to enable the buttons, false to disable them
     */
    private void setPanelEnabled(final JPanel panel, final boolean enabled) {
        for (final Component c : panel.getComponents()) {
            c.setEnabled(enabled);
        }
    }
}
