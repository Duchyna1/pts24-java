package sk.uniba.fmph.dcs.game_board;

import sk.uniba.fmph.dcs.stone_age.Effect;

import java.util.Arrays;

public final class GetSomethingThrow implements EvaluateCivilisationCardImmediateEffect {
    private final Effect resource;
    private final CurrentThrow currentThrow;

    public GetSomethingThrow(final Effect resource) {
        this.resource = resource;

        int something = 0; //todo
        currentThrow = new CurrentThrow(resource, something);
    }

    @Override
    public boolean performEffect(final Player player, final Effect choice) {
        if (choice != this.resource) {
            return false;
        }
        currentThrow.initiate(player, choice, 2);
        int pocet = 0; //todo
        Effect[] res = new Effect[pocet];
        Arrays.fill(res, choice);
        player.playerBoard().giveEffect(res);
        return true;
    }
}
