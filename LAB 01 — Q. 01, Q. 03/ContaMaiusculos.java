import java.util.Scanner;

public class ContaMaiusculos {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("FIM")) {
            System.out.println(contaMaiusculos(input));
            input = sc.nextLine();
        }

        sc.close();
    }

    public static int contaMaiusculos(String texto) {
        int count = 0;

        for (int i = 0; i < texto.length(); ++i)
            // Usa-se o método estático `isUpperCase` da classe Character.
            // Isso é necessário porque o tipo `char` não tem métodos.
            if (Character.isUpperCase(texto.charAt(i)))
                ++count;

        return count;
    }
}
