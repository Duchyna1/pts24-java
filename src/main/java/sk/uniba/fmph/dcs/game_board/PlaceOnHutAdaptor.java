package sk.uniba.fmph.dcs.game_board;

import sk.uniba.fmph.dcs.stone_age.ActionResult;
import sk.uniba.fmph.dcs.stone_age.Effect;
import sk.uniba.fmph.dcs.stone_age.HasAction;

public class PlaceOnHutAdaptor implements InterfaceFigureLocationInternal {
    private ToolMakerHutsFields huts;

    public PlaceOnHutAdaptor(ToolMakerHutsFields huts) {
        this.huts = huts;
    }

    @Override
    public boolean placeFigures(Player player, int figureCount) {
        if (tryToPlaceFigures(player, figureCount) == HasAction.NO_ACTION_POSSIBLE) return false;
        return huts.placeOnHut(player);
    }

    @Override
    public HasAction tryToPlaceFigures(Player player, int count) {
        if (!player.playerBoard().hasFigures(count)) return HasAction.NO_ACTION_POSSIBLE;
        if (count != 1) return HasAction.NO_ACTION_POSSIBLE;
        if (!huts.canPlaceOnHut(player)) return HasAction.NO_ACTION_POSSIBLE;
        return HasAction.AUTOMATIC_ACTION_DONE;
    }

    @Override
    public ActionResult makeAction(Player player, Effect[] inputResources, Effect[] outputResources) {
        boolean res = huts.actionHut(player);
        if (res) return ActionResult.ACTION_DONE;
        else return ActionResult.FAILURE;
    }

    @Override
    public boolean skipAction(Player player) {
        return false;
    }

    @Override
    public HasAction tryToMakeAction(Player player) {
        return null;
    }

    @Override
    public boolean newTurn() {
        return false;
    }
}
