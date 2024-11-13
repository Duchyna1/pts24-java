package sk.uniba.fmph.dcs.game_board;

import sk.uniba.fmph.dcs.stone_age.Effect;

import java.util.ArrayList;
import java.util.List;

public final class AllPlayersTakeReward implements EvaluateCivilisationCardImmediateEffect {

    private final RewardMenu menu;
    private static final int WOODID = 1;
    private static final int CLAYID = 2;
    private static final int STONEID = 3;
    private static final int GOLDID = 4;
    private static final int TOOLID = 5;

    public AllPlayersTakeReward(final RewardMenu menu) {
        this.menu = menu;
        int n = menu.getPlayersCount();
        int[] t = new Throw().hod(n);
        List<Effect> m = new ArrayList<>();
        for (var i : t) {
            if (i == WOODID) {
                m.add(Effect.WOOD);
            } else if (i == CLAYID) {
                m.add(Effect.CLAY);
            } else if (i == STONEID) {
                m.add(Effect.STONE);
            } else if (i == GOLDID) {
                m.add(Effect.GOLD);
            } else if (i == TOOLID) {
                m.add(Effect.TOOL);
            } else {
                m.add(Effect.FIELD);
            }
        }
        menu.initiate(m);
    }

    @Override
    public boolean performEffect(final Player player, final Effect choice) {
        return menu.takeReward(player.playerOrder(), choice);
    }
}
