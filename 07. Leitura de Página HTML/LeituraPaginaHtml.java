import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LeituraPaginaHtml
{
    public static final String VOGAIS = "aeiouáéíóúàèìòùãõâêîôû";
    public static final String CONSOANTES = "bcdfghjklmnpqrstvwxyz";

    // É necessário explicitar os `throws` porque se não o compilador reclama.
    public static void main(String[] args)
        throws URISyntaxException, IOException
    {
        Scanner sc = new Scanner(System.in);
        String nome = sc.nextLine();

        while (strcmp(nome, "FIM") != 0) {
            String url = sc.nextLine();

            // Baixa a página especificada no `url`.
            String resposta = baixaPagina(url);

            // Computa e printa os resultados.
            Map<String, Integer> resultados = conta(resposta.toLowerCase());
            System.out.println(resultStr(resultados, nome));

            nome = sc.nextLine();
        }

        sc.close();
    }

    public static String baixaPagina(String input)
        throws URISyntaxException, IOException
    {
        URL url = new URI(input).toURL(); // Instancia um objeto URL.

        // Instancia um objeto para ler a resposta.
        BufferedReader br =
            new BufferedReader(new InputStreamReader(url.openStream()));

        String resposta = new String();
        String linha;

        // Itera sobre a resposta no objeto, linha por linha, e apende à saída.
        while ((linha = br.readLine()) != null)
            resposta += linha;

        return resposta;
    }

    public static Map<String, Integer> conta(String html)
    {
        // Contadores locais.
        int consoantes = 0, breaks = 0, tabelas = 0;

        // Cria uma tabela hash para contar todos os itens. Será retornada.
        Map<String, Integer> countMap = new HashMap<String, Integer>();

        // Inicializa todos os itens na tabela como 0.
        for (int i = 0; i < VOGAIS.length(); ++i)
            countMap.put("" + VOGAIS.charAt(i), 0);

        // Itera sobre o HTML caractere por caractere.
        for (int i = 0; i < html.length(); ++i) {
            String c = "" + html.charAt(i);

            // Insere vogais na tabela hash.
            if (VOGAIS.contains(c))
                countMap.put(c, countMap.get(c) + 1);

            // Conta consoantes.
            else if (CONSOANTES.contains(c))
                ++consoantes;
        }

        // Usa Regular Expressions para encontrar as tabelas e os breaks.
        Matcher breakMatcher = Pattern.compile("<br>").matcher(html);
        Matcher tabelaMatcher = Pattern.compile("<table>").matcher(html);
        while (breakMatcher.find())
            ++breaks;
        while (tabelaMatcher.find())
            ++tabelas;

        // Insere os resultados na tabela.
        countMap.put("consoante", consoantes);
        countMap.put("<br>", breaks);
        countMap.put("<table>", tabelas);

        return countMap;
    }

    public static String resultStr(Map<String, Integer> map, String nome)
    {
        // Buffer contendo os caracteres que formarão a String.
        String buf = new String();

        // Adiciona as vogais.
        for (char v : VOGAIS.toCharArray())
            buf += v + "(" + map.get("" + v) + ") ";

        // Adiciona os outros contadores.
        buf += "consoante(" + map.get("consoante") + ") ";
        buf += "<br>(" + map.get("<br>") + ") ";
        buf += "<table>(" + map.get("<table>") + ") ";
        buf += nome;

        return buf;
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
