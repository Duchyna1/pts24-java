package sk.uniba.fmph.dcs.game_board;

import org.junit.Test;
import sk.uniba.fmph.dcs.stone_age.Effect;

import java.util.ArrayList;
import java.util.OptionalInt;

import static org.junit.Assert.assertEquals;

public class VariableBuildingTest {
    @Test
    public void test_calculation() {
        // todo toto by mal spravit niekto iny ako ja zrejme

        VariableBuilding building = new VariableBuilding(4, 2);

        ArrayList<Effect> wrongResources = new ArrayList<Effect>();
        wrongResources.add(Effect.WOOD);
        wrongResources.add(Effect.CLAY);
        assertEquals(building.build(wrongResources), OptionalInt.empty());

        ArrayList<Effect> goodResources = new ArrayList<Effect>();
        goodResources.add(Effect.GOLD);
        goodResources.add(Effect.GOLD);
        goodResources.add(Effect.STONE);
        goodResources.add(Effect.STONE);
        assertEquals(building.build(goodResources), OptionalInt.of(6 + 6 + 5 + 5));

        wrongResources.clear();
        wrongResources.add(Effect.STONE);
        wrongResources.add(Effect.STONE);
        wrongResources.add(Effect.STONE);
        wrongResources.add(Effect.STONE);
        assertEquals(building.build(wrongResources), OptionalInt.empty());

        wrongResources.clear();
        wrongResources.add(Effect.WOOD);
        wrongResources.add(Effect.WOOD);
        wrongResources.add(Effect.WOOD);
        wrongResources.add(Effect.WOOD);
        wrongResources.add(Effect.WOOD);
        assertEquals(building.build(wrongResources), OptionalInt.empty());

        wrongResources.clear();
        wrongResources.add(Effect.WOOD);
        wrongResources.add(Effect.CLAY);
        wrongResources.add(Effect.GOLD);
        wrongResources.add(Effect.WOOD);
        assertEquals(building.build(wrongResources), OptionalInt.empty());
    }
}
