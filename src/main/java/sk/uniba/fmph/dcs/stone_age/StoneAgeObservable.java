package sk.uniba.fmph.dcs.stone_age;

import java.util.HashMap;
import java.util.Map;

public final class StoneAgeObservable implements InterfaceStoneAgeObservable {
    private final Map<Integer, InterfaceStoneAgeObserver> observerMap;

    public StoneAgeObservable() {
        observerMap = new HashMap<>();
    }

    @Override
    public void registerObserver(final int playerId, final InterfaceStoneAgeObserver observer) {
        observerMap.put(playerId, observer);
    }

    public void notify(final String gameState) {
        for (InterfaceStoneAgeObserver observer : observerMap.values()) {
            observer.update(gameState);
        }
    }
}
