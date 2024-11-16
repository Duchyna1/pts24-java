package sk.uniba.fmph.dcs.game_board;

import sk.uniba.fmph.dcs.stone_age.Effect;

import java.util.Collection;
import java.util.OptionalInt;

interface Building {
    OptionalInt build(Collection<Effect> resources);

    String state();
}
