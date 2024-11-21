package sk.uniba.fmph.dcs.game_phase_controller;

import org.junit.Before;
import org.junit.Test;
import sk.uniba.fmph.dcs.stone_age.*;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MakeActionStateTest {

    private MakeActionState makeActionState;
    private Map<Location, InterfaceFigureLocation> places;
    private PlayerOrder player;

    @Before
    public void setUp() {
        places = new HashMap<>();
        player = new PlayerOrder(1, 4);
        makeActionState = new MakeActionState(places);
    }

    @Test
    public void testMakeActionSuccess() {
        InterfaceFigureLocation figureLocationMock = mock(InterfaceFigureLocation.class);
        when(figureLocationMock.makeAction(any(), any(), any())).thenReturn(ActionResult.ACTION_DONE);

        places.put(Location.TOOL_MAKER, figureLocationMock);

        ActionResult result = makeActionState.makeAction(player, Location.TOOL_MAKER, List.of(Effect.WOOD),
                List.of(Effect.STONE));

        assertEquals(ActionResult.ACTION_DONE, result);
        assertNull(places.get(Location.TOOL_MAKER));
    }

    @Test
    public void testMakeActionFailureDueToNoLocation() {
        ActionResult result = makeActionState.makeAction(player, Location.HUT, Arrays.asList(Effect.WOOD),
                Arrays.asList(Effect.STONE));

        assertEquals(ActionResult.FAILURE, result);
    }

    @Test
    public void testMakeActionFailureDueToInvalidLocation() {
        places.put(Location.HUT, null);

        ActionResult result = makeActionState.makeAction(player, Location.HUT, Arrays.asList(Effect.WOOD),
                Arrays.asList(Effect.STONE));

        assertEquals(ActionResult.FAILURE, result);
    }

    @Test
    public void testTryToMakeAutomaticActionNoActionPossible() {
        places.put(Location.TOOL_MAKER, mock(InterfaceFigureLocation.class));
        when(places.get(Location.TOOL_MAKER).tryToMakeAction(player)).thenReturn(HasAction.NO_ACTION_POSSIBLE);

        HasAction result = makeActionState.tryToMakeAutomaticAction(player);

        assertEquals(HasAction.NO_ACTION_POSSIBLE, result);
    }

    @Test
    public void testTryToMakeAutomaticActionAutomaticActionDone() {
        InterfaceFigureLocation figureLocationMock = mock(InterfaceFigureLocation.class);
        when(figureLocationMock.tryToMakeAction(player)).thenReturn(HasAction.WAITING_FOR_PLAYER_ACTION);
        when(figureLocationMock.makeAction(player, null, null)).thenReturn(ActionResult.ACTION_DONE);

        places.put(Location.TOOL_MAKER, figureLocationMock);

        HasAction result = makeActionState.tryToMakeAutomaticAction(player);

        assertEquals(HasAction.AUTOMATIC_ACTION_DONE, result);
        assertNull(places.get(Location.TOOL_MAKER));
    }

    @Test
    public void testTryToMakeAutomaticActionWaitingForPlayerAction() {
        InterfaceFigureLocation figureLocationMock1 = mock(InterfaceFigureLocation.class);
        InterfaceFigureLocation figureLocationMock2 = mock(InterfaceFigureLocation.class);

        when(figureLocationMock1.tryToMakeAction(player)).thenReturn(HasAction.WAITING_FOR_PLAYER_ACTION);
        when(figureLocationMock2.tryToMakeAction(player)).thenReturn(HasAction.WAITING_FOR_PLAYER_ACTION);

        places.put(Location.TOOL_MAKER, figureLocationMock1);
        places.put(Location.HUT, figureLocationMock2);

        HasAction result = makeActionState.tryToMakeAutomaticAction(player);

        assertEquals(HasAction.WAITING_FOR_PLAYER_ACTION, result);
    }
}
