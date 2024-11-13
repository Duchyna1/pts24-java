package sk.uniba.fmph.dcs.game_board;

import sk.uniba.fmph.dcs.stone_age.PlayerOrder;

import java.util.ArrayList;
import java.util.List;

public final class ToolMakerHutsFields {

    private List<PlayerOrder> toolMakerFigures;
    private List<PlayerOrder> hutFigures;
    private List<PlayerOrder> fieldsFigures;
    private int restriction;


    public ToolMakerHutsFields() {
        toolMakerFigures = new ArrayList<>();
        hutFigures = new ArrayList<>();
        fieldsFigures = new ArrayList<>();
    }

    public boolean placeOnToolMaker(final Player player) {
        if (!canPlaceOnToolMaker(player)) {
            return false;
        }
        toolMakerFigures.add(player.playerOrder());
        return true;
    }

    public boolean actionToolMaker(final Player player) {
        //todo
        return false;
    }

    public boolean canPlaceOnToolMaker(final Player player) {
        return toolMakerFigures.isEmpty();
    }

    public boolean placeOnHut(final Player player) {
        if (!canPlaceOnHut(player)) {
            return false;
        }
        hutFigures.add(player.playerOrder());
        hutFigures.add(player.playerOrder());
        return true;
    }

    public boolean actionHut(final Player player) {
        //todo
        return false;
    }

    public boolean canPlaceOnHut(final Player player) {
        return hutFigures.isEmpty();
    }

    public boolean placeOnFields(final Player player) {
        if (!canPlaceOnFields(player)) {
            return false;
        }
        fieldsFigures.add(player.playerOrder());
        return true;
    }

    public boolean actionFields(final Player player) {
        //todo
        return false;
    }

    public boolean canPlaceOnFields(final Player player) {
        return fieldsFigures.isEmpty();
    }

    public void newTurn() {
        toolMakerFigures.clear();
        hutFigures.clear();
        fieldsFigures.clear();
    }
    public String state() {
        return "ToolMakerHutsFields:\n"
                + "ToolMaker: " + toolMakerFigures.toString() + "\n"
                + "Huts: " + hutFigures.toString() + "\n"
                + "Fields: " + fieldsFigures.toString() + "\n";
    }
}
