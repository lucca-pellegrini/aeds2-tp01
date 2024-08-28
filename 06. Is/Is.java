import java.util.Scanner;

public class Is
{
    // Strings globais com os conjuntos de caracteres de interesse.
    public static final String CONSOANTES =
        "bcdfghjklmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ";
    public static final String VOGAIS = "aeiouAEIOU";
    public static final String DIGITOS = "0123456789";

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (strcmp(input, "FIM") != 0) {
            // Salvaremos os resultados de cada método num array de boolean.
            boolean[] resultados = new boolean[4];

            // Executa os métodos e salva no array.
            resultados[0] = isSomenteVogais(input);
            resultados[1] = isSomenteConsoantes(input);
            resultados[2] = isNumeroInteiro(input);
            resultados[3] = isNumeroReal(input);

            printResultados(resultados); // Mostra os resultados.

            input = sc.nextLine();
        }

        sc.close();
    }

    // Método auxiliar. Retorna true somente se o caractere em `c` se encontra
    // na string `conjunto`.
    public static boolean isNoConjunto(char c, String conjunto)
    {
        boolean res = false;

        for (int i = 0; i < conjunto.length(); ++i)
            if (conjunto.charAt(i) == c)
                res = true;

        return res;
    }

    // Método auxiliar. Retorna true somente se todos os caracteres de `texto`
    // se encontram na string `conjunto`.
    public static boolean isAllNoConjunto(String texto, String conjunto)
    {
        boolean res = true; // Valor de retorno.

        // Itera sobre cada caractere. Se não estiver em `conjunto`, termina o
        // loop com resultado `false`.
        for (int i = 0; i < texto.length(); ++i) {
            if (!isNoConjunto(texto.charAt(i), conjunto)) {
                res = false;
                i = texto.length();
            }
        }

        return res;
    }

    public static boolean isSomenteVogais(String texto)
    {
        return isAllNoConjunto(texto, VOGAIS);
    }

    public static boolean isSomenteConsoantes(String texto)
    {
        return isAllNoConjunto(texto, CONSOANTES);
    }

    // Retorna true somente se `texto` é um número inteiro.
    public static boolean isNumeroInteiro(String texto)
    {
        boolean res = true;

        // Verifica se é o primeiro caractere, que pode ser sinal.
        for (int i = 0; i < texto.length(); ++i) {
            if (!isNoConjunto(texto.charAt(i), DIGITOS) &&
                (i != 0 ||
                 (texto.charAt(i) != '-' && texto.charAt(i) != '+'))) {
                res = false;
            }
        }

        return res;
    }

    // Retorna true somente se `texto` é um número real.
    public static boolean isNumeroReal(String texto)
    {
        boolean res = true;
        boolean found_sep = false; // Se já encontramos separador decimal.

        for (int i = 0; i < texto.length(); ++i) {
            if (!isNoConjunto(texto.charAt(i), DIGITOS)) {
                // Se não for dígito, verifica se é separador decimal.
                if (texto.charAt(i) == '.' || texto.charAt(i) == ',') {
                    if (found_sep) { // Vê se já encontramos separador decimal.
                        res = false;
                    } else {
                        found_sep = true; // Atualiza a flag.
                    }

                } else if (i != 0 ||
                           (texto.charAt(i) != '-' && texto.charAt(i) != '+')) {
                    // Verifica se é o primeiro caractere, que pode ser sinal.
                    res = false;
                }
            }
        }

        return res;
    }

    // Para cada boolean em `resultados`, printa "SIM" ou "NAO", em uma linha.
    public static void printResultados(boolean[] resultados)
    {
        // Separador. Começa vazio, mas será um espaço após o primeiro SIM/NAO.
        String sep = "";

        // Iteramos sobre cada elemento `r` do array `resultados`.
        for (boolean r : resultados) {
            System.out.print(sep + (r ? "SIM" : "NAO"));
            sep = " "; // Separaremos cada item subsequente com um espaço.
        }

        System.out.println(); // Termina a linha.
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
