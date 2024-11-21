package sk.uniba.fmph.dcs.stone_age;

import java.util.Collection;

public interface InterfaceStoneAgeGame {
    boolean placeFigures(int playerId, Location location, int figuresCount);

    boolean makeAction(int playerId, Location location, Collection<Effect> usedResources, Collection<Effect> desiredResources);

    boolean skipAction(int playerId, Location location); // : bool (only if resources are required) todo neviem co to
    // znamena

    boolean useTools(int playerId, int toolIndex); // : bool {affects last action} todo neviem co to znamena

    boolean noMoreToolsThisThrow(int playerId);

    boolean feedTribe(int playerId, Collection<Effect> resources);

    boolean doNotFeedThisTurn(int playerId);

    boolean makeAllPlayersTakeARewardChoice(int playerId, Effect reward);

}
