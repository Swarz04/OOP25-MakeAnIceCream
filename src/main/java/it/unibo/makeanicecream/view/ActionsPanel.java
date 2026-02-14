package it.unibo.makeanicecream.view;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import java.awt.Image;
import it.unibo.makeanicecream.api.GameController;
import it.unibo.makeanicecream.api.EventType;

/**
 * Panel responsible for action buttons like Submit and Reset.
 * The Submit button is enabled only when the current ice cream is valid and can be submitted,
 * while the Reset button is always enabled to allow the user to reset their current creation.
 * When the Submit button is disabled, it is hidden to prevent the user from attempting to submit an invalid ice cream.
 */
public final class ActionsPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final int HORIZONTAL_GAP = 20;
    private static final int VERTICAL_GAP = 10;

    private GameController controller;

    private final JButton submitButton = new JButton();
    private final JButton resetButton = new JButton();

    private Runnable submitAction = () -> { };
    private Runnable resetAction = () -> { };

    /**
     * Builds a new ActionsPanel.
     * The Submit button is disabled by default and will be enabled when the current ice cream is valid and can be submitted,
     * while the Reset button is always enabled.
     */
    public ActionsPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER, HORIZONTAL_GAP, VERTICAL_GAP));
        final java.net.URL submitIcon = getClass().getResource("/submit.png");
        final java.net.URL resetIcon = getClass().getResource("/reset.png");

        if (submitIcon == null || resetIcon == null) {
            throw new IllegalStateException("Submit or Reset button icons not found in resources.");
        } else {
            final ImageIcon submitImageIcon = new ImageIcon(submitIcon);
            final Image submitImage = submitImageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            final ImageIcon resetImageIcon = new ImageIcon(resetIcon);
            final Image resetImage = resetImageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            submitButton.setIcon(new ImageIcon(submitImage));
            resetButton.setIcon(new ImageIcon(resetImage));
            submitButton.setToolTipText("Submit");
            resetButton.setToolTipText("Reset");
        }

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
        revalidate();
        repaint();
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
        this.submitAction = (action == null) ? () -> { } : action;
    }

    /**
     * Sets the action to be performed when the Reset button is pressed.
     *
     * @param action the action to set for the Reset button
     */
    public void setResetAction(final Runnable action) {
        this.resetAction = (action == null) ? () -> { } : action;
    }
}
