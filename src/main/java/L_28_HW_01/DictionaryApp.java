package L_28_HW_01;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class DictionaryApp {
    private Dictionary dictionary = new Dictionary();
    private Dictionary.Translator translator = dictionary.new Translator();

    private JFrame frame;
    private JTextField wordField;
    private JTextField translationField;
    private JTextArea dictionaryDisplay;
    private JTextField inputTextField;
    private JTextArea translationResultArea;

    public DictionaryApp() {
        frame = new JFrame("Dictionary Translator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new GridLayout(2, 1));

        // Panel for adding translations and displaying the dictionary
        JPanel dictionaryPanel = new JPanel(new BorderLayout());
        JPanel addTranslationPanel = new JPanel();
        wordField = new JTextField(10);
        translationField = new JTextField(10);
        JButton addButton = new JButton("Add");
        addButton.addActionListener(this::handleAddTranslation);
        addTranslationPanel.add(new JLabel("Word:"));
        addTranslationPanel.add(wordField);
        addTranslationPanel.add(new JLabel("Translation:"));
        addTranslationPanel.add(translationField);
        addTranslationPanel.add(addButton);

        dictionaryDisplay = new JTextArea(10, 40);
        dictionaryDisplay.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(dictionaryDisplay);

        dictionaryPanel.add(addTranslationPanel, BorderLayout.NORTH);
        dictionaryPanel.add(scrollPane, BorderLayout.CENTER);

        frame.add(dictionaryPanel);

        // Panel for translating words
        JPanel translationPanel = new JPanel(new BorderLayout());
        inputTextField = new JTextField();
        JButton translateButton = new JButton("Translate");
        translateButton.addActionListener(this::handleTranslate);
        translationResultArea = new JTextArea(3, 40);
        translationResultArea.setEditable(false);

        translationPanel.add(inputTextField, BorderLayout.NORTH);
        translationPanel.add(translateButton, BorderLayout.CENTER);
        translationPanel.add(new JScrollPane(translationResultArea), BorderLayout.SOUTH);

        frame.add(translationPanel);

        frame.setVisible(true);
    }

    private void handleAddTranslation(ActionEvent e) {
        String word = wordField.getText().trim();
        String translation = translationField.getText().trim();
        if (!word.isEmpty() && !translation.isEmpty() && !dictionary.contains(word)) {
            dictionary.put(word, translation);
            dictionaryDisplay.append(word + " - " + translation + "\n");
            wordField.setText("");
            translationField.setText("");
        }
    }

    private void handleTranslate(ActionEvent e) {
        String inputText = inputTextField.getText();
        String translation = translator.translate(inputText);
        translationResultArea.setText(translation);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DictionaryApp::new);
    }
}
