import java.util.Scanner;

public class CiframentoCesar
{
    public static final char CHAVE = 3;

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("FIM")) {
            System.out.println(cifra(input));
            input = sc.nextLine();
        }

        sc.close();
    }

    public static String cifra(String texto)
    {
        String cifra = new String();

        for (int i = 0; i < texto.length(); ++i) {
            char c = texto.charAt(i);
            // Se o caractere for ASCII (0–127), deve ser somado à chave.
            cifra += (char)((c <= 127) ? c + CHAVE : c);
        }

        return cifra;
    }
}
