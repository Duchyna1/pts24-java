package sk.uniba.fmph.dcs.game_board;

import sk.uniba.fmph.dcs.stone_age.Effect;

import java.util.stream.IntStream;

public final class GetSomethingThrow implements EvaluateCivilisationCardImmediateEffect {
    private final Effect resource;
    private int numberOfResources;

    public GetSomethingThrow(final Effect resource) {
        this.resource = resource;
        numberOfResources = IntStream.of(new Throw().hod(2)).sum();
    }

    @Override
    public boolean performEffect(final Player player, final Effect choice) {
        if (numberOfResources == 0) {
            return false;
        }
        if (choice != resource) {
            return false;
        }
        numberOfResources--;
        player.playerBoard().giveEffect(new Effect[]{choice});
        return true;
    }
}
