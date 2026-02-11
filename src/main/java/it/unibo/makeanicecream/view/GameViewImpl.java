package it.unibo.makeanicecream.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import it.unibo.makeanicecream.api.Game;
import it.unibo.makeanicecream.api.GameController;
import it.unibo.makeanicecream.api.GameView;
import it.unibo.makeanicecream.api.GameState;

/**
 * Implementation of the {@link GameView} interface.
 */
public final class GameViewImpl extends JFrame implements GameView {

    private static final long serialVersionUID = 1L;

    private static final String FRAME_NAME = "Make an Ice Cream";
    private static final String MENU_CARD = "MENU";
    private static final String GAME_CARD = "GAME";

    private final CardLayout layout = new CardLayout();
    private final JPanel mainPanel = new JPanel(layout);

    private final MenuPanel menuPanel;
    private final StatusPanel statusPanel;
    private final CustomerPanel customerPanel;
    private final IngredientsPanel ingredientsPanel;
    private final ActionsPanel actionsPanel;

    private final GameController controller;

    /**
     * Builds a new GameViewImpl.
     *
     * @param controller the game model
     */
    public GameViewImpl(final GameController controller) {
        this.controller = controller;
        setTitle(FRAME_NAME);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.menuPanel = new MenuPanel();
        mainPanel.add(menuPanel, MENU_CARD);
        this.statusPanel = new StatusPanel();
        this.customerPanel = new CustomerPanel();
        this.ingredientsPanel = new IngredientsPanel();
        this.actionsPanel = new ActionsPanel();

        final JPanel gamePanel = new JPanel(new BorderLayout());

        // Layout della schermata di gioco
        gamePanel.add(this.statusPanel, BorderLayout.NORTH);
        gamePanel.add(this.actionsPanel, BorderLayout.EAST);

        final JPanel centerContainer = new JPanel();
        centerContainer.setLayout(new BoxLayout(centerContainer, BoxLayout.Y_AXIS));
        centerContainer.add(this.customerPanel);
        centerContainer.add(this.ingredientsPanel);

        gamePanel.add(centerContainer, BorderLayout.CENTER);
        mainPanel.add(gamePanel, GAME_CARD);

        setContentPane(mainPanel);
        pack();
        setLocationByPlatform(true);
    }

    @Override
    public void setController(final GameController controller) {
        this.menuPanel.setController(controller);
        this.actionsPanel.setController(controller);
    }

    @Override
    public void showCustomer(final String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'showCustomer'");
    }

    @Override
    public void showOrder(final String order) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'showOrder'");
    }

    @Override
    public void showTimer(final double timer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'showTimer'");
    }

    @Override
    public void showLives(final int lives) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'showLives'");
    }

    @Override
    public void showIngredients() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'showIngredients'");
    }

    @Override
    public void showIceCream() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'showIceCream'");
    }

    @Override
    public void start() {
        SwingUtilities.invokeLater(() -> this.setVisible(true));
    }

    @Override
    public void stop() {
        setVisible(false);
        dispose();
    }

    @Override
    public void update() {
        SwingUtilities.invokeLater(() -> {
            final GameState currentState = this.controller.getGameState();
            if (currentState == GameState.MENU) {
                this.layout.show(this.mainPanel, MENU_CARD);
            } else {
                this.layout.show(this.mainPanel, GAME_CARD);
                if (this.controller.getCurrentLevel() != null) {
                    final var level = this.controller.getCurrentLevel();
                    final var customer = level.getCurrentCustomer();

                    this.statusPanel.update(level.getLives(),
                        (customer != null && customer.getTimer() != null) ? customer.getTimer().getTimeLeft() : 0);

                    if (customer != null) {
                        this.customerPanel.update(customer.getName(), customer.getOrder().toString());
                    } else {
                        this.customerPanel.update("LEVEL COMPLETE!", "");
                    }
                }
                if (currentState == GameState.GAME_OVER) {
                    this.customerPanel.update("GAME OVER", "You lost all your lives.");
                }
            }
        });
    }
}
