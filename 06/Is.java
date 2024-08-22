import java.util.Scanner;

public class Is
{
    // Strings globais com os conjuntos de caracteres de interesse.
    public static final String CONSOANTES = "bcdfghjklmnpqrstvwxyz";
    public static final String VOGAIS = "aeiou";

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (strcmp(input, "FIM") != 0) {
            // Salvaremos os resultados de cada método num array de boolean.
            boolean[] resultados = new boolean[4];

            input = input.toLowerCase(); // Converte a input para minúsculas.

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

    // Método auxiliar. Retorna true somente se todos os caracteres de `texto`
    // se encontram na string `conjunto`.
    public static boolean isInteiroNoConjunto(String texto, String conjunto)
    {
        boolean res = true; // Valor de retorno.

        // Itera sobre cada caractere. Se não estiver em `conjunto`, termina o
        // loop com resultado `false`.
        for (int i = 0; i < texto.length(); ++i) {
            if (conjunto.indexOf(texto.charAt(i)) == -1) {
                res = false;
                i = texto.length();
            }
        }

        return res;
    }

    public static boolean isSomenteVogais(String texto)
    {
        return isInteiroNoConjunto(texto, VOGAIS);
    }

    public static boolean isSomenteConsoantes(String texto)
    {
        return isInteiroNoConjunto(texto, CONSOANTES);
    }

    // Retorna true somente se `texto` é um número inteiro.
    public static boolean isNumeroInteiro(String texto)
    {
        boolean res = true;

        // Tenta ler `texto` como objeto Inteiro. Se não conseguir por conta de
        // formatação numérica errada, não é inteiro.
        try {
            Integer.parseInt(texto);
        } catch (NumberFormatException e) {
            res = false;
        }

        return res;
    }

    // Retorna true somente se `texto` é um número real.
    public static boolean isNumeroReal(String texto)
    {
        boolean res = true;

        // Tenta ler `texto` como objeto Double. Se não conseguir por conta de
        // formatação numérica errada, não é número real. Mas é necessário,
        // primeiro, substituir quaisquer vírgulas no texto por pontos.
        try {
            Double.parseDouble(texto.replaceAll(",", "."));
        } catch (NumberFormatException e) {
            res = false;
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
