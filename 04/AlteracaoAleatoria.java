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
        StringBuilder out = new StringBuilder(texto.length());
        char orig = charAleatorio(GERADOR); // Caractere que mudaremos.
        char alvo = charAleatorio(GERADOR); // Caractere que o substituirá.

        for (int i = 0; i < texto.length(); ++i) {
            char c = texto.charAt(i);
            c = (c == orig) ? alvo : c; // Se for `orig`, substituimos por alvo
            out.append(c);
        }

        return out.toString();
    }

    // Método copiado verbatim do enunciado.
    public static char charAleatorio(Random GERADOR) {
        return (char) ('a' + (Math.abs(GERADOR.nextInt()) % 26));
    }
}
