package sk.uniba.fmph.dcs.game_board;

import sk.uniba.fmph.dcs.stone_age.Effect;

import java.util.ArrayList;
import java.util.List;

public class GetSomethingFixed implements EvaluateCivilisationCardImmediateEffect {
    private final List<Effect> effects;
    /**
     * @param effect list of effects that can be chosen.
     */
    public GetSomethingFixed(final List<Effect> effect) {
        effects = new ArrayList<>();
        effects.addAll(effect);
    }

    /**
     * take one resource and give to player.
     *
     * @param player player to give choice to
     * @param choice resource chosen
     * @return false if resource is not available to choose
     */
    @Override
    public boolean performEffect(final Player player, final Effect choice) {
        if (!effects.contains(choice)) {
            return false;
        }
        effects.remove(choice);
        player.playerBoard().giveEffect(new Effect[]{choice});
        return true;
    }
}
