package sk.uniba.fmph.dcs.player_board;

import sk.uniba.fmph.dcs.stone_age.InterfaceGetState;

public class PlayerBoard implements InterfaceGetState {
    private int points;
    private int houses;
    private final PlayerResourcesAndFood playerResourcesAndFood;
    private final PlayerFigures playerFigures;
    private final PlayerTools playerTools;
    private final PlayerCivilisationCards playerCivilisationCards;
    private final TribeFedStatus tribeFedStatus;
    private boolean endOfGamePointsAdded;

    public PlayerBoard() {
        this.playerResourcesAndFood = new PlayerResourcesAndFood();
        this.playerFigures = new PlayerFigures();
        this.playerTools = new PlayerTools();
        this.playerCivilisationCards = new PlayerCivilisationCards();
        this.tribeFedStatus = new TribeFedStatus(this.playerResourcesAndFood, this.playerFigures);

        this.points = 0;
        this.houses = 0;
        this.endOfGamePointsAdded = false;
    }

    /**
     * Initiates the start of a new turn for the player.
     * <p>
     * This method updates various components of the player board to reflect the start of a new turn: - Resets the
     * tribe's fed status and provides food based on fields. - Resets the number of available player figures. - Resets
     * the status of the player's tools to unused.
     */
    public void newTurn() {
        this.tribeFedStatus.newTurn();
        this.playerFigures.newTurn();
        this.playerTools.newTurn();
    }

    /**
     * Retrieves the current resources and food status for the player.
     *
     * @return the PlayerResourcesAndFood object containing the player's resources and food.
     */
    public PlayerResourcesAndFood getPlayerResourcesAndFood() {
        return this.playerResourcesAndFood;
    }

    /**
     * Retrieves the current figures status for the player.
     *
     * @return the PlayerFigures object containing the player's figures status.
     */
    public PlayerFigures getPlayerFigures() {
        return this.playerFigures;
    }

    /**
     * Retrieves the current tools status for the player.
     *
     * @return the PlayerTools object containing the player's tools status.
     */
    public PlayerTools getPlayerTools() {
        return this.playerTools;
    }

    /**
     * Retrieves the current civilization cards for the player.
     *
     * @return the PlayerCivilisationCards object containing the player's civilization cards.
     */
    public PlayerCivilisationCards getPlayerCivilisationCards() {
        return this.playerCivilisationCards;
    }

    /**
     * Retrieves the current fed status of the tribe.
     *
     * @return the TribeFedStatus object representing the current fed status of the tribe.
     */
    public TribeFedStatus getTribeFedStatus() {
        return this.tribeFedStatus;
    }

    /**
     * Adds the specified number of points to the player's total points.
     *
     * @param points
     *            the number of points to add
     *
     * @return the updated total number of points
     */
    public int addPoints(final int points) {
        this.points += points;
        return this.points;
    }

    /**
     * Increments the count of houses owned by the player.
     */
    public void addHouse() {
        this.houses++;
    }

    /**
     * Adds the final game points to the player's total score based on various game components such as civilization
     * cards, tools, fields, figures, and resources.
     */
    public void addEndOfGamePoints() {
        if (this.endOfGamePointsAdded) {
            return;
        }
        this.points += this.playerCivilisationCards.calculateEndOfGameCivilisationCardsPoints(this.houses,
                this.playerTools.getToolsCount(), this.tribeFedStatus.getFieldsCount(),
                this.playerFigures.getTotalFigures());
        this.points += this.playerResourcesAndFood.numberOfResourcesForFinalPoints();

        this.endOfGamePointsAdded = true;
    }

    /**
     * Retrieves the current state of the player board, including points, houses, resources, figures, tools,
     * civilization cards, and tribe fed status.
     *
     * @return a string representing the current state of the player board
     */
    @Override
    public String state() {
        return "Points: " + this.points + ", houses: " + this.houses + "\n" + this.playerResourcesAndFood.state() + "\n"
                + this.playerFigures.state() + "\n" + this.playerTools.state() + "\n"
                + this.playerCivilisationCards.state() + "\n" + this.tribeFedStatus.state();
    }
}
