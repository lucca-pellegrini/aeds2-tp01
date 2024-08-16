#include <locale.h>
#include <stdio.h>
#include <wchar.h>
#include <wctype.h>

#define TAM_BUF 256

unsigned num_maiusculas(const wchar_t *texto, size_t tam);

int main(void)
{
	wchar_t input[TAM_BUF]; // Buffer fixo de texto para a entrada.

	// Ajusta o locale do programa para português em UTF-8 (somente Linux).
	if (!setlocale(LC_ALL, "C.UTF-8")) {
		fprintf(stderr, "Erro ao configurar locale.\n");
		return 1;
	}

	// Lê uma linha da entrada. Ignora newlines.
	while (scanf(" %255l[^\n\r]s", input) != EOF &&
	       wcscmp(input, L"FIM") != 0) {
		printf("%u\n", num_maiusculas(input, wcslen(input)));
	}

	return 0;
}

unsigned num_maiusculas(const wchar_t *texto, size_t tam)
{
	unsigned ret; // Valor de retorno.

	if (tam == 0) {
		ret = 0;
	} else {
		ret = num_maiusculas(texto + 1, tam - 1);
		if (iswupper(*texto))
			++ret;
	}

	return ret;
}
