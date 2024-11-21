package sk.uniba.fmph.dcs.game_phase_controller;

import sk.uniba.fmph.dcs.stone_age.ActionResult;
import sk.uniba.fmph.dcs.stone_age.Effect;
import sk.uniba.fmph.dcs.stone_age.HasAction;
import sk.uniba.fmph.dcs.stone_age.Location;
import sk.uniba.fmph.dcs.stone_age.PlayerOrder;
import sk.uniba.fmph.dcs.stone_age.InterfaceNewTurn;
import sk.uniba.fmph.dcs.stone_age.InterfaceFigureLocation;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public final class NewRoundState implements InterfaceGamePhaseState {
    private final InterfaceFigureLocation[] places;
    private PlayerOrder lastInitialization = null;
    private final Map<PlayerOrder, InterfaceNewTurn> playerPlayerBordMap;

    public NewRoundState(final InterfaceFigureLocation[] places, final Map<PlayerOrder, InterfaceNewTurn> playerPlayerBordMap) {
        this.places = places;
        this.playerPlayerBordMap = playerPlayerBordMap;
    }

    @Override
    public ActionResult placeFigures(final PlayerOrder player, final Location location, final int figuresCount) {
        return ActionResult.FAILURE;
    }

    @Override
    public ActionResult makeAction(final PlayerOrder player, final Location location, final Collection<Effect> inputResources, final Collection<Effect> outputResources) {
        return ActionResult.FAILURE;
    }

    @Override
    public ActionResult skipAction(final PlayerOrder player, final Location location) {
        return ActionResult.FAILURE;
    }

    @Override
    public ActionResult useTools(final PlayerOrder player, final int toolIndex) {
        return ActionResult.FAILURE;
    }

    @Override
    public ActionResult noMoreToolsThisThrow(final PlayerOrder player) {
        return ActionResult.FAILURE;
    }

    @Override
    public ActionResult feedTribe(final PlayerOrder player, final Collection<Effect> resources) {
        return ActionResult.FAILURE;
    }

    @Override
    public ActionResult doNotFeedThisTurn(final PlayerOrder player) {
        return ActionResult.FAILURE;
    }

    @Override
    public ActionResult makeAllPlayersTakeARewardChoice(final PlayerOrder player, final Effect reward) {
        return ActionResult.FAILURE;
    }

    @Override
    public HasAction tryToMakeAutomaticAction(final PlayerOrder player) {
        // returns NO_ACTION_POSSIBLE if any of InterfaceFigureLocations indicates end of game
        for (InterfaceFigureLocation location : places) {
            if (location.newTurn()) {
                return HasAction.NO_ACTION_POSSIBLE;
            }
        }
        // initialize new round on PlayerBoard of given player
        if (lastInitialization == player || !playerPlayerBordMap.containsKey(player)) {
            return HasAction.NO_ACTION_POSSIBLE;
        }
        playerPlayerBordMap.get(player).newTurn();
        lastInitialization = player;
        return HasAction.AUTOMATIC_ACTION_DONE;
    }
}
