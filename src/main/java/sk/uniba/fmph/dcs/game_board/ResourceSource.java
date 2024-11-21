package sk.uniba.fmph.dcs.game_board;

import sk.uniba.fmph.dcs.stone_age.ActionResult;
import sk.uniba.fmph.dcs.stone_age.Effect;
import sk.uniba.fmph.dcs.stone_age.HasAction;
import sk.uniba.fmph.dcs.stone_age.PlayerOrder;

import java.util.ArrayList;

public class ResourceSource implements InterfaceFigureLocationInternal {
    private String name;
    private final Effect resource;
    private int count;
    private int maxFigures;
    private int maxFigureColors;
    private ArrayList<PlayerOrder> figures;

    public ResourceSource(final Effect resource, final int count) {
        this.resource = resource;
        this.count = count;
    }

    /**
     * TODO.
     *
     * @param player
     * @param figureCount
     *
     * @return TODO
     */
    @Override
    public boolean placeFigures(final Player player, final int figureCount) {
        return false;
    }

    /**
     * TODO.
     *
     * @param player
     * @param count
     *
     * @return TODO
     */
    @Override
    public HasAction tryToPlaceFigures(final Player player, final int count) {
        return null;
    }

    /**
     * TODO.
     *
     * @param player
     * @param inputResources
     * @param outputResources
     *
     * @return TODO
     */
    @Override
    public ActionResult makeAction(final Player player, final Effect[] inputResources, final Effect[] outputResources) {
        return null;
    }

    /**
     * TODO.
     *
     * @param player
     *
     * @return TODO
     */
    @Override
    public boolean skipAction(final Player player) {
        return false;
    }

    /**
     * TODO.
     *
     * @param player
     *
     * @return TODO
     */
    @Override
    public HasAction tryToMakeAction(final Player player) {
        return null;
    }

    /**
     * TODO.
     *
     * @return TODO
     */
    @Override
    public boolean newTurn() {
        return false;
    }

    /**
     * TODO.
     *
     * @return TODO
     */
    @Override
    public String state() {
        return "";
    }
}
