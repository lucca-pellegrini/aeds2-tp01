#include <ctype.h>
#include <stdio.h>
#include <string.h>

#define TAM_BUF (1 << 10)

unsigned num_maiusculas(const char *texto, size_t tam);

int main(void)
{
	char input[TAM_BUF]; // Buffer fixo de texto para a entrada.

	// Lê uma linha da entrada até achar “FIM”. Ignora newlines.
	while (scanf(" %1023[^\n\r]s", input) != EOF &&
	       strcmp(input, "FIM") != 0) {
		printf("%u\n", num_maiusculas(input, strlen(input)));
	}

	return 0;
}

unsigned num_maiusculas(const char *texto, size_t tam)
{
	unsigned ret; // Valor de retorno.

	if (tam == 0) {
		ret = 0;
	} else {
		// Chamada recursiva na substring, excluindo o primeiro item.
		ret = num_maiusculas(texto + 1, tam - 1);
		if (isupper(*texto)) // Verifica se é maiúscula.
			++ret;
	}

	return ret;
}
