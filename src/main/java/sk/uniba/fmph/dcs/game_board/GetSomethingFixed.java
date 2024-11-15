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
     * give all resources to player.
     *
     * @param player player to give choice to
     * @param choice ignores
     * @return true - it is always possible to give resources to player
     */
    @Override
    public boolean performEffect(final Player player, final Effect choice) {
        player.playerBoard().giveEffect(effects.toArray(new Effect[0]));
        effects.clear();
        return true;
    }
}
