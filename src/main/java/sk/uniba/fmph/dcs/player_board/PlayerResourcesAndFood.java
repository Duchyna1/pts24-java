package sk.uniba.fmph.dcs.player_board;


import sk.uniba.fmph.dcs.stone_age.Effect;

import java.util.HashMap;
import java.util.Map;

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
     * @param resources - one of resources listed in Effect.
     * @return true if player has at least one of resource resources.
     */
    public boolean hasResources(final Effect resources) {
        return this.resources.get(resources) > 0;
    }


    /**
     * This function increases number of resources of type resource player has by one.
     *
     * @param resources - one of resources listed in Effect.
     * @return true - always. Something else should check if player can take resource.
     */
    public boolean takeResources(final Effect resources) {
        this.resources.put(resources, this.resources.get(resources) + 1);
        return true;
    }

    /**
     * This function decreases number of resources of type resource player has by one.
     *
     * @param resources - one of resources listed in Effect.
     * @return true if player has at least one resource of type resources.
     */
    public boolean giveResources(final Effect resources) {
        if (this.hasResources(resources)) {
            this.resources.put(resources, this.resources.get(resources) - 1);
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
            if (this.hasResources(res)) {
                ans.append(res).append(": ").append(resources.get(res)).append("\n");
            }
        }
        return ans.toString();
    }
}
