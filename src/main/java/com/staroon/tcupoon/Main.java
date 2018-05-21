package com.staroon.tcupoon;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: Staroon
 * Date: 2018/5/17
 * Time: 8:58
 * To change this template use File | Settings | File Templates.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL location = getClass().getResource("/Main.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("Tcupoon");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
