package sk.uniba.fmph.dcs.player_board;

import org.junit.Test;
import sk.uniba.fmph.dcs.stone_age.Effect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class PlayerBoardGameBoardFacadeTest {
    @Test
    public void doNotFeedThisTurnTest() {
        PlayerBoard pb = new PlayerBoard();
        PlayerBoardGameBoardFacade PBGBF = new PlayerBoardGameBoardFacade(pb);
        pb.getPlayerResourcesAndFood().giveResources(new Effect[]{Effect.FOOD, Effect.FOOD, Effect.FOOD, Effect.FOOD, Effect.FOOD});
        pb.getPlayerResourcesAndFood().giveResources(new Effect[]{Effect.WOOD, Effect.CLAY, Effect.STONE, Effect.GOLD, Effect.WOOD});
        boolean ans = PBGBF.doNotFeedThisTurn();
        assert !ans;

        PBGBF.feedTribeIfEnoughFood();
        ans = PBGBF.doNotFeedThisTurn();
        assert !ans;
        int points = pb.addPoints(0);
        assert points == 0;

        PBGBF.newTurn();
        ans = PBGBF.feedTribeIfEnoughFood();
        assert !ans;
        ans = PBGBF.doNotFeedThisTurn();
        assert ans;
        points = pb.addPoints(0);
        assert points == -10;

        PBGBF.newTurn();
        ans = PBGBF.feedTribeIfEnoughFood();
        assert !ans;
        Collection<Effect> food = Arrays.asList(Effect.WOOD, Effect.CLAY, Effect.STONE, Effect.GOLD, Effect.WOOD);
        ans = PBGBF.feedTribe(food);
        assert ans;
        ans = PBGBF.doNotFeedThisTurn();
        assert !ans;
        points = pb.addPoints(0);
        assert points == -10;

        PBGBF.newTurn();
        ans = PBGBF.feedTribeIfEnoughFood();
        assert !ans;
        ans = PBGBF.feedTribe(food);
        assert !ans;
        ans = PBGBF.doNotFeedThisTurn();
        assert ans;
        points = pb.addPoints(0);
        assert points == -20;

    }
}
