import java.util.Scanner;

public class Palindromo
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (strcmp(input, "FIM") != 0) {
            System.out.println(isPalindromo(input) ? "SIM" : "NAO");
            input = sc.nextLine();
        }

        sc.close();
    }

    public static boolean isPalindromo(String str)
    {
        int esq = 0;
        int dir = str.length() - 1;
        boolean ret = true;

        while (esq < dir)
            if (str.charAt(esq++) != str.charAt(dir--))
                ret = false;
        return ret;
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
