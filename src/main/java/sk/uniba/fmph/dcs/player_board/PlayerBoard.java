package sk.uniba.fmph.dcs.player_board;

public class PlayerBoard {
    private int points;
    private int house;
    private final PlayerResourcesAndFood playerResourcesAndFood;
    private final PlayerFigures playerFigures;
    private final PlayerTools playerTools;
    private final PlayerCivilisationCards playerCivilisationCards;
    private final TribeFedStatus tribeFedStatus;

    public PlayerBoard() {
        this.playerResourcesAndFood = new PlayerResourcesAndFood();
        this.playerFigures = new PlayerFigures();
        this.playerTools = new PlayerTools();
        this.playerCivilisationCards = new PlayerCivilisationCards();
        this.tribeFedStatus = new TribeFedStatus(this.playerResourcesAndFood, this.playerFigures);
    }

    public PlayerResourcesAndFood getPlayerResourcesAndFood() {
        return this.playerResourcesAndFood;
    }

    public PlayerFigures getPlayerFigures() {
        return this.playerFigures;
    }

    public PlayerTools getPlayerTools() {
        return this.playerFigures;
    }

    public PlayerCivilisationCards getPlayerCivilisationCards() {
        return this.playerCivilisationCards;
    }

    public TribeFedStatus getTribeFedStatus() {
        return this.tribeFedStatus;
    }

    /**
     * TODO.
     *
     * @param points
     *
     * @return TODO
     */
    public int addPoints(final int points) {
        // TODO
        return 0;
    }

    /**
     * TODO.
     */
    public void addHouse() {
        // TODO
    }

    /**
     * TODO.
     */
    public void addEndOfGamePoints() {
        // TODO
    }

    /**
     * TODO.
     *
     * @return TODO
     */
    public String state() {
        // TODO
        return "TODO";
    }
}
