package tictactoe.gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class EndViewController {

    @FXML
    private Label lblEnd;

    public void setParentController(){
        lblEnd.setText("You won!");
    }
}
