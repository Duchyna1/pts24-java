package sk.uniba.fmph.dcs.player_board;

import sk.uniba.fmph.dcs.stone_age.Effect;

public class TribeFedStatus {
    private boolean tribeFed;
    private int fields;
    private PlayerResourcesAndFood playerResourcesAndFood;
    private PlayerFigures playerFigures;

    private final int startingFields = 0;
    private final int maxFields = 10;

    public TribeFedStatus(final PlayerResourcesAndFood playerResourcesAndFood, final PlayerFigures playerFigures) {
        this.tribeFed = false;
        this.fields = this.startingFields;

        this.playerResourcesAndFood = playerResourcesAndFood;
        this.playerFigures = playerFigures;
    }

    /**
     * Adds field if bellow 10 fields.
     */
    public void addField() {
        if (this.fields > this.maxFields) {
            return;
        }

        this.fields++;
    }

    /**
     * Set tribe to unfed.
     */
    public void newTurn() {
        this.tribeFed = false;
    }

    /**
     * Attempts to feed the tribe with only food.
     *
     * @return true if tribe was successfully fed.
     */
    public boolean feedTribeIfEnoughFood() {
        // TODO
        return false;
    }

    /**
     * Attempts to feed the tribe with //TODO.
     *
     * @param resources resources
     * @return true if tribe was successfully fed.
     */
    public boolean feedTribe(final Effect[] resources) {
        // TODO
        return false;
    }

    /**
     * TODO.
     *
     * @return true
     */
    public boolean setTribeFed() {
        // TODO
        return false;
    }

    /**
     * @return true if tribe is fed
     */
    public boolean isTribeFed() {
        return this.tribeFed;
    }

    /**
     * @return string with fed status and number of fields.
     */
    public String state() {
        return "Fed: " + this.tribeFed + ", # of fields: " + this.fields;
    }
}
