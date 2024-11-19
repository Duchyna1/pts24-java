package sk.uniba.fmph.dcs.player_board;

import org.junit.Test;
import sk.uniba.fmph.dcs.stone_age.Effect;

public class PlayerResourcesAndFoodTest {
    @Test
    public void test_givingResources() {
        PlayerResourcesAndFood playerResourcesAndFood = new PlayerResourcesAndFood();
        playerResourcesAndFood.giveResources(new Effect[] { Effect.FOOD, Effect.FOOD, Effect.GOLD });
        assert playerResourcesAndFood.hasResources(new Effect[] { Effect.FOOD });
        assert playerResourcesAndFood.hasResources(new Effect[] { Effect.GOLD });
        assert playerResourcesAndFood.hasResources(new Effect[] { Effect.GOLD, Effect.FOOD });
        assert playerResourcesAndFood.hasResources(new Effect[] { Effect.FOOD, Effect.FOOD });
        assert playerResourcesAndFood.hasResources(new Effect[] { Effect.FOOD, Effect.FOOD, Effect.GOLD });
        assert !playerResourcesAndFood
                .hasResources(new Effect[] { Effect.FOOD, Effect.FOOD, Effect.FOOD, Effect.GOLD });
        assert !playerResourcesAndFood.hasResources(new Effect[] { Effect.FOOD, Effect.FOOD, Effect.FOOD });
        assert !playerResourcesAndFood
                .hasResources(new Effect[] { Effect.FOOD, Effect.FOOD, Effect.GOLD, Effect.STONE });
    }

    @Test
    public void test_takingResources() {
        PlayerResourcesAndFood playerResourcesAndFood = new PlayerResourcesAndFood();
        playerResourcesAndFood
                .giveResources(new Effect[] { Effect.FOOD, Effect.FOOD, Effect.FOOD, Effect.STONE, Effect.GOLD });
        assert playerResourcesAndFood.hasResources(new Effect[] { Effect.FOOD });
        assert playerResourcesAndFood.hasResources(new Effect[] { Effect.STONE });
        assert playerResourcesAndFood.hasResources(new Effect[] { Effect.GOLD });

        playerResourcesAndFood.takeResources(new Effect[] { Effect.FOOD });
        assert playerResourcesAndFood.hasResources(new Effect[] { Effect.FOOD, Effect.FOOD });
        assert !playerResourcesAndFood.hasResources(new Effect[] { Effect.FOOD, Effect.FOOD, Effect.FOOD });
        assert playerResourcesAndFood.hasResources(new Effect[] { Effect.STONE });
        assert playerResourcesAndFood.hasResources(new Effect[] { Effect.GOLD });

        playerResourcesAndFood.takeResources(new Effect[] { Effect.FOOD, Effect.STONE });
        assert playerResourcesAndFood.hasResources(new Effect[] { Effect.FOOD });
        assert !playerResourcesAndFood.hasResources(new Effect[] { Effect.FOOD, Effect.FOOD });
        assert !playerResourcesAndFood.hasResources(new Effect[] { Effect.STONE });
        assert playerResourcesAndFood.hasResources(new Effect[] { Effect.GOLD });

        assert !playerResourcesAndFood.takeResources(new Effect[] { Effect.CLAY });
    }

    @Test
    public void test_numberOfResourcesForFinalPoints() {
        PlayerResourcesAndFood playerResourcesAndFood = new PlayerResourcesAndFood();
        assert playerResourcesAndFood.numberOfResourcesForFinalPoints() == 0;
        int sum = 0;

        sum += 2;
        playerResourcesAndFood.giveResources(new Effect[] { Effect.FOOD });
        assert playerResourcesAndFood.numberOfResourcesForFinalPoints() == sum;

        sum += 3;
        playerResourcesAndFood.giveResources(new Effect[] { Effect.WOOD });
        assert playerResourcesAndFood.numberOfResourcesForFinalPoints() == sum;

        sum += 4;
        playerResourcesAndFood.giveResources(new Effect[] { Effect.CLAY });
        assert playerResourcesAndFood.numberOfResourcesForFinalPoints() == sum;

        sum += 5;
        playerResourcesAndFood.giveResources(new Effect[] { Effect.STONE });
        assert playerResourcesAndFood.numberOfResourcesForFinalPoints() == sum;

        sum += 6;
        playerResourcesAndFood.giveResources(new Effect[] { Effect.GOLD });
        assert playerResourcesAndFood.numberOfResourcesForFinalPoints() == sum;

        playerResourcesAndFood.giveResources(new Effect[] { Effect.TOOL, Effect.FIELD, Effect.BUILDING,
                Effect.ONE_TIME_TOOL2, Effect.ONE_TIME_TOOL3, Effect.ONE_TIME_TOOL4 });
        assert playerResourcesAndFood.numberOfResourcesForFinalPoints() == sum;
    }
}
