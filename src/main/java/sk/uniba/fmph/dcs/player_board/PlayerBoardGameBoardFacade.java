package sk.uniba.fmph.dcs.player_board;

import sk.uniba.fmph.dcs.stone_age.Effect;
import sk.uniba.fmph.dcs.stone_age.EndOfGameEffect;
import sk.uniba.fmph.dcs.stone_age.InterfaceFeedTribe;
import sk.uniba.fmph.dcs.stone_age.InterfaceNewTurn;
import sk.uniba.fmph.dcs.stone_age.InterfacePlayerBoardGameBoard;

import java.util.Collection;
import java.util.Optional;

public class PlayerBoardGameBoardFacade implements InterfaceFeedTribe, InterfaceNewTurn, InterfacePlayerBoardGameBoard {
    private final PlayerBoard playerBoard;

    private final int minusPointsForNotFeeding = -10;

    /**
     * Constructs a new PlayerBoardGameBoardFacade and initializes the PlayerBoard.
     */
    public PlayerBoardGameBoardFacade(PlayerBoard playerBoard) {
        this.playerBoard = playerBoard;
    }

    /**
     * Feeds the tribe if there is enough food available.
     *
     * @return {@code true} if the tribe is fed, {@code false} otherwise.
     */
    @Override
    public boolean feedTribeIfEnoughFood() {
        return this.playerBoard.getTribeFedStatus().feedTribeIfEnoughFood();
    }

    /**
     * Feeds the tribe using the provided resources.
     *
     * @param resources
     *            The collection of resources to use for feeding the tribe.
     *
     * @return {@code true} if the tribe is successfully fed, {@code false} otherwise.
     */
    @Override
    public boolean feedTribe(final Collection<Effect> resources) {
        Effect[] resourcesArray = resources.toArray(new Effect[0]);
        return this.playerBoard.getTribeFedStatus().feedTribe(resourcesArray);
    }

    /**
     * Indicates that the tribe should not be fed this turn, applying any penalties if applicable.
     *
     * @return {@code true} if the tribe feeding status is set, {@code false} otherwise.
     */
    @Override
    public boolean doNotFeedThisTurn() {
        boolean fed = this.playerBoard.getTribeFedStatus().setTribeFed();
        if (fed) {
            this.playerBoard.addPoints(this.minusPointsForNotFeeding);
        }
        return fed;
    }

    /**
     * Checks if the tribe has been fed.
     *
     * @return {@code true} if the tribe is fed, {@code false} otherwise.
     */
    @Override
    public boolean isTribeFed() {
        return this.playerBoard.getTribeFedStatus().isTribeFed();
    }

    /**
     * Advances the game to a new turn.
     */
    @Override
    public void newTurn() {
        this.playerBoard.newTurn();
    }

    /**
     * Provides resources to the player.
     *
     * @param stuff
     *            The array of effects to give to the player.
     */
    @Override
    public void giveEffect(final Effect[] stuff) {
        this.playerBoard.getPlayerResourcesAndFood().giveResources(stuff);
    }

    /**
     * Provides end-of-game effects to the player's civilization cards.
     *
     * @param stuff
     *            The array of end-of-game effects to give to the player.
     */
    @Override
    public void giveEndOfGameEffect(final EndOfGameEffect[] stuff) {
        this.playerBoard.getPlayerCivilisationCards().addEndOfGameEffects(stuff);
    }

    /**
     * Takes the specified resources from the player if they are available.
     *
     * @param stuff
     *            The array of resources to be taken.
     *
     * @return {@code true} if the resources were successfully taken, {@code false} otherwise.
     */
    @Override
    public boolean takeResources(final Effect[] stuff) {
        if (this.playerBoard.getPlayerResourcesAndFood().hasResources(stuff)) {
            this.playerBoard.getPlayerResourcesAndFood().takeResources(stuff);
            return true;
        }
        return false;
    }

    /**
     * Adds a new figure to the player's collection.
     */
    @Override
    public void giveFigure() {
        this.playerBoard.getPlayerFigures().addNewFigure();
    }

    /**
     * Takes the specified number of figures from the player if they have enough.
     *
     * @param count
     *            The number of figures to take.
     *
     * @return {@code true} if the figures were successfully taken, {@code false} otherwise.
     */
    @Override
    public boolean takeFigures(final int count) {
        if (this.playerBoard.getPlayerFigures().hasFigures(count)) {
            this.playerBoard.getPlayerFigures().takeFigures(count);
            return true;
        }
        return false;
    }

    /**
     * Checks if the player has at least the specified number of figures.
     *
     * @param count
     *            The number of figures to check for.
     *
     * @return {@code true} if the player has enough figures, {@code false} otherwise.
     */
    @Override
    public boolean hasFigures(final int count) {
        return this.playerBoard.getPlayerFigures().hasFigures(count);
    }

    /**
     * Checks if the player has sufficient tools to meet the specified goal.
     *
     * @param goal
     *            The goal to check against the player's tools.
     *
     * @return {@code true} if the player has sufficient tools, {@code false} otherwise.
     */
    @Override
    public boolean hasSufficientTools(final int goal) {
        return this.playerBoard.getPlayerTools().hasSufficientTools(goal);
    }

    /**
     * Uses a tool specified by its index.
     *
     * @param idx
     *            The index of the tool to use.
     *
     * @return An {@code Optional} containing the tool's value if valid, or {@code Optional.empty()} if not.
     */
    @Override
    public Optional<Integer> useTool(final int idx) {
        Integer value = this.playerBoard.getPlayerTools().useTool(idx);
        if (value == null) { // Assuming -1 signifies an error or invalid state
            return Optional.empty();
        }
        return Optional.of(value);
    }
}
