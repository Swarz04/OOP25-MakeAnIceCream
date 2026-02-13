package it.unibo.makeanicecream.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import it.unibo.makeanicecream.api.GameController;
import it.unibo.makeanicecream.api.GameView;
import it.unibo.makeanicecream.api.GameState;
import it.unibo.makeanicecream.api.Icecream;

/**
 * Implementation of the {@link GameView} interface.
 */
public final class GameViewImpl extends JFrame implements GameView {

    private static final long serialVersionUID = 1L;

    private static final String FRAME_NAME = "Make an Ice Cream";
    private static final String MENU_CARD = "MENU";
    private static final String GAME_CARD = "GAME";
    private static final String PAUSE_CARD = "PAUSE";

    private final CardLayout layout = new CardLayout();
    private final JPanel mainPanel = new JPanel(layout);

    private final MenuPanel menuPanel;
    private final StatusPanel statusPanel;
    private final CustomerPanel customerPanel;
    private final IngredientsPanel ingredientsPanel;
    private final AreaPlayerPanel areaPlayerPanel;
    private final ActionsPanel actionsPanel;
    private final PausePanel pausePanel;

    private GameController controller;

    private String currentCustomer;
    private String currentOrder;
    private int currentLives;
    private double currentTimer;

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

        this.pausePanel = new PausePanel();
        mainPanel.add(pausePanel, PAUSE_CARD);

        this.statusPanel = new StatusPanel();
        this.customerPanel = new CustomerPanel();
        this.ingredientsPanel = new IngredientsPanel();
        this.areaPlayerPanel = new AreaPlayerPanel();
        this.actionsPanel = new ActionsPanel();

        this.actionsPanel.setResetAction(() -> {
            this.areaPlayerPanel.showConePanel();
            this.areaPlayerPanel.updateIceCreamView("IceCream reset");
        });

        this.actionsPanel.setSubmitAction(() -> {
            this.areaPlayerPanel.showConePanel();
            this.areaPlayerPanel.updateIceCreamView("IceCream submitted");
        });

        final JPanel gamePanel = new JPanel(new BorderLayout());

        // Layout della schermata di gioco
        //TOP:status e customer
        final JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(this.statusPanel, BorderLayout.NORTH);
        topPanel.add(this.customerPanel, BorderLayout.CENTER);
        gamePanel.add(topPanel, BorderLayout.NORTH);
        //BOTTOM: left: ingredients, right: area player e actions
        final JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(this.ingredientsPanel, BorderLayout.WEST);

        final JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(this.areaPlayerPanel, BorderLayout.CENTER);
        rightPanel.add(this.actionsPanel, BorderLayout.SOUTH);

        bottomPanel.add(rightPanel, BorderLayout.CENTER);

        gamePanel.add(bottomPanel, BorderLayout.CENTER);

        mainPanel.add(gamePanel, GAME_CARD);

        setContentPane(mainPanel);
        pack();
        setMinimumSize(getPreferredSize());
        setLocationRelativeTo(null);
    }

    @Override
    public void setController(final GameController controller) {
        this.controller = controller;

        this.menuPanel.setController(controller);
        this.actionsPanel.setController(controller);
        this.ingredientsPanel.setController(controller);
        this.areaPlayerPanel.setController(controller);
        this.pausePanel.setController(controller);
    }

    @Override
    public void showCustomer(final String name) {
        this.currentCustomer = name;
        this.customerPanel.update(currentCustomer, currentOrder);
    }

    @Override
    public void showOrder(final String order){
        this.currentOrder = order;
        this.customerPanel.update(currentCustomer, currentOrder);
    }

    @Override
    public void showTimer(final double timer) {
        this.currentTimer = timer;
        this.statusPanel.update(currentLives, currentTimer);
    }

    @Override
    public void showLives(final int lives) {
        this.currentLives = lives;
        this.statusPanel.update(currentLives, currentTimer);
    }

    @Override
    public void showIngredients() {
        ingredientsPanel.setToppingButtonsEnabled(true);
    }

    @Override
    public void showIceCream() {
        final Icecream iceCream = controller.getGameIceCream();
        areaPlayerPanel.showBuilderPanel();
        areaPlayerPanel.updateIceCreamView("IceCream updated: " + iceCream.toString());
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
            if (this.controller == null) {
                return;
            }

            final GameState currentState = this.controller.getGameState();
            if (currentState == GameState.MENU) {
                this.layout.show(this.mainPanel, MENU_CARD);
            } else if (currentState == GameState.PAUSED) {
                this.layout.show(this.mainPanel, PAUSE_CARD);
            } else {
                this.layout.show(this.mainPanel, GAME_CARD);
                showIceCream();
            }
        });
    }
}
