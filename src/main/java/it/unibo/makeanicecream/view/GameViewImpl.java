package it.unibo.makeanicecream.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
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
    private final AreaPlayerPanel areaPlayerPanel;
    private final ActionsPanel actionsPanel;

    private GameController controller;

    /**
     * Builds a new GameViewImpl.
     *
     * @param controller the game model
     */
    public GameViewImpl() {
        setTitle(FRAME_NAME);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.menuPanel = new MenuPanel();
        mainPanel.add(menuPanel, MENU_CARD);

        this.statusPanel = new StatusPanel();
        this.customerPanel = new CustomerPanel();
        this.ingredientsPanel = new IngredientsPanel();
        this.areaPlayerPanel = new AreaPlayerPanel();
        this.actionsPanel = new ActionsPanel();

        final JPanel gamePanel = new JPanel(new BorderLayout());

        // Layout della schermata di gioco
        final JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(this.statusPanel, BorderLayout.NORTH);
        topPanel.add(this.customerPanel, BorderLayout.CENTER);
        gamePanel.add(topPanel, BorderLayout.NORTH);

        final JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(this.ingredientsPanel, BorderLayout.CENTER);
        
        final JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(this.areaPlayerPanel, BorderLayout.CENTER);
        rightPanel.add(this.actionsPanel, BorderLayout.SOUTH);
        
        bottomPanel.add(rightPanel, BorderLayout.CENTER);
        gamePanel.add(bottomPanel, BorderLayout.CENTER);
        mainPanel.add(gamePanel, GAME_CARD);

        //setController(controller);
        /** la parte precedente era:
        final JPanel centerContainer = new JPanel();
        centerContainer.setLayout(new BoxLayout(centerContainer, BoxLayout.Y_AXIS));
        centerContainer.add(this.customerPanel);
        centerContainer.add(this.ingredientsPanel);

        gamePanel.add(centerContainer, BorderLayout.CENTER);
        mainPanel.add(gamePanel, GAME_CARD);

        setContentPane(mainPanel);
        
        pack();
        setLocationByPlatform(true);
        */
        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
    }

    @Override
    public void setController(final GameController controller) {
        this.controller = controller;

        this.menuPanel.setController(controller);
        this.actionsPanel.setController(controller);
        this.ingredientsPanel.setController(controller);
        this.areaPlayerPanel.setController(controller);
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
        ingredientsPanel.setToppingButtonsEnabled(true);
    }

    @Override
    public void showIceCream() {
        areaPlayerPanel.updateIceCreamView("IceCream updated");
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
        if (this.controller == null) {
            return;
        }

        SwingUtilities.invokeLater(() -> {
            if (this.controller == null) {
                return;
            }

            final GameState currentState = this.controller.getGameState();
            if (currentState == GameState.MENU) {
                this.layout.show(this.mainPanel, MENU_CARD);
            } else {
                this.layout.show(this.mainPanel, GAME_CARD);
            }
        });
    }
}
