import java.util.Scanner;

public class ParOuImpar {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();

        while (input != 0) {
            System.out.println((input % 2 != 0) ? "I" : "P");
            input = sc.nextInt();
        }

        sc.close();
    }
}
