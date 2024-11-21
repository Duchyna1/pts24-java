package sk.uniba.fmph.dcs.player_board;

public class PlayerFigures {
    private int totalFigures;
    private int figures;
    private boolean canAddFigure;

    private static final int DEFAULT_STARTING_FIGURES = 5;
    private final int startingFigures;
    private final int maxFigurines = 10;

    /**
     * Universal constructor with adjustable number of starting figures.
     *
     * @param startingFigures
     *            number of starting figures
     */
    public PlayerFigures(final int startingFigures) {
        if (startingFigures < 0) {
            this.startingFigures = 0;
        } else {
            this.startingFigures = Math.min(startingFigures, maxFigurines);
        }
        this.totalFigures = this.startingFigures;
        this.figures = this.startingFigures;
        this.canAddFigure = true;
    }

    /**
     * Give player 5 starting figures by default.
     */
    public PlayerFigures() {
        this(DEFAULT_STARTING_FIGURES);
    }

    /**
     * Adds figure to players total figures. Has no effect when he has more than 10 figures. Can add only one figure per
     * round.
     */
    public void addNewFigure() {
        if (this.totalFigures >= this.maxFigurines) {
            return;
        }

        if (!this.canAddFigure) {
            return;
        }

        this.totalFigures++;
        this.canAddFigure = false;
    }

    /**
     * @param count
     *            number of figures to be checked
     *
     * @return true if player has at least count available figures
     */
    public boolean hasFigures(final int count) {
        return count >= 0 && this.figures >= count;
    }

    /**
     * @return number of total figures
     */
    public int getTotalFigures() {
        return totalFigures;
    }

    /**
     * Take count figures from player if available.
     *
     * @param count
     *            number of figures to be taken
     *
     * @return true if count figures available, otherwise returns false
     */
    public boolean takeFigures(final int count) {
        if (!this.hasFigures(count)) {
            return false;
        }

        this.figures -= count;
        return true;
    }

    /**
     * Resets the number of available figures.
     */
    public void newTurn() {
        this.figures = this.totalFigures;
        this.canAddFigure = true;
    }

    /**
     * @return String with total figures and available figures.
     */
    public String state() {
        return "Total figures: " + this.totalFigures + ", available: " + String.valueOf(this.figures);
    }
}
