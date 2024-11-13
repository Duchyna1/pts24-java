package sk.uniba.fmph.dcs.player_board;

import sk.uniba.fmph.dcs.stone_age.Effect;
import sk.uniba.fmph.dcs.stone_age.EndOfGameEffect;

import java.util.HashMap;
import java.util.Map;

public class PlayerCivilisationCards {

    private Map<EndOfGameEffect, Integer> endOfGameEffects;
    private EndOfGameEffect[] greenBackGround = {EndOfGameEffect.MEDICINE, EndOfGameEffect.ART,
            EndOfGameEffect.WRITING, EndOfGameEffect.POTTERY, EndOfGameEffect.SUNDIAL,
            EndOfGameEffect.TRANSPORT, EndOfGameEffect.MUSIC, EndOfGameEffect.WEAVING};

    public PlayerCivilisationCards() {
        endOfGameEffects = new HashMap<>();
        for (EndOfGameEffect effect : EndOfGameEffect.values()) {
            endOfGameEffects.put(effect, 0);
        }
    }

    public void addEndOfGameEffects(EndOfGameEffect[] effects) {
        for (EndOfGameEffect effect : effects) {
            endOfGameEffects.put(effect, endOfGameEffects.get(effect) + 1);
        }
    }

    public int calculateEndOfGameCivilisationCardsPoints(int buildings, int tools, int fields, int figures) {
        int ans = 0;
        while (true) {
            int points = 0;
            for (EndOfGameEffect effect : greenBackGround) {
                if (endOfGameEffects.get(effect) > 0) {
                    points += 1;
                    endOfGameEffects.put(effect, endOfGameEffects.get(effect) - 1);
                }
            }
            ans += points * points;
            if (points == 0) {
                break;
            }
        }

        ans += buildings * endOfGameEffects.get(EndOfGameEffect.BUILDER);
        ans += tools * endOfGameEffects.get(EndOfGameEffect.TOOL_MAKER);
        ans += fields * endOfGameEffects.get(EndOfGameEffect.FARMER);
        ans += figures * endOfGameEffects.get(EndOfGameEffect.SHAMAN);

        return ans;
    }

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
