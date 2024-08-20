#define _POSIX_C_SOURCE 200809L // Permite-nos usar a função getline().

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void combina(char *restrict const input, char *restrict output);

int main(void)
{
	char *res = NULL; // String dinamicamente alocada de resultado.
	char *texto = NULL; // String dinamicamente alocada de entrada.
	size_t cap = 0; // Capacidade da string de entrada.

	// Aloca memória e lê uma linha da entrada.
	while (getline(&texto, &cap, stdin) != -1) {
		res = realloc(res, cap); // Aloca memória para o resultado.
		combina(texto, res); // Combina as strings.
		puts(res); // Mostra o resultado.
	}

	// Libera memória alocada para as strings.
	free(texto);
	free(res);

	return 0;
}

/* É proveitoso ler a documentação de strtok() para compreender como a string
 * de input é dividida: <https://man.archlinux.org/man/strtok.3>. */
void combina(char *restrict const input, char *restrict output)
{
	int tam1, tam2, tam_max; // Tamanhos das substrings de entrada.
	char *restrict str1 = strtok(input, " \r\n"); // Primeira substring.
	char *restrict str2 = strtok(NULL, " \r\n"); // Substring após espaço.

	// Verificação de erros.
	if (str1 == NULL || str2 == NULL || str1 == str2) {
		fputs("Erro: input mal formada.\n", stderr);
		exit(EXIT_FAILURE);
	}

	// Computa os tamanhos.
	tam1 = str2 - str1 - 1;
	tam2 = strlen(str2);
	tam_max = (tam1 > tam2) ? tam1 : tam2;

	/* Itera sobre as substrings, alternando entre elas e copiando para a
	 * string de saída. */
	for (int i = 0; i < tam_max; ++i) {
		if (i < tam1)
			*output++ = str1[i];
		if (i < tam2)
			*output++ = str2[i];
	}
	*output = '\0';
}
