package sk.uniba.fmph.dcs.game_board;


import sk.uniba.fmph.dcs.stone_age.ActionResult;
import sk.uniba.fmph.dcs.stone_age.Effect;
import sk.uniba.fmph.dcs.stone_age.HasAction;

public class PlaceOnToolMakerAdaptor implements InterfaceFigureLocationInternal{
    private ToolMakerHutsFields tools;

    public PlaceOnToolMakerAdaptor(ToolMakerHutsFields tools) {
        this.tools = tools;
    }

    @Override
    public boolean placeFigures(Player player, int figureCount) {
        if (tryToPlaceFigures(player, figureCount) == HasAction.NO_ACTION_POSSIBLE) return false;
        return tools.placeOnToolMaker(player);
    }

    @Override
    public HasAction tryToPlaceFigures(Player player, int count) {
        if (!player.playerBoard().hasFigures(count)) return HasAction.NO_ACTION_POSSIBLE;
        if (count != 1) return HasAction.NO_ACTION_POSSIBLE;
        if (!tools.canPlaceOnToolMaker(player)) return HasAction.NO_ACTION_POSSIBLE;
        return HasAction.AUTOMATIC_ACTION_DONE;
    }

    @Override
    public ActionResult makeAction(Player player, Effect[] inputResources, Effect[] outputResources) {
        boolean res = tools.actionToolMaker(player);
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
