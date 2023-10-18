package SudokuGame;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameScene {

    public static Scene create(Stage stage, Difficulty difficulty) {
        VBox root = new VBox(10);
        GridPane boardPane = new GridPane();

        SudokuGenerator generator = new SudokuGenerator();
        SudokuGenerator.BoardPair boardPair = generator.generate(difficulty);
        int[][] fullBoard = boardPair.fullBoard;
        int[][] gameBoard = boardPair.gameBoard;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField cell = new TextField();
                cell.setPrefWidth(40);
                cell.setPrefHeight(40);

                // Center text in the cell
                cell.setAlignment(javafx.geometry.Pos.CENTER);

                // Add border
                cell.setStyle("-fx-border-color: black;");

                cell.textProperty().addListener((observable, oldValue, newValue) -> {
                    if (!newValue.matches("\\d") || newValue.equals("0")) {
                        if (!oldValue.equals(newValue)) {
                            cell.setText(oldValue);
                        }
                    } else {
                        cell.setStyle("-fx-background-color: white; -fx-border-color: black;");
                    }
                });

                cell.setOnMouseClicked(event -> {
                    if (cell.isEditable()) {
                        cell.clear();
                        cell.setStyle("-fx-background-color: white; -fx-border-color: black;");
                    }
                });

                if (gameBoard[i][j] != 0) {
                    cell.setText(Integer.toString(gameBoard[i][j]));
                    cell.setEditable(false);
                    cell.setStyle("-fx-background-color: #D3D3D3; -fx-border-color: black;");
                }
                boardPane.add(cell, j, i);
            }
        }

        Button checkButton = new Button("Check");
        checkButton.setOnAction(e -> {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    TextField cell = (TextField) boardPane.getChildren().get(i * 9 + j);
                    int value = cell.getText().isEmpty() ? 0 : Integer.parseInt(cell.getText());
                    if (value != fullBoard[i][j] && cell.isEditable()) {
                        cell.setStyle("-fx-background-color: red; -fx-border-color: black;");
                    }
                }
            }
        });

        Button restartButton = new Button("Restart");
        restartButton.setOnAction(e -> {
            // Reinitialize the game with the same difficulty
            stage.setScene(create(stage, difficulty));
        });

        Button exitButton = new Button("Exit to Main Menu");
        exitButton.setOnAction(e -> {
            stage.setScene(MainMenuScene.create(stage));
        });

        root.getChildren().addAll(boardPane, checkButton, restartButton, exitButton);
        return new Scene(root, 400, 500);
    }
}