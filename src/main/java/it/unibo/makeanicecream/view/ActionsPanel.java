package it.unibo.makeanicecream.view;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import it.unibo.makeanicecream.api.GameController;
import it.unibo.makeanicecream.api.EventType;

/**
 * Panel responsible for action buttons like Submit and Reset.
 * The Submit button is enabled only when the current ice cream is valid and can be submitted, 
 * while the Reset button is always enabled to allow the user to reset their current creation.
 * When the Submit button is disabled, it is hidden to prevent the user from attempting to submit an invalid ice cream.
 */
public class ActionsPanel extends JPanel {
    private GameController controller;

    private final JButton submitButton;
    private final JButton resetButton;

    private Runnable submitAction = () -> {};
    private Runnable resetAction = () -> {};

    /**
     * Builds a new ActionsPanel. 
     * The Submit button is disabled by default and will be enabled when the current ice cream is valid and can be submitted, 
     * while the Reset button is always enabled.
     */
    public ActionsPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        ImageIcon submitIcon = new ImageIcon(getClass().getResource("/submit.png"));
        ImageIcon resetIcon = new ImageIcon(getClass().getResource("/reset.png"));
        
        submitButton = new JButton(submitIcon);
        resetButton = new JButton(resetIcon);

        submitButton.setVisible(false);

        submitButton.addActionListener(e -> {
            sendEvent(EventType.DELIVER);
            submitAction.run();
        });
        resetButton.addActionListener(e -> {
            sendEvent(EventType.CANCEL);
            resetAction.run();
        });

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
     * Configures the visibility of the Submit button based on whether the current ice cream can be submitted or not.
     * 
     * @param enabled true to enable the Submit button, false to disable it
     */
    public void setSubmitEnabled(final boolean enabled) {
        submitButton.setVisible(enabled);
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

    /**
     * Sets the action to be performed when the Submit button is pressed.
     * 
     * @param action the action to set for the Submit button
     */
    public void setSubmitAction(final Runnable action) {
        this.submitAction = (action == null) ? () -> {} : action;
    }

    /**
     * Sets the action to be performed when the Reset button is pressed.
     * 
     * @param action the action to set for the Reset button
     */
    public void setResetAction(final Runnable action) {
        this.resetAction = (action == null) ? () -> {} : action;
    }
}
