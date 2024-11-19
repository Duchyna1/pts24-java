package sk.uniba.fmph.dcs.game_phase_controller;

import org.junit.Test;
import sk.uniba.fmph.dcs.stone_age.*;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class WaitingForToolUseTest {
    private static class InterfaceToolUseMock implements InterfaceToolUse {

        private final boolean finishedUsingTools;
        private final boolean canUseTools;

        InterfaceToolUseMock(boolean canUseTools, boolean finishedUsingTools) {
            this.canUseTools = canUseTools;
            this.finishedUsingTools = finishedUsingTools;
        }

        @Override
        public boolean useTool(int idx) {
            return canUseTools;
        }

        @Override
        public boolean canUseTools() {
            return canUseTools;
        }

        @Override
        public boolean finishUsingTools() {
            return finishedUsingTools;
        }

    }

    @Test
    public void test_tryToMakeAutomaticAction() {
        HashMap<PlayerOrder, InterfaceToolUse> map = new HashMap<>();
        PlayerOrder player1 = new PlayerOrder(1, 1);
        PlayerOrder player2 = new PlayerOrder(2, 2);
        PlayerOrder player3 = new PlayerOrder(3, 3);

        map.put(player1, new WaitingForToolUseTest.InterfaceToolUseMock(false, false));
        map.put(player2, new WaitingForToolUseTest.InterfaceToolUseMock(false, true));
        map.put(player3, new WaitingForToolUseTest.InterfaceToolUseMock(true, true));
        WaitingForToolUse toolUse = new WaitingForToolUse(map);
        assertEquals(toolUse.tryToMakeAutomaticAction(player1), HasAction.NO_ACTION_POSSIBLE);
        assertEquals(toolUse.tryToMakeAutomaticAction(player2), HasAction.NO_ACTION_POSSIBLE);
        assertEquals(toolUse.tryToMakeAutomaticAction(player3), HasAction.AUTOMATIC_ACTION_DONE);
    }

    @Test
    public void test_useTool() {
        HashMap<PlayerOrder, InterfaceToolUse> map = new HashMap<>();
        PlayerOrder player1 = new PlayerOrder(1, 1);
        PlayerOrder player2 = new PlayerOrder(2, 2);

        map.put(player1, new InterfaceToolUseMock(false, false));
        map.put(player2, new InterfaceToolUseMock(true, false));

        WaitingForToolUse toolUse = new WaitingForToolUse(map);

        assertEquals(toolUse.useTools(player1, 1), ActionResult.FAILURE);
        assertEquals(toolUse.useTools(player2, 1), ActionResult.ACTION_DONE);
    }
}
