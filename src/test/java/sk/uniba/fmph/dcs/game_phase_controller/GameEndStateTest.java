package sk.uniba.fmph.dcs.game_phase_controller;

import org.junit.Test;
import sk.uniba.fmph.dcs.stone_age.HasAction;
import sk.uniba.fmph.dcs.stone_age.PlayerOrder;

import static org.junit.Assert.assertEquals;

public class GameEndStateTest {
    @Test
    public void test_tryToMakeAutomaticAction() {
        GameEndState gameEndState = new GameEndState();
        int order = 1;
        int players = 1;
        PlayerOrder player = new PlayerOrder(order, players);
        assertEquals(gameEndState.tryToMakeAutomaticAction(player), HasAction.WAITING_FOR_PLAYER_ACTION);
    }
}
