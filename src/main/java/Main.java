import java.util.Scanner;

public class Main {
    private static int count = 0;
    private static char[] s;

    private static char at(int i) {
        return count % 2 == 0 ? s[i] : (s[i] == '0' ? '1' : '0');
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        s = line.toCharArray();

        int i = s.length - 1;
        while (i >= 0) {
            if (at(i) == '0') {
                while (i >= 0 && at(i) == '0') i--;
            } else {
                while (i >= 0 && at(i) != '0') i--;
                count++;
            }
        }

        System.out.println(count);
    }
}