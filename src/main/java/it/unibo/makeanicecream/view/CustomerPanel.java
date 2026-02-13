package it.unibo.makeanicecream.view;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.*;
/**
 * Panel responsible for displaying the current customer and their order.
 */
public class CustomerPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private final JLabel customerImage = new JLabel();
    private final JLabel customerLabel = new JLabel();
    private final JTextArea orderArea = new JTextArea();
    /**
     * Builds a new CustomerPanel.
     */
    public CustomerPanel() {
        setLayout(new BorderLayout(20, 0));
        
        Jpanel leftPanel = new Panel()
        orderArea.setEditable(false);
        orderArea.setOpaque(true);
        orderArea.setWrapStyleWord(true);

        add(customerLabel, BorderLayout.WEST);
        add(orderArea, BorderLayout.CENTER);
    }

    /**
     * Updates the customer display.
     *
     * @param name the customer's name
     * @param order the string representation of the order
     */
    public void update(final String name, final String order) {
        orderArea.setText(order);
        customerLabel.setText(name);
    }
}
