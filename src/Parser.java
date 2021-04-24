import java.io.*;
import java.util.*;

public class Parser {

    public static Integer trainIndianNamesCount = 0;
    public static Integer trainSpanishNamesCount = 0;
    public static Integer testIndianNamesCount = 0;
    public static Integer testSpanishNamesCount = 0;

    public static final String TRAIN_PATH = "src/train/";
    public static final String TEST_PATH = "src/test/";


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

        parseIndian(indianReader);
        parseSpanish(spanishReader);
        System.out.println("trainIndianNamesCount = " + trainIndianNamesCount);
        System.out.println("trainSpanishNamesCount = " + trainSpanishNamesCount);
        System.out.println("testIndianNamesCount = " + testIndianNamesCount);
        System.out.println("testSpanishNamesCount = " + testSpanishNamesCount);
    }

    private static void parseIndian(Reader reader) {
        FileWriter trainWriter = null;
        FileWriter testWriter = null;
        try {
            trainWriter = new FileWriter(TRAIN_PATH + "train_indian_names.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            testWriter = new FileWriter(TEST_PATH + "test_indian_names.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner sc = new Scanner(reader);
        while (sc.hasNext()) {
            String str = sc.nextLine();
            String name = str.substring(str.indexOf(',') + 1);
            System.out.println(name);
            
            int random = (int) (Math.random() * 10);

            //allocate 30% of the data for training, rest for testing
            if (random >= 7) {
                try {
                    trainWriter.write(name + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                trainIndianNamesCount++;
            } else {
                try {
                    testWriter.write(name + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                testIndianNamesCount++;
            }

        }
    }

    private static void parseSpanish(Reader reader) {
        FileWriter trainWriter = null;
        FileWriter testWriter = null;
        try {
            trainWriter = new FileWriter(TRAIN_PATH + "train_spanish_names.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            testWriter = new FileWriter(TEST_PATH + "test_spanish_names.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scanner sc = new Scanner(reader);
        int count = 0;
        while (sc.hasNext()) {
            String str = sc.nextLine();
            String name = str.substring(0, str.indexOf(','));
            System.out.println(name);

            int random = (int) (Math.random() * 10);

            //allocate 30% of data for training, rest for testing.
            if (random >= 7) {
                try {
                    trainWriter.write(name + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                trainSpanishNamesCount++;
            } else {
                try {
                    testWriter.write(name + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                testSpanishNamesCount++;
            }
        }
    }

}

