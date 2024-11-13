package sk.uniba.fmph.dcs.player_board;

import org.junit.Test;
import sk.uniba.fmph.dcs.stone_age.Effect;

import java.util.Arrays;
import java.util.List;

public class PlayerResourcesAndFoodTest {
    @Test
    public void test_takingResources() {
        PlayerResourcesAndFood playerResourcesAndFood = new PlayerResourcesAndFood();
        playerResourcesAndFood.takeResources(Arrays.asList(Effect.FOOD, Effect.FOOD, Effect.GOLD));
        assert playerResourcesAndFood.hasResources(List.of(Effect.FOOD));
        assert playerResourcesAndFood.hasResources(List.of(Effect.GOLD));
        assert !playerResourcesAndFood.hasResources(List.of(Effect.GOLD));
        assert playerResourcesAndFood.hasResources(Arrays.asList(Effect.GOLD, Effect.FOOD));
        assert playerResourcesAndFood.hasResources(Arrays.asList(Effect.FOOD, Effect.FOOD));
        assert playerResourcesAndFood.hasResources(Arrays.asList(Effect.FOOD, Effect.FOOD, Effect.GOLD));
        assert !playerResourcesAndFood.hasResources(Arrays.asList(Effect.FOOD, Effect.FOOD, Effect.FOOD, Effect.GOLD));
        assert !playerResourcesAndFood.hasResources(Arrays.asList(Effect.FOOD, Effect.FOOD, Effect.FOOD));
        assert !playerResourcesAndFood.hasResources(Arrays.asList(Effect.FOOD, Effect.FOOD, Effect.GOLD, Effect.STONE));
    }

    @Test
    public void test_givingResources() {
        PlayerResourcesAndFood playerResourcesAndFood = new PlayerResourcesAndFood();
        playerResourcesAndFood.takeResources(Arrays.asList(Effect.FOOD, Effect.FOOD, Effect.FOOD, Effect.STONE, Effect.GOLD));
        assert playerResourcesAndFood.hasResources(List.of(Effect.FOOD));
        assert playerResourcesAndFood.hasResources(List.of(Effect.STONE));
        assert playerResourcesAndFood.hasResources(List.of(Effect.GOLD));

        playerResourcesAndFood.takeResources(Effect.FOOD);
        assert playerResourcesAndFood.hasResources(Arrays.asList(Effect.FOOD, Effect.FOOD));
        assert !playerResourcesAndFood.hasResources(Arrays.asList(Effect.FOOD, Effect.FOOD, Effect.FOOD));
        assert playerResourcesAndFood.hasResources(List.of(Effect.STONE));
        assert playerResourcesAndFood.hasResources(List.of(Effect.GOLD));

        playerResourcesAndFood.takeResources(Arrays.asList(Effect.FOOD, Effect.STONE));
        assert playerResourcesAndFood.hasResources(List.of(Effect.FOOD));
        assert !playerResourcesAndFood.hasResources(Arrays.asList(Effect.FOOD, Effect.FOOD));
        assert !playerResourcesAndFood.hasResources(List.of(Effect.STONE));
        assert playerResourcesAndFood.hasResources(List.of(Effect.GOLD));
    }

    @Test
    public void test_numberOfResourcesForFinalPoints() {
        PlayerResourcesAndFood playerResourcesAndFood = new PlayerResourcesAndFood();
        assert playerResourcesAndFood.numberOfResourcesForFinalPoints() == 0;
        int sum = 0;

        sum += 2;
        playerResourcesAndFood.takeResources(List.of(Effect.FOOD));
        assert playerResourcesAndFood.numberOfResourcesForFinalPoints() == sum;

        sum += 3;
        playerResourcesAndFood.takeResources(List.of(Effect.WOOD));
        assert playerResourcesAndFood.numberOfResourcesForFinalPoints() == sum;

        sum += 4;
        playerResourcesAndFood.takeResources(List.of(Effect.CLAY));
        assert playerResourcesAndFood.numberOfResourcesForFinalPoints() == sum;

        sum += 5;
        playerResourcesAndFood.takeResources(List.of(Effect.STONE));
        assert playerResourcesAndFood.numberOfResourcesForFinalPoints() == sum;

        sum += 6;
        playerResourcesAndFood.takeResources(List.of(Effect.GOLD));
        assert playerResourcesAndFood.numberOfResourcesForFinalPoints() == sum;

        playerResourcesAndFood.takeResources(Arrays.asList(Effect.TOOL, Effect.FIELD, Effect.BUILDING, Effect.ONE_TIME_TOOL2, Effect.ONE_TIME_TOOL3, Effect.ONE_TIME_TOOL4));
        assert playerResourcesAndFood.numberOfResourcesForFinalPoints() == sum;
    }
}
