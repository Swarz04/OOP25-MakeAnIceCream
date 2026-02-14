package it.unibo.makeanicecream.view;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Image;

import java.util.Map;
import java.util.logging.Logger;

/**
 * Panel responsible for displaying the current customer and their order.
 */
public final class CustomerPanel extends JPanel {
    private static final Logger LOGGER = Logger.getLogger(CustomerPanel.class.getName());
    private static final long serialVersionUID = 1L;
    private static final int HORIZONTAL_GAP = 20;
    private static final int VERTICAL_GAP = 10;
    private static final String DEFAULT_CUSTOMER_IMAGE = "cliente1.png";

    /**
     * Mapping of customer names to their corresponding image file names in the resources folder.
     * If a customer's name is not found in this map, a default image will be used.
     */
    private static final Map<String, String> NAME_TO_IMAGE = Map.of(
        "Maria", "cliente1.png",
        "Paolo", "cliente2.png",
        "Giulia", "cliente3.png",
        "Giorgio", "cliente4.png",
        "Lucia", "cliente5.png",
        "Mario", DEFAULT_CUSTOMER_IMAGE
    );

    private final JLabel customerImageLabel;
    private final JLabel customerLabel;
    private final JLabel orderLabel;

    /**
     * Builds a new CustomerPanel.
     */
    public CustomerPanel() {
        super(new BorderLayout(HORIZONTAL_GAP, VERTICAL_GAP));

        this.customerImageLabel = new JLabel();
        this.customerLabel = new JLabel();
        this.orderLabel = new JLabel();

        initLayout();
    }

    /**
     * Initializes the layout and graphical components.
     */
    private void initLayout() {

        final JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(customerImageLabel, BorderLayout.CENTER);
        leftPanel.add(customerLabel, BorderLayout.SOUTH);
        orderLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        orderLabel.setVerticalAlignment(JLabel.TOP);
        final JScrollPane scrollPane = new JScrollPane(orderLabel);
        scrollPane.setBorder(null);
        this.add(leftPanel, BorderLayout.WEST);
        this.add(scrollPane, BorderLayout.CENTER);
        setVisible(false);
    }

    /**
     * Updates the customer display.
     *
     * @param name the customer's name
     * @param order the string representation of the order
     */
    public void update(final String name, final String order) {
        customerLabel.setText(name == null ? "" : name);
        customerImageLabel.setIcon(loadCustomerIcon(name));
        orderLabel.setText(order == null ? "" : order);
        setVisible(true);
    }

    /**
     * Clears the customer display, resetting the name, order, and image to their default states, and hides the panel.
     */
    public void clear() {
        customerLabel.setText("");
        orderLabel.setText("");
        setVisible(false);
    }

    /**
     * Loads the customer's image based on their name. If the name is not found in the mapping, a default image is returned.
     * 
     * @param name the customer's name
     * @return the ImageIcon corresponding to the customer's image, or null if the image file is not found
     */
    private ImageIcon loadCustomerIcon(final String name) {
        final String imagePath = NAME_TO_IMAGE.getOrDefault(name, DEFAULT_CUSTOMER_IMAGE);
        final java.net.URL imgURL = getClass().getResource("/" + imagePath);

        if (imgURL == null) {
            LOGGER.warning("Couldn't find file: " + imagePath);
        }

        final ImageIcon icon = imgURL != null ? new ImageIcon(imgURL) : null;

        final Image scaledImage = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);

        return new ImageIcon(scaledImage);
    }
}
