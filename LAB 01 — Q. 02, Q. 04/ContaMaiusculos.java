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

    // Recebe o índice (inicialmente 0) e o tamanho (inicialmente .length())
    public static int contaMaiusculos(String texto, int i, int tam)
    {
        int ret; // Valor de retorno.

        if (tam == 0) {
            ret = 0;
        } else {
            ret = contaMaiusculos(texto + 1, i + 1, tam - 1);
            if (Character.isUpperCase(texto.charAt(i)))
                ++ret;
        }

        return ret;
    }

    // Fazemos overloading para poder passar uma String sem o tamanho em main.
    // Não usamos String.substring() porque a criação recursiva de novas
    // Strings será menos eficiente..
    public static int contaMaiusculos(String texto)
    {
        return contaMaiusculos(texto, 0, texto.length());
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
