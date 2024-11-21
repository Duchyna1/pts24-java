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

    /**
     * Constructs a new TribeFedStatus with the specified player resources and figures.
     *
     * @param playerResourcesAndFood
     *            the player's resources and food
     * @param playerFigures
     *            the player's figures
     */
    public TribeFedStatus(final PlayerResourcesAndFood playerResourcesAndFood, final PlayerFigures playerFigures) {
        this.tribeFed = false;
        this.fields = this.startingFields;

        this.playerResourcesAndFood = playerResourcesAndFood;
        this.playerFigures = playerFigures;

        this.fieldsHarvested = false;
    }

    /**
     * Gets the count of fields.
     *
     * @return the number of fields
     */
    public int getFieldsCount() {
        return this.fields;
    }

    /**
     * Adds a field if the current number of fields is below the maximum limit of fields.
     */
    public void addField() {
        if (this.fields >= this.maxFields) {
            return;
        }

        this.fields++;
        playerResourcesAndFood.giveResources(new Effect[] {Effect.FIELD});
    }

    /**
     * Starts a new turn, setting the tribe to unfed and giving the player food according to the number of fields.
     */
    public void newTurn() {
        this.harvestFields();

        this.tribeFed = false;
        this.fieldsHarvested = false;
    }

    /**
     * Harvests fields to gather food based on the current number of fields. This can only be done once per turn.
     */
    private void harvestFields() {
        if (!this.fieldsHarvested) {
            Effect[] food = new Effect[this.fields];
            Arrays.fill(food, Effect.FOOD);
            this.playerResourcesAndFood.giveResources(food);
            this.fieldsHarvested = true;
        }
    }

    /**
     * Attempts to feed the tribe with only food.
     *
     * @return true if the tribe was successfully fed
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

        this.playerResourcesAndFood.takeResources(foodRequired);
        this.tribeFed = true;
        return true;
    }

    /**
     * Attempts to feed the tribe with the specified resources.
     *
     * @param resources
     *            the resources to use for feeding the tribe
     *
     * @return true if the tribe was successfully fed
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

        // not right amount of food + resources
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
     * Feeds the tribe if the player does not have enough food, causing the player to lose 10 victory points.
     *
     * @return true if the tribe was fed
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
            this.playerResourcesAndFood.takeResources(oneFood);
        }

        this.tribeFed = true;

        return true;
    }

    /**
     * Checks if the tribe is fed.
     *
     * @return true if the tribe is fed
     */
    public boolean isTribeFed() {
        return this.tribeFed;
    }

    /**
     * Returns the current state of the tribe's fed status and the number of fields.
     *
     * @return a string representing the fed status and number of fields
     */
    public String state() {
        return "Fed: " + this.tribeFed + ", # of fields: " + this.fields;
    }
}
