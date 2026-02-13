package it.unibo.makeanicecream.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import it.unibo.makeanicecream.api.GameController;
import it.unibo.makeanicecream.api.GameView;
import it.unibo.makeanicecream.api.Icecream;
import it.unibo.makeanicecream.api.GameState;

/**
 * Implementation of the {@link GameView} interface.
 */
public final class GameViewImpl extends JFrame implements GameView {

    private static final long serialVersionUID = 1L;

    private static final String FRAME_NAME = "Make an Ice Cream";
    private static final String MENU_CARD = "MENU";
    private static final String GAME_CARD = "GAME";
    private static final String PAUSE_CARD = "PAUSE";
    private static final String GAME_OVER_CARD = "GAME OVER";
    private static final String LEVEL_COMPLETED_CARD = "LEVEL_COMPLETED";

    private final CardLayout layout = new CardLayout();
    private final JPanel mainPanel = new JPanel(layout);

    private final MenuPanel menuPanel;
    private final StatusPanel statusPanel;
    private final CustomerPanel customerPanel;
    private final IngredientsPanel ingredientsPanel;
    private final AreaPlayerPanel areaPlayerPanel;
    private final ActionsPanel actionsPanel;
    private final PausePanel pausePanel;
    private final GameOverPanel gameOverPanel;
    private final LevelCompletedPanel levelCompletedPanel;

    private GameController controller;

    private String currentCustomer;
    private String currentOrder;

    /**
     * Builds a new GameViewImpl.
     */
    public GameViewImpl() {
        setTitle(FRAME_NAME);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.menuPanel = new MenuPanel();
        mainPanel.add(menuPanel, MENU_CARD);

        this.pausePanel = new PausePanel();
        mainPanel.add(pausePanel, PAUSE_CARD);

        this.gameOverPanel = new GameOverPanel();
        mainPanel.add(gameOverPanel, GAME_OVER_CARD);

        this.levelCompletedPanel = new LevelCompletedPanel();
        mainPanel.add(levelCompletedPanel, LEVEL_COMPLETED_CARD);

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
        final JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(this.areaPlayerPanel, BorderLayout.CENTER);
        rightPanel.add(this.actionsPanel, BorderLayout.SOUTH);

        final JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, this.ingredientsPanel, rightPanel);
        splitPane.setResizeWeight(0.70);
        splitPane.setDividerSize(8);
        splitPane.setContinuousLayout(true);

        gamePanel.add(splitPane, BorderLayout.CENTER);

        mainPanel.add(gamePanel, GAME_CARD);

        setContentPane(mainPanel);
        pack();
        SwingUtilities.invokeLater(() -> splitPane.setDividerLocation(0.65));
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
        this.statusPanel.setController(controller);
        this.gameOverPanel.setController(controller);
        this.levelCompletedPanel.setController(controller);
    }

    @Override
    public void showCustomer(final String name) {
        this.currentCustomer = name;
        this.customerPanel.update(currentCustomer, currentOrder);
    }

    @Override
    public void showOrder(final String order) {
        this.currentOrder = order;
        this.customerPanel.update(currentCustomer, currentOrder);
    }

    @Override
    public void showTimer(final double timer) {
        this.statusPanel.updateTimer(timer);
    }

    @Override
    public void showLives(final int lives) {
        this.statusPanel.updateLives(lives);
    }

    @Override
    public void showIceCream() {
        final Icecream iceCream = this.controller.getGameIceCream();
        if (iceCream == null || iceCream.getConetype() == null) {
            areaPlayerPanel.showConePanel();
            actionsPanel.setSubmitEnabled(false);
            return;
        } 

        areaPlayerPanel.showBuilderPanel();
        areaPlayerPanel.updateIceCreamView(iceCream.toString());
        final boolean canSubmit = iceCream.getConetype() != null && !iceCream.getIngredients().isEmpty();
        actionsPanel.setSubmitEnabled(canSubmit);
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
                return;
            } else if (currentState == GameState.PAUSED) {
                this.layout.show(this.mainPanel, PAUSE_CARD);
                return;
            } else if (currentState == GameState.GAME_OVER) {
                this.layout.show(this.mainPanel, GAME_OVER_CARD);
                return;
            } else if (currentState == GameState.LEVEL_COMPLETED) {
                this.layout.show(this.mainPanel, LEVEL_COMPLETED_CARD);
                return;
            }

            this.layout.show(this.mainPanel, GAME_CARD);
            ingredientsPanel.setToppingButtonsEnabled(this.controller.areToppingsEnabled());
            showIceCream();
        });
    }
}
