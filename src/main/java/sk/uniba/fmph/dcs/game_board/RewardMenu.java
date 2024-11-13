package sk.uniba.fmph.dcs.game_board;

import sk.uniba.fmph.dcs.stone_age.Effect;
import sk.uniba.fmph.dcs.stone_age.HasAction;
import sk.uniba.fmph.dcs.stone_age.InterfaceTakeReward;
import sk.uniba.fmph.dcs.stone_age.PlayerOrder;

import java.util.ArrayList;
import java.util.List;

public final class RewardMenu implements InterfaceTakeReward {
    private final List<Effect> menu;
    private final List<Player> players;

    public RewardMenu(final List<Effect> menu, final List<Player> players) {
        this.menu = new ArrayList<>();
        this.menu.addAll(menu);
        this.players = new ArrayList<>();
        this.players.addAll(players);
    }

    public int getPlayersCount() {
        return players.size();
    }

    public void initiate(final List<Effect> menu) {
        this.menu.addAll(menu);
    }

    public String state() {
        return "Currently availible: " + menu.toString() + "\n"
                + "Players remaining: " + players.toString() + "\n";
    }

    @Override
    public boolean takeReward(final PlayerOrder player, final Effect reward) {
        if (!menu.contains(reward)) {
            return false;
        }
        //todo Player p = ziskaj Player z PlayerOrder
        //if(!players.contains(p)) {
        //    return false;
        //}
        menu.remove(reward);
        //todo p.getPlayerBoard().addEffect(reward);
        //todo return true;
        return false;
    }

    @Override
    public HasAction tryMakeAction(final PlayerOrder player) {
        if (menu.isEmpty()) {
            return HasAction.NO_ACTION_POSSIBLE;
        }
        return HasAction.WAITING_FOR_PLAYER_ACTION;
    }
}
