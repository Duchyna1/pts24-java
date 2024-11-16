package sk.uniba.fmph.dcs.player_board;

import org.junit.Test;
import sk.uniba.fmph.dcs.stone_age.Effect;

import java.util.Arrays;

public class TribeFedStatusTest {
    @Test
    public void testAddingFields() {
        PlayerResourcesAndFood resourcesAndFood = new PlayerResourcesAndFood();
        TribeFedStatus tfs = new TribeFedStatus(resourcesAndFood, null);
        String state;
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
        TribeFedStatus tfs = new TribeFedStatus(prf, new PlayerFigures());
        for (int i = 0; i < 10; i++) {
            tfs.newTurn();
        }
        assert !(prf.hasResources(new Effect[] { Effect.FOOD }));

        tfs.addField();
        for (int i = 0; i < 10; i++) {
            tfs.newTurn();
        }
        Effect[] ef1 = new Effect[10];
        Arrays.fill(ef1, Effect.FOOD);
        assert (prf.hasResources(ef1));

        Effect[] ef2 = new Effect[11];
        Arrays.fill(ef2, Effect.FOOD);
        assert !(prf.hasResources(ef2));

        for (int i = 0; i < 9; i++) {
            tfs.addField();
        }

        for (int i = 0; i < 10; i++) {
            tfs.newTurn();
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
        TribeFedStatus tfs = new TribeFedStatus(prf, figures);

        for (int i = 0; i < 5; i++) {
            tfs.addField();
        }
        boolean ans = tfs.feedTribeIfEnoughFood();
        assert (ans);
        assert (tfs.isTribeFed());
        assert (tfs.isTribeFed());

        tfs.newTurn();
        figures.addNewFigure();
        ans = tfs.feedTribeIfEnoughFood();
        assert (!ans);
        assert (!tfs.isTribeFed());

        tfs.newTurn();
        tfs.addField();
        ans = tfs.feedTribeIfEnoughFood();
        assert (ans);
        assert (tfs.isTribeFed());
    }

    @Test
    public void testFeedTribe() {
        PlayerResourcesAndFood resources = new PlayerResourcesAndFood();
        PlayerFigures figures = new PlayerFigures();
        TribeFedStatus tfs = new TribeFedStatus(resources, figures);
        figures.addNewFigure();
        figures.newTurn();
        tfs.newTurn();// 6 figures
        figures.addNewFigure();
        tfs.newTurn();// 7 figures
        figures.newTurn();
        Effect[] allResources = { Effect.FOOD, Effect.FOOD, Effect.GOLD, Effect.STONE, Effect.WOOD, Effect.CLAY,
                Effect.STONE, Effect.GOLD, Effect.GOLD, Effect.GOLD, Effect.BUILDING, Effect.ONE_TIME_TOOL2,
                Effect.ONE_TIME_TOOL3 };
        resources.takeResources(allResources);
        boolean ans = tfs.feedTribeIfEnoughFood();
        assert !ans;
        ans = tfs.feedTribe(allResources);
        assert !ans;
        ans = tfs.feedTribe(new Effect[] { Effect.FOOD, Effect.FOOD, Effect.GOLD, Effect.STONE, Effect.WOOD,
                Effect.CLAY, Effect.STONE, Effect.ONE_TIME_TOOL2, Effect.ONE_TIME_TOOL3 });
        assert !ans;

        ans = tfs.feedTribe(new Effect[] { Effect.FOOD, Effect.FOOD, Effect.GOLD, Effect.STONE, Effect.WOOD,
                Effect.CLAY, Effect.STONE, Effect.GOLD, Effect.GOLD, Effect.GOLD });
        assert !ans;

        ans = tfs.feedTribe(new Effect[] { Effect.GOLD, Effect.STONE, Effect.WOOD, Effect.CLAY, Effect.STONE,
                Effect.GOLD, Effect.GOLD });
        assert !ans;

        ans = tfs.feedTribe(new Effect[] { Effect.FOOD, Effect.FOOD, Effect.GOLD, Effect.STONE, Effect.WOOD,
                Effect.CLAY, Effect.STONE });
        assert ans;

        tfs.newTurn();
        ans = tfs.feedTribe(new Effect[] { Effect.FOOD, Effect.FOOD, Effect.GOLD, Effect.STONE, Effect.WOOD,
                Effect.CLAY, Effect.STONE });
        assert !ans;
    }

    @Test
    public void testSetTribeFed() {
        PlayerResourcesAndFood resourcesAndFood = new PlayerResourcesAndFood();
        PlayerFigures figures = new PlayerFigures();
        TribeFedStatus tfs = new TribeFedStatus(resourcesAndFood, figures);
        resourcesAndFood.takeResources(new Effect[] {});

        for (int i = 0; i < 3; i++) {
            tfs.addField();
            tfs.newTurn();
        }
        boolean ans = tfs.setTribeFed();
        assert !ans;

        ans = tfs.feedTribeIfEnoughFood();
        assert ans;

        tfs.newTurn();
        assert !tfs.isTribeFed();

        ans = tfs.feedTribeIfEnoughFood();
        assert ans;

        tfs.newTurn();
        ans = tfs.feedTribeIfEnoughFood();
        assert ans;

        tfs.newTurn();
        ans = tfs.feedTribeIfEnoughFood();
        assert !ans;

        ans = tfs.setTribeFed();
        assert ans;

        tfs.newTurn();
        resourcesAndFood.takeResources(new Effect[] { Effect.FOOD, Effect.FOOD });
        ans = tfs.setTribeFed();
        assert !ans;

        ans = tfs.feedTribeIfEnoughFood();
        assert ans;
    }

    @Test
    public void testIsTribeFed() {
        PlayerFigures figures = new PlayerFigures();
        PlayerResourcesAndFood resourcesAndFood = new PlayerResourcesAndFood();
        TribeFedStatus tfs = new TribeFedStatus(resourcesAndFood, figures);
        resourcesAndFood.takeResources(new Effect[] { Effect.FOOD, Effect.FOOD, Effect.FOOD, Effect.FOOD, Effect.FOOD,
                Effect.GOLD, Effect.GOLD, Effect.STONE, Effect.CLAY, Effect.WOOD });

        tfs.feedTribeIfEnoughFood();
        assert tfs.isTribeFed();
        tfs.feedTribeIfEnoughFood();
        assert tfs.isTribeFed();

        tfs.newTurn();
        tfs.feedTribeIfEnoughFood();
        assert !tfs.isTribeFed();
        tfs.feedTribe(new Effect[] { Effect.GOLD, Effect.GOLD, Effect.STONE, Effect.CLAY, Effect.WOOD });
        assert tfs.isTribeFed();
        tfs.feedTribe(new Effect[] { Effect.GOLD, Effect.GOLD, Effect.STONE, Effect.CLAY, Effect.WOOD });
        assert tfs.isTribeFed();

        tfs.newTurn();
        tfs.feedTribeIfEnoughFood();
        assert !tfs.isTribeFed();
        tfs.feedTribe(new Effect[] { Effect.GOLD, Effect.GOLD, Effect.STONE, Effect.CLAY, Effect.WOOD });
        System.out.println(resourcesAndFood.state());
        assert !tfs.isTribeFed();
        tfs.setTribeFed();
        assert tfs.isTribeFed();
    }
}
