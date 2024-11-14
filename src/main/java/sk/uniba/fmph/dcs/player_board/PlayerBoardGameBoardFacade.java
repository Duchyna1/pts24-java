package sk.uniba.fmph.dcs.player_board;

import sk.uniba.fmph.dcs.stone_age.Effect;
import sk.uniba.fmph.dcs.stone_age.EndOfGameEffect;
import sk.uniba.fmph.dcs.stone_age.InterfaceFeedTribe;
import sk.uniba.fmph.dcs.stone_age.InterfaceNewTurn;
import sk.uniba.fmph.dcs.stone_age.InterfacePlayerBoardGameBoard;

import java.util.Optional;

public class PlayerBoardGameBoardFacade implements InterfaceFeedTribe, InterfaceNewTurn, InterfacePlayerBoardGameBoard {
    /**
     * TODO.
     *
     * @return TODO
     */
    @Override
    public boolean feedTribeIfEnoughFood() {
        // TODO
        return false;
    }

    /**
     * TODO.
     *
     * @param resources 
     * @return TODO
     */
    @Override
    public boolean feedTribe(Effect[] resources) {
        // TODO
        return false;
    }

    /**
     * TODO.
     * @return TODO
     */
    @Override
    public boolean doNotFeedThisTurn() {
        // TODO
        return false;
    }

    /**
     * TODO
     * @return TODO
     */
    @Override
    public boolean isTribeFed() {
        // TODO
        return false;
    }

    /**
     * TODO
     */
    @Override
    public void newTurn() {
        // TODO
    }

    /**
     * TODO
     * @param stuff 
     */
    @Override
    public void giveEffect(Effect[] stuff) {
        // TODO
    }

    /**
     * TODO
     * @param stuff 
     */
    @Override
    public void giveEndOfGameEffect(EndOfGameEffect[] stuff) {
        // TODO
    }

    /**
     * TODO
     * @param stuff 
     * @return TODO
     */
    @Override
    public boolean takeResources(Effect[] stuff) {
        // TODO
        return false;
    }

    /**
     * TODO
     */
    @Override
    public void giveFigure() {
        // TODO
    }

    /**
     * TODO
     * @param count 
     * @return TODO
     */
    @Override
    public boolean takeFigures(int count) {
        // TODO
        return false;
    }

    /**
     * TODO
     * @param count 
     * @return TODO
     */
    @Override
    public boolean hasFigures(int count) {
        // TODO
        return false;
    }

    /**
     * TODO
     * @param goal 
     * @return TODO
     */
    @Override
    public boolean hasSufficientTools(int goal) {
        // TODO
        return false;
    }

    /**
     * TODO
     * @param idx 
     * @return TODO
     */
    @Override
    public Optional<Integer> useTool(int idx) {
        // TODO
        return Optional.empty();
    }
}
