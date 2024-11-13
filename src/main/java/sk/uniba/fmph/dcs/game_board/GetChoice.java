package sk.uniba.fmph.dcs.game_board;

import sk.uniba.fmph.dcs.stone_age.Effect;

public final class GetChoice implements EvaluateCivilisationCardImmediateEffect {
    private int numberOfResources;

    public GetChoice(final int numberOfResources) {
        this.numberOfResources = numberOfResources;
    }

    @Override
    public boolean performEffect(final Player player, final Effect choice) {
        if (numberOfResources == 0) {
            return false;
        }
        numberOfResources--;
        player.playerBoard().giveEffect(new Effect[]{choice});
        return true;
    }
}
