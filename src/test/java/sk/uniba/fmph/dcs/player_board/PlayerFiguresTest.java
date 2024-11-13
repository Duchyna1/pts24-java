package sk.uniba.fmph.dcs.player_board;

import org.junit.Test;

public class PlayerFiguresTest {
    @Test
    public void test_initialization() {
        PlayerFigures pf = new PlayerFigures();
        assert (pf.getTotalFigures() == 5);
        assert (pf.hasFigures(5));
    }

    @Test
    public void testAddNewFigure() {
        PlayerFigures pf = new PlayerFigures();
        for (int i = 0; i < 5; i++) {
            pf.addNewFigure();
        }
        pf.newTurn();
        assert (pf.getTotalFigures() == 10);
        assert (pf.hasFigures(10));
        assert !(pf.hasFigures(11));

        for (int i = 0; i < 10; i++) {
            pf.addNewFigure();
        }

        assert (pf.getTotalFigures() == 10);
        assert (pf.hasFigures(10));
        assert !(pf.hasFigures(11));

    }

    @Test
    public void testTakeFigures() {
        PlayerFigures pf = new PlayerFigures();
        for (int i = 0; i < 5; i++) {
            pf.addNewFigure();
        }
        pf.newTurn();
        assert (pf.hasFigures(10));
        assert (pf.takeFigures(5));
        assert !(pf.takeFigures(6));
        assert (pf.takeFigures(5));
        assert (pf.hasFigures(0));

        pf.newTurn();
        assert (pf.hasFigures(10));
        assert !(pf.takeFigures(-1));
        assert !(pf.takeFigures(-111111111));
    }

    @Test
    public void testState() {
        PlayerFigures pf = new PlayerFigures();
        String state = pf.state();
        String desiredOutcome = "Total figures: 5, available: 5";
        assert (state.equals(desiredOutcome));

        for (int i = 0; i < 5; i++) {
            pf.addNewFigure();
        }

        state = pf.state();
        desiredOutcome = "Total figures: 10, available: 5";
        assert (state.equals(desiredOutcome));
        pf.newTurn();
        state = pf.state();
        desiredOutcome = "Total figures: 10, available: 10";
        assert (state.equals(desiredOutcome));
    }
}
