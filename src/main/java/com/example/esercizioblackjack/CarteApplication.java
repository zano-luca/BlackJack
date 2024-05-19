package com.example.esercizioblackjack;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;

public class CarteApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CarteApplication.class.getResource("carte-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 680);
        stage.setTitle("BLACKJACK");
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}