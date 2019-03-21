import java.util.Scanner;

public class Settings {
    public static final int MAX_DIMENSION = 10;
    public static final int PLAYERS_NUM = 2;
    public static final Scanner scanner = new Scanner(System.in);
    public static void output(Object o) {
        if (o instanceof String && o.toString().length() >= 1 && o.toString().charAt(o.toString().length() - 1) == ':')
            System.out.print(o);
        else
            System.out.println(o);
    }

}