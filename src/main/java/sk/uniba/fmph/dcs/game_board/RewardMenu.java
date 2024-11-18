package sk.uniba.fmph.dcs.game_board;

import sk.uniba.fmph.dcs.stone_age.Effect;
import sk.uniba.fmph.dcs.stone_age.HasAction;
import sk.uniba.fmph.dcs.stone_age.InterfaceTakeReward;
import sk.uniba.fmph.dcs.stone_age.PlayerOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RewardMenu implements InterfaceTakeReward {
    private final List<Effect> menu;
    private final List<Player> players;
    private List<PlayerOrder> playersLeft;

    /**
     * creates class.
     *
     * @param menu
     *            resources availible to take
     * @param players
     *            list of all players
     */
    public RewardMenu(final List<Effect> menu, final List<Player> players) {
        this.menu = new ArrayList<>();
        this.menu.addAll(menu);
        this.players = new ArrayList<>();
        this.players.addAll(players);
        playersLeft = players.stream().map(Player::playerOrder).collect(Collectors.toList());
    }

    /**
     * I mean who does not know what this does maven?.
     *
     * @return registered players count
     */
    public int getPlayersCount() {
        return players.size();
    }

    /**
     * don't really know why is this, I guess so that I can restart menu.
     *
     * @param menu
     *            new menu that replaces the old one
     */
    public void initiate(final List<Effect> menu) {
        menu.clear();
        this.menu.addAll(menu);
        playersLeft = players.stream().map(Player::playerOrder).collect(Collectors.toList());
    }

    /**
     * @return items left to choose and players choosing.
     */
    public String state() {
        return "Currently availible: " + menu.toString() + "\n" + "Players remaining: " + players.toString() + "\n";
    }

    private Player getPlayerFromPlayerOrder(final PlayerOrder player) {
        Player p = null;
        for (var i : players) {
            if (i.playerOrder().equals(player)) {
                p = i;
            }
        }
        return p;
    }

    /**
     * removes reward from menu and gives it to player.
     *
     * @param player
     *            gives reward to player with this order
     * @param reward
     *            reward from menu
     *
     * @return `true` if found player with order `player` and reward is in menu, `false` otherwise
     */
    @Override
    public boolean takeReward(final PlayerOrder player, final Effect reward) {
        if (!menu.contains(reward)) {
            return false;
        }
        Player p = getPlayerFromPlayerOrder(player);
        if (p == null) {
            return false;
        }
        if (!playersLeft.contains(player)) {
            return false;
        }
        playersLeft.remove(player);
        menu.remove(reward);
        p.playerBoard().giveEffect(new Effect[] {reward});
        return true;
    }

    /**
     * don't really know either.
     *
     * @param player
     *            can this player take something
     *
     * @return NO_ACTION_POSSIBLE when either player is not allowed to choose or menu is empty
     */
    @Override
    public HasAction tryMakeAction(final PlayerOrder player) {
        if (menu.isEmpty()) {
            return HasAction.NO_ACTION_POSSIBLE;
        }
        if (!playersLeft.contains(player)) {
            return HasAction.NO_ACTION_POSSIBLE;
        }
        if (menu.size() == 1) {
            Player p = getPlayerFromPlayerOrder(player);
            p.playerBoard().giveEffect(menu.toArray(new Effect[0]));
            playersLeft.remove(player);
            return HasAction.AUTOMATIC_ACTION_DONE;
        }
        return HasAction.WAITING_FOR_PLAYER_ACTION;
    }
}
