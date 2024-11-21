package sk.uniba.fmph.dcs.game_board;

import org.junit.Test;
import sk.uniba.fmph.dcs.player_board.PlayerBoard;
import sk.uniba.fmph.dcs.player_board.PlayerBoardGameBoardFacade;
import sk.uniba.fmph.dcs.stone_age.*;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class BuildingTileTest {

    @Test
    public void test_placeFigures() {
        var resources = new ArrayList<Effect>();
        resources.add(Effect.WOOD);
        var t = new BuildingTile(new SimpleBuilding(resources));
        Player player1 = new Player(new PlayerOrder(1, 1), null);
        Player player2 = new Player(new PlayerOrder(2, 2), null);
        var ret = t.placeFigures(player1, 2);
        assertEquals(ret, false);
        ret = t.placeFigures(player1, 1);
        assertEquals(ret, true);
        ret = t.placeFigures(player1, 1);
        assertEquals(ret, false);
        ret = t.placeFigures(player2, 1);
        assertEquals(ret, true);
    }

    @Test
    public void test_makeAction() {
        var resources = new ArrayList<Effect>();
        resources.add(Effect.WOOD);
        var t = new BuildingTile(new SimpleBuilding(resources));
        Player player1 = new Player(new PlayerOrder(1, 1), new PlayerBoardGameBoardFacade(new PlayerBoard()));
        Player player2 = new Player(new PlayerOrder(2, 2), new PlayerBoardGameBoardFacade(new PlayerBoard()));
        t.placeFigures(player1, 1);
        var ret = t.makeAction(player2, new Effect[] { Effect.WOOD }, new Effect[] {});
        assertEquals(ret, ActionResult.FAILURE);
        ret = t.makeAction(player1, new Effect[] { Effect.WOOD }, new Effect[] {});
        assertEquals(ret, ActionResult.ACTION_DONE);
        ret = t.makeAction(player1, new Effect[] {}, new Effect[] {});
        assertEquals(ret, ActionResult.FAILURE);
    }
}
