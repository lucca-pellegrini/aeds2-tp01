import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Arquivo
{
    // Objeto que formatará nossos números com as casas decimais corretas.
    public static final DecimalFormat FMT = new DecimalFormat("0.######");
    public static final String FN = "arquivoTesteJava.txt"; // Nome do arquivo.

    public static void main(String[] args)
    {
        // Abre o arquivo no modo de escrita.
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FN))) {
            Scanner sc = new Scanner(System.in);
            final int numLines = sc.nextInt(); // Lê o número de linhas.

            for (int i = 0; i < numLines; ++i) {
                double input = sc.nextDouble(); // Lê os números na entrada.
                writer.write(formataDouble(input) + "\n"); // Salva no arquivo.
            }

            sc.close(); // Fecha o Scanner.
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Abre o arquivo no modo de leitura.
        try (RandomAccessFile arq = new RandomAccessFile(FN, "r")) {
            // Itera sobre o arquivo de trás pra frente.
            for (long i = arq.length() - 1; i >= 0; --i) {
                arq.seek(i); // Move para a i-ésima posição.

                // Verifica se está num fim de linha ou no começo do arquivo.
                if (arq.readByte() == '\n' || i == 0) {
                    if (i != 0)
                        // Avança uma posição após '\n'.
                        arq.seek(i + 1);
                    else
                        // No começo do arquivo, não avança (reseta posição).
                        arq.seek(i);

                    // Lê um valor dessa linha.
                    String lin = arq.readLine();
                    // Confere se a linha existe e não é vazia.
                    if (lin != null && lin.length() != 0) {
                        double input = Double.parseDouble(lin); // Converte.
                        System.out.println(formataDouble(input)); // Formata.
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Converte um double para String, usando a formatação esperada no Verde.
    public static String formataDouble(double d)
    {
        return FMT.format(d);
    }
}
