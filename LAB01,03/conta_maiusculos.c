#include <ctype.h>
#include <stdio.h>
#include <string.h>

#define TAM_BUF (1 << 10)

int num_maiusculas(const char *texto);

int main(void)
{
	char input[TAM_BUF]; // Buffer fixo de texto para a entrada.

	// Lê uma linha da entrada até achar “FIM”. Ignora newlines.
	while (scanf(" %1023[^\n\r]s", input) != EOF &&
	       strcmp(input, "FIM") != 0) {
		printf("%u\n", num_maiusculas(input)); // Printa resposta.
	}

	return 0;
}

int num_maiusculas(const char *texto)
{
	int count = 0; // Valor de retorno.

	for (int i = 0; texto[i] != '\0'; ++i)
		if (isupper(texto[i])) // Verifica se é maiúscula.
			++count;

	return count;
}
