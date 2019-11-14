import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // сосчитать частоту встречаемости слова или словосочетания в тесксте(слово передать аргументом)
        File file = new File("src/wp.txt");
        try {
            Scanner scanner = new Scanner(new FileInputStream(file));
            int count = 0;
            String search = "the";
            while (scanner.hasNext()) {
                if (scanner.next().equalsIgnoreCase(search)) count++;
            }
            System.out.println("Слово " + search + " повторяется " + count + " раз");
        } catch (FileNotFoundException a) {
            System.out.println("файл не найден");
        }
        System.out.println();

        // собрать все слова в группы по количеству букв
        try {
            Scanner scanner = new Scanner(new FileInputStream(file));
            int longestWord = 0;
            while (scanner.hasNext()) {
                if (scanner.next().length() > longestWord) longestWord = scanner.next().length();
            }
            System.out.println(longestWord);
            HashMap<Integer, String> stringHashMap = new HashMap<>();
            for (int i = 1; i < longestWord; i++) {
                while (scanner.hasNext()) {
                    if (scanner.next().length() == i) stringHashMap.put(i, scanner.next());
                }
            }
            System.out.println(stringHashMap.size());
        } catch (FileNotFoundException a) {
            System.out.println("файл не найден");
        }
    }

}
