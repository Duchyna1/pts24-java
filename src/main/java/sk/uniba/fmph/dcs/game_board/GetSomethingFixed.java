package sk.uniba.fmph.dcs.game_board;

import sk.uniba.fmph.dcs.stone_age.Effect;

import java.util.ArrayList;
import java.util.List;

public class GetSomethingFixed implements EvaluateCivilisationCardImmediateEffect {
    final List<Effect> effects;

    public GetSomethingFixed(final List<Effect> effect) {
        effects = new ArrayList<>();
        effects.addAll(effect);
    }

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
