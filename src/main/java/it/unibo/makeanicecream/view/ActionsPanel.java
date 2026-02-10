package it.unibo.makeanicecream.view;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import it.unibo.makeanicecream.api.GameController;
import it.unibo.makeanicecream.api.EventType;
import it.unibo.makeanicecream.api.Event;

/**
 * Panel responsible for action buttons like Submit and Reset.
 * The Submit button is enabled only when the current ice cream is valid and can be submitted, 
 * while the Reset button is always enabled to allow the user to reset their current creation.
 */
public class ActionsPanel extends JPanel {
    private GameController controller;

    private final JButton submitButton = new JButton("Submit");
    private final JButton resetButton = new JButton("Reset");

    /**
     * Builds a new ActionsPanel. 
     * The Submit button is disabled by default and will be enabled when the current ice cream is valid and can be submitted, 
     * while the Reset button is always enabled.
     */
    public ActionsPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        
        submitButton.setEnabled(false);

        submitButton.addActionListener(e -> sendEvent(EventType.DELIVER));
        resetButton.addActionListener(e -> sendEvent(EventType.CANCEL));

        add(submitButton);
        add(resetButton);
    }

    /**
     * Sets the controller for this panel.
     *
     * @param controller the game controller
     */
    public void setController(final GameController controller) {
        this.controller = controller;
    }

    /**
     * Enables or disables the Submit button based on whether the current ice cream can be submitted.
     * 
     * @param enabled true to enable the Submit button, false to disable it
     */
    public void setSubmitEnabled(final boolean enabled) {
        submitButton.setEnabled(enabled);
    }

    /**
     * Sends an event to the controller when a button is pressed.
     * 
     * @param event the type of event to send
     */
    private void sendEvent(final EventType event) {
        if (controller != null) {
            controller.handleInput(new EventImpl(event, null));
        }
    }
}
