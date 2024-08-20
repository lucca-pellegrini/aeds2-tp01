#include <stdio.h>

#define FN "arquivo_teste_c.txt" // Nome do arquivo.

int main(void)
{
	FILE *fd; // Objeto representando o arquivo.
	int num; // Número de linhas que serão processadas.

	scanf("%d", &num); // Lê o número de linhas.

	fd = fopen(FN, "w"); // Abre o arquivo em modo de escrita.
	for (int i = 0; i < num; ++i) {
		double input;
		scanf("%lf", &input); // Lê entrada.
		fprintf(fd, "\n%g", input); // Salva entrada no arquivo.
	}
	fclose(fd); // Fecha o arquivo.

	fd = fopen(FN, "r"); // Abre o arquivo em modo de leitura.

	// Move o cursor para o final do arquivo
	fseek(fd, 0, SEEK_END);

	// Itera sobre o arquivo de trás pra frente.
	for (long long i = ftell(fd) - 1; i >= 0; --i) {
		fseek(fd, i, SEEK_SET); // Move para a i-ésima posição.

		// Verifica se está num fim de linha ou no começo do arquivo.
		if (fgetc(fd) == '\n' || i == 0) {
			double input;
			if (i != 0)
				// Avança uma posição após '\n'.
				fseek(fd, i + 1, SEEK_SET);
			else
				// No começo do arquivo, não avança.
				fseek(fd, i, SEEK_SET);

			fscanf(fd, "%lf", &input); // Lê um valor dessa linha.
			printf("%g\n", input); // Mostra o valor.
		}
	}

	fclose(fd); // Fecha o arquivo.
	return 0;
}
