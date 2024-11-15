package sk.uniba.fmph.dcs.game_board;


public final class Throw {
    private static final int DICESIDES = 6;

    private Throw() {
        throw new UnsupportedOperationException("Utility class");
    }

    private static int randomDice() {
        return (int) (Math.random() * DICESIDES) + 1;
    }

    /**
     * renamed to hod so that it won't conflict with compilers.
     *
     * @param dices number of dices to throw
     * @return resulting throws
     */
    public static int[] hod(final int dices) {
        int[] res = new int[dices];
        for (int i = 0; i < dices; i++) {
            res[i] = randomDice();
        }
        return res;
    }
}
