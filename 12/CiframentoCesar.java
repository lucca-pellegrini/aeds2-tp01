import java.util.Scanner;

public class CiframentoCesar
{
    public static final char CHAVE = 3;

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (strcmp(input, "FIM") != 0) {
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

    public static int strcmp(String str, String cmp)
    {
        int ret = 0;
        int min_len = (str.length() > cmp.length()) ? cmp.length() :
                                                      str.length();

        for (int i = min_len - 1; i >= 0; --i)
            if (str.charAt(i) != cmp.charAt(i))
                ret = str.charAt(i) - cmp.charAt(i);

        return ret;
    }
}
