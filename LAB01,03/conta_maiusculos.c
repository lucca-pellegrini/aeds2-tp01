#include <locale.h>
#include <stdio.h>
#include <wchar.h>

#define TAM_BUF 256

int num_maiusculas(const wchar_t *texto);

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
		printf("%u\n", num_maiusculas(input)); // Printa resposta.
	}

	return 0;
}

int num_maiusculas(const wchar_t *texto)
{
	int count = 0; // Valor de retorno.

	for (int i = 0; texto[i] != L'\0'; ++i)
		// Verifica se é ASCII e maiúscula.
		if (texto[i] >= L'A' && texto[i] <= L'Z')
			++count;

	return count;
}
