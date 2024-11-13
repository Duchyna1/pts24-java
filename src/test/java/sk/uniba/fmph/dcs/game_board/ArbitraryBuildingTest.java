package sk.uniba.fmph.dcs.game_board;

import org.junit.Test;
import sk.uniba.fmph.dcs.stone_age.Effect;

import java.util.ArrayList;
import java.util.OptionalInt;

import static org.junit.Assert.assertEquals;

public class ArbitraryBuildingTest {
    @Test
    public void test_calculation() {
        ArbitraryBuilding building = new ArbitraryBuilding(4);


        ArrayList<Effect> wrongResources = new ArrayList<Effect>();
        wrongResources.add(Effect.WOOD);
        assertEquals(building.build(wrongResources), OptionalInt.empty());

        ArrayList<Effect> goodResources = new ArrayList<Effect>();
        goodResources.add(Effect.GOLD);
        goodResources.add(Effect.WOOD);
        goodResources.add(Effect.STONE);
        goodResources.add(Effect.CLAY);
        assertEquals(building.build(goodResources), OptionalInt.of(3 + 4 + 5 + 6));

        wrongResources.add(Effect.WOOD);
        wrongResources.add(Effect.WOOD);
        wrongResources.add(Effect.WOOD);
        wrongResources.add(Effect.WOOD);
        assertEquals(building.build(wrongResources), OptionalInt.empty());
    }
}
