package sk.uniba.fmph.dcs.player_board;


import sk.uniba.fmph.dcs.stone_age.Effect;

import java.util.HashMap;
import java.util.Map;

public class PlayerResourcesAndFood {
    private Map<Effect, Integer> resources;

    public PlayerResourcesAndFood() {
        resources = new HashMap<>();
        for (Effect res : Effect.values()) {
            resources.put(res, 0);
        }
    }

    public boolean hasResources(Effect resources) {
        return this.resources.get(resources) > 0;
    }


    // the player takes one resource of resources from game board
    // always returns true, because player can always take resource
    // something else should check, it there is enough resources of resources
    public boolean takeResources(Effect resources) {
        this.resources.put(resources, this.resources.get(resources) + 1);
        return true;
    }

    //returns true, if player has at least 1 of resource type resources
    public boolean giveResources(Effect resources) {
        if (this.hasResources(resources)) {
            this.resources.put(resources, this.resources.get(resources) - 1);
            return true;
        }
        return false;
    }

    public int numberOfResourcesForFinalPoints() {
        int ans = 0;
        for (Effect res : resources.keySet()) {
            ans += res.points() * resources.get(res);
        }
        return ans;
    }

    public String state() {
        StringBuilder ans = new StringBuilder();
        for (Effect res : resources.keySet()) {
            ans.append(res).append(": ").append(resources.get(res)).append("\n");
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        PlayerResourcesAndFood pr = new PlayerResourcesAndFood();
        for (int i = 0; i < 10; i++) {
            pr.takeResources(Effect.WOOD);
        }
        for (int i = 0; i < 10; i++) {
            pr.takeResources(Effect.FOOD);
        }
        System.out.println(pr.state());

        System.out.println(pr.numberOfResourcesForFinalPoints());

        for (int i = 0; i < 20; i++) {
            System.out.println(pr.giveResources(Effect.WOOD));
        }
        System.out.println(pr.state());

        System.out.println(pr.numberOfResourcesForFinalPoints());


    }
}
