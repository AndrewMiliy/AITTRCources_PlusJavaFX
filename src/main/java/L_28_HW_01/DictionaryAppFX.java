package L_28_HW_01;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DictionaryAppFX extends Application {

    private Dictionary dictionary = new Dictionary();
    private Dictionary.Translator translator = dictionary.new Translator();

    private TextField wordField = new TextField();
    private TextField translationField = new TextField();
    private TextArea dictionaryDisplay = new TextArea();
    private TextField inputTextField = new TextField();
    private TextArea translationResultArea = new TextArea();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Dictionary Translator");

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        gridPane.add(new Label("Word:"), 0, 0);
        gridPane.add(wordField, 1, 0);
        gridPane.add(new Label("Translation:"), 0, 1);
        gridPane.add(translationField, 1, 1);

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> handleAddTranslation());
        gridPane.add(addButton, 1, 2);

        dictionaryDisplay.setEditable(false);
        dictionaryDisplay.setPrefHeight(200);

        VBox topBox = new VBox(10, gridPane, dictionaryDisplay);

        inputTextField.setPromptText("Enter words to translate...");
        Button translateButton = new Button("Translate");
        translateButton.setOnAction(e -> handleTranslate());

        VBox bottomBox = new VBox(10, inputTextField, translateButton, translationResultArea);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(10));
        borderPane.setTop(topBox);
        borderPane.setBottom(bottomBox);

        Scene scene = new Scene(borderPane, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleAddTranslation() {
        String word = wordField.getText().trim();
        String translation = translationField.getText().trim();
        if (!word.isEmpty() && !translation.isEmpty() && !dictionary.contains(word)) {
            dictionary.put(word, translation);
            dictionaryDisplay.appendText(word + " - " + translation + "\n");
            wordField.clear();
            translationField.clear();
        }
    }

    private void handleTranslate() {
        String inputText = inputTextField.getText();
        String translation = translator.translate(inputText);
        translationResultArea.setText(translation);
    }
}
