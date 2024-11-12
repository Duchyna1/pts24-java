package sk.uniba.fmph.dcs.game_board;

import sk.uniba.fmph.dcs.stone_age.InterfacePlayerBoardGameBoard;
import sk.uniba.fmph.dcs.stone_age.PlayerOrder;

public class Player {
    private PlayerOrder playerOrder;  // Player's order in the game
    private InterfacePlayerBoardGameBoard playerBoard;  // Interface for the player's board in the game

    // Constructor
    public Player(PlayerOrder playerOrder, InterfacePlayerBoardGameBoard playerBoard) {
        this.playerOrder = playerOrder;
        this.playerBoard = playerBoard;
    }

    // Getter for playerOrder
    public PlayerOrder getPlayerOrder() {
        return playerOrder;
    }

    // Setter for playerOrder
    public void setPlayerOrder(PlayerOrder playerOrder) {
        this.playerOrder = playerOrder;
    }

    // Getter for playerBoard
    public InterfacePlayerBoardGameBoard getPlayerBoard() {
        return playerBoard;
    }

    // Setter for playerBoard
    public void setPlayerBoard(InterfacePlayerBoardGameBoard playerBoard) {
        this.playerBoard = playerBoard;
    }
}
