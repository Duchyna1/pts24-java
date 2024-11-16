package sk.uniba.fmph.dcs.game_phase_controller;

import sk.uniba.fmph.dcs.stone_age.ActionResult;
import sk.uniba.fmph.dcs.stone_age.Effect;
import sk.uniba.fmph.dcs.stone_age.HasAction;
import sk.uniba.fmph.dcs.stone_age.Location;
import sk.uniba.fmph.dcs.stone_age.PlayerOrder;

import java.util.Collection;

public interface InterfaceGamePhaseState {
    ActionResult placeFigures(PlayerOrder player, Location location, int figuresCount);

    ActionResult makeAction(PlayerOrder player, Location location, Collection<Effect> inputResources,
            Collection<Effect> outputResources);

    ActionResult skipAction(PlayerOrder player, Location location);

    ActionResult useTools(PlayerOrder player, int toolIndex);

    ActionResult noMoreToolsThisThrow(PlayerOrder player);

    ActionResult feedTribe(PlayerOrder player, Collection<Effect> resources);

    ActionResult doNotFeedThisTurn(PlayerOrder player);

    ActionResult makeAllPlayersTakeARewardChoice(PlayerOrder player, Effect reward);

    HasAction tryToMakeAutomaticAction(PlayerOrder player);
}
