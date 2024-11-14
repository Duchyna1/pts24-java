package sk.uniba.fmph.dcs.player_board;

public class PlayerFigures {
    private int totalFigures;
    private int figures;
    private boolean canAddFigure;

    private final int startingFigures = 5;
    private final int maxFigurines = 10;

    /**
     * Give player 5 starting figures.
     */
    public PlayerFigures() {
        this.totalFigures = this.startingFigures;
        this.figures = this.startingFigures;
        this.canAddFigure = true;
    }

    /**
     * Adds figure to players total figures. Has no effect when he has more than 10 figures.
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
     * @param count number of figures to be checked
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
     * @param count number of figures to be taken
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
