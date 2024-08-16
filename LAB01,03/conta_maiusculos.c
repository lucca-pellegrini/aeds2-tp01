#include <locale.h>
#include <stdio.h>
#include <wchar.h>

#define TAM_BUF (1 << 9) // Tamanho do buffer fixo de texto (512 caracteres).

unsigned num_maiusculas(const wchar_t *texto);

int main(void)
{
	wchar_t input[TAM_BUF]; // Buffer fixo de texto para a entrada.

	// Ajusta o locale do programa para português em UTF-8 (somente Linux).
	if (!setlocale(LC_ALL, "C.UTF-8")) {
		fprintf(stderr, "Erro ao configurar locale.\n");
		return 1;
	}

	// Lê uma linha da entrada. Ignora newlines.
	while (wscanf(L" %l[^\n\r]s", input) == 1 &&
	       wcscmp(input, L"FIM") != 0) {
		printf("%u\n", num_maiusculas(input)); // Printa resposta.
	}

	return 0;
}

unsigned num_maiusculas(const wchar_t *texto)
{
	unsigned count = 0; // Valor de retorno.

	// Usamos `size_t` pois o compilador reclama com `int`.
	for (size_t i = 0; texto[i] != L'\0'; ++i)
		// Verifica se é ASCII e maiúscula.
		if (texto[i] >= 'A' && texto[i] <= 'Z')
			++count;

	return count;
}
