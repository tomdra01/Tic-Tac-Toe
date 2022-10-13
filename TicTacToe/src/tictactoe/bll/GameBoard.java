package tictactoe.bll;

import tictactoe.gui.controller.TicTacViewController;
import java.util.Arrays;

public class GameBoard implements IGameModel {

    private String player;

    private String winner;

    String [][] boardTiles;

    public String getNextPlayer() {
        if (player == "O"){
            player = "X";
        }
        else {
            player =  "O";
        }
        return player;
    }

    /**
     * Attempts to let the current player play at the given coordinates. It the
     * attempt is succesfull the current player has ended his turn and it is the
     * next players turn.
     *
     * @param col column to place a marker in.
     * @param row row to place a marker in.
     * @return true if the move is accepted, otherwise false. If gameOver == true
     * this method will always return false.
     */
    public boolean play(int col, int row) {
        return true;
    }

    /**
     * isGameOver checks for victory conditions by calling various checking methods, returns false if none are true.
     *
     */
    public boolean isGameOver() {
        boardTiles = TicTacViewController.getBoardTiles();

        if(checkRows(boardTiles)){
            return true;
        }else if(checkColumns(boardTiles)){
            return true;
        }else if(checkDiagonal(boardTiles)){
            return true;
        } else if (checkForDraw(boardTiles)) {
            return true;
        }

        return false;
    }

    /**
     * checkRows checks all the rows on the board for winning conditions
     */
    private boolean checkRows(String[][] boardTiles) {
        for (int row = 0; row < 3; row++) {
            if (boardTiles[row][0] == boardTiles[row][1] && boardTiles[row][1] == boardTiles[row][2] && boardTiles[row][0] != null) {
                winner = boardTiles[row][0];
                return true;
            }
        }
        return false;
    }

    /**
     * checkColumn checks all the columns on the board for winning conditions
     */
    private boolean checkColumns(String[][] boardTiles) {
        for (int col = 0; col < 3; col++) {
            if (boardTiles[0][col] == boardTiles[1][col] && boardTiles[1][col] == boardTiles[2][col] && boardTiles[0][col] != null) {
                winner = boardTiles[0][col];
                return true;
            }
        }
        return false;
    }

    /**
     * checkDiagonal checks the two diagonals on the board for winning conditions
     */
    private boolean checkDiagonal(String[][] boardTiles) {
        if (boardTiles[0][0] == boardTiles[1][1] && boardTiles[1][1] == boardTiles[2][2] && boardTiles[0][0] != null) {
            winner = boardTiles[0][0];
            return true;
        }else if (boardTiles[0][2] == boardTiles[1][1] && boardTiles[1][1] == boardTiles[2][0] && boardTiles[0][2] != null){
            winner = boardTiles[0][2];
            return true;}

        return false;
    }

    private boolean checkForDraw(String[][] boardTiles){
        for (int i = 0; i < 3; i++){
            if (boardTiles[i][0] == null || boardTiles[i][1] == null || boardTiles[i][2] == null){
                return false;
            }
        }
        winner = "draw";
        return true;
    }

    public String getWinner() {
       return winner;
    }

    /**
     * Resets the game to a new game state.
     */
    public void newGame() {
        player = "X";
        for( int i = 0; i < boardTiles.length; i++ )
            Arrays.fill( boardTiles[i], null );
    }
}
