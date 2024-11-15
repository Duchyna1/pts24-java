package sk.uniba.fmph.dcs.game_phase_controller;

import org.junit.Test;
import sk.uniba.fmph.dcs.stone_age.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class FeedTribeStateTest {

    class InterfaceFeedTribeMock implements InterfaceFeedTribe {
        private boolean returnValue;

        InterfaceFeedTribeMock(boolean returnValue) {
            this.returnValue = returnValue;
        }

        @Override
        public boolean feedTribeIfEnoughFood() {
            return returnValue;
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

        map.put(player1, new InterfaceFeedTribeMock(false));
        map.put(player2, new InterfaceFeedTribeMock(true));

        FeedTribeState feedState = new FeedTribeState(map);
        assertEquals(feedState.tryToMakeAutomaticAction(player1), HasAction.WAITING_FOR_PLAYER_ACTION);
        assertEquals(feedState.tryToMakeAutomaticAction(player2), HasAction.NO_ACTION_POSSIBLE);

    }

    @Test
    public void test_feedTribe() {
        HashMap<PlayerOrder, InterfaceFeedTribe> map = new HashMap<>();
        PlayerOrder player1 = new PlayerOrder(1, 1);
        PlayerOrder player2 = new PlayerOrder(2, 2);

        map.put(player1, new InterfaceFeedTribeMock(false));
        map.put(player2, new InterfaceFeedTribeMock(true));
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

        map.put(player1, new InterfaceFeedTribeMock(false));
        map.put(player2, new InterfaceFeedTribeMock(true));

        assertEquals(feedState.doNotFeedThisTurn(player1), ActionResult.FAILURE);
        assertEquals(feedState.doNotFeedThisTurn(player2), ActionResult.ACTION_DONE);
    }
}
