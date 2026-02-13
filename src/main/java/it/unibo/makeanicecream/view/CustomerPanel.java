package it.unibo.makeanicecream.view;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import java.awt.Color;

/**
 * Panel responsible for displaying the current customer and their order.
 */
public class CustomerPanel extends JPanel {
    private static final int HORIZONTAL_GAP = 20;
    private static final int VERTICAL_GAP = 0;

    private static final long serialVersionUID = 1L;

    private final JLabel customerImage;
    private final JLabel customerLabel;
    private final JLabel orderLabel;

    /**
     * Builds a new CustomerPanel.
     */
    public CustomerPanel() {
        super(new BorderLayout(HORIZONTAL_GAP, VERTICAL_GAP));
        
        this.customerImage = new JLabel();
        this.customerLabel = new JLabel();
        this.orderLabel = new JLabel();

        initLayout();
    }

    /**
     * Initializes the layout and graphical components.
     */
    private void initLayout() {

        final JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(customerImage, BorderLayout.CENTER);
        leftPanel.add(customerLabel, BorderLayout.SOUTH);
        orderLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        orderLabel.setVerticalAlignment(JLabel.TOP);
        final JScrollPane scrollPane = new JScrollPane(orderLabel);
        scrollPane.setBorder(null);
        this.add(leftPanel, BorderLayout.WEST);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Updates the customer display.
     *
     * @param name the customer's name
     * @param order the string representation of the order
     */
    public void update(final String name, final String order) {
        customerLabel.setText(name == null ? "" : name);
        orderLabel.setText(order == null ? "" : order);
        
    }
}
