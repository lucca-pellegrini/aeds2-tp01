import java.util.Scanner;

public class ContaMaiusculos
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (strcmp(input, "FIM") != 0) {
            System.out.println(contaMaiusculos(input));
            input = sc.nextLine();
        }

        sc.close();
    }

    public static int contaMaiusculos(String texto)
    {
        int count = 0;

        for (int i = 0; i < texto.length(); ++i)
            // Usa-se o método estático `isUpperCase` da classe Character.
            // Isso é necessário porque o tipo `char` não tem métodos.
            if (Character.isUpperCase(texto.charAt(i)))
                ++count;

        return count;
    }

    public static int strcmp(String str, String cmp)
    {
        int ret = 0;
        int min_len = (str.length() > cmp.length()) ? cmp.length() : str.length();

        for (int i = min_len - 1; i >= 0; --i)
            if (str.charAt(i) != cmp.charAt(i))
                ret = str.charAt(i) - cmp.charAt(i);

        return ret;
    }
}
