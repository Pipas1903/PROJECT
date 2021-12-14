package Common;

import java.util.Scanner;

public class Utils {
    public static Scanner scanString = new Scanner(System.in);
    public static Scanner scanInt = new Scanner(System.in);

    public static String convertToFileName(String name) {
        return (name + ".txt");
    }

}
