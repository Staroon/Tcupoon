package com.staroon.tcupoon.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;

import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: Staroon
 * Date: 2018/5/17
 * Time: 15:33
 */
public class AboutController implements Initializable {
    @FXML
    private Hyperlink toGit;
    @FXML
    private Hyperlink toBlog;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void toGit(ActionEvent action) throws Exception {
        java.awt.Desktop.getDesktop().browse(new URI("https://github.com/Staroon"));

    }

    public void toBlog(ActionEvent action) throws Exception {
        java.awt.Desktop.getDesktop().browse(new URI("https://staroon.pro"));
    }
}
