package tictactoe.gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class EndViewController {

    @FXML
    private Label lblEnd;

    private TicTacViewController controller;

    public void setParentController(TicTacViewController controller){
        this.controller = controller;
        lblEnd.setText("You won!");
    }
}
