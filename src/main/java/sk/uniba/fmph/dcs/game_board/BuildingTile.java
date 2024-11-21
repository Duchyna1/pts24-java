package sk.uniba.fmph.dcs.game_board;

import sk.uniba.fmph.dcs.stone_age.ActionResult;
import sk.uniba.fmph.dcs.stone_age.Effect;
import sk.uniba.fmph.dcs.stone_age.HasAction;
import sk.uniba.fmph.dcs.stone_age.PlayerOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;

public final class BuildingTile implements InterfaceFigureLocationInternal {
    private final Building building;
    private final ArrayList<PlayerOrder> figures;

    /**
     * @param building
     *            building on that building tile
     */
    public BuildingTile(final Building building) {
        this.building = building;
        figures = new ArrayList<>();
    }

    /**
     * @param player
     *            player that places figures
     * @param figureCount
     *            how many figures are placed
     *
     * @return if the placement was successful
     */
    @Override
    public boolean placeFigures(final Player player, final int figureCount) {
        var action = tryToPlaceFigures(player, figureCount);
        if (action == HasAction.WAITING_FOR_PLAYER_ACTION) {
            figures.add(player.playerOrder());
            return true;
        }

        return false;
    }

    /**
     * @param player
     *            player that places figures
     * @param count
     *            how many figures are placed
     *
     * @return WAITING_FOR_PLAYER_ACTION if the figures can be placed, NO_ACTION_POSSIBLE otherwise
     */
    @Override
    public HasAction tryToPlaceFigures(final Player player, final int count) {
        if (count > 1) {
            return HasAction.NO_ACTION_POSSIBLE;
        }
        if (figures.contains(player.playerOrder())) {
            return HasAction.NO_ACTION_POSSIBLE;
        }
        return HasAction.WAITING_FOR_PLAYER_ACTION;
    }

    /**
     * @brief Player claims this building tile
     *
     * @param player
     *            player that takes the tile
     * @param inputResources
     *            resources that player paid to construct
     * @param outputResources
     *            unused
     *
     * @return if the tile was successfully claimed
     */
    @Override
    public ActionResult makeAction(final Player player, final Effect[] inputResources, final Effect[] outputResources) {
        OptionalInt points = building.build(List.of(inputResources));
        if (tryToMakeAction(player) == HasAction.NO_ACTION_POSSIBLE) {
            return ActionResult.FAILURE;
        }
        if (points.isEmpty()) {
            return ActionResult.FAILURE;
        }

        player.playerBoard().addPoints(points.getAsInt());
        player.playerBoard().takeResources(inputResources);
        figures.remove(player.playerOrder());
        return ActionResult.ACTION_DONE;
    }

    /**
     * @param player
     *
     * @return
     */
    @Override
    public boolean skipAction(final Player player) {
        return false;
    }

    /**
     * @brief determines if player can make action on this tile
     *
     * @param player
     *            player from query
     *
     * @return
     */
    @Override
    public HasAction tryToMakeAction(final Player player) {
        if (figures.contains(player.playerOrder())) {
            return HasAction.WAITING_FOR_PLAYER_ACTION;
        }
        return HasAction.NO_ACTION_POSSIBLE;
    }

    /**
     * @return
     */
    @Override
    public boolean newTurn() {
        return false;
    }

    @Override
    public String state() {
        return "";
    }
}
