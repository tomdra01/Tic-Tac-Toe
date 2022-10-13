package tictactoe.gui.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tictactoe.gui.TicTacToe;

import java.io.IOException;

public class StartViewController {


    public StartViewController() {
    }

    public void startButton() throws IOException {
        System.out.println("startbutton");
        openGameWindow();

    }

    public void openGameWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(TicTacToe.class.getResource("views/TicTacView.fxml"));
        Scene scene = new Scene(loader.load());

        TicTacViewController gameCon = loader.getController();
        gameCon.setParentController2();

        Stage stage = new Stage();
        stage.setTitle("Tic Tac Toe");
        stage.setScene(scene);
        stage.show();
    }
}