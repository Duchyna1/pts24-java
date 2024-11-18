package sk.uniba.fmph.dcs.game_phase_controller;

import org.junit.Test;

import sk.uniba.fmph.dcs.stone_age.ActionResult;
import sk.uniba.fmph.dcs.stone_age.InterfaceFigureLocation;
import sk.uniba.fmph.dcs.stone_age.Location;
import sk.uniba.fmph.dcs.stone_age.PlayerOrder;
import sk.uniba.fmph.dcs.stone_age.Effect;
import sk.uniba.fmph.dcs.stone_age.HasAction;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static sk.uniba.fmph.dcs.stone_age.Location.FIELD;
import static sk.uniba.fmph.dcs.stone_age.Location.FOREST;

public class PlaceFigureStateTest {

    private static class InterfaceFigureLocationMock implements InterfaceFigureLocation {
        @Override
        public boolean placeFigures(PlayerOrder player, int figureCount) {
            return figureCount == 1;
        }

        @Override
        public HasAction tryToPlaceFigures(PlayerOrder player, int count) {
            HasAction result = switch (player.getPlayers()) {
                case 1 -> HasAction.AUTOMATIC_ACTION_DONE;
                case 2 -> HasAction.WAITING_FOR_PLAYER_ACTION;
                default -> HasAction.NO_ACTION_POSSIBLE;
            };
            return result;
        }

        @Override
        public ActionResult makeAction(PlayerOrder player, Collection<Effect> inputResources, Collection<Effect> outputResources) {
            return null;
        }

        @Override
        public boolean skipAction(PlayerOrder player) {
            return false;
        }

        @Override
        public HasAction tryToMakeAction(PlayerOrder player) {
            return null;
        }

        @Override
        public boolean newTurn() {
            return false;
        }
    }

    @Test
    public void test_placeFigures() {
        Map<Location, InterfaceFigureLocation> places = new HashMap<>();
        places.put(FIELD, new InterfaceFigureLocationMock());
        PlaceFigureState placeFigureState = new PlaceFigureState(places);
        assertEquals(placeFigureState.placeFigures(new PlayerOrder(1, 1), FIELD, 1), ActionResult.ACTION_DONE);
        assertEquals(placeFigureState.placeFigures(new PlayerOrder(1, 1), FOREST, 1), ActionResult.FAILURE);
        assertEquals(placeFigureState.placeFigures(new PlayerOrder(1, 1), FIELD, 2), ActionResult.FAILURE);
    }

    @Test
    public void test_tryToMakeAutomaticAction() {
        Map<Location, InterfaceFigureLocation> places = new HashMap<>();
        places.put(Location.FIELD, new InterfaceFigureLocationMock());
        places.put(Location.HUT, new InterfaceFigureLocationMock());
        places.put(FOREST, new InterfaceFigureLocationMock());

        PlaceFigureState placeFigureState = new PlaceFigureState(places);

        PlayerOrder failPlayer = new PlayerOrder(1, 3);
        PlayerOrder actionDonePLayer = new PlayerOrder(1, 1);
        PlayerOrder waitingPlayer = new PlayerOrder(1, 2);

        assertEquals(placeFigureState.tryToMakeAutomaticAction(failPlayer), HasAction.NO_ACTION_POSSIBLE);
        assertEquals(placeFigureState.tryToMakeAutomaticAction(actionDonePLayer), HasAction.AUTOMATIC_ACTION_DONE);
        assertEquals(placeFigureState.tryToMakeAutomaticAction(waitingPlayer), HasAction.WAITING_FOR_PLAYER_ACTION);
    }

}


