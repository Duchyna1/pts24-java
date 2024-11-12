package sk.uniba.fmph.dcs.stone_age;

public class CivilisationCard {
    private ImmediateEffect[] immediateEffect;
    private EndOfGameEffect[] endOfGameEffect;

    // Constructor
    public CivilisationCard(ImmediateEffect[] immediateEffect, EndOfGameEffect[] endOfGameEffect) {
        this.immediateEffect = immediateEffect;
        this.endOfGameEffect = endOfGameEffect;
    }

    // Getter for immediateEffect
    public ImmediateEffect[] getImmediateEffect() {
        return immediateEffect;
    }

    // Setter for immediateEffect
    public void setImmediateEffect(ImmediateEffect[] immediateEffect) {
        this.immediateEffect = immediateEffect;
    }

    // Getter for endOfGameEffect
    public EndOfGameEffect[] getEndOfGameEffect() {
        return endOfGameEffect;
    }

    // Setter for endOfGameEffect
    public void setEndOfGameEffect(EndOfGameEffect[] endOfGameEffect) {
        this.endOfGameEffect = endOfGameEffect;
    }
}

