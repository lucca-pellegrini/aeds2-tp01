#include <locale.h>
#include <stdio.h>
#include <wchar.h>
#include <wctype.h>

#define TAM_BUF (1 << 9) // Tamanho do buffer fixo de texto (512 caracteres).

// Usamos `size_t` pois o compilador reclama se usarmos `int`.
unsigned num_maiusculas(const wchar_t *texto, size_t tam);

int main(void)
{
	wchar_t input[TAM_BUF]; // Buffer fixo de texto para a entrada.

	// Ajusta o locale do programa para português em UTF-8 (somente Linux).
	setlocale(LC_ALL, "C.UTF-8");

	// Lê uma linha da entrada. Ignora newlines.
	wscanf(L"%l[^\n\r]s", input);

	while (wcscmp(input, L"FIM") != 0) {
		printf("%u\n", num_maiusculas(input, wcslen(input)));
		wscanf(L" %l[^\n\r]s", input); // Lê a próxima linha.
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
