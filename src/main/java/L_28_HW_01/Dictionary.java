package L_28_HW_01;

import java.util.HashMap;
import java.util.Map;

public class Dictionary {
    private Map<String, String> translations = new HashMap<>();

    public void put(String word, String translation) {
        translations.put(word, translation);
        translations.put(translation, word);  // Добавляем обратный перевод
    }

    public boolean contains(String word) {
        return translations.containsKey(word);
    }

    public String get(String word) {
        return translations.get(word);
    }

    public class Translator {
        public String translate(String phrase) {
            String[] words = phrase.split("\\s+");
            for (int i = 0; i < words.length; i++) {
                String translatedWord = translations.get(words[i]);
                words[i] = translatedWord != null ? translatedWord : words[i];
            }
            return String.join(" ", words);
        }
    }
}
