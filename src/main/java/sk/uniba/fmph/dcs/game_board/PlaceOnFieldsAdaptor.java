package sk.uniba.fmph.dcs.game_board;

import sk.uniba.fmph.dcs.stone_age.ActionResult;
import sk.uniba.fmph.dcs.stone_age.Effect;
import sk.uniba.fmph.dcs.stone_age.HasAction;

public final class PlaceOnFieldsAdaptor implements InterfaceFigureLocationInternal {
    private ToolMakerHutsFields fields;

    public PlaceOnFieldsAdaptor(final ToolMakerHutsFields fields) {
        this.fields = fields;
    }

    @Override
    public boolean placeFigures(final Player player, final int figureCount) {
        if (tryToPlaceFigures(player, figureCount) == HasAction.NO_ACTION_POSSIBLE) {
            return false;
        }
        return fields.placeOnFields(player);
    }

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
        return HasAction.AUTOMATIC_ACTION_DONE;
    }

    @Override
    public ActionResult makeAction(final Player player, final Effect[] inputResources, final Effect[] outputResources) {
        boolean res = fields.actionFields(player);
        if (res) {
            return ActionResult.ACTION_DONE;
        } else {
            return ActionResult.FAILURE;
        }
    }

    @Override
    public boolean skipAction(final Player player) {
        return false;
    }

    @Override
    public HasAction tryToMakeAction(final Player player) {
        return null;
    }

    @Override
    public boolean newTurn() {
        return false;
    }
}
