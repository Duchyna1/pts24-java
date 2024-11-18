package sk.uniba.fmph.dcs.game_board;

import sk.uniba.fmph.dcs.stone_age.CivilisationCard;
import sk.uniba.fmph.dcs.stone_age.Effect;

import java.util.Optional;

public class GetCard implements EvaluateCivilisationCardImmediateEffect {
    private final CivilizationCardDeck deck;

    /**
     * @param deck
     *            to draw cards from
     */
    public GetCard(final CivilizationCardDeck deck) {
        this.deck = deck;
    }

    /**
     * @param player
     *            gives player top card of the deck
     * @param choice
     *            ignored
     *
     * @return true if card exists
     */
    @Override
    public boolean performEffect(final Player player, final Effect choice) {
        Optional<CivilisationCard> c = deck.getTop();
        if (c.isEmpty()) {
            return false;
        }
        player.playerBoard().giveEndOfGameEffect(c.get().endOfGameEffect());
        return true;
    }
}
