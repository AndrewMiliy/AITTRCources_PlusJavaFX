package SudokuGame;

import javafx.application.Application;
import javafx.stage.Stage;

public class SudokuApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sudoku Game");
        primaryStage.setScene(MainMenuScene.create(primaryStage));
        primaryStage.show();
    }
}
