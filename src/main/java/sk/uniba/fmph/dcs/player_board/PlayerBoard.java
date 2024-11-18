package sk.uniba.fmph.dcs.player_board;

public class PlayerBoard {
    private int points;
    private int houses;
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

        this.points = 0;
        this.houses = 0;
    }

    public PlayerResourcesAndFood getPlayerResourcesAndFood() {
        return this.playerResourcesAndFood;
    }

    public PlayerFigures getPlayerFigures() {
        return this.playerFigures;
    }

    public PlayerTools getPlayerTools() {
        return this.playerTools;
    }

    public PlayerCivilisationCards getPlayerCivilisationCards() {
        return this.playerCivilisationCards;
    }

    public TribeFedStatus getTribeFedStatus() {
        return this.tribeFedStatus;
    }

    public int addPoints(final int points) {
        this.points += points;
        return this.points;
    }

    public void addHouse() {
        this.houses++;
    }

    public void addEndOfGamePoints() {
        this.points += this.playerCivilisationCards.calculateEndOfGameCivilisationCardsPoints(
                this.houses,
                this.playerTools.getToolsCount(),
                this.tribeFedStatus.getFieldsCount(),
                this.playerFigures.getTotalFigures());
    }

    public String state() {
        return "Points: " + this.points + ", houses: " + this.houses + "\n" +
                this.playerResourcesAndFood.state() + "\n" +
                this.playerFigures.state() + "\n" +
                this.playerTools.state() + "\n" +
                this.playerCivilisationCards.state() + "\n" +
                this.tribeFedStatus.state();
    }
}
