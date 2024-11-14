package sk.uniba.fmph.dcs.game_board;

import sk.uniba.fmph.dcs.stone_age.Effect;

public class GetChoice implements EvaluateCivilisationCardImmediateEffect {
    private int numberOfResources;

    /**
     * @param numberOfResources number of resources available to choose.
     */
    public GetChoice(final int numberOfResources) {
        this.numberOfResources = numberOfResources;
    }

    /**
     * gives one resource of any type.
     *
     * @param player player to give resource to
     * @param choice resource wanted
     * @return true if player can take resource, false otherwise
     */
    @Override
    public boolean performEffect(final Player player, final Effect choice) {
        if (!choice.isResource()) {
            return false;
        }
        if (numberOfResources == 0) {
            return false;
        }
        numberOfResources--;
        player.playerBoard().giveEffect(new Effect[]{choice});
        return true;
    }
}
