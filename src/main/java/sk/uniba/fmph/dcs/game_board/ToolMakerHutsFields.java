package sk.uniba.fmph.dcs.game_board;

import sk.uniba.fmph.dcs.stone_age.ActionResult;
import sk.uniba.fmph.dcs.stone_age.Effect;
import sk.uniba.fmph.dcs.stone_age.HasAction;
import sk.uniba.fmph.dcs.stone_age.PlayerOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ToolMakerHutsFields {

    private List<PlayerOrder> toolMakerFigures;
    private List<PlayerOrder> hutFigures;
    private List<PlayerOrder> fieldsFigures;
    private int restriction;


    public ToolMakerHutsFields() {
        toolMakerFigures = new ArrayList<>();
        hutFigures = new ArrayList<>();
        fieldsFigures = new ArrayList<>();
    }

    public boolean placeOnToolMaker(Player player) {
        if (!canPlaceOnToolMaker(player)) return false;
        toolMakerFigures.add(player.playerOrder());
        return true;
    }

    public boolean actionToolMaker(Player player) {
        //todo
        return false;
    }

    public boolean canPlaceOnToolMaker(Player player) {
        return toolMakerFigures.isEmpty();
    }

    public boolean placeOnHut(Player player) {
        if (!canPlaceOnHut(player)) return false;
        hutFigures.add(player.playerOrder());
        hutFigures.add(player.playerOrder());
        return true;
    }

    public boolean actionHut(Player player) {
        //todo
        return false;
    }

    public boolean canPlaceOnHut(Player player) {
        return hutFigures.isEmpty();
    }

    public boolean placeOnFields(Player player) {
        if (!canPlaceOnFields(player)) return false;
        fieldsFigures.add(player.playerOrder());
        return true;
    }

    public boolean actionFields(Player player) {
        //todo
        return false;
    }

    public boolean canPlaceOnFields(Player player) {
        return fieldsFigures.isEmpty();
    }

    public void newTurn() {
        toolMakerFigures.clear();
        hutFigures.clear();
        fieldsFigures.clear();
    }

    public String state() {
        return "ToolMakerHutsFields:\n" +
                "ToolMaker: " + toolMakerFigures.toString() + "\n" +
                "Huts: " + hutFigures.toString() + "\n" +
                "Fields: " + fieldsFigures.toString() + "\n";
    }
}
