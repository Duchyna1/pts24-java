package sk.uniba.fmph.dcs.game_board;

import sk.uniba.fmph.dcs.stone_age.Effect;
import sk.uniba.fmph.dcs.stone_age.PlayerOrder;

import java.util.ArrayList;
import java.util.List;

public final class ToolMakerHutsFields {
    private static final int NORMAL_FIGURES = -1;
    private static final int HUT_FIGURES = -3;
    private static final int MAX_FILLED_RESTRICTION = 2;
    private final List<PlayerOrder> toolMakerFigures;
    private final List<PlayerOrder> hutFigures;
    private final List<PlayerOrder> fieldsFigures;
    private final int restriction;
    private static final int FOUR_PLAYERS = 4;
    private boolean violatesRestriction() {
        int result = (toolMakerFigures.isEmpty() ? 1 : 0)
                + (hutFigures.isEmpty() ? 1 : 0)
                + (fieldsFigures.isEmpty() ? 1 : 0);
        if (restriction == FOUR_PLAYERS) {
            return false;
        } else {
            return result < MAX_FILLED_RESTRICTION;
        }
    }

    public ToolMakerHutsFields(final int playerCount) {
        toolMakerFigures = new ArrayList<>();
        hutFigures = new ArrayList<>();
        fieldsFigures = new ArrayList<>();
        this.restriction = playerCount;
    }

    public boolean placeOnToolMaker(final Player player) {
        if (!canPlaceOnToolMaker(player)) {
            return false;
        }
        toolMakerFigures.add(player.playerOrder());
        return true;
    }

    public boolean actionToolMaker(final Player player) {
        if (!toolMakerFigures.getFirst().equals(player.playerOrder())) {
            return false;
        }
        player.playerBoard().giveEffect(new Effect[]{Effect.TOOL});
        player.playerBoard().takeFigures(NORMAL_FIGURES);
        return true;
    }

    public boolean canPlaceOnToolMaker(final Player player) {
        if (violatesRestriction()) {
            return false;
        }
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
        if (!hutFigures.getFirst().equals(player.playerOrder())) {
            return false;
        }
        player.playerBoard().takeFigures(HUT_FIGURES);
        return false;
    }

    public boolean canPlaceOnHut(final Player player) {
        if (violatesRestriction()) {
            return false;
        }
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
        if (!fieldsFigures.getFirst().equals(player.playerOrder())) {
            return false;
        }
        player.playerBoard().giveEffect(new Effect[]{Effect.FIELD});
        player.playerBoard().takeFigures(NORMAL_FIGURES);
        return false;
    }

    public boolean canPlaceOnFields(final Player player) {
        if (violatesRestriction()) {
            return false;
        }
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
