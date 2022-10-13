package tictactoe.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import tictactoe.bll.GameBoard;
import tictactoe.bll.IGameModel;
import tictactoe.gui.TicTacToe;


public class TicTacViewController implements Initializable {
    @FXML
    private Label lblPlayer;

    @FXML
    private GridPane gridPane;

    private static final String TXT_PLAYER = "Player: ";

    private static String player;

    private IGameModel game;

    private static final String [][] boardTiles = new String[3][3];

    private final Button [] buttons = new Button[9];


    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {

        Integer row = GridPane.getRowIndex((Node) event.getSource());
        Integer col = GridPane.getColumnIndex((Node) event.getSource());

        int r = (row == null) ? 0 : row;
        int c = (col == null) ? 0 : col;

        player = game.getNextPlayer();

        if (game.play(c, r))
        {
            Button btn = (Button) event.getSource();
            System.out.println("Player:" +player);

            String symbolO = "X";
            String symbolX = "O";

            if (Objects.equals(player, "X")){
                btn.setText(symbolX);
            }
            else if (Objects.equals(player, "O")){
                btn.setText(symbolO);
            }
            btn.setDisable(true);
            btn.setOpacity(1);

            boardTiles[r][c] =  btn.getText();
            setPlayer();
            boolean isGameOver = game.isGameOver();

            if (isGameOver)
            {
                for(Button selectedBtn : buttons){
                    selectedBtn.setDisable(true);
                    selectedBtn.setOpacity(1);
                }
                displayWinner();
            }
        }
    }

    @FXML
    private void handleNewGame() {
        game.newGame();
        setPlayer();
        clearBoard();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        game = new GameBoard();
        setPlayer();
        for (int i = 0; i < 9; i++){
            buttons[i] = (Button)gridPane.getChildren().get(i);
        }
    }

    private void setPlayer() {
        player = game.getNextPlayer();
        lblPlayer.setText(TXT_PLAYER + game.getNextPlayer());
    }

    private void displayWinner() throws IOException {
        String winner = game.getWinner();
        String message;

        if(Objects.equals(winner, "draw")){
            message = "It's a draw :-(";
        }else{
            message = "Player " + winner + " wins!!!";
            newWindow();
        }

        lblPlayer.setText(message);
    }

    @FXML
    protected void newWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(TicTacToe.class.getResource("views/EndView.fxml"));

        Scene scene = new Scene(loader.load());

        EndViewController endView = loader.getController();
        endView.setParentController();

        Stage stage = new Stage();
        stage.setTitle("Results");
        stage.setScene(scene);
        stage.show();
    }

    private void clearBoard() {
        for(Node n : gridPane.getChildren())
        {
            Button btn = (Button) n;
            btn.setText(" ");
            for(Button selectedBtn : buttons){
                selectedBtn.setDisable(false);
            }
        }
    }

    public void setParentController2() {

    }

    public static String[][] getBoardTiles(){
        return boardTiles;
    }
}

