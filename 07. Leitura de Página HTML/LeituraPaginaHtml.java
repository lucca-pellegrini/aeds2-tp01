import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;

public class LeituraPaginaHtml {
    // É necessário explicitar os `throws` porque se não o compilador reclama.
    public static void main(String[] args) throws URISyntaxException, IOException {
        PrintStream out = new PrintStream(System.out, true, "ISO-8859-1");
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in, Charset.forName("UTF-8")));
        String nome = br.readLine();

        while (strcmp(nome, "FIM") != 0) {
            String url = br.readLine();

            // Baixa a página especificada no `url`.
            String resposta = baixaPagina(url);

            out.println(contaElementosHtml(resposta) + " " + nome);

            nome = br.readLine();
        }
    }

    public static String baixaPagina(String input) throws URISyntaxException, IOException {
        URL url = new URI(input).toURL(); // Instancia um objeto URL.

        // Instancia um objeto para ler a resposta.
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

        String resposta = new String();
        String linha;

        // Itera sobre a resposta no objeto, linha por linha, e apende à saída.
        while ((linha = br.readLine()) != null)
            resposta += linha + "\n";

        return resposta;
    }

    public static boolean isConsoante(char c) {
        boolean ret = false;
        if (c >= 'b' && c <= 'd' || c >= 'f' && c <= 'h' || c >= 'j' && c <= 'n' ||
                c >= 'p' && c <= 't' || c >= 'v' && c <= 'z') {
            ret = true;
        }
        return ret;
    }

    public static String contaElementosHtml(String html) {
        int[] vog = new int[23]; // Contadores de vogais.
        int con = 0; // Contador de consoantes.
        int brk = 0; // Contador de line breaks `<br>`.
        int tbl = 0; // Contador de tabelas `<table>`

        for (int i = 0; i < html.length(); i++) {
            char charAtual = html.charAt(i);
            int intAtual = (int) charAtual; // charAtual codificado como int.

            // Começamos contando as vogais simples.
            switch (charAtual) {
                case 'a':
                    ++vog[1];
                    break;
                case 'e':
                    ++vog[2];
                    break;
                case 'i':
                    ++vog[3];
                    break;
                case 'o':
                    ++vog[4];
                    break;
                case 'u':
                    ++vog[5];
                    break;
            }

            // Depois, contamos as vogais acentuadas. Em cada seção desse
            // switch-case, um acento é considerado, e cada vogal é testada em
            // ordem alfabética (i.e.: á é í ó ú à è ì ò ù...).
            switch (intAtual) {
                // Acento agudo: ´
                case 225:
                    ++vog[6];
                    break;
                case 233:
                    ++vog[7];
                    break;
                case 237:
                    ++vog[8];
                    break;
                case 243:
                    ++vog[9];
                    break;
                case 250:
                    ++vog[10];
                    break;

                // Crase: `
                case 224:
                    ++vog[11];
                    break;
                case 232:
                    ++vog[12];
                    break;
                case 236:
                    ++vog[13];
                    break;
                case 242:
                    ++vog[14];
                    break;
                case 249:
                    ++vog[15];
                    break;

                // Til: ~
                case 227:
                    ++vog[16];
                    break;
                case 245:
                    ++vog[17];
                    break;

                // Acento circunflexo: ^
                case 226:
                    ++vog[18];
                    break;
                case 234:
                    ++vog[19];
                    break;
                case 238:
                    ++vog[20];
                    break;
                case 244:
                    ++vog[21];
                    break;
                case 251:
                    ++vog[22];
                    break;
            }

            // Testamos se é consoante.
            if (isConsoante(charAtual))
                ++con;

            // Se encontrar uma abertura de tag (`<`), testamos manualmente se
            // é line break ou tabela.
            if (charAtual == '<') {
                if (i + 3 < html.length() && html.charAt(i + 1) == 'b' &&
                        html.charAt(i + 2) == 'r' && html.charAt(i + 3) == '>') {
                    ++brk;
                }

                if (i + 6 < html.length() && html.charAt(i + 1) == 't' &&
                        html.charAt(i + 2) == 'a' && html.charAt(i + 3) == 'b' &&
                        html.charAt(i + 4) == 'l' && html.charAt(i + 5) == 'e' &&
                        html.charAt(i + 6) == '>') {
                    ++tbl;
                }
            }
        }

        // Decrementamos os contadores de vogais e consoantes para
        // desconsiderar caracteres que estão nas tags que contamos.
        con -= 2 * brk + 3 * tbl; // Desconsidera b, r, t, b, e l nas tags.
        vog[1] -= 1 * tbl; // Desconsidera os `a`s nas `<table>`s.
        vog[2] -= 1 * tbl; // Desconsidera os `e`s nas `<table>`s.

        return ("a(" + vog[1] + ") "
                + "e(" + vog[2] + ") "
                + "i(" + vog[3] + ") "
                + "o(" + vog[4] + ") "
                + "u(" + vog[5] + ") "
                + "á(" + vog[6] + ") "
                + "é(" + vog[7] + ") "
                + "í(" + vog[8] + ") "
                + "ó(" + vog[9] + ") "
                + "ú(" + vog[10] + ") "
                + "à(" + vog[11] + ") "
                + "è(" + vog[12] + ") "
                + "ì(" + vog[13] + ") "
                + "ò(" + vog[14] + ") "
                + "ù(" + vog[15] + ") "
                + "ã(" + vog[16] + ") "
                + "õ(" + vog[17] + ") "
                + "â(" + vog[18] + ") "
                + "ê(" + vog[19] + ") "
                + "î(" + vog[20] + ") "
                + "ô(" + vog[21] + ") "
                + "û(" + vog[22] + ") "
                + "consoante(" + con + ") "
                + "<br>(" + brk + ") "
                + "<table>(" + tbl + ")");
    }

    public static int strcmp(String str, String cmp) {
        int ret = 0;
        int min_len = (str.length() > cmp.length()) ? cmp.length() : str.length();

        for (int i = min_len - 1; i >= 0; --i)
            if (str.charAt(i) != cmp.charAt(i))
                ret = str.charAt(i) - cmp.charAt(i);

        return ret;
    }
}
