package SudokuGame;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenuScene {
    public static Scene create(Stage stage) {
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);

        Button playButton = new Button("Play");
        playButton.setOnAction(e -> stage.setScene(DifficultyChoiceScene.create(stage)));

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e -> System.exit(0));

        root.getChildren().addAll(playButton, exitButton);

        return new Scene(root, 300, 300);
    }
}
