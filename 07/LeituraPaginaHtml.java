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

public class LeituraPaginaHtml {
    public static final String VOGAIS = "aeiouáéíóúàèìòùãõâêîôû";
    public static final String CONSOANTES = "bcdfghjklmnpqrstvwxyz";

    public static void main(String[] args)
            throws URISyntaxException, IOException {
        Scanner sc = new Scanner(System.in);
        String nome = sc.nextLine();

        while (!nome.equals("FIM")) {
            String url = sc.nextLine();
            String resposta = baixaPagina(url);

            Map<String, Integer> resultados = conta(resposta.toLowerCase());
            System.out.println(resultStr(resultados, nome));

            nome = sc.nextLine();
        }

        sc.close();
    }

    public static String baixaPagina(String input)
            throws URISyntaxException, IOException {
        URL url = new URI(input).toURL();

        BufferedReader br = new BufferedReader(
                new InputStreamReader(url.openStream()));

        StringBuilder resposta = new StringBuilder();
        String linha;
        while ((linha = br.readLine()) != null)
            resposta.append(linha);

        return resposta.toString();
    }

    public static Map<String, Integer> conta(String html) {
        // Cria uma tabela hash para contar todos os itens. Será retornada.
        Map<String, Integer> countMap = new HashMap<String, Integer>();
        int consoantes = 0, breaks = 0,
                tabelas = 0; // Contadores locais.

        // Inicializa todos os itens na tabela como 0.
        for (int i = 0; i < VOGAIS.length(); ++i)
            countMap.put(Character.toString(VOGAIS.charAt(i)), 0);

        // Itera sobre o HTML caractere por caractere.
        for (int i = 0; i < html.length(); ++i) {
            String c = Character.toString(html.charAt(i));

            // Insere vogais na tabela hash.
            if (VOGAIS.contains(c))
                countMap.put(c, countMap.get(c) + 1);

            // Conta consoantes.
            if (CONSOANTES.contains(c))
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

    public static String resultStr(Map<String, Integer> map, String nome) {
        // Buffer contendo os caracteres que formarão a String.
        StringBuilder buf = new StringBuilder();

        // Adiciona as vogais.
        for (char v : VOGAIS.toCharArray())
            buf.append(v + "(" + map.get(Character.toString(v)) +
                    ") ");

        // Adiciona os outros contadores.
        buf.append("consoante(" + map.get("consoante") + ") ");
        buf.append("<br>(" + map.get("<br>") + ") ");
        buf.append("<table>(" + map.get("<table>") + ") ");
        buf.append(nome);

        return buf.toString();
    }
}
