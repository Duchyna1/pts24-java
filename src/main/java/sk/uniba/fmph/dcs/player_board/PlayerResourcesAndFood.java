package sk.uniba.fmph.dcs.player_board;


import sk.uniba.fmph.dcs.stone_age.Effect;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.max;

public class PlayerResourcesAndFood {
    private final Map<Effect, Integer> resources;

    /**
     * Initialise resources such that every resource is zero.
     */
    public PlayerResourcesAndFood() {
        resources = new HashMap<>();
        for (Effect res : Effect.values()) {
            resources.put(res, 0);
        }
    }

    /**
     * For each resource R: if this resource is x times in list resources, this function checks if player has at
     * least x amount of resource R.
     *
     * @param resources - list of resources
     * @return true if player has at least x of each resource R listed in resources (x is number of occurrences of R in resources).
     */
    public boolean hasResources(final Effect[] resources) {
        Map<Effect, Integer> amountToCheck = new HashMap<>();
        for (Effect resource : resources) {
            if (!amountToCheck.containsKey(resource)) {
                amountToCheck.put(resource, 0);
            }
            amountToCheck.put(resource, amountToCheck.get(resource) + 1);
            if (this.resources.get(resource) < amountToCheck.get(resource)) {
                return false;
            }
        }
        return true;
    }


    /**
     * This function increases value of resource R by one for each occurrence of R in resources.
     *
     * @param resources - list of resources to be taken from board.
     * @return true - always. Something else should check if player can take resource.
     */
    public boolean takeResources(final Effect[] resources) {
        for (Effect resource : resources) {
            this.resources.put(resource, this.resources.get(resource) + 1);
        }
        return true;
    }

    /**
     * .
     *
     * @param resources - list of resources listed in Effect.
     * @return true if player has at least one resource of each resource listed in resources.
     */
    public boolean giveResources(final Effect[] resources) {
        if (this.hasResources(resources)) {
            for (Effect resource : resources) {
                this.resources.put(resource, max(this.resources.get(resource) - 1, 0));
            }
            return true;
        }
        return false;
    }

    /**
     * @return number of points player has from resources.
     */
    public int numberOfResourcesForFinalPoints() {
        int ans = 0;
        for (Effect res : resources.keySet()) {
            ans += res.points() * resources.get(res);
        }
        return ans;
    }

    /**
     * @return state of PlayerResourcesAndFood. Only lists resources which are greater than zero.
     */
    public String state() {
        StringBuilder ans = new StringBuilder();
        for (Effect res : resources.keySet()) {
            if (resources.get(res) > 0) {
                ans.append(res).append(": ").append(resources.get(res)).append("\n");
            }
        }
        return ans.toString();
    }
}
