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

    public static String cifra(String texto, int i, int tam)
    {
        String cifra = new String();

        if (tam != 0) {
            char c = texto.charAt(i);
            cifra += (char)((c <= 127) ? c + CHAVE : c);
            cifra += cifra(texto + 1, i + 1, tam - 1);
        }

        return cifra;
    }

    public static String cifra(String texto)
    {
        return cifra(texto, 0, texto.length());
    }
}
