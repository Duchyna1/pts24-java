package sk.uniba.fmph.dcs.stone_age;

import java.util.Optional;

public interface InterfacePlayerBoardGameBoard {
    void giveEffect(Effect[] stuff);

    void giveEndOfGameEffect(EndOfGameEffect[] stuff);

    boolean takeResources(Effect[] stuff);

    boolean takeFigures(int count);

    boolean hasFigures(int count);

    boolean hasSufficientTools(int goal);

    Optional<Integer> useTool(int idx);

}
