package sk.uniba.fmph.dcs.game_board;


import sk.uniba.fmph.dcs.stone_age.ActionResult;
import sk.uniba.fmph.dcs.stone_age.Effect;
import sk.uniba.fmph.dcs.stone_age.HasAction;
import sk.uniba.fmph.dcs.stone_age.InterfaceFigureLocation;
import sk.uniba.fmph.dcs.stone_age.PlayerOrder;

final class FigureLocationAdaptor implements InterfaceFigureLocation {
    private final InterfaceFigureLocationInternal internal;

    FigureLocationAdaptor(final InterfaceFigureLocationInternal internal) {
        this.internal = internal;
    }

    //todo
    @Override
    public boolean placeFigures(final PlayerOrder player, final int figureCount) {
        return false;
    }

    @Override
    public HasAction tryToPlaceFigures(final PlayerOrder player, final int count) {
        return null;
    }

    @Override
    public ActionResult makeAction(final PlayerOrder player, final Effect[] inputResources, final Effect[] outputResources) {
        return null;
    }

    @Override
    public boolean skipAction(final PlayerOrder player) {
        return false;
    }

    @Override
    public HasAction tryToMakeAction(final PlayerOrder player) {
        return null;
    }

    @Override
    public boolean newTurn() {
        return false;
    }
}
