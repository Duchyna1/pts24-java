package sk.uniba.fmph.dcs.game_board;

import sk.uniba.fmph.dcs.stone_age.ActionResult;
import sk.uniba.fmph.dcs.stone_age.Effect;
import sk.uniba.fmph.dcs.stone_age.HasAction;

public class PlaceOnFieldsAdaptor implements InterfaceFigureLocationInternal {
    private final ToolMakerHutsFields fields;

    /**
     * @param fields
     *            using methods from ToolMakerHutsFields
     */
    public PlaceOnFieldsAdaptor(final ToolMakerHutsFields fields) {
        this.fields = fields;
    }

    /**
     * @param player
     *            player that places figures
     * @param figureCount
     *            number of figures to place
     *
     * @return true if move is possible
     */
    @Override
    public boolean placeFigures(final Player player, final int figureCount) {
        if (tryToPlaceFigures(player, figureCount) == HasAction.NO_ACTION_POSSIBLE) {
            return false;
        }
        return fields.placeOnFields(player);
    }

    /**
     * does field allow for player to place figures.
     *
     * @param player
     *            player that places figures
     * @param count
     *            number of figures
     *
     * @return `NO_ACTION_POSSIBLE` when player cant place figures on field, `WAITING_FOR_PLAYER_ACTION` otherwise
     */
    @Override
    public HasAction tryToPlaceFigures(final Player player, final int count) {
        if (!player.playerBoard().hasFigures(count)) {
            return HasAction.NO_ACTION_POSSIBLE;
        }
        if (count != 1) {
            return HasAction.NO_ACTION_POSSIBLE;
        }
        if (!fields.canPlaceOnFields(player)) {
            return HasAction.NO_ACTION_POSSIBLE;
        }
        return HasAction.WAITING_FOR_PLAYER_ACTION;
    }

    /**
     * resolve fields.
     *
     * @param player
     *            player that wants to resolve
     * @param inputResources
     *            really don't know todo
     * @param outputResources
     *            don't know either todo
     *
     * @return ACTION_DONE when player can resolve fields, FAILURE otherwise
     */
    @Override
    public ActionResult makeAction(final Player player, final Effect[] inputResources, final Effect[] outputResources) {
        boolean res = fields.actionFields(player);
        if (res) {
            return ActionResult.ACTION_DONE;
        } else {
            return ActionResult.FAILURE;
        }
    }

    // todo

    /**
     * does exactly nothing.
     *
     * @param player
     *            player to do nothing with
     *
     * @return false
     */
    @Override
    public boolean skipAction(final Player player) {
        return false;
    }

    // todo

    /**
     * does exactly nothing.
     *
     * @param player
     *            player to do nothing with
     *
     * @return null
     */
    @Override
    public HasAction tryToMakeAction(final Player player) {
        return null;
    }

    // todo

    /**
     * does exactly nothing.
     *
     * @return false
     */
    @Override
    public boolean newTurn() {
        return false;
    }
}
