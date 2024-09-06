import java.util.Scanner;

public class Is
{
    // Strings globais com os conjuntos de caracteres de interesse.
    public static final String CONSOANTES = "bcdfghjklmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ";
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
    public static boolean isNoConjunto(char c, String conjunto, int i)
    {
        boolean res = false;

        if (i >= conjunto.length())
            res = false;
        else if (conjunto.charAt(i) == c)
            res = true;
        else
            res = isNoConjunto(c, conjunto, i + 1);

        return res;
    }

    // Método auxiliar. Retorna true somente se todos os caracteres de `texto`
    // se encontram na string `conjunto`.
    public static boolean isAllNoConjunto(String texto, String conjunto, int i)
    {
        boolean res = true; // Valor de retorno.

        if (i >= texto.length())
            res = true;
        else if (isNoConjunto(texto.charAt(i), conjunto))
            res = isAllNoConjunto(texto, conjunto, i + 1);
        else
            res = false;

        return res;
    }

    public static boolean isSomenteVogais(String texto)
    {
        return isAllNoConjunto(texto, VOGAIS, 0);
    }

    public static boolean isSomenteConsoantes(String texto)
    {
        return isAllNoConjunto(texto, CONSOANTES, 0);
    }

    // Retorna true somente se `texto` é um número inteiro.
    public static boolean isNumeroInteiro(String texto, int i)
    {
        boolean res = true;

        if (i >= texto.length())
            res = true;
        else if (!isNoConjunto(texto.charAt(i), DIGITOS) &&
                 // Verifica se é o primeiro caractere, que pode ser sinal.
                 (i != 0 || (texto.charAt(i) != '-' && texto.charAt(i) != '+')))
            res = false;
        else
            res = isNumeroInteiro(texto, i + 1);

        return res;
    }

    // Retorna true somente se `texto` é um número real.
    public static boolean isNumeroReal(String texto, int i, boolean found_sep)
    {
        boolean res = true;

        if (i >= texto.length()) {
            res = true;
        } else if (!isNoConjunto(texto.charAt(i), DIGITOS)) {
            // Se não for dígito, verifica se é separador decimal.
            if (texto.charAt(i) == '.' || texto.charAt(i) == ',') {
                if (found_sep) { // Vê se já encontramos separador decimal.
                    res = false;
                } else {
                    found_sep = true; // Atualiza a flag.
                    res = isNumeroReal(texto, i + 1, found_sep);
                }
            } else if (i != 0 || (texto.charAt(i) != '-' && texto.charAt(i) != '+')) {
                // Verifica se é o primeiro caractere, que pode ser sinal.
                res = false;
            } else {
                res = isNumeroReal(texto, i + 1, found_sep);
            }
        } else {
            res = isNumeroReal(texto, i + 1, found_sep);
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

    public static boolean isNoConjunto(char c, String conjunto)
    {
        return isNoConjunto(c, conjunto, 0);
    }

    public static boolean isNumeroInteiro(String texto)
    {
        return isNumeroInteiro(texto, 0);
    }

    public static boolean isNumeroReal(String texto)
    {
        return isNumeroReal(texto, 0, false);
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
