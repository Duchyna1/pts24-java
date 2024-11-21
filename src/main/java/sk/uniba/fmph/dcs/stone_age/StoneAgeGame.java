package sk.uniba.fmph.dcs.stone_age;

import java.util.Collection;
import java.util.Map;

public final class StoneAgeGame implements InterfaceStoneAgeGame {

    private final Map<Integer, PlayerOrder> players;
    private final InterfaceGamePhaseController phaseController;
    private final InterfaceGetState playerBoardState;
    private final InterfaceGetState gameBoardState;
    private final StoneAgeObservable observable;


    public StoneAgeGame(final Map<Integer, PlayerOrder> players, final StoneAgeObservable observable, final InterfaceGamePhaseController phaseController,
                        final InterfaceGetState playerBoardState, final InterfaceGetState gameBoardState) {
        this.players = players;
        this.phaseController = phaseController;
        this.observable = observable;
        this.playerBoardState = playerBoardState;
        this.gameBoardState = gameBoardState;
    }

    private void notif() {
        String state1 = playerBoardState.state();
        String state2 = gameBoardState.state();
        String state3 = phaseController.state();
        observable.notify(state1);
        observable.notify(state2);
        observable.notify(state3);
    }

    @Override
    public boolean placeFigures(final int playerId, final Location location, final int figuresCount) {
        if (!players.containsKey(playerId)) {
            return false;
        }
        boolean result = phaseController.placeFigures(players.get(playerId), location, figuresCount);
        notif();
        return result;

    }

    @Override
    public boolean makeAction(final int playerId, final Location location, final Collection<Effect> usedResources, final Collection<Effect> desiredResources) {
        if (!players.containsKey(playerId)) {
            return false;
        }
        boolean result = phaseController.makeAction(players.get(playerId), location, desiredResources, usedResources); //Todo is this the correct way of passing the resources?
        notif();
        return result;
    }

    @Override
    public boolean skipAction(final int playerId, final Location location) {
        if (!players.containsKey(playerId)) {
            return false;
        }
        boolean result = phaseController.skipAction(players.get(playerId), location);
        notif();
        return result;
    }

    @Override
    public boolean useTools(final int playerId, final int toolIndex) {
        if (!players.containsKey(playerId)) {
            return false;
        }
        boolean result = phaseController.useTools(players.get(playerId), toolIndex);
        notif();
        return result;
    }

    @Override
    public boolean noMoreToolsThisThrow(final int playerId) {
        if (!players.containsKey(playerId)) {
            return false;
        }
        boolean result = phaseController.noMoreToolsThisThrow(players.get(playerId));
        notif();
        return result;
    }

    @Override
    public boolean feedTribe(final int playerId, final Collection<Effect> resources) {
        if (!players.containsKey(playerId)) {
            return false;
        }
        boolean result = phaseController.feedTribe(players.get(playerId), resources);
        notif();
        return result;
    }

    @Override
    public boolean doNotFeedThisTurn(final int playerId) {
        if (!players.containsKey(playerId)) {
            return false;
        }
        boolean result = phaseController.doNotFeedThisTurn(players.get(playerId));
        notif();
        return result;
    }

    @Override
    public boolean makeAllPlayersTakeARewardChoice(final int playerId, final Effect reward) {
        if (!players.containsKey(playerId)) {
            return false;
        }
        boolean result = phaseController.makeAllPlayersTakeARewardChoice(players.get(playerId), reward);
        notif();
        return result;
    }
}
