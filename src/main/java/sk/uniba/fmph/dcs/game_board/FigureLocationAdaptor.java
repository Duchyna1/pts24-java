package sk.uniba.fmph.dcs.game_board;

import sk.uniba.fmph.dcs.stone_age.ActionResult;
import sk.uniba.fmph.dcs.stone_age.Effect;
import sk.uniba.fmph.dcs.stone_age.HasAction;
import sk.uniba.fmph.dcs.stone_age.InterfaceFigureLocation;
import sk.uniba.fmph.dcs.stone_age.PlayerOrder;

import java.util.Collection;
import java.util.List;

public final class FigureLocationAdaptor implements InterfaceFigureLocation {
    private final InterfaceFigureLocationInternal figureLocation;
    private final List<Player> players;

    public FigureLocationAdaptor(final InterfaceFigureLocationInternal figureLocation, final List<Player> players) {
        this.figureLocation = figureLocation;
        this.players = players;
    }

    private Player findPlayerOrder(final PlayerOrder player) {
        for (Player p : players) {
            if (p.playerOrder().equals(player)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public boolean placeFigures(final PlayerOrder player, final int figureCount) {
        Player p = findPlayerOrder(player);
        if (p != null) {
            return figureLocation.placeFigures(p, figureCount);
        }
        return false;
    }

    @Override
    public HasAction tryToPlaceFigures(final PlayerOrder player, final int count) {
        Player p = findPlayerOrder(player);
        if (p != null) {
            return figureLocation.tryToPlaceFigures(p, count);
        }
        return null;
    }

    @Override
    public ActionResult makeAction(final PlayerOrder player, final Collection<Effect> inputResources,
            final Collection<Effect> outputResources) {
        Effect[] input = inputResources.toArray(new Effect[0]);
        Effect[] output = outputResources.toArray(new Effect[0]);
        Player p = findPlayerOrder(player);
        if (p != null) {
            return figureLocation.makeAction(p, input, output);
        }
        return null;
    }

    @Override
    public boolean skipAction(final PlayerOrder player) {
        Player p = findPlayerOrder(player);
        if (p != null) {
            return figureLocation.skipAction(p);
        }
        return false;
    }

    @Override
    public HasAction tryToMakeAction(final PlayerOrder player) {
        Player p = findPlayerOrder(player);
        if (p != null) {
            figureLocation.tryToMakeAction(p);
        }
        return null;
    }

    @Override
    public boolean newTurn() {
        return figureLocation.newTurn();
    }
}
