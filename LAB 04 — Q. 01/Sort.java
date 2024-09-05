import java.util.Scanner;

public class Sort
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int[] vec; // Referência ao vetor.
        int num, mod; // Tamanho do vetor e módulo para comparação.

        // Lê até encontrar “0 0”.
        for (num = sc.nextInt(), mod = sc.nextInt(); (num != 0) || (mod != 0);
             num = sc.nextInt(), mod = sc.nextInt()) {
            // Mostra os valores entrados.
            System.out.println(num + " " + mod);

            vec = new int[num]; // Instancia um novo arranjo de tamanho `num`.

            for (int i = 0; i < vec.length; ++i) // Lê os N números.
                vec[i] = sc.nextInt();

            ordenaInsercao(vec, mod);

            for (int val : vec) // Mostra os resultados.
                System.out.println(val);
        }

        // Mostra os últimos valores entrados.
        System.out.println(num + " " + mod);

        sc.close();
    }

    public static void ordenaInsercao(final int[] vec, final int mod)
    {
        // Itera do segundo ao último item.
        for (int i = 1; i < vec.length; ++i) {
            int n = vec[i]; // Salva o valor na posição atual.
            int j;

            for (j = i - 1; j >= 0 && compara(vec[j], n, mod); --j)
                vec[j + 1] = vec[j]; // Desloca para a direita.

            vec[j + 1] = n; // Insere o valor salvo na posição correta.
        }
    }

    public static boolean compara(int a, int b, int mod)
    {
        boolean ret;
        int mod_a = a % mod;
        int mod_b = b % mod;
        boolean par_a = (a % 2) == 0;
        boolean par_b = (b % 2) == 0;

        // Realiza as verificações dadas pelo enunciado.
        // Ordena segundo os módulos, se forem diferentes entre os elementos.
        if (mod_a > mod_b) {
            ret = true;
        } else if (mod_a < mod_b) {
            ret = false;
        } else { // Caso contrário, há empate entre `mod_a` e `mod_b`
            // Verifica se os elementos têm paridade diferente.
            if (!par_a && par_b)
                ret = false;
            else if (par_a && !par_b)
                ret = true;
            // Se têm paridade igual, ordena-se ímpares em decrescente...
            else if (!par_a && !par_b)
                ret = a < b;
            else // ... e pares em ordem crescente.
                ret = a > b;
        }

        return ret;
    }
}
