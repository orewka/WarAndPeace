import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        File file = new File("src/wp.txt");
        List<String> strings = Files.readAllLines(file.toPath()); // каждая строка файла - отдельный элемент списка
        List<String> arrayList = new ArrayList<>();
        for (String string : strings) {
            String[] strings1 = string.replaceAll("\\p{Punct}", " ").trim().split(" ");
            for (String word : strings1) {
                if (word.length() > 0) {
                    arrayList.add(word);
                }
            }
        }

        // сосчитать частоту встречаемости слова или словосочетания в тесксте(слово передать аргументом)
        String searchWord = "the";
        int countWord = 0;
        for (String word : arrayList) {
            if (word.equalsIgnoreCase(searchWord)) countWord++;
        }
        System.out.println("Слово " + searchWord + " повторяется " + countWord + " раз");

        // собрать все слова в группы по количеству букв
        Map<Integer, ArrayList<String>> hashMap = new HashMap<>();
        int len;
        for (String word : arrayList) {
            len = word.length();
            if (hashMap.containsKey(len)) {
                hashMap.get(len).add(word);
            } else {
                ArrayList<String> strings1 = new ArrayList<>();
                strings1.add(word);
                hashMap.put(len, strings1);
            }
        }
        System.out.println("Количество групп слов в тексте " + hashMap.size());

        // вывести топ 10 слов
        Map<String,Integer> top10 = new TreeMap<>();
        for (String word : arrayList) {
            String tmp = word.toLowerCase();
            int count;
            if (top10.containsKey(tmp)) {
                count = top10.get(tmp);
                top10.replace(tmp,count,++count);
            } else {
                count = 1;
                top10.put(tmp, count);
            }
        }
        Map<Integer,String> top10SortRev = new TreeMap<>(Collections.reverseOrder());
        // Map<Integer,String> top10SortRev1 = ((TreeMap<Integer, String>) top10Sort).descendingMap();
        for (Map.Entry<String, Integer> entry: top10.entrySet()) {
            top10SortRev.put(entry.getValue(),entry.getKey());
        }
        int cnt = 0;
        for (Map.Entry<Integer, String> entry: top10SortRev.entrySet()) {
            System.out.println("Слово " + entry.getValue() + " повторяется " + entry.getKey() + " раз");
            cnt++;
            if (cnt == 10) break;
        }

        // вывести встечаемость букв в процентах
        List<Character> charArr = new ArrayList<>();
        for (String string : strings) {
            char[] chars = string.replaceAll("[\\p{Punct}[0-9][\\s]]","").trim().toLowerCase().toCharArray();
            for (Character s : chars) {
                    charArr.add(s);
            }
        }
        Map<Character, Integer> charsMap = new HashMap<>();
        for (Character char1 : charArr) {
            Character tmp = char1;
            int count;
            if (charsMap.containsKey(tmp)) {
                count = charsMap.get(tmp);
                charsMap.replace(tmp,count,++count);
            } else {
                count = 1;
                charsMap.put(tmp, count);
            }
        }
        float procent;
        DecimalFormatSymbols df = new DecimalFormatSymbols(Locale.getDefault());
        df.setDecimalSeparator('.');
        DecimalFormat f = new DecimalFormat("#.##'%'",df);
        for (Map.Entry<Character, Integer> entry: charsMap.entrySet()) {
            procent = (float) entry.getValue() / ((float) charArr.size() / 100);
            System.out.println(f.format(procent) + " " + entry.getKey());
        }
    }
}
