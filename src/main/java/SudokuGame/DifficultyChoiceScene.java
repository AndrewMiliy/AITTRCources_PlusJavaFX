package SudokuGame;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DifficultyChoiceScene {
    public static Scene create(Stage stage) {
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);

        Button easyButton = new Button("Easy");
        easyButton.setOnAction(e -> stage.setScene(GameScene.create(stage, Difficulty.EASY)));

        Button mediumButton = new Button("Medium");
        mediumButton.setOnAction(e -> stage.setScene(GameScene.create(stage, Difficulty.MEDIUM)));

        Button hardButton = new Button("Hard");
        hardButton.setOnAction(e -> stage.setScene(GameScene.create(stage, Difficulty.HARD)));

        root.getChildren().addAll(easyButton, mediumButton, hardButton);

        return new Scene(root, 300, 300);
    }
}
