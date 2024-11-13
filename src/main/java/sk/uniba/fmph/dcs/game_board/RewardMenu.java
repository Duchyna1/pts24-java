package sk.uniba.fmph.dcs.game_board;

import sk.uniba.fmph.dcs.stone_age.Effect;

import java.util.ArrayList;
import java.util.List;

public final class RewardMenu {
    private final List<Effect> menu;
    private final List<Player> players;

    public RewardMenu(final List<Effect> menu, final List<Player> players) {
        this.menu = new ArrayList<>();
        this.menu.addAll(menu);
        this.players = new ArrayList<>();
        this.players.addAll(players);
    }

    //todo asi metoda na odoberanie nie som si isty
    public void initiate(final List<Effect> menu) {
        this.menu.addAll(menu);
    }

    public String state() {
        return "Currently availible: " + menu.toString() + "\n"
                + "Players remaining: " + players.toString() + "\n";
    }
}
