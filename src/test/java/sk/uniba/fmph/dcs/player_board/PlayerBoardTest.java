package sk.uniba.fmph.dcs.player_board;

import org.junit.Test;
import sk.uniba.fmph.dcs.stone_age.Effect;
import sk.uniba.fmph.dcs.stone_age.EndOfGameEffect;

public class PlayerBoardTest {
    @Test
    public void newTurnTest() {
        PlayerResourcesAndFood prf = new PlayerResourcesAndFood(0);
        PlayerFigures pf = new PlayerFigures();
        TribeFedStatus tfs = new TribeFedStatus(prf, pf);
        PlayerBoard pb = new PlayerBoard(new PlayerCivilisationCards(), pf, prf, new PlayerTools(), tfs);
        pb.getPlayerFigures().addNewFigure();
        pb.getTribeFedStatus().addField();
        pb.getPlayerTools().addTool();
        assert !pb.getTribeFedStatus().isTribeFed();
        pb.getPlayerResourcesAndFood()
                .giveResources(new Effect[] { Effect.FOOD, Effect.FOOD, Effect.FOOD, Effect.FOOD, Effect.FOOD });
        boolean ans = pb.getTribeFedStatus().feedTribeIfEnoughFood();
        assert ans;
        assert pb.getPlayerFigures().getTotalFigures() == 6;
        assert pb.getPlayerFigures().hasFigures(5);
        assert !pb.getPlayerFigures().hasFigures(6);
        assert pb.getPlayerTools().hasSufficientTools(1);
        assert !pb.getPlayerTools().hasSufficientTools(2);
        pb.getPlayerTools().useTool(0);
        assert !pb.getPlayerTools().hasSufficientTools(1);

        pb.newTurn();
        assert !pb.getTribeFedStatus().isTribeFed();
        assert !pb.getPlayerResourcesAndFood().hasResources(new Effect[] { Effect.FOOD });
        assert pb.getPlayerTools().hasSufficientTools(1);
        assert pb.getPlayerFigures().hasFigures(6);
        assert !pb.getPlayerFigures().hasFigures(7);
    }

    @Test
    public void addPointsTest() {
        PlayerBoard pb = new PlayerBoard();
        int ans = pb.addPoints(10);
        assert ans == 10;
        ans = pb.addPoints(-10);
        assert ans == 0;
        ans = pb.addPoints(7);
        assert ans == 7;
        ans = pb.addPoints(10);
        assert ans == 17;
    }

    @Test
    public void addHouseTest() {
        PlayerBoard pb = new PlayerBoard();
        pb.addHouse();
        pb.addHouse();
        System.out.println(pb.state());
    }

    @Test
    public void addEndOfGamePointsTest() {
        PlayerBoard pb = new PlayerBoard();
        pb.getPlayerTools().addTool();
        pb.getPlayerTools().addTool();
        pb.getPlayerTools().addTool(); // 3 tools;
        int expectedPoints = 0;
        for (int i = 0; i < 4; i++) {
            pb.getTribeFedStatus().addField();
            pb.newTurn();
        } // 4 fields
      
        pb.addHouse();
        pb.addHouse(); // 2 houses
        pb.getPlayerResourcesAndFood()
                .giveResources(new Effect[] { Effect.FOOD, Effect.WOOD, Effect.CLAY, Effect.STONE, Effect.GOLD });
        // 4 resources
        expectedPoints += 4;

        pb.getPlayerCivilisationCards()
                .addEndOfGameEffects(new EndOfGameEffect[] { EndOfGameEffect.SHAMAN, EndOfGameEffect.SHAMAN });
        // 2 shamans * 5 figures = 10;
        expectedPoints += 10;
        pb.getPlayerCivilisationCards().addEndOfGameEffects(new EndOfGameEffect[] { EndOfGameEffect.FARMER });
        // 1 farmer * 4 fields = 4;
        expectedPoints += 4;
        pb.getPlayerCivilisationCards()
                .addEndOfGameEffects(new EndOfGameEffect[] { EndOfGameEffect.TOOL_MAKER, EndOfGameEffect.TOOL_MAKER,
                        EndOfGameEffect.TOOL_MAKER, EndOfGameEffect.TOOL_MAKER, EndOfGameEffect.TOOL_MAKER });
        // 5 tool makers * 3 tools = 15;
        expectedPoints += 15;
        pb.getPlayerCivilisationCards()
                .addEndOfGameEffects(new EndOfGameEffect[] { EndOfGameEffect.BUILDER, EndOfGameEffect.BUILDER,
                        EndOfGameEffect.BUILDER, EndOfGameEffect.BUILDER, EndOfGameEffect.BUILDER,
                        EndOfGameEffect.BUILDER });
        // 6 builders * 2 houses = 12;
        expectedPoints += 12;
        pb.getPlayerCivilisationCards().addEndOfGameEffects(new EndOfGameEffect[] { EndOfGameEffect.WEAVING,
                EndOfGameEffect.ART, EndOfGameEffect.MEDICINE, EndOfGameEffect.ART });
        // 3^2 + 1 = 10
        expectedPoints += 10;
        pb.addEndOfGamePoints();
        int realPoints = pb.addPoints(-10);
        expectedPoints -= 10;
        realPoints = pb.addPoints(4);
        expectedPoints += 4;
        assert expectedPoints == realPoints;
    }
}
