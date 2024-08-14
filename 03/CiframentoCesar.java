import java.util.Scanner;

public class CiframentoCesar {
    public static final char CHAVE = 3;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("FIM")) {
            System.out.println(cifra(input));
            input = sc.nextLine();
        }

        sc.close();
    }

    public static String cifra(String texto) {
        // Caracteres em um objeto StringBuilder podem ser modificados.
        StringBuilder cifra = new StringBuilder(texto.length());

        for (int i = 0; i < texto.length(); ++i) {
            char c = texto.charAt(i);
            c += (c <= 127) ? CHAVE : 0; // Se for ASCII (0–127), soma à chave.
            cifra.append(c);
        }

        return cifra.toString();
    }
}
