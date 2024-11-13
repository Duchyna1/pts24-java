package sk.uniba.fmph.dcs.game_board;

import sk.uniba.fmph.dcs.stone_age.*;

public class FigureLocationAdaptor implements InterfaceFigureLocation, InterfaceFigureLocationInternal {
    @Override
    public boolean placeFigures(Player player, int figureCount) {
        return false;
    }

    @Override
    public HasAction tryToPlaceFigures(Player player, int count) {
        return null;
    }

    @Override
    public ActionResult makeAction(Player player, Effect[] inputResources, Effect[] outputResources) {
        return null;
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
    public boolean placeFigures(PlayerOrder player, int figureCount) {
        return false;
    }

    @Override
    public HasAction tryToPlaceFigures(PlayerOrder player, int count) {
        return null;
    }

    @Override
    public ActionResult makeAction(PlayerOrder player, Effect[] inputResources, Effect[] outputResources) {
        return null;
    }

    @Override
    public boolean skipAction(PlayerOrder player) {
        return false;
    }

    @Override
    public HasAction tryToMakeAction(PlayerOrder player) {
        return null;
    }

    @Override
    public boolean newTurn() {
        return false;
    }
}
