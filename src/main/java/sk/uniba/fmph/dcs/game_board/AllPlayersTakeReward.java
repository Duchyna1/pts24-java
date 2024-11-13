package sk.uniba.fmph.dcs.game_board;

import sk.uniba.fmph.dcs.stone_age.Effect;

public final class AllPlayersTakeReward implements EvaluateCivilisationCardImmediateEffect {

    public AllPlayersTakeReward() {
        //todo neviem zistit pocet hracov
    }

    @Override
    public boolean performEffect(final Player player, final Effect choice) {
        return false; //todo
    }
}
