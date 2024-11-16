package sk.uniba.fmph.dcs.game_phase_controller;

import org.junit.Test;
import sk.uniba.fmph.dcs.stone_age.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class FeedTribeStateTest {

    private static class InterfaceFeedTribeMock implements InterfaceFeedTribe {
        private final int figures;
        private final int food;
        private final boolean returnValue;

        InterfaceFeedTribeMock(int figures, int food, boolean ret) {
            this.figures = figures;
            this.food = food;
            returnValue = ret;
        }

        @Override
        public boolean feedTribeIfEnoughFood() {
            return food >= figures;
        }

        @Override
        public boolean feedTribe(Collection<Effect> resources) {
            return returnValue;
        }

        @Override
        public boolean doNotFeedThisTurn() {
            return returnValue;
        }

        @Override
        public boolean isTribeFed() {
            return returnValue;
        }
    }

    @Test
    public void test_tryToMakeAutomaticAction() {
        HashMap<PlayerOrder, InterfaceFeedTribe> map = new HashMap<>();
        PlayerOrder player1 = new PlayerOrder(1, 1);
        PlayerOrder player2 = new PlayerOrder(2, 2);
        PlayerOrder player3 = new PlayerOrder(3,3);

        map.put(player1, new InterfaceFeedTribeMock(2,1,false));
        map.put(player2, new InterfaceFeedTribeMock(2,1,true));
        map.put(player3, new InterfaceFeedTribeMock(1,2,false));

        FeedTribeState feedState = new FeedTribeState(map);
        assertEquals(feedState.tryToMakeAutomaticAction(player1), HasAction.WAITING_FOR_PLAYER_ACTION);
        assertEquals(feedState.tryToMakeAutomaticAction(player2), HasAction.NO_ACTION_POSSIBLE);
        assertEquals(feedState.tryToMakeAutomaticAction(player3), HasAction.AUTOMATIC_ACTION_DONE);
    }

    @Test
    public void test_feedTribe() {
        HashMap<PlayerOrder, InterfaceFeedTribe> map = new HashMap<>();
        PlayerOrder player1 = new PlayerOrder(1, 1);
        PlayerOrder player2 = new PlayerOrder(2, 2);

        map.put(player1, new InterfaceFeedTribeMock(2,2, false));
        map.put(player2, new InterfaceFeedTribeMock(2,2,true));
        ArrayList<Effect> resources = new ArrayList<>();

        FeedTribeState feedState = new FeedTribeState(map);

        assertEquals(feedState.feedTribe(player1, resources), ActionResult.FAILURE);
        assertEquals(feedState.feedTribe(player2, resources), ActionResult.ACTION_DONE);
    }

    @Test
    public void test_doNotFeedThisTurn() {
        HashMap<PlayerOrder, InterfaceFeedTribe> map = new HashMap<>();
        PlayerOrder player1 = new PlayerOrder(1, 1);
        PlayerOrder player2 = new PlayerOrder(2, 2);
        FeedTribeState feedState = new FeedTribeState(map);

        map.put(player1, new InterfaceFeedTribeMock(2,2,false));
        map.put(player2, new InterfaceFeedTribeMock(2,2, true));

        assertEquals(feedState.doNotFeedThisTurn(player1), ActionResult.FAILURE);
        assertEquals(feedState.doNotFeedThisTurn(player2), ActionResult.ACTION_DONE);
    }
}
