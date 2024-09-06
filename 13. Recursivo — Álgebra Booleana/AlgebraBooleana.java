import java.util.Scanner;

public class AlgebraBooleana
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // Lê o número de variáveis.

        processInput(n, sc);

        sc.close();
    }

    public static void processInput(int n, Scanner sc)
    {
        if (n == 0)
            return;

        // Lê as variáveis booleanas.
        int[] entradas = new int[n];
        for (int i = 0; i < n; ++i)
            entradas[i] = sc.nextInt();

        sc.nextLine(); // Consome a nova linha após as entradas.

        // Lê a expressão booleanas.
        String expr = sc.nextLine();

        expr = tokeniza(expr); // Preprocessa a expressão.
        expr = insere(expr, entradas); // Insere as entradas.

        String fila = shuntingYard(expr); // Constrói a fila, como em RPN.

        boolean res = avaliaRPN(fila); // Avalia a fila RPN booleana.

        System.out.println(res ? 1 : 0);

        n = sc.nextInt(); // Lê o próximo número de variáveis.
        // Chamada recursiva para o próximo conjunto de entradas.
        processInput(n, sc);
    }

    public static String tokeniza(String in)
    {
        return tokeniza(in, 0, new String());
    }

    private static String tokeniza(String in, int index, String out)
    {
        if (index >= in.length())
            return out;

        switch (in.charAt(index)) {
        case 'o': // Or.
            out += 'v';
            return tokeniza(in, index + 2, out);
        case 'a': // And.
            out += '^';
            return tokeniza(in, index + 3, out);
        case 'n': // Not.
            out += '~';
            return tokeniza(in, index + 3, out);
        case ' ':
            return tokeniza(in, index + 1, out);
        default:
            out += in.charAt(index);
            return tokeniza(in, index + 1, out);
        }
    }

    public static String insere(String expr, int[] vals)
    {
        return insere(expr, vals, 0, new String());
    }

    private static String insere(String expr, int[] vals, int index, String out)
    {
        if (index >= expr.length())
            return out;

        char tok = expr.charAt(index);
        if (tok >= 'A' && tok <= 'Z')
            out += vals[tok - 'A'];
        else
            out += tok;

        return insere(expr, vals, index + 1, out);
    }

    public static String shuntingYard(String expr)
    {
        return shuntingYard(expr, 0, new String(), new char[expr.length()], -1);
    }

    private static String shuntingYard(String expr, int index, String out, char[] op,
                                       int ind)
    {
        if (index >= expr.length()) {
            while (ind >= 0) {
                assert op[ind] != '(' : "Erro de parênteses!";
                out += op[ind--];
            }
            return out;
        }

        char tok = expr.charAt(index);

        switch (tok) {
        case '0', '1':
            out += tok;
            break;
        case '^', 'v', '~':
            op[++ind] = tok;
            break;
        case ',':
            while (op[ind] != '(')
                out += op[ind--];
            break;
        case '(':
            op[++ind] = tok;
            break;
        case ')':
            while (op[ind] != '(') {
                assert ind >= 0 : "Erro de parênteses!";
                out += op[ind--];
            }
            assert op[ind] == '(';
            --ind;
            if (ind >= 0 && (op[ind] == '^' || op[ind] == 'v' || op[ind] == '~'))
                out += op[ind--];
            break;
        }

        return shuntingYard(expr, index + 1, out, op, ind);
    }

    public static boolean avaliaRPN(String fila)
    {
        return avaliaRPN(fila, 0, new boolean[fila.length()], -1);
    }

    private static boolean avaliaRPN(String fila, int index, boolean[] ops, int ind)
    {
        if (index >= fila.length())
            return ops[0];

        char tok = fila.charAt(index);

        if (tok == '0' || tok == '1') {
            ops[++ind] = (tok == '1');
        } else if (tok == '~') {
            ops[ind] = !ops[ind];
        } else {
            boolean a = ops[ind--];
            boolean b = ops[ind--];
            boolean res;

            switch (tok) {
            case '^':
                res = a && b;
                break;
            case 'v':
                res = a || b;
                break;
            default:
                throw new IllegalArgumentException("Operador inexiste!");
            }

            ops[++ind] = res;
        }

        return avaliaRPN(fila, index + 1, ops, ind);
    }
}
