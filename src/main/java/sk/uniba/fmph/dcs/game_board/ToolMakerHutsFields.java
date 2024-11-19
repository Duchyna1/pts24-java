package sk.uniba.fmph.dcs.game_board;

import org.json.JSONObject;
import sk.uniba.fmph.dcs.stone_age.Effect;
import sk.uniba.fmph.dcs.stone_age.PlayerOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ToolMakerHutsFields {
    private static final int MAX_FILLED_RESTRICTION = 2;
    private final List<PlayerOrder> toolMakerFigures;
    private final List<PlayerOrder> hutFigures;
    private final List<PlayerOrder> fieldsFigures;
    private final int restriction;
    private static final int FOUR_PLAYERS = 4;

    private boolean violatesRestriction() {
        int result = (toolMakerFigures.isEmpty() ? 1 : 0) + (hutFigures.isEmpty() ? 1 : 0)
                + (fieldsFigures.isEmpty() ? 1 : 0);
        if (restriction == FOUR_PLAYERS) {
            return false;
        } else {
            return result < MAX_FILLED_RESTRICTION;
        }
    }

    /**
     * @param playerCount
     *            needed for restrictions
     */
    public ToolMakerHutsFields(final int playerCount) {
        toolMakerFigures = new ArrayList<>();
        hutFigures = new ArrayList<>();
        fieldsFigures = new ArrayList<>();
        this.restriction = playerCount;
    }

    /**
     * resolve action place on toolmaker.
     *
     * @param player
     *            player to place
     *
     * @return true if action can be completed
     */
    public boolean placeOnToolMaker(final Player player) {
        if (!canPlaceOnToolMaker(player)) {
            return false;
        }
        toolMakerFigures.add(player.playerOrder());
        return true;
    }

    /**
     * resolve action toolmaker.
     *
     * @param player
     *            player to give resource to
     *
     * @return true if action can be completed
     */
    public boolean actionToolMaker(final Player player) {
        if (!toolMakerFigures.getFirst().equals(player.playerOrder())) {
            return false;
        }
        player.playerBoard().giveEffect(new Effect[] { Effect.TOOL });
        return true;
    }

    /**
     * @param player
     *            irrelevant
     *
     * @return true if figure can be placed on toolmaker
     */
    public boolean canPlaceOnToolMaker(final Player player) {
        if (violatesRestriction()) {
            return false;
        }
        return toolMakerFigures.isEmpty();
    }

    /**
     * resolve action place on hut.
     *
     * @param player
     *            player to place
     *
     * @return true if action can be completed
     */
    public boolean placeOnHut(final Player player) {
        if (!canPlaceOnHut(player)) {
            return false;
        }
        hutFigures.add(player.playerOrder());
        hutFigures.add(player.playerOrder());
        return true;
    }

    /**
     * resolve action hut.
     *
     * @param player
     *            player to place
     *
     * @return true if action can be completed
     */
    public boolean actionHut(final Player player) {
        if (!hutFigures.getFirst().equals(player.playerOrder())) {
            return false;
        }
        player.playerBoard().giveFigure();
        return false;
    }

    /**
     * @param player
     *            irrelevant
     *
     * @return true figure can be placed on hut
     */
    public boolean canPlaceOnHut(final Player player) {
        if (violatesRestriction()) {
            return false;
        }
        return hutFigures.isEmpty();
    }

    /**
     * resolve action place on fields.
     *
     * @param player
     *            player to place
     *
     * @return true if action can be completed
     */
    public boolean placeOnFields(final Player player) {
        if (!canPlaceOnFields(player)) {
            return false;
        }
        fieldsFigures.add(player.playerOrder());
        return true;
    }

    /**
     * resolve action fields.
     *
     * @param player
     *            player to give resource to
     *
     * @return true if action can be completed
     */
    public boolean actionFields(final Player player) {
        if (!fieldsFigures.getFirst().equals(player.playerOrder())) {
            return false;
        }
        player.playerBoard().giveEffect(new Effect[] { Effect.FIELD });
        return false;
    }

    /**
     * @param player
     *            irrelevant
     *
     * @return true if figure can be placed on fields
     */
    public boolean canPlaceOnFields(final Player player) {
        if (violatesRestriction()) {
            return false;
        }
        return fieldsFigures.isEmpty();
    }

    /**
     * @return true if all actions are resolved and next turn can start
     */
    public boolean newTurn() {
        return toolMakerFigures.isEmpty() && hutFigures.isEmpty() && fieldsFigures.isEmpty();
    }

    /**
     * @return state of figure placment
     */
    public String state() {
        Map<String, String> state = Map.of("toolMakerFigures", toolMakerFigures.toString(), "hutFigures",
                hutFigures.toString(), "fieldsFigures", fieldsFigures.toString());
        return new JSONObject(state).toString();
    }
}
