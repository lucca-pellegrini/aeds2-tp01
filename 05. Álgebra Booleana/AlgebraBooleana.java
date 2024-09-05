import java.util.Scanner;

public class AlgebraBooleana
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // Lê o número de variáveis.

        while (n != 0) {
            // Lê as variáveis booleanas.
            int[] entradas = new int[n];
            for (int i = 0; i < n; ++i)
                entradas[i] = sc.nextInt();

            // Lê a expressão booleanas.
            String expr = sc.nextLine();

            /* for (int i : entradas)
                System.err.print(i + " ");
            System.err.println();
            System.err.println(expr); */

            expr = tokeniza(expr); // Preprocessa a expressão.
            expr = insere(expr, entradas); // Insere as entradas.

            // System.err.println(expr);

            String fila = shuntingYard(expr); // Constrói a fila, como em RPN.

            // System.err.println(fila);

            boolean res = avaliaRPN(fila); // Avalia a fila RPN booleana.

            // System.err.println(fila + '\n');

            System.out.println(res ? 1 : 0);

            n = sc.nextInt(); // Lê o próximo número de variáveis.
        }

        sc.close();
    }

    // Recebe uma expressão e a converte para ser fácil de iterar.
    public static String tokeniza(String in)
    {
        String out = new String();

        for (int i = 0; i < in.length(); ++i) {
            switch (in.charAt(i)) {
            // Tokens que correspondem às operações booleanas.
            case 'n': // Not.
                out += '~'; // Pula o 'ot'.
                i += 2;
                break;

            // Operações que podem receber muitos parâmetros devem ser tratadas
            // separadamente. Usaremos tokens adicionais para indicar a
            // quantidade.
            case 'o': // Or.
                /* System.err.println("Encontrei um Or com " + numParams(in, i) +
                                   " parâmetros."); */
                out += 'v';
                out += numParams(in, i);
                i += 1; // Pula o 'r'.
                break;
            case 'a': // And.
                /* System.err.println("Encontrei um And com " + numParams(in, i) +
                                   " parâmetros."); */
                out += '^';
                out += numParams(in, i);
                i += 2; // Pula o 'nd'.
                break;

            // Caracteres que serão ignorados.
            case ' ':
                break;

            // Comportamento padrão: copie os demais caracteres.
            default:
                out += in.charAt(i);
                break;
            }
        }

        return out;
    }

    // Função auxiliar que recebe uma expressão tokenizada e um índice e
    // retorna quantos parâmetros são passados à função na expressão na posição
    // apontada pelo índice. Ex.: se expr for "not(or(A, B, C))" e i for 4,
    // devemos retornar 3, que é o número de parâmetros da função na posição 4
    // (`or`). Não funcionará com 10 ou mais parâmetros, pois não sei como
    // adaptar o Shunting Yard para isso.
    public static int numParams(String expr, int i)
    {
        int ret = 0; // Contador de parâmetros, que retornaremos.
        int parens = 0; // Contador de parenteses.
        int len = expr.length();
        boolean inParametro = false; // Indica se estamos num parâmetro.

        // Encontra a abertura inicial de parênteses.
        while (i < len && expr.charAt(i) != '(')
            ++i;

        if (i >= len)
            ret = 0; // Não encontramos nenhum parênteses.

        ++i; // Avança uma posição, para o primeiro parâmetro.

        // Itera sobre o restante da expressão.
        for (int j = i; j < len; ++j) {
            char c = expr.charAt(j);

            if (c == '(') {
                ++parens;
            } else if (c == ')') {
                if (parens == 0) { // Chegamos ao parêntese final.
                    if (inParametro)
                        ++ret;
                    j = len; // Quebra o loop.
                } else {
                    parens--;
                }
            } else if (c == ',' && parens == 0) {
                // Vírgula no mesmo nível significa que há um novo parâmetro.
                if (inParametro) {
                    ++ret;
                    inParametro = false;
                }
            } else if (parens == 0) {
                // Qualquer outro caractere significa que estamos num
                // parâmetro.
                inParametro = true;
            }
        }

        /*
         * // Se ainda estamos num parâmetro, é necessário contá-lo.
         * if (inParametro)
         * ret++;
         */

        return ret;
    }

    // Recebe a expressão tokenizada e a lista de valores binários e retorna a
    // expressão tokenizada com os valores inseridos.
    public static String insere(String expr, int[] vals)
    {
        String out = new String();

        for (int i = 0; i < expr.length(); ++i) {
            char tok = expr.charAt(i);

            if (tok >= 'A' && tok <= 'Z')
                out += vals[tok - 'A'];
            else
                out += tok;
        }

        return out;
    }

    // Implementação do algoritmo Shunting Yard para esse problema.
    // De <https://en.wikipedia.org/wiki/Shunting_yard_algorithm>
    public static String shuntingYard(String expr)
    {
        // Não consegui descobrir como implementar essa Stack como um array.
        String out = new String(); // Fila de output.
        char[] op = new char[expr.length()]; // Stack de operadores.
        int ind = -1; // Índice da stack.

        for (int i = 0; i < expr.length(); ++i) { // Enquanto houver tokens...
            char tok = expr.charAt(i); // ...leia um token.

            switch (tok) { // Se o token for...
            case '0', '1': // ...um literal:
                out += tok; // coloca-o na fila de saída.
                break;
            case '~': // ...uma função simples:
                op[++ind] = tok; // apende-o à stack de operadores.
                break;
            case '^', 'v': // ...uma função variádica:
                op[++ind] = expr.charAt(++i); // apende o número de parâmetros à stack,
                op[++ind] = tok; // e apende o token à stack.
                break;
            case ',': // ...um separador de parâmetros:
                // enquanto o operador no topo da stack não for `(`:
                while (op[ind] != '(')
                    out += op[ind--]; // insere-o daí à fila de saída.
                break;
            case '(': // ...abertura de parênteses:
                op[++ind] = tok; // apende-o à stack de operadores.
                break;
            case ')': // ...fechamento de parênteses:
                // enquanto o operador no topo da stack não for `(`:
                while (op[ind] != '(') {
                    assert ind >= 0 : "Erro de parênteses!";
                    out += op[ind--]; // insere-o daí à fila de saída.
                }

                assert op[ind] == '(';
                --ind; // Descarta o `(` no topo da stack.

                // Se há uma função no topo da stack:
                switch (op[ind]) {
                case '^', 'v', '~':
                    // insere-a daí à fila de saída.
                    out += op[ind--];
                    break;
                }
                break;
            }
        }

        // Terminados os tokens, apende os itens restantes na stack à fila.
        while (ind >= 0) {
            assert op[ind] != '(' : "Erro de parênteses!";
            out += op[ind--];
        }

        return out.toString(); // Converte vetor de char para String.
    }

    public static boolean avaliaRPN(String fila)
    {
        boolean[] ops = new boolean[fila.length()]; // Nossa Stack™ da Shopee.
        int ind = -1; // O “índice” da nossa “Stack”.

        // Itera sobre cada item na fila.
        for (int i = 0; i < fila.length(); ++i) {
            char tok = fila.charAt(i); // Extrai token da fila.

            if (tok == '0' || tok == '1') { // Se for valor, insere na Stack.
                ops[++ind] = (tok == '1') ? true : false;
            } else if (tok == '~') { // Caso contrário, se for operador NOT...
                ops[ind] = (ops[ind]) ? false : true; // Nega o valor no topo.
            } else { // Caso contrário, é operador variádico.
                boolean res; // Resultado da operação.
                int n = fila.charAt(++i) - '0'; // Número de operandos.

                // System.err.println("Esse operador " + tok + " quer " + n + " operandos.");

                // Verifica qual é o operador.
                switch (tok) {
                case '^': // É um AND.
                    res = true; // Inicializa o resultado.

                    // Extrai os `n` operandos e os computa.
                    for (int j = 0; j < n; ++j)
                        res = ops[ind--] && res;

                    break;

                case 'v': // É um OR.
                    res = false; // Inicializa o resultado.

                    // Extrai os `n` operandos e os computa.
                    for (int j = 0; j < n; ++j)
                        res = ops[ind--] || res;

                    break;

                default:
                    throw new IllegalArgumentException("Operador inexiste!");
                }

                // Insere o resultado na Stack™.
                ops[++ind] = res;
            }
        }

        return ops[0]; // O elemento que sobra na “Stack” é o resultado.
    }
}
