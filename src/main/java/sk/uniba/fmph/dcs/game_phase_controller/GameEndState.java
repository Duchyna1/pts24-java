package sk.uniba.fmph.dcs.game_phase_controller;

import sk.uniba.fmph.dcs.stone_age.*;

import java.util.Collection;

public class GameEndState implements InterfaceGamePhaseState{
    @Override
    public ActionResult placeFigures(PlayerOrder player, Location location, int figuresCount) {
        return null;
    }

    @Override
    public ActionResult makeAction(PlayerOrder player, Location location, Collection<Effect> inputResources, Collection<Effect> outputResources) {
        return null;
    }

    @Override
    public ActionResult skipAction(PlayerOrder player, Location location) {
        return null;
    }

    @Override
    public ActionResult useTools(PlayerOrder player, int toolIndex) {
        return null;
    }

    @Override
    public ActionResult noMoreToolsThisThrow(PlayerOrder player) {
        return null;
    }

    @Override
    public ActionResult feedTribe(PlayerOrder player, Collection<Effect> resources) {
        return null;
    }

    @Override
    public ActionResult doNotFeedThisTurn(PlayerOrder player) {
        return null;
    }

    @Override
    public ActionResult makeAllPlayersTakeARewardChoice(PlayerOrder player, Effect reward) {
        return null;
    }

    @Override
    public HasAction tryToMakeAutomaticAction(PlayerOrder player) {
        return HasAction.WAITING_FOR_PLAYER_ACTION;
    }
}
