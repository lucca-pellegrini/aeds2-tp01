import java.util.Scanner;

public class SequenciaEspelho {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextInt()) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            System.out.println(sequencia(a, b));
        }

        sc.close();
    }

    public static String sequencia(int a, int b) {
        StringBuilder res = new StringBuilder();

        for (int i = a; i <= b; ++i)
            res.append(Integer.toString(i));

        return res.append(new StringBuilder(res).reverse()).toString();
    }
}
