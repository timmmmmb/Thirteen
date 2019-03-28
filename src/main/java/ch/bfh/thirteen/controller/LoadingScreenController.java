package main.java.ch.bfh.thirteen.controller;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import main.java.ch.bfh.thirteen.model.FieldLabel;

public class LoadingScreenController {
    @FXML
    public void onClick(MouseEvent event){
        if(event.getSource() instanceof FieldLabel){
            FieldLabel fl = ((FieldLabel)event.getSource());
            fl.setTextAndClass(String.valueOf(Integer.parseInt(fl.getText())+1));
        }
    }
}
