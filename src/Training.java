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

    public static final double TOTAL_INDIAN_NAMES = 6486;
    public static final double TOTAL_SPANISH_NAMES = 25400;
    public static final double TOTAL_NAMES = TOTAL_INDIAN_NAMES + TOTAL_SPANISH_NAMES;

    public static final String INDIAN_LABEL = "Indian";
    public static final String NON_INDIAN_LABEL = "Not Indian";

    public static void main(String[] args) {

        InputStream indianStream = Training.class.getResourceAsStream("/train/train_indian_names.txt");
        InputStream spanishStream = Training.class.getResourceAsStream("/train/train_spanish_names.txt");
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

        test();

        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("Enter a name:");

            String name = input.nextLine();

            System.out.println(predict(name));
            System.out.println();
            System.out.println();
        }


    }

    private static void parseIndian(Reader reader, Map<String, Integer> map) {
        Scanner sc = new Scanner(reader);
        int count = 0;
        while (sc.hasNext()) {
            String str = sc.nextLine();
            String name = str;
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
            String name = str;
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

    private static String predict(String name) {
        double p_indian = Math.log(TOTAL_INDIAN_NAMES / TOTAL_NAMES);
        double p_spanish = Math.log(TOTAL_SPANISH_NAMES / TOTAL_NAMES);

        double total_p_indian = 0;
        double total_p_spanish = 0;
        for (int i = 0; i < name.length() - 3; i++) {
            String sub = name.substring(i, i + 3);
            total_p_indian += Math.log(((double) (indianMap.getOrDefault(sub, 0) + 1)) / ((double) (TOTAL_INDIAN_NAMES + 2)));
            total_p_spanish += Math.log(((double) (spanishMap.getOrDefault(sub, 0) + 1)) / ((double) (TOTAL_SPANISH_NAMES + 2)));
        }

        double indian_score = total_p_indian + p_indian;
        double spanish_score = total_p_spanish + p_indian;

        if (indian_score >= spanish_score) {
            return INDIAN_LABEL;
        } else {
            return NON_INDIAN_LABEL;
        }
    }

    private static void test() {
        int indian_correct = 0;
        int indian_total = 0;
        int spanish_correct = 0;
        int spanish_total = 0;

        InputStream indianStream = Training.class.getResourceAsStream("/test/test_indian_names.txt");
        InputStream spanishStream = Training.class.getResourceAsStream("/test/test_spanish_names.txt");
        if(indianStream == null) {
            throw new IllegalArgumentException("provided an invalid file name");
        }

        if(spanishStream == null) {
            throw new IllegalArgumentException("provided an invalid file name");
        }

        Reader indianReader = new BufferedReader(new InputStreamReader(indianStream));
        Reader spanishReader = new BufferedReader(new InputStreamReader(spanishStream));

        Scanner indianSC = new Scanner(indianReader);
        Scanner spanishSC = new Scanner(spanishReader);

        while (indianSC.hasNext()) {
            String name = indianSC.nextLine();
            indian_total++;

            if (predict(name).equals(INDIAN_LABEL)) {
                indian_correct++;
            }
        }

        while (spanishSC.hasNext()) {
            String name = spanishSC.nextLine();
            spanish_total++;

            if (predict(name).equals(NON_INDIAN_LABEL)) {
                spanish_correct++;
            }
        }

        System.out.println("Testing Accuracy :" + ((double) indian_correct + (double) spanish_correct)
                / ((double) indian_total + (double) spanish_total));

    }




}

