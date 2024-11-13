package sk.uniba.fmph.dcs.game_phase_controller;

import org.junit.Test;
import sk.uniba.fmph.dcs.stone_age.*;

import java.util.ArrayList;
import java.util.Collection;

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

    @Test
    public void test_methodsReturnActionFailure() {
        int order = 1;
        int players = 1;
        PlayerOrder player = new PlayerOrder(order, players);

        Collection<Effect> resources = new ArrayList<>();

        GameEndState gameEndState = new GameEndState();

        assertEquals(gameEndState.doNotFeedThisTurn(player), ActionResult.FAILURE);
        assertEquals(gameEndState.feedTribe(player, resources), ActionResult.FAILURE);
        assertEquals(gameEndState.makeAction(player, Location.BUILDING_TILE1, resources, resources), ActionResult.FAILURE);
        assertEquals(gameEndState.skipAction(player, Location.BUILDING_TILE1), ActionResult.FAILURE);
        assertEquals(gameEndState.makeAllPlayersTakeARewardChoice(player, Effect.WOOD), ActionResult.FAILURE);
        assertEquals(gameEndState.placeFigures(player, Location.BUILDING_TILE1, 1), ActionResult.FAILURE);
        assertEquals(gameEndState.noMoreToolsThisThrow(player), ActionResult.FAILURE);
        assertEquals(gameEndState.useTools(player, 0), ActionResult.FAILURE);
    }
}
