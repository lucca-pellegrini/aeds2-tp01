#define _POSIX_C_SOURCE 200809L // Permite-nos usar a função getline().

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Funções principais.
void combina(char *restrict const input, char *restrict output);

// Reimplementações (simplificadas) de funções padrão.
void remove_newline(char *str); // Simula parte de `strtok()`.
unsigned strlen2(char *str);

int main(void)
{
	char *res = NULL; // String dinamicamente alocada de resultado.
	char *texto = NULL; // String dinamicamente alocada de entrada.
	size_t cap = 0; // Capacidade da string de entrada.

	// Aloca memória e lê uma linha da entrada.
	while (getline(&texto, &cap, stdin) != -1) {
		remove_newline(texto);
		res = realloc(res, cap); // Aloca memória para o resultado.
		combina(texto, res); // Combina as substrings no buffer `res`.
		puts(res); // Mostra o resultado.
	}

	// Libera memória alocada para as strings.
	free(texto);
	free(res);

	return 0;
}

void combina(char *restrict const input, char *restrict output)
{
	int tam1, tam2, tam_max; // Tamanhos das substrings de entrada.
	char *restrict str1 = input; // Primeira substring.
	char *restrict str2 = input; // Substring após espaço.

	// Move o segundo ponteiro até a segunda substring e delimita a primeira.
	while (*str2 != ' ')
		++str2;
	*str2++ = '\0'; // Delimita primeira, incrementa segunda.

	// Verificação de erros.
	if (str1 == NULL || str2 == NULL || str1 == str2) {
		fputs("Erro: input mal formada.\n", stderr);
		exit(EXIT_FAILURE);
	}

	// Computa os tamanhos.
	tam1 = str2 - str1 - 1;
	tam2 = strlen2(str2);
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

void remove_newline(char *strp) // Pois não se usa strtok() nos TPs.
{
	while (*strp != '\n' && *strp != '\r')
		++strp;
	*strp = '\0';
}

unsigned strlen2(char *str) // Pois não se usa strlen() nos TPs.
{
	unsigned count;
	for (count = 0; str[count] != '\0'; ++count)
		; // Loop vazio.
	return count;
}
