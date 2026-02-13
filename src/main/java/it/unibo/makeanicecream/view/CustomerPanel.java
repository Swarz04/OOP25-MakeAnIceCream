package it.unibo.makeanicecream.view;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import java.awt.*;
/**
 * Panel responsible for displaying the current customer and their order.
 */
public class CustomerPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private final JLabel customerImage;
    private final JLabel customerLabel;
    private final JTextArea orderArea;
    /**
     * Builds a new CustomerPanel.
     */
    public CustomerPanel() {
        super(new BorderLayout(20, 0));
        
        this.customerImage = new JLabel();
        this.customerLabel = new JLabel();
        this.orderArea = new JTextArea();

        initLayout();
    }

    /**
     * Initializes the layout and graphical components.
     */
    private void initLayout(){

        final JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(customerImage, BorderLayout.CENTER);
        leftPanel.add(customerLabel, BorderLayout.SOUTH);
        orderArea.setEditable(false);
        orderArea.setOpaque(true);
        orderArea.setWrapStyleWord(true);
        orderArea.setBackground(Color.WHITE);
        orderArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        this.add(leftPanel, BorderLayout.WEST);
        this.add(orderArea, BorderLayout.CENTER);
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
