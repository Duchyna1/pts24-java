package sk.uniba.fmph.dcs.game_board;

import sk.uniba.fmph.dcs.stone_age.Effect;

import java.util.ArrayList;
import java.util.List;

public class RewardMenu {
    final List<Effect> menu;
    final List<Player> players;

    public RewardMenu(final List<Effect> menu, final List<Player> players) {
        this.menu = new ArrayList<>();
        this.menu.addAll(menu);
        this.players = new ArrayList<>();
        this.players.addAll(players);
    }

    public void initiate(List<Effect> menu) {
        this.menu.addAll(menu);
    }

    public String state() {
        return "Currently availible: " + menu.toString() + "\n" +
                "Players remaining: " + players.toString() + "\n";
    }
}
