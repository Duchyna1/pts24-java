package sk.uniba.fmph.dcs.player_board;

import org.junit.Test;

public class PlayerToolsTest {
    @Test
    public void test_noTools() {
        PlayerTools tools = new PlayerTools();
        assert tools.hasSufficientTools(0);
        assert !tools.hasSufficientTools(1);
    }

    @Test
    public void test_addingTools() {
        PlayerTools tools = new PlayerTools();
        assert tools.hasSufficientTools(0);
        assert !tools.hasSufficientTools(1);

        tools.addTool();
        assert tools.hasSufficientTools(1);
        assert !tools.hasSufficientTools(2);

        tools.addTool();
        assert tools.hasSufficientTools(2);
        assert !tools.hasSufficientTools(3);
    }

    @Test
    public void test_maxTools() {
        PlayerTools tools = new PlayerTools();
        for (int i = 0; i < 12; i++) {
            tools.addTool();
        }

        assert tools.hasSufficientTools(12);
        assert !tools.hasSufficientTools(13);

        tools.addTool();
        assert tools.hasSufficientTools(12);
        assert !tools.hasSufficientTools(13);
    }

    @Test
    public void test_newTurn() {
        PlayerTools tools = new PlayerTools();
        for (int i = 0; i < 4; i++) {
            tools.addTool();
        }
        assert tools.hasSufficientTools(2);
        assert tools.hasSufficientTools(1);

        assert tools.useTool(0) == 2;
        assert tools.useTool(1) == 1;

        assert !tools.hasSufficientTools(2);
        assert tools.hasSufficientTools(1);

        assert tools.useTool(0) == null;

        tools.newTurn();

        assert tools.hasSufficientTools(2);
        assert tools.hasSufficientTools(1);

        assert tools.useTool(0) == 2;
        assert tools.useTool(1) == 1;

        assert !tools.hasSufficientTools(2);
        assert tools.hasSufficientTools(1);
    }

    @Test
    public void test_singleUseTool() {
        PlayerTools tools = new PlayerTools();
        assert !tools.hasSufficientTools(1);

        tools.addSingleUseTool(4);
        assert tools.hasSufficientTools(4);
        assert tools.useTool(0) == 0;
        assert tools.useTool(1) == 0;
        assert tools.useTool(2) == 0;
        assert tools.useTool(3) == 4;
        assert tools.useTool(3) == null;
    }

    @Test
    public void test_maxSingleUseTool() {
        PlayerTools tools = new PlayerTools();
        tools.addSingleUseTool(5);
        assert !tools.hasSufficientTools(5);
        tools.addSingleUseTool(4);
        assert tools.hasSufficientTools(4);
        tools.addSingleUseTool(4);
        assert tools.hasSufficientTools(8);
        tools.addSingleUseTool(4);
        assert tools.hasSufficientTools(12);
        tools.addSingleUseTool(4);
        assert !tools.hasSufficientTools(13);
    }
}
