package it.unibo.makeanicecream.view;

import javax.swing.JPanel;
import it.unibo.makeanicecream.api.GameController;

/**
 * Panel responsible for action buttons like Deliver and Cancel.
 */
public class ActionsPanel extends JPanel {
    private GameController controller;

    /**
     * Builds a new ActionsPanel.
     */
    public ActionsPanel() {
        super();
    }

    /**
     * Sets the controller for this panel.
     *
     * @param controller the game controller
     */
    public void setController(final GameController controller) {
        this.controller = controller;
    }
}
