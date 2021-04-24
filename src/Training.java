import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Training {

    /*
    Frequency Map:
    Keeps track of the frequencies of different
    3-letter combinations that appear in names.
     */
    public static Map<String, Integer> indianMap = new HashMap<>();
    public static Map<String, Integer> spanishMap = new HashMap<>();
    public static Integer indianNamesCount = 0;
    public static Integer spanishNamesCount = 0;

    public static void main(String[] args) {

        InputStream indianStream = Training.class.getResourceAsStream("Indian_Names.csv");
        InputStream spanishStream = Training.class.getResourceAsStream("surnames_freq_ge_100.csv");
        if(indianStream == null) {
            throw new IllegalArgumentException("provided an invalid file name");
        }

        if(spanishStream == null) {
            throw new IllegalArgumentException("provided an invalid file name");
        }

        Reader indianReader = new BufferedReader(new InputStreamReader(indianStream));
        Reader spanishReader = new BufferedReader(new InputStreamReader(spanishStream));

        parseIndian(indianReader, indianMap);
        parseSpanish(spanishReader, spanishMap);
        System.out.println("indianNamesCount = " + indianNamesCount);
        System.out.println("spanishNamesCount = " + spanishNamesCount);
    }

    private static void parseIndian(Reader reader, Map<String, Integer> map) {
        Scanner sc = new Scanner(reader);
        int count = 0;
        while (sc.hasNext()) {
            String str = sc.nextLine();
            String name = str.substring(str.indexOf(',') + 1);
            System.out.println(name);

            fit(name, map);
            count++;
        }
        indianNamesCount = count;
    }

    private static void parseSpanish(Reader reader, Map<String, Integer> map) {
        Scanner sc = new Scanner(reader);
        int count = 0;
        while (sc.hasNext()) {
            String str = sc.nextLine();
            String name = str.substring(0, str.indexOf(','));
            System.out.println(name);

            fit(name, map);
            count++;
        }
        spanishNamesCount = count;
    }
    private static void fit(String name, Map<String, Integer> map) {
        for (int i = 0; i < name.length() - 3; i++) {
            String sub = name.substring(i, i + 3);
            if (map.containsKey(sub)) {
                map.put(sub, map.get(sub) + 1);
            } else {
                map.put(sub, 1);
            }
        }

    }
}

