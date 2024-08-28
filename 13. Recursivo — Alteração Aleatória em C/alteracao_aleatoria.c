#define _POSIX_C_SOURCE 200809L // Permite-nos usar a função getline().

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(void)
{
	char *texto = NULL; // String dinamicamente alocada de entrada.
	size_t cap = 0; // Capacidade da string de entrada.

	// Aloca memória e lê uma linha da entrada.
	while (getline(&texto, &cap, stdin) != -1) {
		combina(texto, res); // Combina as strings.
		puts(res); // Mostra o resultado.
	}

	// Libera memória alocada para as strings.
	free(texto);

	return 0;
}
