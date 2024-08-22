import java.util.Scanner;

public class SequenciaEspelho
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextInt()) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            System.out.println(sequencia(a, b));
        }

        sc.close();
    }

    public static String sequencia(int a, int b)
    {
        String res = new String();

        // Itera sobre a sequência, adicionando os elementos à string.
        for (int i = a; i <= b; ++i)
            res += i;

        // Itera string ao contrário, inserindo os caracteres em ordem reversa.
        for (int i = res.length() - 1; i >= 0; --i)
            res += res.charAt(i);

        return res;
    }
}
