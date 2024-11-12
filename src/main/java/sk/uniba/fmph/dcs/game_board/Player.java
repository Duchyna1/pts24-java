package sk.uniba.fmph.dcs.game_board;

import sk.uniba.fmph.dcs.stone_age.InterfacePlayerBoardGameBoard;
import sk.uniba.fmph.dcs.stone_age.PlayerOrder;

/**
 * @param playerOrder Player's order in the game
 * @param playerBoard Interface for the player's board in the game
 */
public record Player(PlayerOrder playerOrder, InterfacePlayerBoardGameBoard playerBoard) { }
