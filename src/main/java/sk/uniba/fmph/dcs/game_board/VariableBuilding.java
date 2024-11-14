package sk.uniba.fmph.dcs.game_board;

import org.json.JSONObject;
import sk.uniba.fmph.dcs.stone_age.Effect;

import java.util.Collection;
import java.util.OptionalInt;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map;


public final class VariableBuilding implements Building {
    private final int numberOfResources;
    private final int numberOfResourcesTypes;

    public VariableBuilding(final int numberOfResources, final int numberOfResourcesTypes) {
        this.numberOfResources = numberOfResources;
        this.numberOfResourcesTypes = numberOfResourcesTypes;
    }

    @Override
    public OptionalInt build(final Collection<Effect> resources) {
        Set<Effect> types = new TreeSet<>(resources);
        if (resources.size() != numberOfResources || types.size() != numberOfResourcesTypes) {
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
        Map<String, String> state = Map.of("numberOfResources", Integer.toString(numberOfResources),
                "numberOfResourcesTypes", Integer.toString(numberOfResourcesTypes));
        return new JSONObject(state).toString();
    }
}
