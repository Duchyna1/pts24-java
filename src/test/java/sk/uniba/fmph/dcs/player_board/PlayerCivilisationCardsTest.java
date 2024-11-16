package sk.uniba.fmph.dcs.player_board;

import org.junit.Test;
import sk.uniba.fmph.dcs.stone_age.EndOfGameEffect;

public class PlayerCivilisationCardsTest {
    @Test
    public void test_init() {
        PlayerCivilisationCards pc = new PlayerCivilisationCards();
        assert (pc.state().isEmpty());
    }

    @Test
    public void test_farmerEffect() {
        PlayerCivilisationCards pc = new PlayerCivilisationCards();

        assert (pc.calculateEndOfGameCivilisationCardsPoints(0, 0, 1, 0) == 0);
        assert (pc.calculateEndOfGameCivilisationCardsPoints(1, 1, 0, 1) == 0);
        assert (pc.calculateEndOfGameCivilisationCardsPoints(-1, -1, -1, -1) == 0);

        pc.addEndOfGameEffects(new EndOfGameEffect[] { EndOfGameEffect.FARMER });

        assert (pc.calculateEndOfGameCivilisationCardsPoints(0, 0, 1, 0) == 1);
        assert (pc.calculateEndOfGameCivilisationCardsPoints(0, 0, 2, 0) == 2);

        pc.addEndOfGameEffects(new EndOfGameEffect[] { EndOfGameEffect.FARMER });
        pc.addEndOfGameEffects(new EndOfGameEffect[] { EndOfGameEffect.FARMER });

        assert (pc.calculateEndOfGameCivilisationCardsPoints(0, 0, 2, 0) == 6);
        assert (pc.calculateEndOfGameCivilisationCardsPoints(0, 0, 10, 0) == 30);
        assert (pc.calculateEndOfGameCivilisationCardsPoints(1, 1, 0, 1) == 0);
        assert (pc.calculateEndOfGameCivilisationCardsPoints(0, 0, -1, 0) == 0);
    }

    @Test
    public void test_toolMakerEffect() {
        PlayerCivilisationCards pc = new PlayerCivilisationCards();

        assert (pc.calculateEndOfGameCivilisationCardsPoints(0, 1, 0, 0) == 0);
        assert (pc.calculateEndOfGameCivilisationCardsPoints(1, 0, 1, 1) == 0);
        assert (pc.calculateEndOfGameCivilisationCardsPoints(-1, -1, -1, -1) == 0);

        pc.addEndOfGameEffects(new EndOfGameEffect[] { EndOfGameEffect.TOOL_MAKER });

        assert (pc.calculateEndOfGameCivilisationCardsPoints(0, 1, 0, 0) == 1);
        assert (pc.calculateEndOfGameCivilisationCardsPoints(0, 2, 0, 0) == 2);

        pc.addEndOfGameEffects(new EndOfGameEffect[] { EndOfGameEffect.TOOL_MAKER });
        pc.addEndOfGameEffects(new EndOfGameEffect[] { EndOfGameEffect.TOOL_MAKER });

        assert (pc.calculateEndOfGameCivilisationCardsPoints(0, 2, 0, 0) == 6);
        assert (pc.calculateEndOfGameCivilisationCardsPoints(0, 10, 0, 0) == 30);
        assert (pc.calculateEndOfGameCivilisationCardsPoints(1, 0, 1, 1) == 0);
        assert (pc.calculateEndOfGameCivilisationCardsPoints(0, -1, 0, 0) == 0);
    }

    @Test
    public void test_builderEffect() {
        PlayerCivilisationCards pc = new PlayerCivilisationCards();

        assert (pc.calculateEndOfGameCivilisationCardsPoints(1, 0, 0, 0) == 0);
        assert (pc.calculateEndOfGameCivilisationCardsPoints(0, 1, 1, 1) == 0);
        assert (pc.calculateEndOfGameCivilisationCardsPoints(-1, -1, -1, -1) == 0);

        pc.addEndOfGameEffects(new EndOfGameEffect[] { EndOfGameEffect.BUILDER });

        assert (pc.calculateEndOfGameCivilisationCardsPoints(1, 0, 0, 0) == 1);
        assert (pc.calculateEndOfGameCivilisationCardsPoints(2, 0, 0, 0) == 2);

        pc.addEndOfGameEffects(new EndOfGameEffect[] { EndOfGameEffect.BUILDER });
        pc.addEndOfGameEffects(new EndOfGameEffect[] { EndOfGameEffect.BUILDER });

        assert (pc.calculateEndOfGameCivilisationCardsPoints(2, 0, 0, 0) == 6);
        assert (pc.calculateEndOfGameCivilisationCardsPoints(10, 0, 0, 0) == 30);
        assert (pc.calculateEndOfGameCivilisationCardsPoints(0, 1, 1, 1) == 0);
        assert (pc.calculateEndOfGameCivilisationCardsPoints(-1, 0, 0, 0) == 0);
    }

    @Test
    public void test_shamanEffect() {
        PlayerCivilisationCards pc = new PlayerCivilisationCards();

        assert (pc.calculateEndOfGameCivilisationCardsPoints(0, 0, 0, 1) == 0);
        assert (pc.calculateEndOfGameCivilisationCardsPoints(1, 1, 1, 0) == 0);
        assert (pc.calculateEndOfGameCivilisationCardsPoints(-1, -1, -1, -1) == 0);

        pc.addEndOfGameEffects(new EndOfGameEffect[] { EndOfGameEffect.SHAMAN });

        assert (pc.calculateEndOfGameCivilisationCardsPoints(0, 0, 0, 1) == 1);
        assert (pc.calculateEndOfGameCivilisationCardsPoints(0, 0, 0, 2) == 2);

        pc.addEndOfGameEffects(new EndOfGameEffect[] { EndOfGameEffect.SHAMAN });
        pc.addEndOfGameEffects(new EndOfGameEffect[] { EndOfGameEffect.SHAMAN });

        assert (pc.calculateEndOfGameCivilisationCardsPoints(0, 0, 0, 2) == 6);
        assert (pc.calculateEndOfGameCivilisationCardsPoints(0, 0, 0, 10) == 30);
        assert (pc.calculateEndOfGameCivilisationCardsPoints(0, 1, 1, 0) == 0);
        assert (pc.calculateEndOfGameCivilisationCardsPoints(0, 0, 0, -1) == 0);
    }

    @Test
    public void test_sandBackgroundCards() {
        PlayerCivilisationCards pc = new PlayerCivilisationCards();
        pc.addEndOfGameEffects(new EndOfGameEffect[] { EndOfGameEffect.BUILDER, EndOfGameEffect.TOOL_MAKER,
                EndOfGameEffect.TOOL_MAKER, EndOfGameEffect.FARMER, EndOfGameEffect.FARMER, EndOfGameEffect.FARMER,
                EndOfGameEffect.SHAMAN, EndOfGameEffect.SHAMAN, EndOfGameEffect.SHAMAN, EndOfGameEffect.SHAMAN });

        assert (pc.calculateEndOfGameCivilisationCardsPoints(0, 0, 0, 0) == 0);
        assert (pc.calculateEndOfGameCivilisationCardsPoints(-1, -1, -1, -1) == 0);
        assert (pc.calculateEndOfGameCivilisationCardsPoints(1, 0, 0, 0) == 1);
        assert (pc.calculateEndOfGameCivilisationCardsPoints(1, 1, 0, 0) == 3);
        assert (pc.calculateEndOfGameCivilisationCardsPoints(1, 1, 1, 0) == 6);
        assert (pc.calculateEndOfGameCivilisationCardsPoints(1, 1, 1, 1) == 10);
        assert (pc.calculateEndOfGameCivilisationCardsPoints(1, 2, 1, 2) == 16);
    }

    @Test
    public void test_grassBackgroundCards1() {
        PlayerCivilisationCards pc = new PlayerCivilisationCards();
        assert (pc.calculateEndOfGameCivilisationCardsPoints(0, 0, 0, 0) == 0);
        pc.addEndOfGameEffects(new EndOfGameEffect[] { EndOfGameEffect.MEDICINE });
        assert (pc.calculateEndOfGameCivilisationCardsPoints(0, 0, 0, 0) == 1);
        pc.addEndOfGameEffects(new EndOfGameEffect[] { EndOfGameEffect.ART });
        assert (pc.calculateEndOfGameCivilisationCardsPoints(0, 0, 0, 0) == 4);
        pc.addEndOfGameEffects(new EndOfGameEffect[] { EndOfGameEffect.WRITING });
        assert (pc.calculateEndOfGameCivilisationCardsPoints(0, 0, 0, 0) == 9);
        pc.addEndOfGameEffects(new EndOfGameEffect[] { EndOfGameEffect.POTTERY });
        assert (pc.calculateEndOfGameCivilisationCardsPoints(0, 0, 0, 0) == 16);
        pc.addEndOfGameEffects(new EndOfGameEffect[] { EndOfGameEffect.SUNDIAL });
        assert (pc.calculateEndOfGameCivilisationCardsPoints(0, 0, 0, 0) == 25);
        pc.addEndOfGameEffects(new EndOfGameEffect[] { EndOfGameEffect.TRANSPORT });
        assert (pc.calculateEndOfGameCivilisationCardsPoints(0, 0, 0, 0) == 36);
        pc.addEndOfGameEffects(new EndOfGameEffect[] { EndOfGameEffect.MUSIC });
        assert (pc.calculateEndOfGameCivilisationCardsPoints(0, 0, 0, 0) == 49);
        pc.addEndOfGameEffects(new EndOfGameEffect[] { EndOfGameEffect.WEAVING });
        assert (pc.calculateEndOfGameCivilisationCardsPoints(0, 0, 0, 0) == 64);
    }

    @Test
    public void test_grassBackgroundCards2() {
        PlayerCivilisationCards pc = new PlayerCivilisationCards();
        pc.addEndOfGameEffects(new EndOfGameEffect[] { EndOfGameEffect.WEAVING, EndOfGameEffect.ART,
                EndOfGameEffect.MEDICINE, EndOfGameEffect.MUSIC });
        assert (pc.calculateEndOfGameCivilisationCardsPoints(0, 0, 0, 0) == 16);
        pc.addEndOfGameEffects(new EndOfGameEffect[] { EndOfGameEffect.WEAVING, EndOfGameEffect.ART,
                EndOfGameEffect.MEDICINE, EndOfGameEffect.MUSIC });
        assert (pc.calculateEndOfGameCivilisationCardsPoints(0, 0, 0, 0) == 32);
        pc.addEndOfGameEffects(
                new EndOfGameEffect[] { EndOfGameEffect.WEAVING, EndOfGameEffect.ART, EndOfGameEffect.MUSIC });
        assert (pc.calculateEndOfGameCivilisationCardsPoints(0, 0, 0, 0) == 41);
        pc.addEndOfGameEffects(new EndOfGameEffect[] { EndOfGameEffect.WEAVING });
        assert (pc.calculateEndOfGameCivilisationCardsPoints(0, 0, 0, 0) == 42);
        pc.addEndOfGameEffects(new EndOfGameEffect[] { EndOfGameEffect.POTTERY });
        assert (pc.calculateEndOfGameCivilisationCardsPoints(0, 0, 0, 0) == 51);
        pc.addEndOfGameEffects(new EndOfGameEffect[] { EndOfGameEffect.POTTERY });
        assert (pc.calculateEndOfGameCivilisationCardsPoints(0, 0, 0, 0) == 60);
    }

    @Test
    public void test_addEndOfGameEffects() {
        PlayerCivilisationCards pc = new PlayerCivilisationCards();
        pc.addEndOfGameEffects(new EndOfGameEffect[] { EndOfGameEffect.WEAVING, EndOfGameEffect.ART,
                EndOfGameEffect.MEDICINE, EndOfGameEffect.MUSIC });
        pc.addEndOfGameEffects(new EndOfGameEffect[] { EndOfGameEffect.WEAVING, EndOfGameEffect.ART,
                EndOfGameEffect.MEDICINE, EndOfGameEffect.MUSIC });
        pc.addEndOfGameEffects(
                new EndOfGameEffect[] { EndOfGameEffect.WEAVING, EndOfGameEffect.ART, EndOfGameEffect.MUSIC });
        pc.addEndOfGameEffects(new EndOfGameEffect[] { EndOfGameEffect.WEAVING });
        pc.addEndOfGameEffects(new EndOfGameEffect[] { EndOfGameEffect.POTTERY });
        pc.addEndOfGameEffects(new EndOfGameEffect[] { EndOfGameEffect.POTTERY });
        assert (pc.calculateEndOfGameCivilisationCardsPoints(1, 2, 1, 2) == 60);
        pc.addEndOfGameEffects(new EndOfGameEffect[] { EndOfGameEffect.BUILDER, EndOfGameEffect.TOOL_MAKER,
                EndOfGameEffect.TOOL_MAKER, EndOfGameEffect.FARMER, EndOfGameEffect.FARMER, EndOfGameEffect.FARMER,
                EndOfGameEffect.SHAMAN, EndOfGameEffect.SHAMAN, EndOfGameEffect.SHAMAN, EndOfGameEffect.SHAMAN });
        assert (pc.calculateEndOfGameCivilisationCardsPoints(1, 2, 1, 2) == 76);
    }
}
