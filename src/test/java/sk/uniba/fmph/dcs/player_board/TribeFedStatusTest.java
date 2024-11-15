package sk.uniba.fmph.dcs.player_board;

import org.junit.Test;
import sk.uniba.fmph.dcs.stone_age.Effect;

import java.util.Arrays;

public class TribeFedStatusTest {
    @Test
    public void testAddingFields() {
        TribeFedStatus tfs = new TribeFedStatus(null, null);
        String state = tfs.state();
        for (int i = 0; i < 10; i++) {
            tfs.addField();
            state = tfs.state();
            assert (state.equals("Fed: false, # of fields: " + (i + 1)));
        }
        for (int i = 0; i < 10; i++) {
            tfs.addField();
        }
        state = tfs.state();
        assert (state.equals("Fed: false, # of fields: 10"));
    }

    @Test
    public void testNewTurn() {
        PlayerResourcesAndFood prf = new PlayerResourcesAndFood();
        TribeFedStatus trf = new TribeFedStatus(prf, new PlayerFigures());
        for (int i = 0; i < 10; i++) {
            trf.newTurn();
        }
        assert !(prf.hasResources(new Effect[]{Effect.FOOD}));

        trf.addField();
        for (int i = 0; i < 10; i++) {
            trf.newTurn();
        }
        Effect[] ef1 = new Effect[10];
        Arrays.fill(ef1, Effect.FOOD);
        assert (prf.hasResources(ef1));

        Effect[] ef2 = new Effect[11];
        Arrays.fill(ef2, Effect.FOOD);
        assert !(prf.hasResources(ef2));

        for (int i = 0; i < 100; i++) {
            trf.addField();
        }

        for (int i = 0; i < 10; i++) {
            trf.newTurn();
        }

        Effect[] ef3 = new Effect[110];
        Arrays.fill(ef3, Effect.FOOD);
        assert (prf.hasResources(ef3));

        Effect[] ef4 = new Effect[111];
        Arrays.fill(ef4, Effect.FOOD);
        assert !(prf.hasResources(ef4));
    }

    @Test
    public void testFeedTribeIfEnoughFood() {
        PlayerResourcesAndFood prf = new PlayerResourcesAndFood();
        PlayerFigures figures = new PlayerFigures();
        TribeFedStatus trf = new TribeFedStatus(prf, figures);

        for (int i = 0; i < 5; i++) {
            trf.addField();
        }
    }
}
