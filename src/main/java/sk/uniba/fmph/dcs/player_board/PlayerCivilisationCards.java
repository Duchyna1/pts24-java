package sk.uniba.fmph.dcs.player_board;

import sk.uniba.fmph.dcs.stone_age.EndOfGameEffect;

import java.util.HashMap;
import java.util.Map;

public class PlayerCivilisationCards {

    private Map<EndOfGameEffect, Integer> endOfGameEffects;
    private final EndOfGameEffect[] greenBackGround = {EndOfGameEffect.MEDICINE, EndOfGameEffect.ART,
            EndOfGameEffect.WRITING, EndOfGameEffect.POTTERY, EndOfGameEffect.SUNDIAL, EndOfGameEffect.TRANSPORT,
            EndOfGameEffect.MUSIC, EndOfGameEffect.WEAVING};

    /**
     * Initialization.
     */
    public PlayerCivilisationCards() {
        endOfGameEffects = new HashMap<>();
        for (EndOfGameEffect effect : EndOfGameEffect.values()) {
            endOfGameEffects.put(effect, 0);
        }
    }

    /**
     * @param effects
     *            - list of effects to be added
     */
    public void addEndOfGameEffects(final EndOfGameEffect[] effects) {
        for (EndOfGameEffect effect : effects) {
            endOfGameEffects.put(effect, endOfGameEffects.get(effect) + 1);
        }
    }

    /**
     * @param buildings
     *            - number of buildings
     * @param tools
     *            - sum of values of multiple use tools (check rules)
     * @param fields
     *            - number on agriculture track
     * @param figures
     *            - number of player figures
     *
     * @return - score from green cards, tools, buildings, tools, agriculture track, figures as said it should be
     *         calculated in rules
     */
    public int calculateEndOfGameCivilisationCardsPoints(final int buildings, final int tools, final int fields,
            final int figures) {
        Map<EndOfGameEffect, Integer> endOfGameEffectsCopy = new HashMap<>();
        for (EndOfGameEffect effect : EndOfGameEffect.values()) {
            endOfGameEffectsCopy.put(effect, endOfGameEffects.get(effect));
        }

        int ans = 0;
        while (true) {
            int points = 0;
            for (EndOfGameEffect effect : greenBackGround) {
                if (endOfGameEffectsCopy.get(effect) > 0) {
                    points += 1;
                    endOfGameEffectsCopy.put(effect, endOfGameEffectsCopy.get(effect) - 1);
                }
            }
            ans += points * points;
            if (points == 0) {
                break;
            }
        }

        ans += Math.max(buildings, 0) * endOfGameEffectsCopy.get(EndOfGameEffect.BUILDER);
        ans += Math.max(tools, 0) * endOfGameEffectsCopy.get(EndOfGameEffect.TOOL_MAKER);
        ans += Math.max(fields, 0) * endOfGameEffectsCopy.get(EndOfGameEffect.FARMER);
        ans += Math.max(figures, 0) * endOfGameEffectsCopy.get(EndOfGameEffect.SHAMAN);

        return ans;
    }

    /**
     * @return inner state of this class
     */
    public String state() {
        StringBuilder ans = new StringBuilder();
        for (EndOfGameEffect egf : endOfGameEffects.keySet()) {
            if (endOfGameEffects.get(egf) > 0) {
                ans.append(egf).append(": ").append(endOfGameEffects.get(egf)).append("\n");
            }
        }
        return ans.toString();
    }
}
