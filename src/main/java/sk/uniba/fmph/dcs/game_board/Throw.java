package sk.uniba.fmph.dcs.game_board;


public final class Throw {
    private static final int DICESIDES = 6;

    private int randomDice() {
        return (int) (Math.random() * DICESIDES);
    }

    public int[] hod(final int dices) {
        int[] res = new int[dices];
        for (int i = 0; i < dices; i++) {
            res[i] = randomDice();
        }
        return res;
    }
}