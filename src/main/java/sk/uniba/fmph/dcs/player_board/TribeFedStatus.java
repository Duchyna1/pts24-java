package sk.uniba.fmph.dcs.player_board;

import sk.uniba.fmph.dcs.stone_age.Effect;

import java.util.Arrays;

public class TribeFedStatus {
    private boolean tribeFed;
    private int fields;
    private final PlayerResourcesAndFood playerResourcesAndFood;
    private final PlayerFigures playerFigures;

    private boolean fieldsHarvested;

    private final int startingFields = 0;
    private final int maxFields = 10;

    public TribeFedStatus(final PlayerResourcesAndFood playerResourcesAndFood, final PlayerFigures playerFigures) {
        this.tribeFed = false;
        this.fields = this.startingFields;

        this.playerResourcesAndFood = playerResourcesAndFood;
        this.playerFigures = playerFigures;

        this.fieldsHarvested = false;
    }

    /**
     * Adds field if bellow 10 fields.
     */
    public void addField() {
        if (this.fields >= this.maxFields) {
            return;
        }

        this.fields++;
    }

    /**
     * Set tribe to unfed and give player food according to number of fields.
     */
    public void newTurn() {
        this.harvestFields();

        this.tribeFed = false;
        this.fieldsHarvested = false;
    }

    private void harvestFields() {
        if (!this.fieldsHarvested) {
            Effect[] food = new Effect[this.fields];
            Arrays.fill(food, Effect.FOOD);
            this.playerResourcesAndFood.takeResources(food);
            this.fieldsHarvested = true;
        }
    }

    /**
     * Attempts to feed the tribe with only food.
     *
     * @return true if tribe was successfully fed.
     */
    public boolean feedTribeIfEnoughFood() {
        this.harvestFields();

        if (this.tribeFed) {
            return true;
        }

        Effect[] foodRequired = new Effect[this.playerFigures.getTotalFigures()];
        Arrays.fill(foodRequired, Effect.FOOD);

        if (!playerResourcesAndFood.hasResources(foodRequired)) {
            return false;
        }

        this.playerResourcesAndFood.giveResources(foodRequired);
        this.tribeFed = true;
        return true;
    }

    /**
     * Attempts to feed the tribe with those resources.
     *
     * @param resources
     *            resources
     *
     * @return true if tribe was successfully fed.
     */
    public boolean feedTribe(final Effect[] resources) {
        this.harvestFields();

        if (this.tribeFed) {
            return true;
        }

        if (!this.playerResourcesAndFood.hasResources(resources)) {
            return false;
        }

        int numberOfFood = 0;
        int numberOfResources = 0;
        for (Effect resource : resources) {
            if (resource.isResource()) {
                numberOfResources++;
            } else if (resource == Effect.FOOD) {
                numberOfFood++;
            }
        }

        // checks if player has more food
        Effect[] food = new Effect[numberOfFood + 1];
        Arrays.fill(food, Effect.FOOD);
        if (this.playerResourcesAndFood.hasResources(food)) {
            return false;
        }

        // not enough food + resources
        if (numberOfFood + numberOfResources != this.playerFigures.getTotalFigures()) {
            return false;
        }

        // only using food or resource
        for (Effect effect : resources) {
            if (!effect.isResourceOrFood()) {
                return false;
            }
        }

        this.playerResourcesAndFood.takeResources(resources);
        this.tribeFed = true;

        return true;
    }

    /**
     * Feeds the tribe if player does not have enough food. Should lose 10 victory points after this.
     *
     * @return true if tribe was fed.
     */
    public boolean setTribeFed() {
        this.harvestFields();

        if (this.tribeFed) {
            return false;
        }

        Effect[] foodRequired = new Effect[this.playerFigures.getTotalFigures()];
        Arrays.fill(foodRequired, Effect.FOOD);

        if (this.playerResourcesAndFood.hasResources(foodRequired)) {
            return false;
        }

        Effect[] oneFood = new Effect[1];
        oneFood[0] = Effect.FOOD;
        while (this.playerResourcesAndFood.hasResources(oneFood)) {
            this.playerResourcesAndFood.giveResources(oneFood);
        }

        this.tribeFed = true;

        return true;
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
