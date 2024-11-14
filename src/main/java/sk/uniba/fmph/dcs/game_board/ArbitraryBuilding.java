package sk.uniba.fmph.dcs.game_board;

import org.json.JSONObject;
import sk.uniba.fmph.dcs.stone_age.Effect;

import java.util.Collection;
import java.util.Map;
import java.util.OptionalInt;

public final class ArbitraryBuilding implements Building {
    private final int numberOfResources;

    public ArbitraryBuilding(final int numberOfResources) {
        this.numberOfResources = numberOfResources;
    }

    @Override
    public OptionalInt build(final Collection<Effect> resources) {
        if (resources.size() > numberOfResources) {
            return OptionalInt.empty();
        }
        int sum = 0;
        for (Effect resource : resources) {
            sum += resource.points();
        }
        return OptionalInt.of(sum);
    }

    @Override
    public String state() {
        Map<String, String> state = Map.of("numberOfResources", Integer.toString(numberOfResources));
        return new JSONObject(state).toString();
    }
}
