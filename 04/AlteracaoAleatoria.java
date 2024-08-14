import java.util.Random;
import java.util.Scanner;

public class AlteracaoAleatoria {
    // Referência constante ao objeto Random para evitar ter que passá-lo como
    // parâmetro a cada método.
    public static final Random GERADOR = new Random();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input;

        GERADOR.setSeed(4);

        input = sc.nextLine();
        while (!input.equals("FIM")) {
            System.out.println(codificar(input));
            input = sc.nextLine();
        }

        sc.close();
    }

    public static String codificar(String texto) {
        String out = new String();
        char orig = charAleatorio(); // Caractere que mudaremos.
        char alvo = charAleatorio(); // Caractere que o substituirá.

        for (int i = 0; i < texto.length(); ++i) {
            char c = texto.charAt(i);
            // Se o caractere for `orig`, substituimos por `alvo`.
            out += (c == orig) ? alvo : c;
        }

        return out;
    }

    // Método copiado verbatim do enunciado.
    public static char charAleatorio() {
        return (char) ('a' + (Math.abs(GERADOR.nextInt()) % 26));
    }
}
