package sk.uniba.fmph.dcs.game_phase_controller;

import sk.uniba.fmph.dcs.stone_age.ActionResult;
import sk.uniba.fmph.dcs.stone_age.Effect;
import sk.uniba.fmph.dcs.stone_age.HasAction;
import sk.uniba.fmph.dcs.stone_age.Location;
import sk.uniba.fmph.dcs.stone_age.PlayerOrder;
import sk.uniba.fmph.dcs.stone_age.InterfaceFigureLocation;

import java.util.Collection;
import java.util.Map;

public class MakeActionState implements InterfaceGamePhaseState {

    private Map<Location, InterfaceFigureLocation> places;

    /**
     * @param places
     *            - initial places
     */
    public MakeActionState(final Map<Location, InterfaceFigureLocation> places) {
        this.places = places;
    }

    /**
     * @param player
     *            - current player
     * @param location
     *            - location
     * @param figuresCount
     *            - number of figures
     *
     * @return - always return FAILURE, because this class is not responsible for placing figures
     */
    @Override
    public ActionResult placeFigures(final PlayerOrder player, final Location location, final int figuresCount) {
        return ActionResult.FAILURE;
    }

    /**
     * @param player
     *            - current player
     * @param location
     *            - location, on which player is trying to make action
     * @param inputResources
     *            - input resources
     * @param outputResources
     *            - output resources
     *
     * @return - Action result of places.get(location).makeAction(player, inputR, outputR)
     */
    @Override
    public ActionResult makeAction(final PlayerOrder player, final Location location,
            final Collection<Effect> inputResources, final Collection<Effect> outputResources) {
        Effect[] inputR = new Effect[inputResources.size()];
        Effect[] outputR = new Effect[outputResources.size()];
        int helper = 0;
        for (Effect res : inputResources) {
            inputR[helper] = res;
            helper++;
        }
        helper = 0;
        for (Effect res : outputResources) {
            outputR[helper] = res;
            helper++;
        }
        return places.get(location).makeAction(player, inputR, outputR);
    }

    /**
     * @param player
     *            - current player
     * @param location
     *            - location
     *
     * @return - always returns ACTION_DONE
     */
    @Override
    public ActionResult skipAction(final PlayerOrder player, final Location location) {
        return ActionResult.ACTION_DONE;
    }

    /**
     * @param player
     *            - current player
     * @param toolIndex
     *            - tool he is trying to use
     *
     * @return - always return FAILURE, because this class is not responsible for using tools
     */
    @Override
    public ActionResult useTools(final PlayerOrder player, final int toolIndex) {
        return ActionResult.FAILURE;
    }

    /**
     * @param player
     *            - current player
     *
     * @return - always return FAILURE, because this class is not responsible for using tools
     */
    @Override
    public ActionResult noMoreToolsThisThrow(final PlayerOrder player) {
        return ActionResult.FAILURE;
    }

    /**
     * @param player
     *            - current player
     * @param resources
     *            - resources with which he is trying to feed his tribe
     *
     * @return - always return FAILURE, because this class is not responsible for feeding tribe
     */
    @Override
    public ActionResult feedTribe(final PlayerOrder player, final Collection<Effect> resources) {
        return ActionResult.FAILURE;
    }

    /**
     * @param player
     *            - current player
     *
     * @return - always return FAILURE, because this class is not responsible for feeding tribe
     */
    @Override
    public ActionResult doNotFeedThisTurn(final PlayerOrder player) {
        return ActionResult.FAILURE;
    }

    /**
     * @param player
     *            - current player
     * @param reward
     *            - rewards he is trying to take
     *
     * @return - always return FAILURE, because this class is not responsible for taking rewards
     */
    @Override
    public ActionResult makeAllPlayersTakeARewardChoice(final PlayerOrder player, final Effect reward) {
        return ActionResult.FAILURE;
    }

    /**
     * This function tries to make action, which does not require any resources. If such action exist, this function
     * makes that action
     *
     * @param player
     *            - current player
     *
     * @return - AUTOMATIC_ACTION_DONE if there was action, for which player did not need resources. Returns
     *         WAITING_FOR_PLAYER_ACTION otherwise.
     */
    @Override
    public HasAction tryToMakeAutomaticAction(final PlayerOrder player) {
        for (Location loc : places.keySet()) {
            if (places.get(loc).makeAction(player, null, null) == ActionResult.ACTION_DONE) {
                return HasAction.AUTOMATIC_ACTION_DONE;
            }
        }
        return HasAction.WAITING_FOR_PLAYER_ACTION;
    }
}
